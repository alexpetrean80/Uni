package model.operations;

import model.expressions.Expression;
import model.values.IntValue;
import model.values.Value;

public class Subtraction implements ArithmeticOperation {
    private Expression ex1;
    private Expression ex2;

    public Subtraction(Expression ex1, Expression ex2) {
        this.ex1 = ex1;
        this.ex2 = ex2;
    }

    @Override
    public Value<?> compute() {
        return new IntValue((int) ex1.eval().getValue() - (int) ex2.eval().getValue());
    }
}
