package cz.izar.game.world;

import cz.izar.game.Environment;
import cz.izar.game.entity.manager.EntityManager;
import cz.izar.game.map.TileManager;

public class World {
	
	private TileManager tileManager;
	private EntityManager entityManager;
	// TODO: World should be capable to hold more environments
	private Environment environment;

	public TileManager getTileManager() {
		return tileManager;
	}

	public void setTileManager(TileManager tileManager) {
		this.tileManager = tileManager;
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public Environment getEnvironment() {
		return environment;
	}

	public void setEnvironment(Environment environment) {
		this.environment = environment;
	}
	
	
	
	
}
