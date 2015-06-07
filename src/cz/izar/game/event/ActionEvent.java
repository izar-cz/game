package cz.izar.game.event;

import cz.izar.game.entity.action.Action;

public class ActionEvent extends Event {

	private final Action action;
	
	public ActionEvent(String type, Action action) {
		super(type);
		this.action = action;
	}

	public Action getAction() {
		return action;
	}

}
