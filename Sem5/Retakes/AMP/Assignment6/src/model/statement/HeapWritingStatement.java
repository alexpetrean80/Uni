package model.statement;

import exception.AddressUndefinedException;
import exception.TypeCheckException;
import exception.VariableUndefinedException;
import exception.WrongTypeException;
import model.ProgramState;
import model.adt.Dict;
import model.adt.Heap;
import model.expression.Expression;
import model.type.RefType;
import model.type.Type;
import model.value.RefValue;
import model.value.Value;

public class HeapWritingStatement implements Statement{

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
        Dict<String, Value> symbolTable = programState.getSymbolTable();
        Heap<Value> heap = programState.getHeap();

        if (!(symbolTable.containsKey(this.variableName))){
            throw new VariableUndefinedException(this.variableName);
        }

        Value heapAddress = symbolTable.lookup(this.variableName);

        if (!(heapAddress.getType() instanceof RefType)){
            throw new WrongTypeException("Variable should have RefType.");
        }

        synchronized (programState.getHeap()) {
            if (!(heap.containsKey(((RefValue) heapAddress).getAddress()))) {
                throw new AddressUndefinedException("Address is not in the heap.");
            }

            Value expressionValue = this.expression.evaluate(symbolTable, heap);

            if (!(expressionValue.getType().equals(((RefValue) heapAddress).getLocationType()))) {
                throw new WrongTypeException("The expression doesn't have the good type.");
            }

            heap.update(((RefValue) heapAddress).getAddress(), expressionValue);
        }
        return null;
    }

    @Override
    public Statement deepCopy() {
        return new HeapWritingStatement(this.variableName, this.expression.deepCopy());
    }

    @Override
    public Dict<String, Type> typeCheck(Dict<String, Type> typeEnvironment) {
        Type expressionType = this.expression.typeCheck(typeEnvironment);
        Type variableType = typeEnvironment.lookup(this.variableName);

        if (!(variableType.equals(new RefType(expressionType)))){
            throw new TypeCheckException("rH statement: left hand side and right hand side don't have the same type.");
        }

        return typeEnvironment;
    }

    @Override
    public String toString() {
        return "wH(" + this.variableName + ", " + this.expression + ")";
    }
}
