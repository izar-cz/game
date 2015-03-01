package cz.izar.game.entity;

import cz.izar.game.entity.event.EventHandler;
import cz.izar.game.presentation.Presentation;

public interface ComplexEntityCore extends EventHandler {
	boolean getPassability();
	Presentation getPresentation();
	String getProp(int propType);
}
