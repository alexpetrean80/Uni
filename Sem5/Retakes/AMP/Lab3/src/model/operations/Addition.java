package model.operations;

import model.expressions.Expression;
import model.values.IntValue;
import model.values.Value;

public class Addition implements ArithmeticOperation {
    Expression ex1, ex2;

    @Override
    public Value<?> compute() {
        return new IntValue((int) ex1.eval().getValue() + (int) ex2.eval().getValue());
    }
}
