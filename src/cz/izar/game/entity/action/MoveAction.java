package cz.izar.game.entity.action;

import cz.izar.game.Log;
import cz.izar.game.entity.Being;
import cz.izar.game.entity.Entity;
import cz.izar.game.map.Direction;
import cz.izar.game.map.GridIndexOutOfBoundsException;
import cz.izar.game.map.OffsetCoordinates;
import cz.izar.game.map.Tile;
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
		try {
			Tile destination = actor.getTile().in(getDirection());
			if ( destination.isPassable() ) {
				destination.appendChild(actor);
			}
		} catch (GridIndexOutOfBoundsException ex) {
			Log.warn(ex.getMessage());
		}
	}

	public OffsetCoordinates getOffset() {
		return offset;
	}

}
