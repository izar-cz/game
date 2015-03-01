package cz.izar.game.utils;

public class UidManager {

	private long last;
	
	
	public UidManager() {
		last = 0;
	}


	public long next() {
		return ++last;
	}

}
