package cz.izar.game.world;

import cz.izar.game.Environment;
import cz.izar.game.entity.manager.EntityManager;
import cz.izar.game.map.TileManager;
import cz.izar.game.tree.ListNode;

public class World extends ListNode<Environment> {
	
	private final TileManager tileManager;
	private final EntityManager entityManager;
	
	public World(EntityManager entityManager, TileManager tileManager) {
		this.tileManager = tileManager;
		this.entityManager = entityManager;
	}

	public TileManager getTileManager() {
		return tileManager;
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	@Override
	public String getTypeName() {
		return "WORLD";
	}

	
	
	
}
