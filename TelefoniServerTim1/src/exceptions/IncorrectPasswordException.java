package exceptions;

public class IncorrectPasswordException extends Exception {

	private static final long serialVersionUID = 1L;

	public IncorrectPasswordException() {
		super("Password is incorrect!");
	}
}
