package model.expression;

import model.adt.Dict;
import model.adt.Heap;
import model.type.Type;
import model.value.Value;

public class ValueExpression implements Expression{

    Value value;
    public ValueExpression(Value value) {
        this.value = value;
    }

    @Override
    public Value evaluate(Dict<String, Value> symbolTable, Heap<Value> heap) {
        return this.value;
    }

    @Override
    public Expression deepCopy() {
        return new ValueExpression(this.value.deepCopy());
    }

    @Override
    public Type typeCheck(Dict<String, Type> typeEnvironment) {
        return value.getType();
    }

    @Override
    public String toString(){return this.value.toString();}
}
