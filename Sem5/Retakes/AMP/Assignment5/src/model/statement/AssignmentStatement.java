package model.statement;

import exception.VariableUndefinedException;
import exception.WrongTypeAssignedException;
import model.ProgramState;
import model.adt.IDictionary;
import model.adt.IHeap;
import model.adt.IStack;
import model.expression.Expression;
import model.type.Type;
import model.value.Value;

public class AssignmentStatement implements Statement{

    private final String variableName;
    private final Expression expression;

    public AssignmentStatement(String variableName, Expression expression){
        this.variableName = variableName;
        this.expression = expression;
    }

    public String getVariableName() {
        return variableName;
    }

    public Expression getExpression() {
        return expression;
    }

    @Override
    public String toString() {
        return this.variableName + " = " + this.expression.toString();
    }

    @Override
    public ProgramState execute(ProgramState programState) {
        IStack<Statement> stack = programState.getExecutionStack();
        IDictionary<String, Value> symbolTable = programState.getSymbolTable();
        IHeap<Value> heap = programState.getHeap();

        if (!(symbolTable.containsKey(this.variableName)))
            throw new VariableUndefinedException(this.variableName);

        Type variableType = symbolTable.lookUp(this.variableName).getType();
        Value value = this.expression.evaluate(symbolTable, heap);

        if (!(value.getType().equals(variableType)))
            throw new WrongTypeAssignedException(this.variableName);

        symbolTable.update(this.variableName, value);
        return null;
    }

    @Override
    public Statement deepCopy() {
        return new AssignmentStatement(this.variableName, this.expression.deepCopy());
    }

}
