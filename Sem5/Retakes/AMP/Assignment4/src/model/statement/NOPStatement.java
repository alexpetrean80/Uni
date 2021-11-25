package model.statement;

import model.ProgramState;

public class NOPStatement implements Statement {

    @Override
    public ProgramState execute(ProgramState programState) {
        return programState;
    }

    @Override
    public String toString() {
        return "";
    }

    @Override
    public Statement deepCopy() {
        return new NOPStatement();
    }
}
