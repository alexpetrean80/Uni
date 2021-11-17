package model.statement;

import exception.CustomException;
import exception.WrongTypeException;
import model.ProgramState;
import model.adt.IDictionary;
import model.expression.Expression;
import model.type.StringType;
import model.value.StringValue;
import model.value.Value;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class OpenRFileStatement implements Statement {

    private final Expression expression;

    public OpenRFileStatement(Expression expression) {
        this.expression = expression;
    }

    public Expression getFileName() {
        return expression;
    }


    @Override
    public ProgramState execute(ProgramState programState) {
        var fileTable = programState.getFileTable();
        var symbolTable = programState.getSymbolTable();

        var valueOfExpression = this.expression.evaluate(symbolTable);

        if (!(valueOfExpression.getType().equals(new StringType()))) {
            throw new WrongTypeException("This expression is not a string type.");
        }

        var stringValueOfExpression = (StringValue) valueOfExpression;
        if (fileTable.containsKey(stringValueOfExpression)) {
            throw new CustomException("File is already opened.");
        }

        try {
            var myReader = new BufferedReader(new FileReader(stringValueOfExpression.getValue()));
            fileTable.update(stringValueOfExpression, myReader);
        } catch (FileNotFoundException exception) {
            throw new CustomException("File not found!");
        }

        return programState;
    }

    @Override
    public Statement deepCopy() {
        return new OpenRFileStatement(this.expression);
    }

    @Override
    public String toString() {
        return "open ( " + this.expression + " )";
    }
}
