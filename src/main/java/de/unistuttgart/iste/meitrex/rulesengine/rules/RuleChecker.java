package de.unistuttgart.iste.meitrex.rulesengine.rules;

import de.unistuttgart.iste.meitrex.generated.dto.Event;
import de.unistuttgart.iste.meitrex.generated.dto.Rule;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

@RequiredArgsConstructor
public class RuleChecker implements Consumer<Event> {

    private final Supplier<List<Rule>>          rulesSupplier;

    @Override
    public void accept(Event event) {
        List<Rule> rules = rulesSupplier.get();
        try {
            for (Rule rule : rules) {
                // EvaluationResult result = rule.evaluate(event);
//                if (result.isSuccess()) {
//                    eventPublisher.accept(rule.getResponseEvent(event));
//                }
            }
        } catch (Exception e) {
            // TODO: Log error
        }
    }
}
