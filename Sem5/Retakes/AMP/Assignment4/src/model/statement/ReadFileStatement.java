package model.statement;

import exception.MyException;
import model.ProgramState;
import model.adt.IDictionary;
import model.adt.IHeap;
import model.expression.Expression;
import model.type.IntType;
import model.type.StringType;
import model.value.IntValue;
import model.value.StringValue;
import model.value.Value;

import java.io.BufferedReader;
import java.io.IOException;

public class ReadFileStatement implements Statement{

    private final Expression expression;
    private final String variableName;

    public ReadFileStatement(Expression expression, String variableName){
        this.expression = expression;
        this.variableName = variableName;
    }

    @Override
    public ProgramState execute(ProgramState programState) {
        IDictionary<String, Value> symbolTable = programState.getSymbolTable();
        IHeap<Value> heap = programState.getHeap();

        if (!(symbolTable.containsKey(this.variableName))){
            throw new MyException("Variable name is not defined.");
        }

        if (!(symbolTable.lookUp(this.variableName).getType().equals(new IntType()))){
            throw new MyException("Variable it doesn't have int type.");
        }

        Value value = this.expression.evaluate(symbolTable, heap);
        if (!(value.getType().equals(new StringType()))){
            throw new MyException("Variable is not a string type.");
        }

        StringValue stringValue = (StringValue)value;

        IDictionary<StringValue, BufferedReader> fileTable = programState.getFileTable();
        if (!(fileTable.containsKey(stringValue))){
            throw new MyException("The string value is not in the file table.");
        }

        try {
            BufferedReader bufferedReader = fileTable.lookUp(stringValue);
            String line = bufferedReader.readLine();
            IntValue readValue = new IntValue(0);
            if (!(line == null)){
                readValue = new IntValue(Integer.parseInt(line));
            }
            symbolTable.update(this.variableName, readValue);
        }
        catch (IOException except){
            throw new MyException(except.getMessage());
        }
        return programState;
    }

    @Override
    public Statement deepCopy() {
        return null;
    }

    @Override
    public String toString(){
        return "read form " + this.expression.toString() + " into " + this.variableName +  ")";
    }
}
