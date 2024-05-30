package de.unistuttgart.iste.meitrex.scrumgame.service.ims.gropius;

import de.unistuttgart.iste.gropius.generated.dto.GropiusIssue;
import de.unistuttgart.iste.gropius.generated.dto.GropiusIssuePriority;
import de.unistuttgart.iste.gropius.generated.dto.GropiusJSONField;
import de.unistuttgart.iste.gropius.generated.dto.GropiusLabel;
import de.unistuttgart.iste.meitrex.generated.dto.Issue;
import de.unistuttgart.iste.meitrex.generated.dto.IssuePriority;
import de.unistuttgart.iste.meitrex.generated.dto.IssueType;
import de.unistuttgart.iste.meitrex.generated.dto.TShirtSizeEstimation;
import de.unistuttgart.iste.meitrex.scrumgame.util.TShirtSizeEstimationStoryPointsConverter;
import lombok.NoArgsConstructor;

import java.util.*;

@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class GropiusMapping {

    public static Issue gropiusIssueToScrumGameIssue(
            GropiusIssue issueResponse,
            GropiusIssueMappingConfiguration mappingConfiguration) {

        Optional<Object> estimationField = findTemplateField(
                issueResponse.getTemplatedFields(),
                mappingConfiguration.getEstimationTemplateFieldName());
        Optional<TShirtSizeEstimation> estimation = estimationField
                .map(Object::toString)
                .flatMap(estimateString -> parseEnum(estimateString, TShirtSizeEstimation.class));
        Optional<Integer> storyPointsValue = estimation
                .map(TShirtSizeEstimationStoryPointsConverter::getStoryPoints);

        return Issue.builder()
                .setId(issueResponse.getId())
                .setProjectId(mappingConfiguration.getScrumGameProjectId())
                .setTitle(issueResponse.getTitle())
                .setDescription(issueResponse.getBody().getBody())
                .setState(mappingConfiguration.getIssueStateConverter()
                        .getIssueState(issueResponse.getState().getId()))
                .setType(IssueType.builder()
                        .setName(issueResponse.getType().getName())
                        .setDescription(issueResponse.getType().getDescription())
                        .setIconPath(issueResponse.getType().getIconPath())
                        .build())
                .setPriority(getIssuePriority(issueResponse.getPriority(), mappingConfiguration))
                .setAssigneeIds(
                        issueResponse.getAssignments().getNodes().stream()
                                .map(assignment -> assignment.getUser().getId())
                                .map(UUID::fromString)
                                .toList())
                .setSprintNumber(
                        findTemplateField(issueResponse.getTemplatedFields(), mappingConfiguration.getSprintFieldName())
                                .flatMap(GropiusMapping::parseIntSafely)
                                .orElse(null))
                .setStoryPoints(storyPointsValue.orElse(null))
                .setEffortEstimation(estimation.orElse(null))
                .setLabels(issueResponse.getLabels().getNodes().stream()
                        .map(GropiusLabel::getName)
                        .toList())
                .setIssueUrl(mappingConfiguration.getGropiusBaseUrl()
                             + "/projects/" + mappingConfiguration.getImsProjectId()
                             + "/issues/" + issueResponse.getId()) // TODO doesn't work for component issues
                .build();
    }

    private static IssuePriority getIssuePriority(GropiusIssuePriority priority,
            GropiusIssueMappingConfiguration mappingConfiguration) {
        IssuePriority issuePriority = null;
        if (priority != null) {
            issuePriority = mappingConfiguration.getIssuePriorityMapping().getIssuePriority(priority.getId());
        }
        return issuePriority != null ? issuePriority : IssuePriority.MEDIUM;
    }

    private static Optional<Object> findTemplateField(List<GropiusJSONField> templatedFields, String fieldName) {
        return templatedFields.stream()
                .filter(field -> field.getName().equals(fieldName))
                .map(GropiusJSONField::getValue)
                .filter(Objects::nonNull)
                .findFirst();
    }

    private static <T extends Enum<T>> Optional<T> parseEnum(String value, Class<T> enumType) {
        try {
            return Optional.of(Enum.valueOf(enumType, value));
        } catch (IllegalArgumentException e) {
            return Optional.empty();
        }
    }

    private static Optional<Integer> parseIntSafely(Object value) {
        if (value instanceof Integer intValue) {
            return Optional.of(intValue);
        }

        if (value instanceof String stringValue) {
            try {
                return Optional.of(Integer.parseInt(stringValue));
            } catch (NumberFormatException e) {
                return Optional.empty();
            }
        }

        return Optional.empty();
    }

}
