package cz.izar.game.map;

import java.util.ArrayList;
import java.util.List;

import cz.izar.game.Target;
import cz.izar.game.entity.Entity;
import cz.izar.game.presentation.Presentation;

public class Tile implements Target {
	
	public static final int ENTITY_DENSITY = 4;
	
	
	private boolean passable = true;
	private final List<Entity> entities = new ArrayList<Entity>(ENTITY_DENSITY);
	private final TileType type;
	private final long uid;
	
	
	protected Tile( TileType type, long uid ) {
		this.passable = type.isPassable();
		this.type = type;
		this.uid = uid;
	}

	public boolean isPassable() {
		if( !passable ) {
//			System.err.println("IMPASSABLE");
			return false;
		}
		for( Entity entity : getEntities() ) {
			if( entity.getPassability() ) {
//				System.err.println("passable "+entity.toString() );
			} else {
//				System.err.println("Impassable "+entity.toString() );
				return false;
			}
		}
		return true;
	}

	protected TileType getType() {
		return type;
	}
	/**
	 * TODO: replace with identifier based on coordinates and map
	 * @deprecated
	 * @return uid of tile
	 */
	public long getUid() {
		return uid;
	}
	public long getTypeUid() {
		return getType().getUid();
	}
	@Override
	public String getTypeName() {
		return getType().getName();
	}
	public Presentation getPresentation() {
		return getType().getPresentation();
	}
	
	public List<Entity> getEntities() {
		return entities;
	}
	public void addEntity(Entity entity) {
		entities.add(entity);
	}
	public void removeEntity(Entity entity) {
		entities.remove(entity);
	}

	@Override
	public String toString() {
		return "Tile("+getUid()+", \""+type.getName()+"\")";
	}
}
