package exception;

public class DefinedVariableException extends MyException {

    public DefinedVariableException(String variableName) {
        super("Variable " + variableName + " is already declared");
    }
}
