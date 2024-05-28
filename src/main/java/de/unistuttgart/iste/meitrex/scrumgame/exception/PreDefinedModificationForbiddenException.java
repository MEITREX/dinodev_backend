package de.unistuttgart.iste.meitrex.scrumgame.exception;

import de.unistuttgart.iste.meitrex.common.exception.ExceptionWithGraphQlErrorType;
import graphql.ErrorType;
import graphql.GraphQLError;
import lombok.experimental.StandardException;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@StandardException
public class PreDefinedModificationForbiddenException extends RuntimeException implements ExceptionWithGraphQlErrorType {

    public PreDefinedModificationForbiddenException(String resourceName, Object id) {
        this(resourceName + " with id " + id + " is predefined and cannot be modified.");
    }

    @Override
    public ErrorType getErrorType() {
        return ErrorType.ValidationError;
    }
}
