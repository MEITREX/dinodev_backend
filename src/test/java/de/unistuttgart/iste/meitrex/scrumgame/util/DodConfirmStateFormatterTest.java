package de.unistuttgart.iste.meitrex.scrumgame.util;

import de.unistuttgart.iste.meitrex.generated.dto.DefinitionOfDoneConfirmState;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

class DodConfirmStateFormatterTest {

    @Test
    void testFormatDodConfirmStates() {
        // Arrange
        List<DefinitionOfDoneConfirmState> dodConfirmStates = List.of(
                DefinitionOfDoneConfirmState.builder()
                        .setDodText("Test")
                        .setChecked(false)
                        .setReasonIfNotChecked("Reason")
                        .build(),
                DefinitionOfDoneConfirmState.builder()
                        .setDodText("Test 2")
                        .setChecked(true)
                        .setReasonIfNotChecked("Should not be shown")
                        .setChildren(List.of(
                                DefinitionOfDoneConfirmState.builder()
                                        .setDodText("Child")
                                        .setChecked(false)
                                        .setReasonIfNotChecked("Child Reason")
                                        .build()
                        ))
                        .build()
        );

        // Act
        String result = DodConfirmStateFormatter.formatDodConfirmStates(dodConfirmStates);

        // Assert
        assertThat(result, containsString("""
                - [ ] **Test** No, because: Reason
                - [x] **Test 2**
                    - [ ] **Child** No, because: Child Reason
                """));
    }
}
