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
import java.io.FileNotFoundException;
import java.io.FileReader;

public class OpenRFileStatement implements Statement{

    private final Expression expression;

    public OpenRFileStatement(Expression expression){
        this.expression = expression;
    }

    public Expression getFileName() {
        return expression;
    }


    @Override
    public ProgramState execute(ProgramState programState){
        Dict<StringValue, BufferedReader> fileTable = programState.getFileTable();
        Dict<String, Value> symbolTable = programState.getSymbolTable();
        Heap<Value> heap = programState.getHeap();

        Value valueOfExpression = this.expression.evaluate(symbolTable, heap);

        if (!(valueOfExpression.getType().equals(new StringType ()))){
            throw  new MyException("This expression is not a string type.");
        }

        synchronized (programState.getFileTable()) {
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
        }
        return null;
    }

    @Override
    public Statement deepCopy() {
        return new OpenRFileStatement(this.expression);
    }

    @Override
    public Dict<String, Type> typeCheck(Dict<String, Type> typeEnvironment) {
        if (!(this.expression.typeCheck(typeEnvironment).equals(new StringType()))){
            throw new TypeCheckException("Expression is not a string!");
        }

        return typeEnvironment;
    }

    @Override
    public String toString(){
        return "open ( " + this.expression + " )";
    }
}
