package model.statement;

import exception.CustomException;
import exception.UndefinedVariableException;
import exception.WrongTypeException;
import model.ProgramState;
import model.adt.IDictionary;
import model.expression.Expression;
import model.type.IntType;
import model.type.StringType;
import model.value.IntValue;
import model.value.StringValue;
import model.value.Value;

import java.io.BufferedReader;
import java.io.IOException;

public class ReadFileStatement implements Statement {

    private final Expression expression;
    private final String variableName;

    public ReadFileStatement(Expression expression, String variableName) {
        this.expression = expression;
        this.variableName = variableName;
    }

    @Override
    public ProgramState execute(ProgramState programState) {
        var symbolTable = programState.getSymbolTable();

        if (!(symbolTable.containsKey(this.variableName))) {
            throw new UndefinedVariableException("Variable name is not defined.");
        }

        if (!(symbolTable.lookUp(this.variableName).getType().equals(new IntType()))) {
            throw new WrongTypeException("Variable it doesn't have int type.");
        }

        var value = this.expression.evaluate(symbolTable);
        if (!(value.getType().equals(new StringType()))) {
            throw new WrongTypeException("Variable is not a string type.");
        }

        var stringValue = (StringValue) value;

        var fileTable = programState.getFileTable();
        if (!(fileTable.containsKey(stringValue))) {
            throw new UndefinedVariableException("The string value is not in the file table.");
        }
        var bufferedReader = fileTable.lookUp(stringValue);
        try {
            var line = bufferedReader.readLine();
            var readValue = new IntValue(0);
            if (!(line == null)) {
                readValue = new IntValue(Integer.parseInt(line));
            }
            symbolTable.update(this.variableName, readValue);
        } catch (IOException except) {
            throw new CustomException(except.getMessage());
        }
        return programState;
    }

    @Override
    public Statement deepCopy() {
        return new ReadFileStatement(this.expression.deepCopy(), this.variableName);
    }

    @Override
    public String toString() {
        return "read form " + this.expression.toString() + " into " + this.variableName + ")";
    }
}
