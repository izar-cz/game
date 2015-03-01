package cz.izar.game.entity.action;

import cz.izar.game.Target;
import cz.izar.game.entity.Entity;
import cz.izar.game.map.Direction;
import cz.izar.game.mind.Intent;

public class Action {

	private static final short DEFAULT_DURATION = 10;

	protected final Intent intent;
	protected final Entity actor;
	
	// all this properties are optional (i.e. null value is permitted)
	protected final Target target;
	protected final Entity tool;
	
	protected final short ticksTotal;
	protected final short ticksBeforeEffect;
	
	protected short ticksElapsed = 0;
	private boolean completed = false;
	

	/**
	 * @param actor
	 * @param intent
	 * @throws ActionException when intent doesn't contain appropriate information for given action type (i.e. direction is null)
	 */
	protected Action( Entity actor, Intent intent ) throws ActionException {
		assert null != intent;
		assert null != actor;

		// TODO: at this point should be conversion from entity representation to actual entity
		this.target = intent.getTarget();
		this.tool = intent.getTool();
//		this.subtype = intent.getSubtype();
//		this.additionalData  = intent.getAdditionalData();

		this.actor = actor;
		this.intent = intent;

		short duration = _getTotalDuration();
		assert duration > 0;
		this.ticksTotal = duration;
		this.ticksBeforeEffect = duration > 2
			? (short)(duration / 2)
			: (short)1;
	}

	/**
	 * @return subject executing the action
	 */
	public Entity getActor() {
		return actor;
	}

	/**
	 * @return primary target of action (Tile, Entity, entity Feature)
	 */
	public Target getTarget() {
		return target;
	}
	
	/**
	 * @return Entity used as tool or secondary target of the action
	 */
	public Entity getTool() {
		return tool;
	}
	
	/**
	 * @return direction of action (as "target" of the action)
	 */
	public Direction getDirection() {
		return intent.getDirection();
	}
	
	public Enum<?> getSubtype() {
		return intent.getSubtype();
	}

	protected void doEffect() {
	}

	public void tick() {
		if ( ticksElapsed < ticksTotal ) {
			++ticksElapsed;
			if ( ticksBeforeEffect == ticksElapsed ) {
				doEffect();
			}
			if ( ticksElapsed == ticksTotal ) {
				setCompleted();
			}
		}
	}
	
	protected short _getTotalDuration() {
		return DEFAULT_DURATION;
	}
	
	protected void setCompleted() {
		completed = true;
	}
	
	public boolean isCompleted() {
		return completed;
	}
	public float getProgress() {
		 return (float)ticksElapsed / (float)ticksTotal;
	}
	public Intent getIntent() {
		return intent;
	}
	
	@Override
	public String toString() {
		return "Action ("+getIntent()+")"+(isCompleted()?"(completed)":"");
	}

}
