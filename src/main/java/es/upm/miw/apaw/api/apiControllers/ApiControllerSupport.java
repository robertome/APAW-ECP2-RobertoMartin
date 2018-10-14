package es.upm.miw.apaw.api.apiControllers;

import es.upm.miw.apaw.api.exceptions.ArgumentNotValidException;

public abstract class ApiControllerSupport {

    protected void validateNotNull(Object property, String message) {
        if (property == null) {
            throw new ArgumentNotValidException(message + " is missing");
        }
    }

    protected void validateNotEmpty(String property, String message) {
        if (property != null && property.trim().isEmpty()) {
            throw new ArgumentNotValidException(message + " is empty");
        }
    }

}
