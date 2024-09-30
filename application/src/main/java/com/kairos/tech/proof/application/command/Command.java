package com.kairos.tech.proof.application.command;

public interface Command<R> {
    R execute();
}
