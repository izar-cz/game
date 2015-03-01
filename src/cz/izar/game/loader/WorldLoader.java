package cz.izar.game.loader;

import cz.izar.game.entity.manager.EntityManager;
import cz.izar.game.js.JsEngine;
import cz.izar.game.map.TileManager;
import cz.izar.game.utils.UidManager;
import cz.izar.game.world.World;

public class WorldLoader extends Loader {
	
	UidManager uidManager = null;
	
	
	public WorldLoader() {
		uidManager = new UidManager();
	}


	public World load() {

		// initialize resource managers
		TileManager tileManager = new TileManager( uidManager );
		EntityManager entityManager = new EntityManager( uidManager );

		// base world object
		World world = new World();

		
		// start JS engine
		JsEngine jsEngine = new JsEngine();
		jsEngine.setJavaObjects(world, tileManager, entityManager, uidManager);
		jsEngine.start();
		setJsEngine( jsEngine );




		// prepare tileManager
		getJsEngine().evaluateResources("tiles");
		world.setTileManager( tileManager );

		// prepare entityManager
		getJsEngine().evaluateResources("objects");
		world.setEntityManager( entityManager );
		
		//construct environment
		// TODO: load from resources
//		Environment environment = new Environment(world);
		
		
		getJsEngine().evaluateResources("map");



//		Environment environment = new SampleEnvironment(world);
//		Map map = new SampleMap(environment);
//		environment.setMap(map);
//		world.setEnvironment( environment ); // TODO: multiple environments per world



//		Map map = new SampleMap(); 
//		Environment environment = new Environment(map);
		 
		
		
		return world;
	}

}
