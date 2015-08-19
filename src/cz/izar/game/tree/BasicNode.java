package cz.izar.game.tree;

import cz.izar.game.event.Observable;
import cz.izar.game.event.ObservableAggregate;
import cz.izar.game.event.SimpleObservable;

public abstract class BasicNode extends Node implements ObservableAggregate {
	
	// implementation of Observable interface
	// TODO: lazy loading?
	private Observable observable = new SimpleObservable(this);
	
	@Override
	public Observable getObservable() {
		return observable;
	}
}
