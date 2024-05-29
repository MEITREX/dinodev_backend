package de.unistuttgart.iste.meitrex.scrumgame.exception;

import de.unistuttgart.iste.meitrex.common.exception.ExceptionWithGraphQlErrorType;
import graphql.ErrorType;
import lombok.experimental.StandardException;

@StandardException
public class PreDefinedModificationForbiddenException extends RuntimeException
        implements ExceptionWithGraphQlErrorType {

    public PreDefinedModificationForbiddenException(String resourceName, Object id) {
        this(resourceName + " with id " + id + " is predefined and cannot be modified.");
    }

    @Override
    public ErrorType getErrorType() {
        return ErrorType.ValidationError;
    }
}
