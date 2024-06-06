package de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.project;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;

import java.util.*;

import static de.unistuttgart.iste.meitrex.scrumgame.util.PersistenceUtils.replaceContent;

@Entity
@Table(name = "ims_settings")
@Getter
@ToString
@EqualsAndHashCode(of = "id")
@Accessors(chain = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ImsSettingsEntity {

    @Id
    @GeneratedValue
    private UUID id;

    @Column
    @Setter
    private String imsName;

    @Embedded
    @Setter
    private IconEmbeddable imsIcon;

    @Column
    @Setter
    private String imsProjectUrl;

    @Column
    @Setter
    private String imsProjectId;

    @Column
    @Setter
    private String imsIssueTemplateId;

    @ElementCollection(fetch = FetchType.EAGER)
    @OrderColumn(name = "position")
    @ToString.Exclude
    @Builder.Default
    private List<IssueStateEmbeddable> issueStates = new ArrayList<>();

    @Column
    @Setter
    private String effortEstimationFieldName;

    @Column
    @Setter
    private String sprintFieldName;

    @Column
    @Setter
    private String partOfRelationId;

    @ElementCollection(fetch = FetchType.EAGER)
    @Builder.Default
    private List<IssuePriorityEmbeddable> issuePriorities = new ArrayList<>();

    @ElementCollection(fetch = FetchType.EAGER)
    @Builder.Default
    private List<IssueTypeEmbeddable> issueTypes = new ArrayList<>();

    public void setIssueStates(List<IssueStateEmbeddable> issueStates) {
        this.issueStates = replaceContent(this.issueStates, issueStates);
    }

    public void setIssuePriorities(List<IssuePriorityEmbeddable> issuePriorities) {
        this.issuePriorities = replaceContent(this.issuePriorities, issuePriorities);
    }

    public void setIssueTypes(List<IssueTypeEmbeddable> issueTypes) {
        this.issueTypes = replaceContent(this.issueTypes, issueTypes);
    }
}
