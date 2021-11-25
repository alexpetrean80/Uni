package exception;

public class WrongTypeAssignedException extends MyException {

    public WrongTypeAssignedException(String variableName) {
        super("Declared type of variable" + variableName +
                "and the type of the assigned expression does not match.");
    }
}
