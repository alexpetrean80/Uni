package repository;

import model.ProgramState;
import model.statement.Statement;

import java.util.List;

public interface IRepository {
    List<ProgramState> getProgramStateList();
    void setProgramList(List<ProgramState> newProgramStateList);
    void logProgramStateExecution(ProgramState programState);
    Statement getOriginalProgram();
}
