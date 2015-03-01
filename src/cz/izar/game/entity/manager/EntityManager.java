package cz.izar.game.entity.manager;

import java.util.Map;

import cz.izar.game.entity.Entity;
import cz.izar.game.utils.UidManager;

/**
 * responsible for storing EntityBlueprints and "instantiating" them into Entities
 * @author izar
 *
 */
public class EntityManager {

	private final Map<Long,Entity> entities = new java.util.HashMap<Long,Entity>();

	private final Map<Long,Blueprint> blueprints = new java.util.HashMap<Long,Blueprint>();
	private final Map<String,Blueprint>  blueprintsById = new java.util.HashMap<String,Blueprint>();

	private final UidManager uidManager;


	public EntityManager(UidManager uidManager) {
		this.uidManager = uidManager;
	}

	
	
	// Entity factory

	public Entity createEntity(long blueprintUid) {
		return createEntity( blueprints.get(blueprintUid) );
	}
	public Entity createEntity(String blueprintId) {
		return createEntity( blueprintsById.get(blueprintId) );
	}
	private Entity createEntity(Blueprint blueprint) {
		if ( null != blueprint ) {
			long uid = uidManager.next();
			Entity entity = blueprint.createEntity(uid);
			entities.put(uid, entity);
			return entity;
		}
		return null;
	}
	
	// blueprints manipulation

	protected Blueprint getBlueprint( long uid ) {
		return blueprints.get(uid);
	}
	protected Blueprint getBlueprint( String name ) {
		return blueprintsById.get(name);
	}

	public long addBlueprint( Blueprint blueprint ) {
		if ( blueprintsById.containsKey( blueprint.getId()) ) {
			System.err.println("Duplicate name '"+blueprint.getId()+"' for tile type.");
		}
		long uid = blueprint.getUid();
		blueprints.put(uid, blueprint);
		blueprintsById.put(blueprint.getId(), blueprint);
		return uid;
	}

}
