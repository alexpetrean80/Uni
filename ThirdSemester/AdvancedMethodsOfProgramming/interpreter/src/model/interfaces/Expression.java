package model.interfaces;

import model.containers.Dict;

public interface Expression {
    Value evaluate(Dict<String, Value> symbolTable) throws Exception;
}
