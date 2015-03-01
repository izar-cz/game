package cz.izar.game.entity.action;

import cz.izar.game.Environment;
import cz.izar.game.entity.Entity;
import cz.izar.game.map.Coordinates;
import cz.izar.game.map.Direction;
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
		Coordinates actorDestination = getActor().getLocation().in(
			getDirection()
		);
		Coordinates targetDestination = getTarget().getLocation().in(
				getDirection()
		);
		if( getTarget().getEnvironment().getMap().isPassable( targetDestination ) ) {
			getTarget().setLocation( targetDestination );
		}
		if( getActor().getEnvironment().getMap().isPassable( actorDestination ) ) {
			getActor().setLocation( actorDestination );
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
