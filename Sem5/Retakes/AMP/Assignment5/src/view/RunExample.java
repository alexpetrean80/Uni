package view;

import controller.Controller;
import exception.MyException;

public class RunExample extends Command{

    private final Controller controller;

    public RunExample(String key, String description, Controller controller){
        super(key, description);
        this.controller = controller;
    }


    @Override
    public void execute() {
        try{
            this.controller.allStepsExecution();
        }
        catch (MyException exception){
            System.out.println(exception.getMessage());
        }
    }
}
