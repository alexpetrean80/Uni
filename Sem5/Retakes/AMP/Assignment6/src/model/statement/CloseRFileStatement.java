package model.statement;

import exception.MyException;
import exception.TypeCheckException;
import model.ProgramState;
import model.adt.Dict;
import model.adt.Heap;
import model.expression.Expression;
import model.type.StringType;
import model.type.Type;
import model.value.StringValue;
import model.value.Value;

import java.io.BufferedReader;
import java.io.IOException;

public class CloseRFileStatement implements Statement{
    private final Expression expression;

    public CloseRFileStatement(Expression expression){
        this.expression = expression;
    }

    @Override
    public ProgramState execute(ProgramState programState) {
        Dict<String, Value> symbolTable = programState.getSymbolTable();
        Heap<Value> heap = programState.getHeap();

        Value expressionValue = this.expression.evaluate(symbolTable, heap);
        if (!(expressionValue.getType().equals(new StringType()))){
            throw new MyException("The value is not a string type");
        }
        StringValue stringValue = (StringValue) expressionValue;

        synchronized (programState.getFileTable()) {
            Dict<StringValue, BufferedReader> fileTable = programState.getFileTable();

            if (!(fileTable.containsKey(stringValue))) {
                throw new MyException("The value is not in the file table.");
            }

            BufferedReader bufferedReader = fileTable.lookup(stringValue);
            try {
                bufferedReader.close();
                fileTable.remove(stringValue);
            } catch (IOException except) {
                throw new MyException(except.getMessage());
            }
        }
        return null;
    }

    @Override
    public Statement deepCopy() {
        return new CloseRFileStatement(this.expression.deepCopy());
    }

    @Override
    public Dict<String, Type> typeCheck(Dict<String, Type> typeEnvironment) {
        if (!(this.expression.typeCheck(typeEnvironment).equals(new StringType()))){
            throw new TypeCheckException("Expression doesn't have string type.");
        }

        return typeEnvironment;
    }

    @Override
    public String toString(){
        return "close " + this.expression.toString();
    }
}
