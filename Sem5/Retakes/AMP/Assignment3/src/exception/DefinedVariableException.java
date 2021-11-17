package exception;

public class DefinedVariableException extends CustomException {
    public DefinedVariableException(String variableName){
        super("Variable " + variableName + " is already declared");
    }
}
