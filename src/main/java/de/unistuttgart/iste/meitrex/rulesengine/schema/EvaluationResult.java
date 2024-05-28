package de.unistuttgart.iste.meitrex.rulesengine.schema;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.util.List;
import java.util.stream.Stream;

@Value
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class EvaluationResult {

    boolean isSuccess;
    @Builder.Default
    List<String> messages = List.of();

    public static EvaluationResult require(boolean condition, String message) {
        return condition ? EvaluationResult.success() : EvaluationResult.failure(message);
    }

    public static EvaluationResult success() {
        return EvaluationResult.builder().isSuccess(true).build();
    }

    public static EvaluationResult failure(String message) {
        return EvaluationResult.builder().isSuccess(false).messages(List.of(message)).build();
    }

    public EvaluationResult and(EvaluationResult other) {
        return EvaluationResult.builder()
                .isSuccess(isSuccess && other.isSuccess)
                .messages(concatMessages(other.messages))
                .build();
    }

    public EvaluationResult or(EvaluationResult other) {
        if (isSuccess) {
            return this;
        } else if (other.isSuccess) {
            return other;
        } else {
            return EvaluationResult.builder()
                    .isSuccess(false)
                    .messages(concatMessages(other.messages))
                    .build();
        }
    }

    public EvaluationResult not(String message) {
        return isSuccess ? EvaluationResult.failure(message) : EvaluationResult.success();
    }

    private List<String> concatMessages(List<String> otherMessages) {
        return Stream.of(messages, otherMessages).flatMap(List::stream).toList();
    }
}
