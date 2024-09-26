package com.inditex.core.platform.application.command;

public interface Command<R> {
    R execute();
}
