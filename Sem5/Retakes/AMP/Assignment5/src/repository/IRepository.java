package repository;

import model.ProgramState;

import java.util.List;

public interface IRepository {
   // void addProgramState(ProgramState programState);
    List<ProgramState> getProgramStateList();
    void setProgramList(List<ProgramState> newProgramStateList);
    void logProgramStateExecution(ProgramState programState);
}
