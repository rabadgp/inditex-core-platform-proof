package com.kairos.tech.proof.application.port.input;

import com.kairos.tech.proof.application.command.Command;
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
