package model.statement;

import exception.NotBooleanException;
import model.ProgramState;
import model.expression.Expression;
import model.value.BoolValue;
import model.value.Value;

public class WhileStatement implements Statement {

    private final Expression expression;
    private final Statement statement;

    public WhileStatement(Expression expression, Statement statement) {
        this.expression = expression;
        this.statement = statement;
    }

    public Expression getExpression() {
        return expression;
    }

    public Statement getStatement() {
        return this.statement;
    }

    @Override
    public ProgramState execute(ProgramState programState) {
        var symbolTable = programState.getSymbolTable();
        var heap = programState.getHeap();
        var executionStack = programState.getExecutionStack();

        var expressionValue = this.expression.evaluate(symbolTable, heap);

        if (!(expressionValue instanceof BoolValue)) {
            throw new NotBooleanException();
        }

        if (Value.toBoolean(expressionValue)) {
            executionStack.push(this);
            executionStack.push(this.statement.deepCopy()); // DO NOT REMOVE DEEPCOPY
        }
        return null;
    }

    @Override
    public Statement deepCopy() {
        return new WhileStatement(this.expression.deepCopy(), this.statement.deepCopy());
    }

    @Override
    public String toString() {
        return "(while (" + this.expression + ")" + this.statement + ")";
    }
}
