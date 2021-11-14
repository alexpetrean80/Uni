package exception;

public class NotBooleanException extends CustomException {

	public NotBooleanException() {
		super("Conditional expression is not boolean.");
	}
}
