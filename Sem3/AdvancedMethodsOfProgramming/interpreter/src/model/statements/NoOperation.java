package model.statements;

import model.interfaces.ProgramState;
import model.interfaces.Statement;

public class NoOperation implements Statement {
    @Override
    public void execute(ProgramState state) throws Exception {
    }

    public String toString() {
        return "NO-OP";
    }
}
