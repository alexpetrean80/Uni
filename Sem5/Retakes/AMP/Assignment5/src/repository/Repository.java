package repository;

import exception.MyException;
import model.ProgramState;
import model.adt.IList;
import model.adt.MyList;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Repository implements IRepository{

    private List<ProgramState> states;
    private final String logPath;

    public Repository(ProgramState programState, String logPath){
        this.states = new ArrayList<ProgramState>();
        this.states.add(programState);
        this.logPath = logPath;
    }

    @Override
    public List<ProgramState> getProgramStateList(){
        return this.states;
    }

    @Override
    public void setProgramList(List<ProgramState> newProgramStateList) {
        this.states = newProgramStateList;
    }

    @Override
    public void logProgramStateExecution(ProgramState programState){
        try {
            PrintWriter logFile = new PrintWriter(new BufferedWriter(new FileWriter(logPath, true)));
            logFile.write("Current thread id: " + programState.getId() + "\n");
            logFile.write(programState.toString());
            logFile.close();
        }
        catch (IOException ex){
            throw new MyException(ex.getMessage());
        }
    }
}
