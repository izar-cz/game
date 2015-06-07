package cz.izar.game.entity;

import cz.izar.game.entity.manager.SimpleBlueprint;
import cz.izar.game.event.Event;
import cz.izar.game.presentation.Presentation;


/**
 * simple stateless entity
 * @author izar
 *
 */
public class SimpleEntity extends FeaturefulEntity {


	public SimpleEntity( SimpleBlueprint blueprint, long uid ) {
		super(blueprint, uid);
	}
	
	@Override
	protected SimpleBlueprint getBlueprint() {
		return (SimpleBlueprint)blueprint;
//		return (SimpleBlueprint)super.getBlueprint();
	}
	
	@Override
	public boolean getPassability() {
		return getBlueprint().getPassability();
	}

	@Override
	public Presentation getPresentation() {
		return getBlueprint().getPresentation();
	}

	@Override
	public String getProp(int propType) {
		return getBlueprint().getProp(propType);
	}



}
