package cz.izar.game.graphics.sprite;

import java.awt.Color;
import java.awt.Graphics;

public class EntitySprite extends SimpleSprite {

	public EntitySprite(Color color, int dimension) {
		super(color, dimension);
	}

	@Override
	public void draw(Graphics context, int x, int y, int tick) {
		context.setColor( getMainColor() );
		int delta = getWidth() / 2 - 2;
		context.fillRect( x+delta, y, getWidth()-2*delta, getHeight() );
		context.fillRect( x, y+delta, getWidth(), getHeight()-2*delta);
	}


}
