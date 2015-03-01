package cz.izar.game.mind;

import cz.izar.game.Body;
import cz.izar.game.map.Coordinates;
import cz.izar.game.map.Direction;

/**
 * interface for artificial intelligence
 * @author izar
 *
 */
public interface BeingAdapter {

	/**
	 * returns true, iff there is no intended action i progress
	 * @return
	 */
	public boolean isReady();


	/**
	 * @return representation of seen world
	 */
	public WorldObservation getPerceivedWorld();

	
	/**
	 * @return the location of entity
	 */
	public Coordinates getLocation();


	/**
	 * @return the direction the entity is facing
	 */
	public Direction getDirection();


	/**
	 * @return representation of physical body of the entity
	 */
	public Body getBody();


	
	/**
	 * sets intent of entity. 
	 * 
	 * The intent would be queued as soon as the entity is ready.
	 * @param intent
	 */
	public void setIntent( Intent intent );
	/**
	 * sets intent of entity.
	 * The intent would be queued immediately, interrupting any intent in progress.
	 * @param intent
	 */
	public void setImmediateIntent( Intent intent );
}
