package model.statements;

import model.PrgState;

public class NopStatement implements Statement{
    @Override
    public PrgState execute(PrgState state) {
        return null;
    }
}
