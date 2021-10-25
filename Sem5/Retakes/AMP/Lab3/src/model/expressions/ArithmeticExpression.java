package model.expressions;

import model.operations.ArithmeticOperation;
import model.values.Value;

public class ArithmeticExpression implements Expression {
    Expression e1, e2;
    ArithmeticOperation op;

    public ArithmeticExpression(Expression e1, Expression e2, ArithmeticOperation op) {
        this.e1 = e1;
        this.e2 = e2;
        this.op = op;
    }

    @Override
    public Value<?> eval() {
        return op.compute();
    }
}
