package model.expression;

import exception.MyException;
import model.adt.IDictionary;
import model.adt.IHeap;
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
        return null;
    }

    @Override
    public String toString() {
        return "rH(" + this.expression + ")";
    }
}
