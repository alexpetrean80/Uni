package model.expression;

import exception.VariableUndefinedException;
import model.adt.IDictionary;
import model.adt.IHeap;
import model.value.Value;

public class VariableExpression implements Expression{

    private final String variableName;

    public VariableExpression(String variableName){
        this.variableName = variableName;
    }

    public String getVariableName(){
        return this.variableName;
    }

    @Override
    public Value evaluate(IDictionary<String, Value> symbolTable, IHeap<Value> heap) {
        Value value = symbolTable.lookUp(this.variableName);
        if (value == null)
            throw new VariableUndefinedException(this.variableName);
        return value;
    }

    @Override
    public Expression deepCopy() {
        return new VariableExpression(this.variableName);
    }

    @Override
    public String toString(){ return this.getVariableName();}
}
