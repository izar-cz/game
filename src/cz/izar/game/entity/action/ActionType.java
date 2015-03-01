package cz.izar.game.entity.action;

public enum ActionType {
	MOVE,        // walk, run, prowl..
	WAIT,        // do nothing
	MOVE_OBJECT, // move object by pushing/pulling (while moving actor itself)
	MANIPULATE,
	GRASP,       // aka TAKE
}