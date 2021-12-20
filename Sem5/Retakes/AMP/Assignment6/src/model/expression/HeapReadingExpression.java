package model.expression;

import exception.MyException;
import exception.TypeCheckException;
import model.adt.Dict;
import model.adt.Heap;
import model.type.RefType;
import model.type.Type;
import model.value.RefValue;
import model.value.Value;

public class HeapReadingExpression implements Expression {

    private final Expression expression;

    public HeapReadingExpression(Expression expression) {
        this.expression = expression;
    }

    public Expression getExpression() {
        return expression;
    }

    @Override
    public Value evaluate(Dict<String, Value> symbolTable, Heap<Value> heap) {

        var expressionValue = this.expression.evaluate(symbolTable, heap);

        if (!(expressionValue instanceof RefValue)) {
            throw new MyException("The expression value is not RefValue.");
        }

        var address = ((RefValue) expressionValue).getAddress();

        return heap.lookup(address);
    }

    @Override
    public Expression deepCopy() {
        return new HeapReadingExpression(this.expression.deepCopy());
    }

    @Override
    public Type typeCheck(Dict<String, Type> typeEnvironment) {
        var expressionType = this.expression.typeCheck(typeEnvironment);

        if (!(expressionType instanceof RefType)) {
            throw new TypeCheckException("The rH argument is not a RefType!");
        }

        var refTypeExpression = (RefType) expressionType;

        return refTypeExpression.getInner();
    }

    @Override
    public String toString() {
        return "rH(" + this.expression + ")";
    }
}
