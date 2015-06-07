package cz.izar.game.entity;

import cz.izar.game.entity.manager.Blueprint;
import cz.izar.game.presentation.Presentation;


public class ComplexEntity extends FeaturefulEntity {

	private ComplexEntityCore core;
	
	public ComplexEntity(Blueprint blueprint, long uid, ComplexEntityCore core) {
		super(blueprint, uid);
		this.core = core;
	}

	@Override
	public boolean getPassability() {
		return core.getPassability();
	}

	@Override
	public Presentation getPresentation() {
		return core.getPresentation();
	}

	@Override
	public String getProp(int propType) {
		return core.getProp(propType);
	}

}
