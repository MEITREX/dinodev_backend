package de.unistuttgart.iste.meitrex.rulesengine.schema;

import de.unistuttgart.iste.meitrex.generated.dto.*;
import lombok.NoArgsConstructor;

import java.util.*;

import static de.unistuttgart.iste.meitrex.rulesengine.schema.EvaluationResult.*;

@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class SchemaValidator {

    public static EvaluationResult validate(Event event, SchemaDefinition eventSchema) {
        return EvaluationResult.success(); //event.getEventType().getEventSchema().evaluate(event);
    }

    private EvaluationResult validateType(Object value) {
//        return switch (getType()) {
//            case AllowedDataType.STRING -> require(value instanceof String, "Field " + getName() + " must be a string");
//            case AllowedDataType.INTEGER ->
//                    require(value instanceof Integer, "Field " + getName() + " must be an integer");
//            case AllowedDataType.DOUBLE -> require(value instanceof Double, "Field " + getName() + " must be a double");
//            case AllowedDataType.BOOLEAN ->
//                    require(value instanceof Boolean, "Field " + getName() + " must be a boolean");
//            case null, default -> success();
//        };
        return success();
    }

    private EvaluationResult validateAllowedValues(Object value) {
//        return require(getAllowedValues().isEmpty() || getAllowedValues().contains(value),
//                "Field " + getName() + " must be one of " + getAllowedValues());
        return success();
    }

    private EvaluationResult evaluate(SchemaDefinition schemaDefinition, List<TemplateField> fields) {
        EvaluationResult result = success();

        for (FieldSchemaDefinition field : schemaDefinition.getFields()) {
            Optional<Object> fieldValue = findField(fields, field.getName());
            result = result.and(validateField(field, fieldValue.orElse(null)));
        }

        return result;

    }

    private EvaluationResult validateField(FieldSchemaDefinition field, Object fieldValue) {
        return validateRequired(field, fieldValue)
                .and(validateType(fieldValue))
                .and(validateAllowedValues(fieldValue));
    }

    private EvaluationResult validateRequired(FieldSchemaDefinition field, Object fieldValue) {
        return field.getRequired() && fieldValue == null
               ? failure("Field " + field.getName() + " is required")
               : success();
    }

    private Optional<Object> findField(List<TemplateField> fields, String fieldName) {
        return fields.stream()
                .filter(field -> field.getKey().equals(fieldName))
                .map(field -> parseValue(field, field.getValue()))
                .findFirst();
    }

    private Object parseValue(TemplateField field, Object value) {
        return switch (field.getType()) {
            case AllowedDataType.STRING -> value.toString();
            case AllowedDataType.INTEGER -> Integer.parseInt(value.toString());
            case AllowedDataType.DOUBLE -> Double.parseDouble(value.toString());
            case AllowedDataType.BOOLEAN -> Boolean.parseBoolean(value.toString());
            case null, default -> value;
        };
    }

}
