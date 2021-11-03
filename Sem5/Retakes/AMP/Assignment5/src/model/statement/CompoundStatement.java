package model.statement;

import model.ProgramState;
import model.adt.IStack;

public class CompoundStatement implements Statement {

    private final Statement leftStatement;
    private final Statement rightStatement;

    public CompoundStatement(Statement leftStatement, Statement rightStatement){
        this.leftStatement = leftStatement;
        this.rightStatement = rightStatement;
    }

    public Statement getLeftStatement() {
        return leftStatement;
    }

    public Statement getRightStatement() {
        return rightStatement;
    }

    @Override
    public String toString(){
        return "(" + this.leftStatement.toString() + "; " + this.rightStatement.toString() + ')';
    }

    @Override
    public ProgramState execute(ProgramState programState) {
        IStack<Statement> executionStack = programState.getExecutionStack();

        executionStack.push(this.rightStatement);
        executionStack.push(this.leftStatement);

        return null;
    }

    @Override
    public Statement deepCopy() {
        return new CompoundStatement(this.leftStatement.deepCopy(), this.rightStatement.deepCopy());
    }
}
