package cz.izar.game.tree;

import java.util.LinkedList;
import java.util.List;


public abstract class ListNode<T extends Node> extends BasicNode {

	private List<T> children;


	public void appendChild(T node)
	{
		if (null == children) {
			children = new LinkedList<T>();
		}
		Node oldParent = node.getParent();
		if (oldParent != this) {
			if (oldParent != null) {
				oldParent.removeChild(node);
			}
			node.setParent(this);
			children.add(node);
		}
	}

	public List<T> getChildNodes()
	{
		return null == children
			? java.util.Collections.<T>emptyList()
			: java.util.Collections.unmodifiableList(children);
	}


	@Override
	public void removeChild(Node node) {
		if (null != children) {
			children.remove(node);
		}
	}

}
