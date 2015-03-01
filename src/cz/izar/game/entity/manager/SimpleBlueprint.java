package cz.izar.game.entity.manager;

import cz.izar.game.entity.Entity;
import cz.izar.game.entity.SimpleEntity;
import cz.izar.game.presentation.Presentation;

public class SimpleBlueprint extends Blueprint {

	private final boolean passability;
	private final String entityName;
	private Presentation presentation;
	
	public SimpleBlueprint(long uid, String id, String entityName, boolean passability ) {
		super(uid, id);
		this.entityName = entityName;
		this.passability = passability;
	}

	@Override
	public Entity createEntity(long uid) {
		return new SimpleEntity(this, uid);
	}


	public boolean getPassability() {
		return passability;
	}
	public Presentation getPresentation() {
		return presentation;
	}
	public void setPresentation(Presentation presentation) {
		this.presentation = presentation;
	}
	public String getProp(int propType) {
		switch (propType) {
		case Entity.PROP_NAME:
			return entityName;
		}
		return null;
	}


}
