package model.statement;

import exception.MyException;
import model.ProgramState;
import model.adt.IDictionary;
import model.adt.IHeap;
import model.expression.Expression;
import model.type.StringType;
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
        IDictionary<String, Value> symbolTable = programState.getSymbolTable();
        IHeap<Value> heap = programState.getHeap();

        Value expressionValue = this.expression.evaluate(symbolTable, heap);
        if (!(expressionValue.getType().equals(new StringType()))){
            throw new MyException("The value is not a string type");
        }
        StringValue stringValue = (StringValue) expressionValue;

        synchronized (programState.getFileTable()) {
            IDictionary<StringValue, BufferedReader> fileTable = programState.getFileTable();

            if (!(fileTable.containsKey(stringValue))) {
                throw new MyException("The value is not in the file table.");
            }

            BufferedReader bufferedReader = fileTable.lookUp(stringValue);
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
    public String toString(){
        return "close " + this.expression.toString();
    }
}
