package cz.izar.game.entity;

import cz.izar.game.tree.ListNode;
import cz.izar.game.tree.Node;

public class Feature extends ListNode<Node> {

	@Override
	public String getTypeName() {
		return "feature";
	}

}
