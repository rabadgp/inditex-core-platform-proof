package com.inditex.core.platform.application.port.input;

import com.inditex.core.platform.application.command.Command;
import org.springframework.stereotype.Component;

@Component
public class UseCaseInvoker {

    private Command<?> command;

    public UseCaseInvoker() {
    }

    public void setCommand(Command<?> command) {
        this.command = command;
    }

    public <R> R executeCommand() {
        return (R) this.command.execute();
    }
}
