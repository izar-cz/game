package cz.izar.game.map;

import cz.izar.game.Environment;


public class Map {

	private final Environment environment;
	private final int width;
	private final int height;
	protected final Tile[][] tiles;

	public Map( Environment environment, int width, int height ) {
		this.environment = environment;
		this.width = width;
		this.height = height;
		tiles = new Tile[width][height];
	}

	public Tile getAt(int x, int y) throws IndexOutOfBoundsException {
		Tile tile = tiles[x][y];
//		if ( null == tile ) {
//			tiles[x][y] = tile = this.createDefaultTile();
//		}
		assert tile != null : "null map tile at "+x+", "+y;
		return tile;
	}

	public Tile getAt(Coordinates coords) {
		assert null != coords;
		return getAt(coords.x, coords.y);
	}
	public void setAt(int x, int y, Tile tile) throws IndexOutOfBoundsException {
		assert tile != null : "null map tile";
		tiles[x][y] = tile;
	}
	
	protected Tile createDefaultTile() {
		return getTileManager().createDefaultTile();
	}
	
	/**
	 * convenience method
	 * @return 
	 */
	protected TileManager getTileManager() {
		return getEnvironment().getWorld().getTileManager();
	}
	



	public boolean isPassable( Coordinates coords ) {
		try {
			return getAt( coords ).isPassable();			
		} catch ( ArrayIndexOutOfBoundsException ex ) {
			return false;
		}
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
	public Environment getEnvironment() {
		return environment;
	}
	
	


}
