package cz.izar.game;


import java.util.List;
//import cz.izar.game.mind.Intent;






import cz.izar.game.entity.Active;
import cz.izar.game.entity.Entity;
import cz.izar.game.entity.Perceptive;
import cz.izar.game.loader.WorldLoader;
import cz.izar.game.world.World;

public class Engine {
	
	private Application application;
//	private Environment environment;
	private World world;
	private boolean worldLoaded = false;
//	private long tick = 0;


	public Engine( Application application ) {
		this.application = application;
	}

	public void gameLoop() {
//		long lastLoopTime = System.currentTimeMillis();
		if ( ! worldLoaded ) {
			throw new IllegalStateException("Engine doesn't have loaded world.");
		}
		while ( true ) {
			
			// TODO: take care of all environments
			Environment environment = world.getChildNodes().iterator().next();

			environment.tick();
//			long delta = System.currentTimeMillis() - lastLoopTime;
//			lastLoopTime = System.currentTimeMillis();

			List<? extends Entity> entities = environment.getEntities();
			Entity entity;
			
			int numberOfEntities = entities.size();
			
//			// thinking phase
//			for (int i=0 ; i<numberOfEntities ; i++) {
//				entity = entities.get(i);
//				if( entity instanceof Consious ) {
//					((Consious)entity).think( world.getTick() );
//				}
//			}

			// execution phase
			for (int i=0 ; i<numberOfEntities ; i++) {
				entity = (Entity)entities.get(i);
				if ( entity instanceof Active ) {
					((Active)entity).behave();
				}
			}

			// perception phase
			for (int i=0 ; i<numberOfEntities ; i++) {
				entity = (Entity)entities.get(i);
				if ( entity instanceof Perceptive ) {
					((Perceptive)entity).preceive();
				}
			}

			// drawing phase
			application.draw( environment );

			try { Thread.sleep(100); } catch (Exception e) {}
		}
	}

	public void load(WorldLoader worldLoader) {
		world = worldLoader.load();
		worldLoaded = true;
		
//		environment = new SampleWorld();

	}

//	public World getWorld() {
//		return world;
//	}


}
