package cz.izar.game.entity.manager;

import cz.izar.game.entity.ComplexEntity;
import cz.izar.game.entity.Entity;

public class ComplexBlueprint extends Blueprint {
	
	protected ComplexBlueprintCore core;

	public ComplexBlueprint(long uid, String id, ComplexBlueprintCore core) {
		super(uid, id);
		this.core = core;
	}


	@Override
	public Entity createEntity(long uid) {
		return new ComplexEntity(this, uid, core.createCore());
	}
}
