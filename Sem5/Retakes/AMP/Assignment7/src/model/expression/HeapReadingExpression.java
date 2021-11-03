package model.expression;

import exception.MyException;
import exception.TypeCheckException;
import model.adt.IDictionary;
import model.adt.IHeap;
import model.type.RefType;
import model.type.Type;
import model.value.RefValue;
import model.value.Value;

public class HeapReadingExpression implements Expression{

      private final Expression expression;

    public HeapReadingExpression(Expression expression) {
        this.expression = expression;
    }

    public Expression getExpression() {
        return expression;
    }

    @Override
    public Value evaluate(IDictionary<String, Value> symbolTable, IHeap<Value> heap) {

        Value expressionValue = this.expression.evaluate(symbolTable, heap);

        if (!(expressionValue instanceof RefValue)){
            throw new MyException("The expression value is not RefValue.");
        }

        int address = ((RefValue) expressionValue).getAddress();

        return heap.lookup(address);
    }

    @Override
    public Expression deepCopy() {
        return new HeapReadingExpression(this.expression.deepCopy());
    }

    @Override
    public Type typeCheck(IDictionary<String, Type> typeEnvironment) {
        Type expressionType = this.expression.typeCheck(typeEnvironment);

        if (!(expressionType instanceof RefType)){
            throw new TypeCheckException("The rH argument is not a RefType!");
        }

        RefType  refTypeExpression = (RefType) expressionType;

        return refTypeExpression.getInner();
    }

    @Override
    public String toString() {
        return "rH(" + this.expression + ")";
    }
}
