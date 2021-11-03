package model.expression;
import model.adt.IDictionary;
import model.adt.IHeap;
import model.type.Type;
import model.value.Value;

public interface Expression {
    Value evaluate(IDictionary<String, Value> symbolTable, IHeap<Value> heap);
    Expression deepCopy();
    Type typeCheck(IDictionary<String, Type> typeEnvironment);
}
