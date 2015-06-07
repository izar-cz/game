package cz.izar.game.map;


import cz.izar.game.Environment;
import cz.izar.game.entity.Entity;
import cz.izar.game.presentation.Presentation;
import cz.izar.game.tree.ListNode;
import cz.izar.game.tree.Node;

public class Tile extends ListNode<Entity> implements Localized<Coordinates> {
	
	private boolean passable = true;
	private final TileType type;
	private final long uid;
	private Coordinates location;
	
	
	protected Tile( TileType type, long uid ) {
		this.passable = type.isPassable();
		this.type = type;
		this.uid = uid;
	}

	public boolean isPassable() {
		if( !passable ) {
//			System.err.println("IMPASSABLE");
			return false;
		}
		for( Node entity : getChildNodes() ) {
			if( ((Entity)entity).getPassability() ) {
//				System.err.println("passable "+entity.toString() );
			} else {
//				System.err.println("Impassable "+entity.toString() );
				return false;
			}
		}
		return true;
	}

	protected TileType getType() {
		return type;
	}
	/**
	 * TODO: replace with identifier based on coordinates and map
	 * @deprecated
	 * @return uid of tile
	 */
	public long getUid() {
		return uid;
	}
	public long getTypeUid() {
		return getType().getUid();
	}
	@Override
	public String getTypeName() {
		return getType().getName();
	}
	@Override
	public Coordinates getLocation() {
		return location;
	}
	@Override
	public void setLocation(Coordinates location) {
		this.location = location;
	}
	public Presentation getPresentation() {
		return getType().getPresentation();
	}

	@Override
	public String toString() {
		return "Tile("+getUid()+", \""+type.getName()+"\")";
	}

	@Override
	protected void setParent(Node parent) {
		if (!(parent instanceof Environment)) {
			throw new IllegalArgumentException("Tile have to be placed in Enviroment");
		}
		super.setParent(parent);
	}

	/**
	 * shortcut
	 * returns adjacent tile in given direction
	 * @param direction
	 * @return
	 */
	public Tile in(Direction direction) throws GridIndexOutOfBoundsException {
		Environment environment = (Environment)getParent();
		if (null != environment) {
			return environment.getNodeAt( getLocation().in(direction) );
		}
		return null;
	}
	

}
