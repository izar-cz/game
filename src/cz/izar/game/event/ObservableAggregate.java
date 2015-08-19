package cz.izar.game.event;

public interface ObservableAggregate extends Observable {

	// should be protected
	public Observable getObservable();
	
	
	@Override
	public default boolean on(String type, Listener handler) {
		return getObservable().on(type, handler);
	}

	
	@Override
	public default boolean off(Listener handler) {
		return getObservable().off(handler);
	}

	@Override
	public default void off(String type) {
		getObservable().off(type);
	}

	@Override
	public default void off() {
		getObservable().off();
	}

	@Override
	public default void dispatch(Event event) {
		getObservable().dispatch(event);
	}
}
