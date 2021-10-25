package repo;

import model.interfaces.ProgramState;
import model.containers.List;

public class Repository implements repo.interfaces.Repository {
    ProgramState state;

    public ProgramState getStates() {
        return state;
    }

    public void setStates(ProgramState state) {
        this.state = state;
    }

    public Repository(ProgramState state) {
        this.state = state;
    }

    @Override
    public ProgramState getCurrentProgram() {
        return state;
    }
}
