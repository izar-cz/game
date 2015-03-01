package cz.izar.game.map;


public class OffsetCoordinates {

	public static final OffsetCoordinates CENTER = new OffsetCoordinates(0f,0f);

	
	public final float x;
	public final float y;

	public OffsetCoordinates(float x, float y) {

//		assert x <= 0.5 && x >= -0.5;
//		assert y <= 0.5 && y >= -0.5;

		this.x = x;
		this.y = y;
	}

	public OffsetCoordinates add( OffsetCoordinates offset ) {
		return new OffsetCoordinates( x + offset.x, y + offset.y );
	}
}
