package co.coconut.api;

public class CoconutException extends Exception {
	public CoconutException(Exception e) {
		super(e);
	}

	private static final long serialVersionUID = 2359724979426966734L;
}
