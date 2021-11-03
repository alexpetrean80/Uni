package model.expression;

import model.adt.IDictionary;
import model.value.Value;

public interface Expression {
    Value evaluate(IDictionary<String, Value> symbolTable);
    Expression deepCopy();
}
