package cz.izar.game.sample;

import cz.izar.game.entity.Entity;
import cz.izar.game.map.Coordinates;
import cz.izar.game.map.Direction;
import cz.izar.game.map.Map;
import cz.izar.game.map.Tile;
import cz.izar.game.mind.Adapter;
import cz.izar.game.mind.Intent;
import cz.izar.game.mind.Mind;
import cz.izar.game.mind.WorldObservation;

public class DummyMind implements Mind, Runnable {

	private static int sequence = 0;
	
	private Adapter adapter;
	private int number;
	
	public DummyMind() {
		number = sequence++;
	}

	@Override
	public void setAdapter(Adapter adapter) {
		this.adapter = adapter;
	}


	@Override
	public void run() {
		while ( true ) {
			if( null != adapter ) {
				think();
			}
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {}
		}
	}


	public void think() {
		WorldObservation perceivedWorld = adapter.getPerceivedWorld();
		if( adapter.isReady() ) {
//			long tick = adapter.getTick();

			if ( null != perceivedWorld ) {
				Intent intent = null;
				Map map = adapter.getPerceivedWorld().getMap();
				Coordinates location = adapter.getLocation();

				Direction direction = adapter.getDirection();
				
				
				assert null != direction;
				if( null != direction ) {
					direction = direction.randomTurn();
				} else {
					direction = Direction.getRandom();
				}

				Coordinates targetLocation = location.in(direction);
				try {
					Tile tile = map.getAt(targetLocation);
					
					if ( tile.isPassable() ) {
						intent = Intent.walk(direction);
					} else {
						Entity targetEntity = tile.getEntities().get(0);
						if( null != targetEntity ) {
							if( number > 1 ) {
								intent = Intent.push(targetEntity, direction);
							} else {
//								intent = Intent.manipulate(targetEntity, ManipulateAction.Type.KNOCK );
								intent = Intent.grasp(targetEntity);
							}
						}
					}
				} catch (IndexOutOfBoundsException ex ) {}

				adapter.setIntent( intent );
			}
		}
	}



}
