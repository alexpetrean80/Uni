package model.statement;

import exception.MyException;
import exception.UndefinedVariableException;
import exception.WrongTypeException;
import model.ProgramState;
import model.adt.IDictionary;
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

        Value expressionValue = this.expression.evaluate(symbolTable);

        if (!(expressionValue.getType().equals(new StringType()))){
            throw new WrongTypeException("The value is not a string type");
        }

        IDictionary<StringValue, BufferedReader> fileTable = programState.getFileTable();
        StringValue stringValue = (StringValue)expressionValue;

        if (!(fileTable.containsKey(stringValue))){
            throw new UndefinedVariableException("The value is not in the file table.");
        }

        BufferedReader bufferedReader = fileTable.lookUp(stringValue);
        try{
            bufferedReader.close();
            fileTable.remove(stringValue);
        }
        catch (IOException except){
            throw new MyException(except.getMessage());
        }


        return programState;
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
