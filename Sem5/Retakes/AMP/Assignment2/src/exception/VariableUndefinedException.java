package exception;

public class VariableUndefinedException extends CustomException {

    public VariableUndefinedException(String variableName){
        super("Variable " + variableName + " is not defined.");
    }
}
