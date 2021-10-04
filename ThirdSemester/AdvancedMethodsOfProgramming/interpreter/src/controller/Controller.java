package controller;

import repo.interfaces.Repository;

public class Controller implements controller.interfaces.Controller {
    Repository repo;

    public Controller(Repository repo) {
        this.repo = repo;
    }


    @Override
    public void executeOneStep() throws Exception {
        var state = repo.getCurrentProgram();
        var stack = state.getExecStack();

        if (stack.isEmpty()) {
            throw new Exception("Program has ended");
        }
        stack.pop().execute(state);
    }

    @Override
    public void executeAllSteps() throws Exception {
        var state = repo.getCurrentProgram();
        System.out.println(state);
        while (!state.getExecStack().isEmpty()) {
            executeOneStep();
            System.out.println(state);
        }
    }
}
