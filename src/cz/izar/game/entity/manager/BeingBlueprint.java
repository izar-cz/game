package cz.izar.game.entity.manager;

import cz.izar.game.entity.Being;
import cz.izar.game.entity.Entity;
import cz.izar.game.sample.DummyMind;

public class BeingBlueprint extends ComplexBlueprint {

	public BeingBlueprint(long uid, String id, ComplexBlueprintCore core) {
		super(uid, id, core);
	}

	@Override
	public Entity createEntity(long uid) {
		Being being = new Being(this, uid, core.createCore());
		being.setMind( new DummyMind() );
		return being;
	}

}
