package com.inditex.core.platform.application.exception;

public class ApplicationException extends RuntimeException {

    public ApplicationException(final String message) {
        super(message);
    }

    protected ApplicationException(final String message, final Throwable cause) {
        super(message, cause);
    }
}