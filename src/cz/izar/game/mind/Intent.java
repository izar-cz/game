package cz.izar.game.mind;

import cz.izar.game.Target;
import cz.izar.game.entity.Entity;
import cz.izar.game.entity.action.ActionType;
import cz.izar.game.entity.action.ManipulateAction;
import cz.izar.game.map.Direction;


public class Intent {
	
	private final ActionType type;
	
	// all this properties are optional (i.e. null value is permitted)
	private final Direction direction;
	// TODO: use representation/observation instead of actual entity
	private final Target target;
	private final Entity tool;
	private final Enum<?> subtype;
	private final Object additionalData;

	private Intent( ActionType type, Target target, Direction direction, Entity tool, Enum<?> subtype, Object additionalData ) {
		this.type = type;
		this.direction = direction;
		this.target = target;
		this.tool = tool;
		this.subtype = subtype;
		this.additionalData  = additionalData ;
	}
	private Intent( ActionType type, Entity tool, Enum<?> subtype, Object additionalData ) {
		this.type = type;
		this.direction = null;
		this.target = null;
		this.tool = tool;
		this.subtype = subtype;
		this.additionalData  = additionalData ;
	}


	
	public ActionType getType() {
		return type;
	}
	public Direction getDirection() {
		return direction;
	}
	public Target getTarget() {
		return target;
	}
	public Entity getTool() {
		return tool;
	}
	public Object getAdditionalData() {
		return additionalData;
	}
	public Enum<?> getSubtype() {
		return subtype;
	}


	@Override
	public String toString() {
		return "Intent(" + type
			+ (null != direction ? ","+direction : "" )
			+ (null != target ? ","+target.getTypeName() : "" )
			+ (null != tool ? ","+tool.getTypeName() : "" )
			+ ")";
	}


	// static "factories"
	public static Intent walk( Direction direction ) {
		return new Intent( ActionType.MOVE, null, direction, null, null, null );
	}
	public static Intent waitForGodot() { // cannot hide instance method wait() of object 
		return new Intent( ActionType.WAIT, null, null, null );
	}
	public static Intent push( Entity target, Direction direction ) { 
		return new Intent( ActionType.MOVE_OBJECT, target, direction, null, null, null );
	}
	public static Intent manipulate(Entity target, ManipulateAction.Type subtype) {
		return new Intent( ActionType.MANIPULATE, target, null, null, subtype, null );
	}
	public static Intent grasp(Entity target) {
		return new Intent( ActionType.GRASP, target, null, null, null, null );
	}
}
