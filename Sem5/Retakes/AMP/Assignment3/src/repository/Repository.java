package repository;

import exception.CustomException;
import model.ProgramState;
import model.adt.IList;
import model.adt.TLList;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Repository implements IRepository {

    private final IList<ProgramState> states;
    private final String logPath;

    public Repository(String logPath) {
        this.states = new TLList<>();
        this.logPath = logPath;
    }

    public Repository(ProgramState programState, String logPath) {
        this.states = new TLList<>();
        this.states.addToEnd(programState);
        this.logPath = logPath;
    }

    @Override
    public void addProgramState(ProgramState programState) {
        this.states.add(0, programState);
    }

    @Override
    public ProgramState getCurrentProgramState() {
        return this.states.get(0);
    }

    @Override
    public void logProgramStateExecution() {
        try (var logFile = new PrintWriter(new BufferedWriter(new FileWriter(logPath, true)))) {
            logFile.write(this.getCurrentProgramState().toString());
        } catch (IOException except) {
            throw new CustomException(except.getMessage());
        }
    }
}
