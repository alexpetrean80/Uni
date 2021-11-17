package model.statement;

import model.ProgramState;
import model.adt.IStack;

public class CompoundStatement implements Statement {

    private final IStack<Statement> statements;

    public CompoundStatement(IStack<Statement> statements) {
        this.statements = statements;
    }

    @Override
    public ProgramState execute(ProgramState programState) {
        var executionStack = programState.getExecutionStack();

        while (!statements.isEmpty()) {
            executionStack.push(statements.pop());
        }

        return programState;
    }

    @Override
    public Statement deepCopy() {
        return new CompoundStatement(statements.deepCopy());
    }

    @Override
    public String toString() {
        return this.statements.toString();
    }

}
