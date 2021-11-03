package repository;

import exception.MyException;
import model.ProgramState;
import model.statement.Statement;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Repository implements IRepository{

    private List<ProgramState> states;
    private final String logPath;
    private final Statement originalProgram;

    public Repository(ProgramState programState, String logPath){
        this.states = new ArrayList<ProgramState>();
        this.states.add(programState);
        this.logPath = logPath;
        this.originalProgram = programState.getOriginalProgram();
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

    @Override
    public Statement getOriginalProgram(){
        return this.originalProgram.deepCopy();
    }
}
