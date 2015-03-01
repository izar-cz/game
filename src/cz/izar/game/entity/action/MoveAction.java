package cz.izar.game.entity.action;

import cz.izar.game.entity.Being;
import cz.izar.game.entity.Entity;
import cz.izar.game.map.Coordinates;
import cz.izar.game.map.Direction;
import cz.izar.game.map.OffsetCoordinates;
import cz.izar.game.mind.Intent;

public class MoveAction extends Action {

	private OffsetCoordinates offset;
	
	protected MoveAction(Entity actor, Intent intent) throws ActionException {
		super(actor, intent);
		if( null == getDirection() ) {
			throw new ActionException("MoveAction: direction is required.");
		}
	}
	
	@Override
	public void tick() {
		super.tick();
		if( 1 == ticksElapsed && actor instanceof Being ) {
			((Being)actor).setDirection(getDirection());
		}
		computeOffset();
		actor.setOffset( getOffset() );
	}
	
	private void computeOffset() {
		float factor = ticksElapsed < ticksBeforeEffect
				? (float)0.5 * (float)ticksElapsed / (float)ticksBeforeEffect
				: (float)0.5 * (float)(ticksElapsed - ticksTotal) / (float)(ticksTotal - ticksBeforeEffect);

		Direction dir = getDirection();
		offset = new OffsetCoordinates(factor * dir.dx(), factor * dir.dy());
	}

	@Override
	protected void doEffect() {
		Coordinates destination = actor.getLocation().in(
			getDirection()
		);
		if( actor.getEnvironment().getMap().isPassable( destination ) ) {
			actor.setLocation( destination );
		}
	}

	public OffsetCoordinates getOffset() {
		return offset;
	}

}
