package repository;

import model.ProgramState;
import model.adt.IList;
import model.adt.TLList;

public class Repository implements IRepository{

    private final IList<ProgramState> states;

    public Repository(){
        this.states = new TLList<ProgramState>();
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
}
