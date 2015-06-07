package cz.izar.game.mind;

import java.util.List;

import cz.izar.game.entity.Entity;
import cz.izar.game.map.Coordinates;
import cz.izar.game.map.Tile;

/**
 * interface pro tridy drzici informace o "svete"
 *  - mapa
 *  - entity
 *  - predmety
 *
 * @author izar
 *
 */
public interface WorldObservation {
	public Tile getNodeAt(Coordinates location);
	public List<? extends Entity> getEntities();
	public long getTick();
	
	void tick();
}
