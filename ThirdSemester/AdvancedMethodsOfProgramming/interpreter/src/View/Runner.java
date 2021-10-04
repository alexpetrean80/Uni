package View;

import controller.interfaces.Controller;
import repo.interfaces.Repository;

public class Runner {
    Repository repo;
    Controller controller;

    public Runner(Repository repo, Controller controller) {
        this.repo = repo;
        this.controller = controller;
    }

    public void run() {
        try {
            controller.executeAllSteps();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        var outputList = repo.getCurrentProgram().getOutput().getInnerList();
        for (var output: outputList) {
            System.out.println(output);
        }
    }

}
