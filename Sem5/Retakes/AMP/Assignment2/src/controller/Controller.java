package controller;

import exception.EmptyStackException;
import model.ProgramState;
import repository.IRepository;

public class Controller {
    IRepository repository;

    public Controller(IRepository repository) {
        this.repository = repository;
    }

    public ProgramState oneStepExecution(ProgramState programState) {
       var executionStack = programState.getExecutionStack();
        if (executionStack.isEmpty()) {
            throw new EmptyStackException("The execution stack is empty");
        }
        var currentStatement = executionStack.pop();
        return currentStatement.execute(programState);
    }

    public void allStepsExecution() {
        var programState = this.repository.getCurrentProgramState();

        System.out.println(programState.toString());
        while (!programState.getExecutionStack().isEmpty()) {
            this.oneStepExecution(programState);
            System.out.println(this.repository.getCurrentProgramState().toString());
        }
    }

    public void addProgramState(ProgramState programState) {
        this.repository.addProgramState(programState);
    }

    public void displayCurrentState() {
        System.out.println(this.repository.getCurrentProgramState().toString());
    }
}
