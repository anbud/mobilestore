package exceptions;

public class NotRegisteredException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public NotRegisteredException() {
		super("You aren't registered!");
	}
}
