package cz.izar.game.map;

import cz.izar.game.presentation.Presentation;

public class TileType {
	private final String name;
	private final boolean passable;

	private long uid;
	private Presentation presentation;
	
	public TileType( String name, boolean passable) {
		this.name = name;
		this.passable = passable;
	}

	public void setId(long uid) {
		this.uid = uid;
	}
	public void setPresentation(Presentation presentation) {
		this.presentation = presentation;
	}


	public String getName() {
		return name;
	}
	public boolean isPassable() {
		return passable;
	}
	public long getUid() {
		return uid;
	}
	public Presentation getPresentation() {
		return presentation;
	}
	
	public Tile createTile(long uid) {
		return new Tile(this, uid);
	}
	
	@Override
	public String toString() {
		return "TileType("+getUid()+", \""+getName()+"\")";
	}
	
}