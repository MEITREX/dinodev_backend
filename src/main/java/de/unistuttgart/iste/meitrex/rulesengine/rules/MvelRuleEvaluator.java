package de.unistuttgart.iste.meitrex.rulesengine.rules;

import de.unistuttgart.iste.meitrex.generated.dto.Event;
import de.unistuttgart.iste.meitrex.generated.dto.EventType;
import de.unistuttgart.iste.meitrex.generated.dto.MvelRule;
import de.unistuttgart.iste.meitrex.rulesengine.schema.EvaluationResult;
import de.unistuttgart.iste.meitrex.rulesengine.schema.Evaluator;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import org.mvel2.MVEL;

import java.io.Serializable;
import java.util.Map;
import java.util.stream.Collectors;

@NoArgsConstructor
public class MvelRuleEvaluator implements Evaluator<MvelRule> {

    private String       mvelExpression;
    private Serializable compiledRule = null;
    private Map<String, String> fieldScripts;
    @Getter
    private EventType          responseEventType;
    @Getter
    private EventType          triggerEventType;

    @Builder
    public MvelRuleEvaluator(String mvelExpression, Map<String, String> fieldScripts, EventType eventType, EventType triggerEventType) {
        this.mvelExpression = mvelExpression;
        this.fieldScripts = fieldScripts;
        this.responseEventType = eventType;
        this.triggerEventType = triggerEventType;
    }

    @Override
    public EvaluationResult evaluate(MvelRule event) {
        Map<String, Object> variables = Map.of();//event.getEventData();

        try {
            Boolean evaluationResult = MVEL.executeExpression(getCompiledRule(), event, variables, Boolean.class);

             return evaluationResult ? EvaluationResult.success() : EvaluationResult.failure("Rule not met");
        } catch (Exception e) {
            return EvaluationResult.failure(e.getMessage());
        }
    }

    // @Override
    public Map<String, Object> getResponseEventData(Event triggerEvent) {
        return Map.of();
//        return fieldScripts.entrySet()
//                .stream()
//                .collect(Collectors.toMap(Map.Entry::getKey,
//                        entry -> MVEL.eval(entry.getValue(), triggerEvent, triggerEvent.getEventData())));
    }

    private Serializable getCompiledRule() {
        if (compiledRule == null) {
            compiledRule = MVEL.compileExpression(mvelExpression);
        }
        return compiledRule;
    }
}
