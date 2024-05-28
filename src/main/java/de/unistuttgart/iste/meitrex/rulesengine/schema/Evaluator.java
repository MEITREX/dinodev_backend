package de.unistuttgart.iste.meitrex.rulesengine.schema;

import java.util.Arrays;
import java.util.Collection;

@FunctionalInterface
public interface Evaluator<T> {

    EvaluationResult evaluate(T object);

    static <T> Evaluator<T> alwaysValid() {
        return event -> EvaluationResult.success();
    }

    static <T> Evaluator<T> allOf(Collection<? extends Evaluator<? super T>> evaluators) {
        return object -> evaluators.stream()
                .map(schema -> schema.evaluate(object))
                .reduce(EvaluationResult.success(), EvaluationResult::and);
    }

    static <T> Evaluator<T> anyOf(Collection<? extends Evaluator<? super T>> evaluators) {
        return object -> evaluators.stream()
                .map(schema -> schema.evaluate(object))
                .reduce(EvaluationResult.failure("No conditions given"), EvaluationResult::or);
    }

    static <T> Evaluator<T> not(Evaluator<? super T> evaluator) {
        return object -> evaluator.evaluate(object).not("Condition not met");
    }

}
