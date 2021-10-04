package model.statements;

import model.interfaces.Expression;
import model.interfaces.ProgramState;
import model.interfaces.Statement;

public class Print implements Statement {
    final private Expression expression;

    public Print(Expression expression) {
        this.expression = expression;
    }

    @Override
    public void execute(ProgramState state) throws Exception {
        var output = state.getOutput();
        var symbolTable = state.getSymbolTable();
        output.add(expression.evaluate(symbolTable));
    }

    @Override
    public String toString() {
        return ("print(" + expression.toString() + ")");
    }
}
