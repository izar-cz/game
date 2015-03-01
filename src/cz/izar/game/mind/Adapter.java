package cz.izar.game.mind;

import cz.izar.game.Body;
import cz.izar.game.map.Coordinates;
import cz.izar.game.map.Direction;

public class Adapter implements BeingAdapter {

	private volatile long tick;
	
	private volatile Intent currentIntent = null;
	private volatile boolean forceIntent = false;
	private volatile boolean entityReady = true;


	private volatile WorldObservation perceivedWorld = null;
	private volatile Coordinates perceivedLocation = null;
	private volatile Direction perceivedDirection = Direction.NORTH;
	
//	private volatile boolean lock = false;
	
	
	// interface for engine/Entity end
	public synchronized Intent takeIntent() {
		if ( null != currentIntent ) {
			Intent intent = currentIntent;
			currentIntent = null;
			return intent;
		}
		return null;
	}

	public synchronized void setReady( boolean ready ) {
		entityReady = ready;
	}
	
//	public void setPerceivedWorld( World perceivedWorld ) {
//		this.perceivedWorld = perceivedWorld;
//	}
//	public synchronized void setCoordinates( Coordinates perceivedLocation ) {
//		this.perceivedLocation = perceivedLocation;
//	}
//	public synchronized void setDirection( Direction perceivedDirection ) {
//		this.perceivedDirection = perceivedDirection;
//	}
	public void setPercepts( WorldObservation perceivedWorld, Coordinates perceivedLocation, Direction perceivedDirection ) {
		
		assert null != perceivedDirection;
		assert null != perceivedLocation;
		assert null != perceivedWorld;
		
		
		this.perceivedLocation = perceivedLocation;
		this.perceivedWorld = perceivedWorld;
		this.perceivedDirection = perceivedDirection;
	}






	public long getTick() {
		return tick;
	}
	
	@Override
	public boolean isReady() {
		return entityReady;
	}

	@Override
	public WorldObservation getPerceivedWorld() {
		return perceivedWorld;
	}
	@Override
	public Coordinates getLocation() {
		return perceivedLocation;
	}

	@Override
	public Direction getDirection() {
		return perceivedDirection;
	}

	@Override
	public Body getBody() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setIntent(Intent intent) {
		forceIntent = false;
		currentIntent = intent;
	}

	@Override
	public void setImmediateIntent(Intent intent) {
		forceIntent = true;
		currentIntent = intent;
	}

}
