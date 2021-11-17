package model.statement;

import exception.CustomException;
import exception.UndefinedVariableException;
import exception.WrongTypeException;
import model.ProgramState;
import model.expression.Expression;
import model.type.StringType;
import model.value.StringValue;

import java.io.IOException;

public class CloseRFileStatement implements Statement {
    private final Expression expression;

    public CloseRFileStatement(Expression expression) {
        this.expression = expression;
    }

    @Override
    public ProgramState execute(ProgramState programState) {
        var symbolTable = programState.getSymbolTable();

        var expressionValue = this.expression.evaluate(symbolTable);

        if (!(expressionValue.getType().equals(new StringType()))) {
            throw new WrongTypeException("The value is not a string type");
        }

        var fileTable = programState.getFileTable();
        var stringValue = (StringValue) expressionValue;

        if (!(fileTable.containsKey(stringValue))) {
            throw new UndefinedVariableException("The value is not in the file table.");
        }
        var bufferedReader = fileTable.lookUp(stringValue);
        try {
            bufferedReader.close();
            fileTable.remove(stringValue);
        } catch (IOException except) {
            throw new CustomException(except.getMessage());
        }


        return programState;
    }

    @Override
    public Statement deepCopy() {
        return new CloseRFileStatement(this.expression.deepCopy());
    }

    @Override
    public String toString() {
        return "close " + this.expression.toString();
    }
}
