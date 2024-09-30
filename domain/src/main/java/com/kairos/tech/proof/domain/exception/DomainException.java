package com.kairos.tech.proof.domain.exception;


public class DomainException extends RuntimeException {

    protected DomainException(String message) {
        super(message);
    }
}