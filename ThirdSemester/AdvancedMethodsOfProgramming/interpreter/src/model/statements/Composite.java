package model.statements;

import model.interfaces.ProgramState;
import model.interfaces.Statement;

public class Composite implements Statement {
    final private Statement first, second;

    public Composite(Statement first, Statement second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public void execute(ProgramState state) throws Exception {
        var stack = state.getExecStack();
        stack.push(second);
        stack.push(first);
    }
}
