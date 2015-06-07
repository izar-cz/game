package cz.izar.game.entity;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import cz.izar.game.entity.manager.Blueprint;

public abstract class FeaturefulEntity extends Entity {

	public FeaturefulEntity(Blueprint blueprint, long uid) {
		super(blueprint, uid);
	}
	
	/*
	@Override
	public boolean getPassability() {
	}

	@Override
	public Presentation getPresentation() {
	}

	@Override
	public String getProp(int propType) {
	}

	@Override
	public void handleEvent(Event event) {
	}
	*/
}
