package cz.izar.game.graphics.sprite;

import java.awt.Graphics;

public class StackedSprite implements Sprite {
	private Sprite mainSprite;
	private Sprite sprite1;
	private Sprite sprite2;
	

	public StackedSprite(Sprite mainSprite, Sprite sprite1, Sprite sprite2) {
		this(mainSprite, sprite1);
		this.sprite2 = sprite2;
	}
	public StackedSprite(Sprite mainSprite, Sprite sprite1) {
		this.mainSprite = mainSprite;
		this.sprite1 = sprite1;
	}
	
	@Override
	public int getWidth() {
		return mainSprite.getWidth();
	}

	@Override
	public int getHeight() {
		return mainSprite.getHeight();
	}

	@Override
	public void draw(Graphics context, int x, int y, int tick) {
		mainSprite.draw(context, x, y, tick);
		if ( null != sprite1 ) {
			sprite1.draw(context, x, y, tick);
			if ( null != sprite2 ) {
				sprite2.draw(context, x, y, tick);
			}
		}
	}

}
