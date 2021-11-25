package exception;

public class VariableUndefinedException extends MyException {

    public VariableUndefinedException(String variableName) {
        super("Variable " + variableName + " is not defined.");
    }
}
