package model.expression;

import exception.TypeCheckException;
import exception.UnknownOperatorException;
import model.adt.IDictionary;
import model.adt.IHeap;
import model.type.BoolType;
import model.type.Type;
import model.value.Value;

import static model.value.Value.fromBoolean;
import static model.value.Value.toBoolean;

public class LogicalExpression implements Expression {
    private final Expression leftExpr;
    private final Expression rightExpr;
    private final String logicalOperator;

    public LogicalExpression(String logicalOperator, Expression leftExpr, Expression rightExpr){
        this.leftExpr = leftExpr;
        this.rightExpr = rightExpr;
        this.logicalOperator = logicalOperator;
    }

    public Expression getLeftExpr() { return this.leftExpr; }

    public Expression getRightExpr() { return this.rightExpr; }

    public String getLogicalOperator() { return this.logicalOperator; }

    @Override
    public Value evaluate(IDictionary<String, Value> symbolTable, IHeap<Value> heap) {
        if (logicalOperator.equals("and")){
            return fromBoolean(toBoolean(leftExpr.evaluate(symbolTable, heap)) && toBoolean(rightExpr.evaluate(symbolTable, heap)));
        }
        else if (logicalOperator.equals("or")){
            return fromBoolean(toBoolean(leftExpr.evaluate(symbolTable, heap)) || toBoolean(rightExpr.evaluate(symbolTable, heap)));
        }
        else
            throw new UnknownOperatorException("Logical operator unknown.");
    }

    @Override
    public Expression deepCopy() {
        return new LogicalExpression(this.logicalOperator, this.leftExpr.deepCopy(), this.rightExpr.deepCopy());
    }

    @Override
    public Type typeCheck(IDictionary<String, Type> typeEnvironment) {
        if (!(this.leftExpr.typeCheck(typeEnvironment).equals(new BoolType()))){
            throw new TypeCheckException("The left expression is not a boolean");
        }

        if (!(this.rightExpr.typeCheck(typeEnvironment).equals(new BoolType()))){
            throw new TypeCheckException("The right expression is not a boolean");
        }

        return new BoolType();
    }

    @Override
    public String toString() {
        return this.leftExpr.toString() + " " + this.logicalOperator + " " + this.rightExpr.toString();}
}
