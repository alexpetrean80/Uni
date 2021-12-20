package model.expression;

import exception.VariableUndefinedException;
import model.adt.Dict;
import model.adt.Heap;
import model.type.Type;
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
    public Value evaluate(Dict<String, Value> symbolTable, Heap<Value> heap) {
        var value = symbolTable.lookup(this.variableName);

        if (value == null)
            throw new VariableUndefinedException(this.variableName);

        return value;
    }

    @Override
    public Expression deepCopy() {
        return new VariableExpression(this.variableName);
    }

    @Override
    public Type typeCheck(Dict<String, Type> typeEnvironment) {
        return typeEnvironment.lookup(variableName);
    }

    @Override
    public String toString(){ return this.getVariableName();}
}
