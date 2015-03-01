package cz.izar.game.entity.action;

import cz.izar.game.Environment;
import cz.izar.game.Target;
import cz.izar.game.entity.Being;
import cz.izar.game.entity.Entity;
import cz.izar.game.entity.FeaturefulEntity;
import cz.izar.game.entity.event.ActionEvent;
import cz.izar.game.entity.event.Event;
import cz.izar.game.mind.Intent;

public class GraspAction extends Action {

	private static final short DEFAULT_DURATION = 7;
	
	protected GraspAction(Entity actor, Intent intent) throws ActionException {
		super(actor, intent);
		if (!(actor instanceof FeaturefulEntity)) {
			throw new ActionException("GraspAction: actor must be instanceof FeaturefulEntity.");
		}
		Target target = getTarget();
		if (null == target) {
			throw new ActionException("GraspAction: target is required.");
		}
		if (!(target instanceof Entity)) {
			throw new ActionException("GraspAction: target must be Entity.");
		}
	}


	@Override
	public FeaturefulEntity getActor() {
		return (FeaturefulEntity)actor;
	}

	/*
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
*/
	@Override
	protected short _getTotalDuration() {
		return DEFAULT_DURATION;
	}
	@Override
	protected void doEffect() {
		Entity item = (Entity)getTarget();
		/*
			if (entity.item) {
				
			}
		
		
		 */
		if (item instanceof Being) {
			// TODO: allow taking beings (currently, game breaks when a being is in any inventory)
			return;
		}
		if (Environment.areAdjacent(actor, item)) {
			ActionEvent event = new ActionEvent("grasp",this);

			Environment environment = actor.getEnvironment();
			environment.dispatch(event);

			if(!event.isDefaultPrevented()) {
				getActor().insertItem( item, null );
				event.setPhase(Event.Phase.POST_PHASE);
				environment.dispatch(event);
			}

		}
	}

}
