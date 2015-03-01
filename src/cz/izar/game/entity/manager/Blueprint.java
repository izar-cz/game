package cz.izar.game.entity.manager;
import cz.izar.game.entity.Entity;


abstract public class Blueprint {
	private final String id;
	private final long uid;

	public Blueprint(long uid, String id) {
		assert null != id;
		this.uid = uid;
		this.id = id;
	}
	
	abstract public Entity createEntity(long uid);

	public String getId() {
		return id;
	}
	public long getUid() {
		return uid;
	}
}
