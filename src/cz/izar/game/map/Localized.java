package cz.izar.game.map;

public interface Localized<T extends Localized.Location> {
	public T getLocation();
	public void setLocation(T location);
	
	public interface Location {}
}
