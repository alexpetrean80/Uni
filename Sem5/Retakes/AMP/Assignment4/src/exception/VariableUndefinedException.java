package exception;

import model.statement.VariableDeclaration;

public class VariableUndefinedException extends MyException{

    public VariableUndefinedException(String variableName){
        super("Variable " + variableName + " is not defined.");
    }
}
