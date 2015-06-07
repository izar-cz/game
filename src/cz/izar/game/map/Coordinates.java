package cz.izar.game.map;


public class Coordinates implements Localized.Location {
	public final int x;
	public final int y;
	
	public Coordinates(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public Coordinates in( Direction dir ) {
		return new Coordinates( x + dir.dx(), y + dir.dy() );
	}
	
	@Override
	public String toString() {
		return "coordinates("+x+","+y+")";
	}

	
	
	public boolean isAdjacentTo(Coordinates target) {
		int dx = target.x - x;
		int dy = target.y - y;
		return -1 <= dx && dx <= 1 && -1 <= dy && dy <= 1;
	}
	
}
