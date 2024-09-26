package com.inditex.core.platform.domain.exception;


public class DomainException extends RuntimeException {

    protected DomainException(String message) {
        super(message);
    }
}