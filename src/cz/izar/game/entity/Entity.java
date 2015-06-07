package cz.izar.game.entity;


import cz.izar.game.Environment;
import cz.izar.game.entity.manager.Blueprint;
import cz.izar.game.map.Coordinates;
import cz.izar.game.map.OffsetCoordinates;
import cz.izar.game.map.Tile;
import cz.izar.game.presentation.Presentation;
import cz.izar.game.tree.ListNode;
import cz.izar.game.tree.Node;



public abstract class Entity extends ListNode<Node> {
	
	public static final int PROP_NAME = 1;
	public static final int PROP_DESCRIPTION = 2;

	
	public static final int PROP_IS_ITEM = 20;
	public static final int PROP_MASS = 21;
	public static final int PROP_DIAMETER = 22;
	public static final int PROP_VOLUME = 23;



	private OffsetCoordinates offset = OffsetCoordinates.CENTER;
	private final long uid;

	/**
	 * blueprint (~class) of entity
	 */
	protected final Blueprint blueprint;


	public Entity(Blueprint blueprint, long uid) {
		this.uid = uid;
		this.blueprint = blueprint;
	}
	protected Blueprint getBlueprint() {
		return blueprint;
	}
	public long getTypeUid() {
		return getBlueprint().getUid();
	}
	@Override
	public String getTypeName() {
		return getBlueprint().getId();
	}
	
	public final long getUid() {
		return uid;
	}

	/**
	 * TODO: crate concept of non-boolean passability (depending on direction etc.)
	 * @return
	 */
	abstract public boolean getPassability();
	
	/**
	 * information how to present this entity to player
	 * @return
	 */
	abstract public Presentation getPresentation();


	/**
	 * @param propType should be one of PROP_* constants
	 * @return value for requested property
	 */
	abstract public String getProp(int propType);



	@Override
	public String toString() {
		return getProp(PROP_NAME) + " ["+getUid()+"]";
	}


	public void insertItem(Entity item, Feature target)
	{
		// TODO: don't ignore target feature
		this.appendChild(item);
	}

	// FINAL methods
	public final Tile getTile() {
		Node node = this;
		do {
			node = node.getParent();
		} while ( !(node instanceof Tile) && null != node );
		return (Tile)node;
	}
	public final Environment getEnvironment() {
		return (Environment)(getTile().getParent());
	}

	public final Coordinates getLocation() {
		return getTile().getLocation();
	}
	
	public final OffsetCoordinates getOffset() {
		return offset;
	}
	public final void setOffset(OffsetCoordinates offset) {
		this.offset = offset;
	}

}
