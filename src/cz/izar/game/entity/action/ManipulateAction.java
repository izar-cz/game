package cz.izar.game.entity.action;

import cz.izar.game.Environment;
import cz.izar.game.entity.Entity;
import cz.izar.game.event.ActionEvent;
import cz.izar.game.event.Event;
import cz.izar.game.mind.Intent;

public class ManipulateAction extends Action {

	public static enum Type {
		PUSH,
		PULL,
		KNOCK,
		OPEN,
		CLOSE
		
	}
	
	protected ManipulateAction(Entity actor, Intent intent)
			throws ActionException {

		super(actor, intent);
		if( ! (getTarget() instanceof Entity) ) {
			throw new ActionException("ManipulateAction: target is must be instance of Entity.");
		}
		if ( ! (getSubtype() instanceof Type) ) {
			throw new ActionException("ManipulateAction: subtype is required and must be instanceof ManipulateAction.Type.");
		}

	}
	
	@Override
	protected void doEffect() {
		System.err.println(
				"ManipulateAction EFFECT:"
				+ getSubtype().toString()
		);
		Entity target = (Entity)getTarget();
		if ( null != target ) {
			
			ActionEvent event = new ActionEvent("manipulate",this);

			Environment environment = actor.getEnvironment();
			environment.dispatch(event);

			if(!event.isDefaultPrevented()) {
				// TODO: default effect of manipulation
				event.setPhase(Event.Phase.POST_PHASE);
				environment.dispatch(event);
			}
		}
		
		
	}

	
	@Override
	public Type getSubtype() {
		return (Type)super.getSubtype();
	}

	@Override
	public Entity getTarget() {
		return (Entity)super.getTarget();
	}
}
