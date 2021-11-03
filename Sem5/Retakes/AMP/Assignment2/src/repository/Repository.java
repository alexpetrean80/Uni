package repository;

import exception.MyException;
import model.ProgramState;
import model.adt.IList;
import model.adt.MyList;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Repository implements IRepository{

    private final IList<ProgramState> states;
    private final String logPath;

    public Repository(String logPath){
        this.states = new MyList<ProgramState>();
        this.logPath = logPath;
    }

    public Repository(ProgramState programState, String logPath){
        this.states = new MyList<ProgramState>();
        this.states.addToEnd(programState);
        this.logPath = logPath;
    }

    @Override
    public void addProgramState(ProgramState programState){
        // it always adds the state on the last position because for the moment
        // we can have only 1 program state in the repo

        this.states.add(0, programState);
    }

    @Override
    public ProgramState getCurrentProgramState() {
        return this.states.get(0);
    }

    @Override
    public void logProgramStateExecution(){
        try {
            PrintWriter logFile = new PrintWriter(new BufferedWriter(new FileWriter(logPath, true)));
            logFile.write(this.getCurrentProgramState().toString());
            logFile.close();
        }
        catch (IOException except){
            throw new MyException(except.getMessage());
        }
    }
}
