package cz.izar.game.event;

public interface Observable {
	/**
	 * add listeners
	 * @param type
	 * @param handler
	 * @return
	 */
	public boolean on(String type, Listener handler);
	
	/**
	 * remove specific listener
	 * @param handler
	 * @return
	 */
	public boolean off(Listener handler);
	
	/**
	 * remove listeners by type
	 * @param type
	 */
	public void off(String type);
	
	/**
	 * remove all listeners
	 */
	public void off();

	/**
	 * dispatch event (notify all appropriate listeners)
	 * @param event
	 */
	public void dispatch(Event event);
}
