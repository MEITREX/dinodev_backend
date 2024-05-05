package de.unistuttgart.iste.meitrex.scrumgame.util;

import de.unistuttgart.iste.meitrex.generated.dto.DefinitionOfDoneConfirmState;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class DodConfirmStateFormatter {

    public static String formatDodConfirmStates(List<DefinitionOfDoneConfirmState> dodConfirmStates) {
        return dodConfirmStates.stream()
                .map(dodConfirmState -> formatDodConfirmState(dodConfirmState, 0))
                .collect(Collectors.joining());
    }

    public static String formatDodConfirmState(DefinitionOfDoneConfirmState dodConfirmState, int indentation) {
        String indent = "    ".repeat(indentation);

        if (!dodConfirmState.getChecked()) {
            String base = indent + "- [ ] " + markDownBold(dodConfirmState.getDodText());
            if (dodConfirmState.getReasonIfNotChecked() != null && !dodConfirmState.getReasonIfNotChecked().isBlank()) {
                return base + " No, because: " + dodConfirmState.getReasonIfNotChecked() + "\n";
            } else {
                return base + "\n";
            }
        }

        return indent
               + "- [x] "
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
