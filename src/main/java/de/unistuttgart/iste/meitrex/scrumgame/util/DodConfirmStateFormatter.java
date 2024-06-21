package de.unistuttgart.iste.meitrex.scrumgame.util;

import de.unistuttgart.iste.meitrex.generated.dto.DefinitionOfDoneConfirmState;
import lombok.NoArgsConstructor;

import java.util.*;
import java.util.stream.*;

/**
 * Utility class to format DefinitionOfDoneConfirmState objects as markdown.
 */
@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class DodConfirmStateFormatter {

    private static final String MD_UNCHECKED_BOX = "- [ ] ";
    private static final String MD_CHECKED_BOX   = "- [x] ";

    /**
     * Formats a list of DefinitionOfDoneConfirmState objects as markdown.
     * This will create a markdown list with checkboxes for each DefinitionOfDoneConfirmState.
     * If a DefinitionOfDoneConfirmState is checked, it will be marked with a checked checkbox.
     *
     * @param dodConfirmStates The list of DefinitionOfDoneConfirmState objects to format
     * @return The formatted markdown string
     */
    public static String formatDodConfirmStates(List<DefinitionOfDoneConfirmState> dodConfirmStates) {
        return dodConfirmStates.stream()
                .map(dodConfirmState -> formatDodConfirmState(dodConfirmState, 0))
                .collect(Collectors.joining());
    }

    private static String formatDodConfirmState(DefinitionOfDoneConfirmState dodConfirmState, int indentation) {
        String indent = "    ".repeat(indentation);

        if (!dodConfirmState.getChecked()) {
            String base = indent + MD_UNCHECKED_BOX + markDownBold(dodConfirmState.getDodText());
            if (dodConfirmState.getReasonIfNotChecked() != null && !dodConfirmState.getReasonIfNotChecked().isBlank()) {
                return base + " No, because: " + dodConfirmState.getReasonIfNotChecked() + "\n";
            } else {
                return base + "\n";
            }
        }

        return indent
               + MD_CHECKED_BOX
               + markDownBold(dodConfirmState.getDodText())
               + "\n"
               + dodConfirmState.getChildren().stream()
                       .map(child -> formatDodConfirmState(child, indentation + 1))
                       .collect(Collectors.joining());
    }

    private static String markDownBold(String text) {
        return "**" + text + "**";
    }

}
