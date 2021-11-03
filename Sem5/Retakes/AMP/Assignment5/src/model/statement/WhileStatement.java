package model.statement;

import exception.NotBooleanException;
import model.ProgramState;
import model.adt.IDictionary;
import model.adt.IHeap;
import model.adt.IStack;
import model.expression.Expression;
import model.value.BoolValue;
import model.value.Value;

public class WhileStatement implements Statement{

    private final Expression expression;
    private final Statement statement;

    public WhileStatement(Expression expression, Statement statement) {
        this.expression = expression;
        this.statement = statement;
    }

    public Expression getExpression() {
        return expression;
    }

    public Statement getStatement(){
        return this.statement;
    }

    @Override
    public ProgramState execute(ProgramState programState) {
        IDictionary<String, Value> symbolTable = programState.getSymbolTable();
        IHeap<Value> heap = programState.getHeap();
        IStack<Statement> executionStack = programState.getExecutionStack();

        Value expressionValue = this.expression.evaluate(symbolTable, heap);

        if (!(expressionValue instanceof BoolValue)){
            throw new NotBooleanException();
        }

        if (Value.toBoolean(expressionValue)){
            executionStack.push(this);
            executionStack.push(this.statement);
        }
        return null;
    }

    @Override
    public Statement deepCopy() {
        return new WhileStatement(this.expression.deepCopy(), this.statement.deepCopy());
    }

    @Override
    public String toString() {
        return "(while (" +  this.expression + ")" + this.statement + ")";
    }
}
