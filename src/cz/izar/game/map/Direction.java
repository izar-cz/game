package cz.izar.game.map;

import java.util.Random;


public enum Direction {
	NORTH(0,-1),
	NORTHWEST(-1,-1),
	WEST(-1,0),
	SOUTHWEST(-1,1),
	SOUTH(0,1),
	SOUTHEAST(1,1),
	EAST(1,0),
	NORTHEAST(1,-1);
	
	private final int dx;
	private final int dy;
	Direction(int dx, int dy) {
		this.dx = dx;
		this.dy = dy;
	}
	
	public int dx() {
		return dx;
	}
	public int dy() {
		return dy;
	}
	public boolean isDiagonal() {
		return (dx != 0 && dy != 0);
	}
	
	public int graphicsIndex() {
		switch (this) {
		case NORTH:
			return 0;
		case EAST:
		case NORTHEAST:
		case SOUTHEAST:
			return 3;
		case SOUTH:
			return 2;
		case WEST:
		case NORTHWEST:
		case SOUTHWEST:
			return 1;
		}
		return 0;
	}
	

	public static Direction getRandom() {
		Random random = new Random();
		int count = Direction.values().length;
		return Direction.values()[random.nextInt(count)];
	}
	public Direction randomTurn() {
		Random random = new Random();
		int dieResult = random.nextInt(20);
		if ( dieResult >= 12 ) {
			return this; // no change
		}
		if ( dieResult >= 10 ) {
			return getRandom();
		}
		int index = this.ordinal() + 1 - (2*dieResult % 2);
		return Direction.values()[ index % Direction.values().length ];
	}
	public static Direction fromInt( int number ) {
		int count = Direction.values().length;
		return Direction.values()[ number % count ];
	}
	
	public static Direction adjacentVector(Coordinates from, Coordinates to) {
		return null;
	}

	
	private static Direction fromAngle(double d) {
		int index = (int)Math.round( 14 - ( d * 4 / Math.PI ) ) % 8;
		return Direction.values()[ index ];
	}
	public static Direction fromAngle(int dx, int dy) {
		if ( 0 == dx && 0 == dy ) {
			return null;
		}
		return fromAngle( Math.atan2(dy, dx));
	}
	public static Direction fromAngle(Coordinates from, Coordinates to) {
		return Direction.fromAngle( to.x - from.x, to.y - from.y );
	}
}
