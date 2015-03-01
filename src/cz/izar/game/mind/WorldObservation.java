package cz.izar.game.mind;

import java.util.List;

import cz.izar.game.entity.Entity;
import cz.izar.game.map.Map;

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
	public Map getMap();
	public List<? extends Entity> getEntities();
	public long getTick();
	
	void tick();
}
