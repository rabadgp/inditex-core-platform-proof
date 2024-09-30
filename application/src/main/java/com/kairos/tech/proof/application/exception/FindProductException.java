package com.kairos.tech.proof.application.exception;

public class FindProductException extends ApplicationException {
    public FindProductException(final String message) {
        super(message);
    }

    public FindProductException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
