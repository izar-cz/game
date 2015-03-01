package cz.izar.game;

import java.util.ArrayList;
import java.util.List;

import cz.izar.game.entity.Entity;
import cz.izar.game.entity.event.Event;
import cz.izar.game.map.Coordinates;
import cz.izar.game.map.Direction;
import cz.izar.game.map.Map;
import cz.izar.game.mind.WorldObservation;
import cz.izar.game.world.World;

public class Environment implements WorldObservation {
	

	
	private final World world;
	private Map map = null;
	private final List<Entity> entities = new ArrayList<Entity>();
	private long tick;

	
	public Environment( World world ) {
		assert world != null : "world == null";
		this.world = world;
	}
	
	public void placeEntity( Entity entity, Coordinates location ) {
		assert entity != null : "entity == null";
		assert location != null : "location == null";

		entity.setEnvironment( this );
		entities.add( entity );
		
		entity.setLocation( location );
	}


	
	public World getWorld() {
		return world;
	}

	public void setMap( Map map ) {
		if ( this.map != null ) {
			throw new IllegalStateException("Overriding Map for Environment is not allowed.");
		}
		this.map = map;
	}
	@Override
	public Map getMap() {
		return map;
	}

	@Override
	public List<? extends Entity> getEntities() {
		return entities;
	}

	@Override
	public long getTick() {
		return tick;
	}

	@Override
	public void tick() {
		++tick;
	}
	
	/**
	 * dispatch event to all entities in this environment
	 * @param event
	 */
	public void dispatch(Event event) {
		for (Entity entity : getEntities()) {
			entity.handle(event);
		}
	}


	//// STATIC methods
	
	public static Direction computeDirection( Entity from, Entity to ) {
		if ( from.getMap() != to.getMap() ) {
			throw new IllegalArgumentException("'from' and 'to' must be on the same Map");
		}
		return Direction.fromAngle( from.getLocation(), to.getLocation() );
	}
	public static boolean areAdjacent( Entity entity1, Entity entity2 ) {
		if ( entity1.getMap() != entity2.getMap() ) {
			throw new IllegalArgumentException("'entity1' and 'entity2' must be on the same Map");
		}
		return entity1.getLocation().isAdjacentTo( entity2.getLocation() );
	}

}
