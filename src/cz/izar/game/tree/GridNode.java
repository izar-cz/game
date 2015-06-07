package cz.izar.game.tree;

import cz.izar.game.map.Coordinates;
import cz.izar.game.map.Grid;
import cz.izar.game.map.Localized;

public abstract class GridNode<T extends Node & Localized<Coordinates>> extends BasicNode {

	private Grid<T> grid;
	
	public GridNode( int width, int height ) {
		grid = new Grid<T>(width, height);
	}
	
	protected void removeGridItem(T node) {
		if (this == node.getParent()) {
			grid.remove(node);
			node.setParent(null);
		}
	}

//	@Override
//	protected void removeChild(Node node) {
//		removeGridItem(node);
//	}
	
	public void setNodeAt(Coordinates coords, T node) throws IndexOutOfBoundsException {
		Node oldParent = node.getParent();
		if (this != oldParent) {
			if (null != oldParent) {
				// detach node from its former parent
				oldParent.removeChild(node);
			}
			node.setParent(this);
		}

//		T formerNode = children[x][y];
//		if (null != formerNode) {
//			// detach former child from self
//			Log.warn("rewriting node in grid");
//			removeChild(formerNode);
//		}
		grid.setAt(coords, node);
	}

	public T getNodeAt(Coordinates coords) throws IndexOutOfBoundsException {
		assert null != coords;
		return grid.getAt(coords);
	}

	public int getWidth() {
		return grid.getWidth();
	}

	public int getHeight() {
		return grid.getHeight();
	}

}
