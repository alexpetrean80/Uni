package repository;

import model.ProgramState;

public interface IRepository {
    ProgramState getCurrentProgramState();
    void addProgramState(ProgramState programState);
}
