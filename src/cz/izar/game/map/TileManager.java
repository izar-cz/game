package cz.izar.game.map;

import java.util.Map;

import cz.izar.game.utils.UidManager;

public class TileManager {

	// TODO: remove this hash (tile uid shuld be generated based on map and coordinates)
	private final Map<Long,Tile> tiles = new java.util.HashMap<Long,Tile>();

	private final Map<Long,TileType> tileTypes = new java.util.HashMap<Long,TileType>();
	private final Map<String,TileType>  tileTypesByName = new java.util.HashMap<String,TileType>();

	private TileType defaultTileType = null;
	private final UidManager uidManager;


	public TileManager(UidManager uidManager) {
		this.uidManager = uidManager;
	}


	// Tile factory

	public Tile createTile(long typeUid) {
		return createTile( getType(typeUid) );
	}
	public Tile createTile(String typeName) {
		return createTile( getType(typeName) );
	}
	public Tile createDefaultTile() {
		return createTile( getDefaultTileType() );
	}
	private Tile createTile(TileType type) {
		if ( null != type ) {
			long uid = uidManager.next();
			Tile tile = type.createTile(uid);
			tiles.put(uid, tile);
			return tile;
		}
		return null;
	}


	public Tile getTile(long uid) {
		return tiles.get(uid);
	}

	// types manipulation

	protected TileType getType( long uid ) {
		return tileTypes.get(uid);
	}
	protected TileType getType( String name ) {
		return tileTypesByName.get(name);
	}
	protected TileType getDefaultTileType() {
		if( null == defaultTileType ) {
			defaultTileType = tileTypes.entrySet().iterator().next().getValue();
		}
		return defaultTileType;
	}

	public void setDefault(TileType type) {
		defaultTileType = type;
	}
	public long addType( TileType type ) {
		if ( tileTypesByName.containsKey(type.getName()) ) {
			System.err.println("Duplicate name '"+type.getName()+"' for tile type.");
		}
		long uid = type.getUid();
		tileTypes.put(uid, type);
		tileTypesByName.put(type.getName(), type);
		return uid;
	}

}
