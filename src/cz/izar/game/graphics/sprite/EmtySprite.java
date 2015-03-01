package cz.izar.game.graphics.sprite;

import java.awt.Graphics;

public class EmtySprite implements Sprite {

	@Override
	public int getWidth() {
		return 0;
	}

	@Override
	public int getHeight() {
		return 0;
	}

	@Override
	public void draw(Graphics context, int x, int y, int tick) {
	}

}
