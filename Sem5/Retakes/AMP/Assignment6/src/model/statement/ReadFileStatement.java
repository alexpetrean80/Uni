package model.statement;

import exception.MyException;
import exception.TypeCheckException;
import model.ProgramState;
import model.adt.Dict;
import model.adt.Heap;
import model.expression.Expression;
import model.type.IntType;
import model.type.StringType;
import model.type.Type;
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
    public synchronized ProgramState execute(ProgramState programState) {
        Dict<String, Value> symbolTable = programState.getSymbolTable();
        Heap<Value> heap = programState.getHeap();

        if (!(symbolTable.containsKey(this.variableName))){
            throw new MyException("Variable name is not defined.");
        }

        if (!(symbolTable.lookup(this.variableName).getType().equals(new IntType()))){
            throw new MyException("Variable it doesn't have int type.");
        }

        Value value = this.expression.evaluate(symbolTable, heap);
        if (!(value.getType().equals(new StringType()))){
            throw new MyException("Variable is not a string type.");
        }

        StringValue stringValue = (StringValue)value;

        synchronized (programState.getFileTable()) {
            Dict<StringValue, BufferedReader> fileTable = programState.getFileTable();
            if (!(fileTable.containsKey(stringValue))) {
                throw new MyException("The string value is not in the file table.");
            }

            try {
                BufferedReader bufferedReader = fileTable.lookup(stringValue);
                String line = bufferedReader.readLine();
                IntValue readValue = new IntValue(0);
                if (!(line == null)) {
                    readValue = new IntValue(Integer.parseInt(line));
                }
                symbolTable.update(this.variableName, readValue);
            } catch (IOException except) {
                throw new MyException(except.getMessage());
            }
        }
        return null;
    }

    @Override
    public Statement deepCopy() {
        return new ReadFileStatement(this.expression.deepCopy(), this.variableName);
    }

    @Override
    public Dict<String, Type> typeCheck(Dict<String, Type> typeEnvironment) {
        if (!(this.expression.typeCheck(typeEnvironment).equals(new StringType()))){
            throw new TypeCheckException("readFile statement: Expression is not a string!");
        }

        if (!(typeEnvironment.lookup(this.variableName).equals(new IntType()))){
            throw new TypeCheckException("readFile statement: The variable is not an integer!");
        }

        return typeEnvironment;
    }

    @Override
    public String toString(){
        return "read form " + this.expression.toString() + " into " + this.variableName +  ")";
    }
}
