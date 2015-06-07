package cz.izar.game.entity;

import cz.izar.game.event.Listener;
import cz.izar.game.presentation.Presentation;

public interface ComplexEntityCore extends Listener {
	boolean getPassability();
	Presentation getPresentation();
	String getProp(int propType);
}
