package model.statement;

import exception.AddressUndefinedException;
import exception.VariableUndefinedException;
import exception.WrongTypeException;
import model.ProgramState;
import model.adt.IDictionary;
import model.adt.IHeap;
import model.expression.Expression;
import model.type.RefType;
import model.value.RefValue;
import model.value.Value;

public class HeapWritingStatement implements Statement {

    private final String variableName;
    private final Expression expression;

    public HeapWritingStatement(String variableName, Expression expression) {
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
    public ProgramState execute(ProgramState programState) {
        IDictionary<String, Value> symbolTable = programState.getSymbolTable();
        IHeap<Value> heap = programState.getHeap();

        if (!(symbolTable.containsKey(this.variableName))) {
            throw new VariableUndefinedException(this.variableName);
        }

        Value heapAddress = symbolTable.lookUp(this.variableName);

        if (!(heapAddress.getType() instanceof RefType)) {
            throw new WrongTypeException("Variable should have RefType.");
        }

        if (!(heap.containsKey(((RefValue) heapAddress).getAddress()))) {
            throw new AddressUndefinedException("Address is not in the heap.");
        }

        Value expressionValue = this.expression.evaluate(symbolTable, heap);

        if (!(expressionValue.getType().equals(((RefValue) heapAddress).getLocationType()))) {
            throw new WrongTypeException("The expression doesn't have the good type.");
        }

        heap.update(((RefValue) heapAddress).getAddress(), expressionValue);

        return null;
    }

    @Override
    public Statement deepCopy() {
        return new HeapWritingStatement(this.variableName, this.expression.deepCopy());
    }

    @Override
    public String toString() {
        return "wH(" + this.variableName + ", " + this.expression + ")";
    }
}
