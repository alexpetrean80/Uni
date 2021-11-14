package model.statement;

import model.ProgramState;
import model.expression.Expression;

public class PrintStatement implements Statement {
    private final Expression expression;

    public PrintStatement(Expression expression){
        this.expression = expression;
    }

    public Expression getExpression() {
        return this.expression;
    }

    @Override
    public ProgramState execute(ProgramState programState) {
        var output = programState.getOutput();
        var symbolTable = programState.getSymbolTable();

        output.addToEnd(this.expression.evaluate(symbolTable));

        return programState;
    }

    @Override
    public Statement deepCopy() {
        return new PrintStatement(this.expression.deepCopy());
    }

    @Override
    public String toString(){
        return "print(" + this.expression.toString() + ")";
    }

}
