package model.expression;

import model.adt.Dict;
import model.adt.Heap;
import model.type.Type;
import model.value.Value;

public interface Expression {
    Value evaluate(Dict<String, Value> symbolTable, Heap<Value> heap);
    Expression deepCopy();
    Type typeCheck(Dict<String, Type> typeEnvironment);
}
