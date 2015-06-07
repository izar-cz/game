package cz.izar.game;

import java.util.ArrayList;
import java.util.List;

import cz.izar.game.entity.Entity;
import cz.izar.game.map.Coordinates;
import cz.izar.game.map.Direction;
import cz.izar.game.map.Tile;
import cz.izar.game.mind.WorldObservation;
import cz.izar.game.tree.GridNode;
import cz.izar.game.tree.Node;
import cz.izar.game.world.World;

public class Environment extends GridNode<Tile> implements WorldObservation {

	
	// TODO: currently, an entity is never removed from the list
	private final List<Entity> entities = new ArrayList<Entity>();
	private long tick;

	
	public Environment(int width, int height ) {
		super(width, height);
	}
	
	public void placeEntity( Entity entity, Coordinates location ) {
		assert entity != null : "entity == null";
		assert location != null : "location == null";

		Tile tile = getNodeAt(location);
		tile.appendChild(entity);
		entities.add(entity);
	}


	
	public World getWorld() {
		Node node = this;
		do {
			node = node.getParent();
		} while ( !(node instanceof World) && null != node );
		return (World)node;
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

	@Override
	protected void removeChild(Node node) {
		if(node instanceof Tile) {
			removeGridItem((Tile)node);
		}
	}
	
	@Override
	protected void setParent(Node parent) {
		if (!(parent instanceof World)) {
			throw new IllegalArgumentException("Enviroment have to be placed in World");
		}
		super.setParent(parent);
	}

	@Override
	public String getTypeName() {
		return "ENVIRONMENT";
	}

	//// STATIC methods
	
	public static Direction computeDirection( Entity from, Entity to ) {
		if ( from.getEnvironment() != to.getEnvironment() ) {
			throw new IllegalArgumentException("'from' and 'to' must be on the same Environment");
		}
		return Direction.fromAngle( from.getLocation(), to.getLocation() );
	}
	public static boolean areAdjacent( Entity entity1, Entity entity2 ) {
		if ( entity1.getEnvironment() != entity2.getEnvironment() ) {
			throw new IllegalArgumentException("'entity1' and 'entity2' must be on the same Environment");
		}
		return entity1.getLocation().isAdjacentTo( entity2.getLocation() );
	}

}
