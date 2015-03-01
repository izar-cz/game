package cz.izar.game.entity;


import cz.izar.game.Environment;
import cz.izar.game.Log;
import cz.izar.game.Target;
import cz.izar.game.entity.event.EventHandler;
import cz.izar.game.entity.manager.Blueprint;
import cz.izar.game.map.Coordinates;
import cz.izar.game.map.Map;
import cz.izar.game.map.OffsetCoordinates;
import cz.izar.game.presentation.Presentation;


public abstract class Entity implements Target, EventHandler {
	
	public static final int PROP_NAME = 1;
	public static final int PROP_DESCRIPTION = 2;

	
	public static final int PROP_IS_ITEM = 20;
	public static final int PROP_MASS = 21;
	public static final int PROP_DIAMETER = 22;
	public static final int PROP_VOLUME = 23;



	private Coordinates location = null;
	private OffsetCoordinates offset = OffsetCoordinates.CENTER;
	private Environment environment = null;
	private final long uid;

	/**
	 * blueprint (~class) of entity
	 */
	protected final Blueprint blueprint;


	public Entity(Blueprint blueprint, long uid) {
		this.uid = uid;
		this.blueprint = blueprint;
	}
	protected Blueprint getBlueprint() {
		return blueprint;
	}
	public long getTypeUid() {
		return getBlueprint().getUid();
	}
	@Override
	public String getTypeName() {
		return getBlueprint().getId();
	}
	
	public final long getUid() {
		return uid;
	}

	/**
	 * TODO: crate concept of non-boolean passability (depending on direction etc.)
	 * @return
	 */
	abstract public boolean getPassability();
	
	/**
	 * information how to present this entity to player
	 * @return
	 */
	abstract public Presentation getPresentation();


	/**
	 * @param propType should be one of PROP_* constants
	 * @return value for requested property
	 */
	abstract public String getProp(int propType);



	@Override
	public String toString() {
		return getProp(PROP_NAME) + " ["+getUid()+"]";
	}



	// FINAL methods

	public final void setEnvironment(Environment environment) {
		this.environment = environment;
	}
	public final Environment getEnvironment() {
		return environment;
	}
	public final Map getMap() {
		return environment.getMap();
	}

	/**
	 * change position of this entity on it's map
	 * called e.g. when entity walks
	 * @see cz.izar.game.entity.action.MoveAction
	 * @param location new location of entity
	 */
	public final void setLocation( Coordinates location ) {
		assert location != null : "location == null";
		if( null != getMap() ) {
			if( null != this.location ) {
				getMap().getAt(this.location).removeEntity(this); // remove entity from last Tile
			}
			getMap().getAt(location).addEntity(this); // place entity to target Tile
		} else {
			Log.warn("setting Location for an Entity without a Map ("+toString()+")");
		}
		this.location = location;
	}
	public final void unsetLocation() {
		if( null != location && null != getMap() ) {
			getMap().getAt(location).removeEntity(this); // remove entity from last Tile
		}
		location = null;
	}
	public final Coordinates getLocation() {
		return location;
	}
	
	public final OffsetCoordinates getOffset() {
		return offset;
	}
	public final void setOffset(OffsetCoordinates offset) {
		this.offset = offset;
	}

}
