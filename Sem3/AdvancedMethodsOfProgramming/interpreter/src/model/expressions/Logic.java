package model.expressions;

import model.containers.Dict;
import model.interfaces.Expression;
import model.interfaces.Value;
import model.operations.LogicOperation;
import model.values.Bool;

public class Logic implements Expression {
    final Expression first, second;
    final LogicOperation op;

    public Logic(Expression first, Expression second, LogicOperation op) {
        this.first = first;
        this.second = second;
        this.op = op;
    }

    @Override
    public Value evaluate(Dict<String, Value> symbolTable) throws Exception {
        Value firstVal = first.evaluate(symbolTable);
        if (!firstVal.getType().isOf(new model.types.Bool())) {
            throw new Exception("first operand is not an integer");
        }
        Value secondVal = second.evaluate(symbolTable);
        if (!secondVal.getType().isOf(new model.types.Bool())) {
            throw new Exception("second operand is not an integer");
        }

        boolean firstBool = ((Bool) firstVal).getValue();
        boolean secondBool = ((Bool) secondVal).getValue();
        boolean result = false;
        switch (op) {
            case AND -> result = firstBool && secondBool;
            case OR -> result = firstBool || secondBool;
        }
        return new model.values.Bool(result);
    }

    @Override
    public String toString() {
        String opSymbol;
        switch (op) {

            case AND -> {
                opSymbol = "&&";
            }
            case OR -> {
                opSymbol = "||";
            }
            default -> throw new IllegalStateException("Unexpected value: " + op);
        }
        return first.toString() + opSymbol + second.toString();

    }
}
