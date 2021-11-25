package model.expression;

import exception.UnknownOperatorException;
import model.adt.IDictionary;
import model.adt.IHeap;
import model.value.Value;

public class RelationalExpression implements Expression {
    private final Expression leftExpression;
    private final Expression rightExpression;
    private final String relationalOperator;

    public RelationalExpression(String relationalOperator, Expression leftExpression, Expression rightExpression) {
        this.relationalOperator = relationalOperator;
        this.leftExpression = leftExpression;
        this.rightExpression = rightExpression;
    }

    public String getRelationalOperator() {
        return this.relationalOperator;
    }

    public Expression getLeftExpression() {
        return this.leftExpression;
    }

    public Expression getRightExpression() {
        return this.rightExpression;
    }

    @Override
    public Value evaluate(IDictionary<String, Value> symbolTable, IHeap<Value> heap) {
        switch (relationalOperator) {
            case "<" -> {
                return Value.fromBoolean(Value.toInteger(this.leftExpression.evaluate(symbolTable, heap)) <
                        Value.toInteger(this.rightExpression.evaluate(symbolTable, heap)));
            }
            case "<=" -> {
                return Value.fromBoolean(Value.toInteger(this.leftExpression.evaluate(symbolTable, heap)) <=
                        Value.toInteger(this.rightExpression.evaluate(symbolTable, heap)));
            }
            case "==" -> {
                return Value.fromBoolean(Value.toInteger(this.leftExpression.evaluate(symbolTable, heap)) ==
                        Value.toInteger(this.rightExpression.evaluate(symbolTable, heap)));
            }
            case "!=" -> {
                return Value.fromBoolean(Value.toInteger(this.leftExpression.evaluate(symbolTable, heap)) !=
                        Value.toInteger(this.rightExpression.evaluate(symbolTable, heap)));
            }
            case ">" -> {
                return Value.fromBoolean(Value.toInteger(this.leftExpression.evaluate(symbolTable, heap)) >
                        Value.toInteger(this.rightExpression.evaluate(symbolTable, heap)));
            }
            case ">=" -> {
                return Value.fromBoolean(Value.toInteger(this.leftExpression.evaluate(symbolTable, heap)) >=
                        Value.toInteger(this.rightExpression.evaluate(symbolTable, heap)));
            }
            default -> {
                throw new UnknownOperatorException("Relational operator unknown.");
            }
        }
    }

    @Override
    public Expression deepCopy() {
        return new RelationalExpression(this.relationalOperator,
                this.leftExpression.deepCopy(), this.rightExpression.deepCopy());
    }

    @Override
    public String toString() {
        return this.leftExpression.toString() + " " + this.relationalOperator + " " + this.rightExpression;
    }
}
