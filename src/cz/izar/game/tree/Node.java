package cz.izar.game.tree;

import cz.izar.game.event.Observable;

/**
 * Node is basic element in game mechanics
 */
public abstract class Node implements Observable {


	private Node parent = null;
	
	public Node getParent() {
		return parent;
	}
	
	protected void setParent(Node parent) {
		this.parent = parent;
	}

	protected abstract void removeChild(Node node); // throws exception


	public abstract String getTypeName();

//	public Node detach()
//	{
//		Node oldParent = this.getParent();
//		if (oldParent instanceof Node) {
//			oldParent.removeChild(this);
//			this.setParent(null);
//		}
//		return this;
//	}
	
}
