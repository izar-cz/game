package cz.izar.game.presentation;

import java.util.Map;

/**
 * Presentation information about an Tile or Entity, provided for presentation adapters.
 * @author izar
 */
public interface Presentation {
	public Map<String, Object> getMap( String key );
	public String getString( String key );
}
