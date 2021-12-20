package model.statement;

import exception.MyException;
import exception.TypeCheckException;
import exception.VariableUndefinedException;
import exception.WrongTypeAssignedException;
import model.ProgramState;
import model.adt.Dict;
import model.adt.Heap;
import model.expression.Expression;
import model.type.RefType;
import model.type.Type;
import model.value.RefValue;
import model.value.Value;

public class HeapAllocationStatement implements Statement{
    private final String variableName;
    private final Expression expression;

    public HeapAllocationStatement(String variableName, Expression expression) {
        this.variableName = variableName;
        this.expression = expression;
    }


    @Override
    public ProgramState execute(ProgramState programState) {
        Dict<String, Value> symbolTable = programState.getSymbolTable();
        Heap<Value> heap = programState.getHeap();

        if (!(symbolTable.containsKey(this.variableName))){
            throw new VariableUndefinedException(this.variableName);
        }

        Type variableType = symbolTable.lookup(this.variableName).getType();

        if (!(variableType instanceof RefType)){
            throw new WrongTypeAssignedException(this.variableName);
        }

        Value valueExpression = this.expression.evaluate(symbolTable, heap);

        if (!(valueExpression.getType().equals(((RefType) variableType).getInner()))){
            throw  new MyException("The expression is not a RefType");
        }

        synchronized (programState.getHeap()) {
            int address = heap.allocate(valueExpression);
            symbolTable.update(this.variableName, new RefValue(address, valueExpression.getType()));
        }
        return null;
    }

    @Override
    public Statement deepCopy() {
        return new HeapAllocationStatement(this.variableName, this.expression.deepCopy());
    }

    @Override
    public Dict<String, Type> typeCheck(Dict<String, Type> typeEnvironment) {
        Type expressionType = this.expression.typeCheck(typeEnvironment);
        Type variableType = typeEnvironment.lookup(this.variableName);

        if (!(variableType.equals(new RefType(expressionType)))){
            throw new TypeCheckException("NEW statement: right hand side and left hand side have different types");
        }

        return typeEnvironment;
    }

    @Override
    public String toString() {
        return "new(" + this.variableName + ", " + this.expression.toString() + ")" ;
    }
}
