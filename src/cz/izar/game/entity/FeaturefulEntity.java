package cz.izar.game.entity;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import cz.izar.game.entity.manager.Blueprint;

public abstract class FeaturefulEntity extends Entity {

	private List<Feature> features = null;
	private List<Entity> inventory = null;
	
	public FeaturefulEntity(Blueprint blueprint, long uid) {
		super(blueprint, uid);
		features = Collections.<Feature>emptyList();
		inventory = new LinkedList<Entity>();
	}


	public List<Entity> getInventory()
	{
		return Collections.unmodifiableList(inventory);
	}
	public List<Feature> getFeatures()
	{
		return features;
	}
	
	public void insertItem(Entity item, Feature target)
	{
		// ? set item's map to container's map
		item.unsetLocation();
		inventory.add(item);
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
