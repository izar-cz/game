package cz.izar.game.entity.action;

import cz.izar.game.Environment;
import cz.izar.game.Log;
import cz.izar.game.entity.Entity;
import cz.izar.game.map.Direction;
import cz.izar.game.map.GridIndexOutOfBoundsException;
import cz.izar.game.map.Tile;
import cz.izar.game.mind.Intent;

public class MoveObjectAction extends MoveAction {

	private Direction objectDirection;
	
	protected MoveObjectAction(Entity actor, Intent intent) throws ActionException {
		super(actor, intent);
		if( ! (getTarget() instanceof Entity) ) {
			throw new ActionException("ManipulateAction: target is must be instance of Entity.");
		}
		if( null == getTarget() ) {
			throw new ActionException("MoveObjectAction: target is requiered.");
		}
		objectDirection = Environment.computeDirection(getActor(), getTarget());
	}
	
	
	@Override
	public void tick() {
		super.tick();
		getTarget().setOffset( getOffset() );
	}
	
	@Override
	protected void doEffect() {
		Entity target = (Entity)getTarget();
		try {
			Tile actorDestination = actor.getTile().in(getDirection());
			Tile targetDestination = target.getTile().in(getDirection());
			
			if ( targetDestination.isPassable() ) {
				targetDestination.appendChild(target);
			}
			if ( actorDestination.isPassable() ) {
				actorDestination.appendChild(actor);
			}
		} catch (GridIndexOutOfBoundsException ex) {
			Log.warn(ex.getMessage());
		}

	}
	
	public Direction getObjectDirection() {
		return objectDirection;
	}

	@Override
	public Entity getTarget() {
		return (Entity)super.getTarget();
	}
}
