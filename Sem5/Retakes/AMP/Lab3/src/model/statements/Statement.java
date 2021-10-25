package model.statements;

import model.PrgState;

public interface Statement {
    PrgState execute(PrgState state);
}
