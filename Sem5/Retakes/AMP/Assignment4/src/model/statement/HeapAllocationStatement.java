package model.statement;

import exception.MyException;
import exception.VariableUndefinedException;
import exception.WrongTypeAssignedException;
import model.ProgramState;
import model.adt.IDictionary;
import model.adt.IHeap;
import model.expression.Expression;
import model.type.RefType;
import model.type.Type;
import model.value.RefValue;
import model.value.Value;

public class HeapAllocationStatement implements Statement {
    private final String variableName;
    private final Expression expression;

    public HeapAllocationStatement(String variableName, Expression expression) {
        this.variableName = variableName;
        this.expression = expression;
    }


    @Override
    public ProgramState execute(ProgramState programState) {
        IDictionary<String, Value> symbolTable = programState.getSymbolTable();
        IHeap<Value> heap = programState.getHeap();

        if (!(symbolTable.containsKey(this.variableName))) {
            throw new VariableUndefinedException(this.variableName);
        }

        Type variableType = symbolTable.lookUp(this.variableName).getType();

        if (!(variableType instanceof RefType)) {
            throw new WrongTypeAssignedException(this.variableName);
        }

        Value valueExpression = this.expression.evaluate(symbolTable, heap);

        if (!(valueExpression.getType().equals(((RefType) variableType).getInner()))) {
            throw new MyException("The expression is not a RefType");
        }

        int address = heap.allocate(valueExpression);
        symbolTable.update(this.variableName, new RefValue(address, valueExpression.getType()));

        return programState;
    }

    @Override
    public Statement deepCopy() {
        return new HeapAllocationStatement(this.variableName, this.expression.deepCopy());
    }

    @Override
    public String toString() {
        return "new(" + this.variableName + ", " + this.expression.toString() + ")";
    }
}
