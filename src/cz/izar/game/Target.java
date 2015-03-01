package cz.izar.game;

/**
 * Target represents game object, which could be "target" of an (usual) action
 * It could be either an Entity, an entity Feature or a Tile
 * 
 * @author izar
 *
 */
public interface Target {

	public String getTypeName();
}
