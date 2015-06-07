package cz.izar.game.world;

import cz.izar.game.Environment;
import cz.izar.game.entity.manager.EntityManager;
import cz.izar.game.map.TileManager;
import cz.izar.game.tree.ListNode;

public class World extends ListNode<Environment> {
	
	private TileManager tileManager;
	private EntityManager entityManager;

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

	@Override
	public String getTypeName() {
		return "WORLD";
	}

	
	
	
}
