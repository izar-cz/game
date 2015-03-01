package cz.izar.game.entity;

import java.util.Random;

import cz.izar.game.Body;
import cz.izar.game.entity.action.Action;
import cz.izar.game.entity.action.ActionFactory;
import cz.izar.game.entity.manager.Blueprint;
import cz.izar.game.map.Direction;
import cz.izar.game.mind.Adapter;
import cz.izar.game.mind.Intent;
import cz.izar.game.mind.Mind;
import cz.izar.game.mind.WorldObservation;

public class Being extends ComplexEntity implements Consious, Perceptive {

	private Body body = null;
	private Direction direction = Direction.NORTH;
	
	private ActionFactory actionFactory;
	private Action currentAction = null;
	
	private Adapter mindAdapter;
	
	protected Random random = new Random();

	public Being(Blueprint blueprint, long uid, ComplexEntityCore core) {
		super(blueprint, uid, core);
		mindAdapter = new Adapter();
		actionFactory = new ActionFactory( this );
	}


	
	@Override
	public void setMind( Mind mind ) {
		mind.setAdapter( mindAdapter );
		
		if ( mind instanceof Runnable ) {
			Thread thread = new Thread( (Runnable)mind );
			thread.start();
		}
		
//		mind.setOwnEntity( this );
//		this.mind = mind;
	}



	
	
	/**
	 * execution phase
	 */
	@Override
	public void behave() {
		checkIntent();
		if( null != currentAction ) {
			currentAction.tick();
			if( currentAction.isCompleted() ) {
				currentAction = null;
				mindAdapter.setReady(true);
			}
		}
	}
	private void checkIntent() {
		if( currentAction != null ) {
			return;
		}
		Intent intent = mindAdapter.takeIntent();
		if( null != intent ) {
			currentAction = actionFactory.createAction(intent);
			if( null != currentAction ) {
				mindAdapter.setReady(false);
			}
		}
	}

	@Override
	public void preceive() {
		// TODO: don't rely on World implementing WorldObservation; create observations properly
		WorldObservation worldObservation = (WorldObservation)getEnvironment();
		mindAdapter.setPercepts(worldObservation, getLocation(), getDirection());
	}
	
	public void setDirection( Direction direction ) {
		this.direction = direction;
	}
	
	public Direction getDirection() {
		return direction;
	}
	public Action getAction() {
		return currentAction;
	}

	public Body getBody() {
		return body;
	}

	public void setBody(Body body) {
		this.body = body;
	}
	
	
	
	@Override
	public String toString() {
		String result = super.toString();
		if( null != direction ) {
			result += "\n "+direction.toString();
		}
		if( null != currentAction ) {
			result += "\n "+currentAction.getIntent().toString();
		}
		return result;
	}

}
