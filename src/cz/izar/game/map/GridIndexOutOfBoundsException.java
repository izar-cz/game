package cz.izar.game.map;

public class GridIndexOutOfBoundsException extends IndexOutOfBoundsException {

	private static final long serialVersionUID = -4295010227519860845L;

	public GridIndexOutOfBoundsException(Throwable cause) {
		super("Grid location out of range");
		initCause(cause);
	}

	public GridIndexOutOfBoundsException(Throwable cause, Localized.Location location) {
		super("Grid location out of range: "+location.toString());
		initCause(cause);
	}
}
