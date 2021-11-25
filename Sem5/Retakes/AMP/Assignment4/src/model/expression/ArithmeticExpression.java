package model.expression;

import exception.DivisionByZeroException;
import exception.UnknownOperatorException;
import model.adt.IDictionary;
import model.adt.IHeap;
import model.value.Value;

public class ArithmeticExpression implements Expression {
    private final Expression leftExpression;
    private final Expression rightExpression;
    private final char arithmeticOperator;

    public ArithmeticExpression(char arithmeticOperator, Expression leftExpression, Expression rightExpression) {
        this.arithmeticOperator = arithmeticOperator;
        this.leftExpression = leftExpression;
        this.rightExpression = rightExpression;
    }

    public char getArithmeticOperator() {
        return this.arithmeticOperator;
    }

    public Expression getLeftExpression() {
        return this.leftExpression;
    }

    public Expression getRightExpression() {
        return this.rightExpression;
    }

    @Override
    public Value evaluate(IDictionary<String, Value> symbolTable, IHeap<Value> heap) {
        switch (arithmeticOperator) {
            case '+' -> {
                return Value.fromInteger(Value.toInteger(this.leftExpression.evaluate(symbolTable, heap)) +
                        Value.toInteger(this.rightExpression.evaluate(symbolTable, heap)));
            }
            case '-' -> {
                return Value.fromInteger(Value.toInteger(this.leftExpression.evaluate(symbolTable, heap)) -
                        Value.toInteger(this.rightExpression.evaluate(symbolTable, heap)));
            }
            case '*' -> {
                return Value.fromInteger(Value.toInteger(this.leftExpression.evaluate(symbolTable, heap)) *
                        Value.toInteger(this.rightExpression.evaluate(symbolTable, heap)));
            }
            case '/' -> {
                int leftNumber = Value.toInteger(this.leftExpression.evaluate(symbolTable, heap));
                int rightNumber = Value.toInteger(this.rightExpression.evaluate(symbolTable, heap));
                if (rightNumber == 0) {
                    throw new DivisionByZeroException();
                }
                return Value.fromInteger(leftNumber / rightNumber);
            }
            default -> {
                throw new UnknownOperatorException("Arithmetic operator unknown.");
            }
        }
    }

    @Override
    public Expression deepCopy() {
        return new ArithmeticExpression(this.arithmeticOperator,
                this.leftExpression.deepCopy(), this.rightExpression.deepCopy());
    }

    @Override
    public String toString() {
        return this.leftExpression.toString() + " " + this.arithmeticOperator + " " + this.rightExpression;
    }
}
