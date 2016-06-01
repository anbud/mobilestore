package exceptions;

public class UsernameExistsException extends Exception {
	
	private static final long serialVersionUID = 1L;
	
	public UsernameExistsException() {
		super("Username already taken.");
	}
}
