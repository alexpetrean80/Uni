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
import java.io.FileNotFoundException;
import java.io.FileReader;

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
        IDictionary<StringValue, BufferedReader> fileTable = programState.getFileTable();
        IDictionary<String, Value> symbolTable = programState.getSymbolTable();
        IHeap<Value> heap = programState.getHeap();

        Value valueOfExpression = this.expression.evaluate(symbolTable, heap);

        if (!(valueOfExpression.getType().equals(new StringType()))) {
            throw new MyException("This expression is not a string type.");
        }

        StringValue stringValueOfExpression = (StringValue) valueOfExpression;
        if (fileTable.containsKey(stringValueOfExpression)) {
            throw new MyException("File is already opened.");
        }

        try {
            BufferedReader myReader = new BufferedReader(new FileReader(stringValueOfExpression.getValue()));
            fileTable.update(stringValueOfExpression, myReader);
        } catch (FileNotFoundException exception) {
            throw new MyException("File not found!");
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
