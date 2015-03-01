package cz.izar.game.entity.action;

import cz.izar.game.entity.Entity;
import cz.izar.game.mind.Intent;

public class ActionFactory {
	private Entity actor;
	
	public ActionFactory(Entity actor) {
		this.actor = actor;
	}

	public Action createAction( Intent intent ) {
		try {
			switch ( intent.getType() ) {
			case MOVE:
				return new MoveAction( actor, intent );
			case MOVE_OBJECT:
				return new MoveObjectAction( actor, intent );
			case MANIPULATE:
				return new ManipulateAction(actor, intent);
			case GRASP:
				return new GraspAction(actor, intent);
			default:
				return new Action(actor, intent);
			}
		} catch (ActionException ex) {
			// An error occurred during action creation.
			// Probably the intent contains inconsistent data (e.g. trying to walk in null direction)
			System.err.println( ex.getMessage() );
		}
		return null;
	}
}
