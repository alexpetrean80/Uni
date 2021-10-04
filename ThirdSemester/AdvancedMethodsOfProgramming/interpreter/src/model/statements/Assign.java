package model.statements;


import model.interfaces.Expression;
import model.interfaces.ProgramState;
import model.interfaces.Statement;

public class Assign implements Statement {
    final private String symbol;
    final private Expression expression;

    public Assign(String symbol, Expression expression) {
        this.symbol = symbol;
        this.expression = expression;
    }

    @Override
    public void execute(ProgramState state) throws Exception {
        var symbolTable = state.getSymbolTable();
        if (!symbolTable.doesKeyExist(symbol)) {
            throw new Exception("Variable" + symbol + "not declare in" + this + "statement");
        }
        var value = expression.evaluate(symbolTable);
        if (!value.getType().isOf(symbolTable.getValue(symbol).getType())) {
            throw new Exception("Wrong type of expression for variable" + symbol);
        }
        symbolTable.update(symbol, value);
    }

    @Override
    public String toString() {
    return symbol + "=" + expression.toString();
    }
}
