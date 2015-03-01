package cz.izar.game.graphics.sprite;

import java.awt.Color;
import java.awt.Graphics;

import cz.izar.game.graphics.MapView;

public class SimpleSprite implements Sprite {
	
	public final int BORDER_WIDTH = 2;

	private int height;
	private int width;
	private Color mainColor;
	private Color innerColor = null;
	
	public SimpleSprite( Color color ) {
		this(color, MapView.DRAW_TILE_DIMENSION, MapView.DRAW_TILE_DIMENSION);
	}
	public SimpleSprite( Color color, int dimension) {
		this(color,dimension,dimension);
	}
	public SimpleSprite(Color mainColor, int width, int height) {
		this.setMainColor(mainColor);
		this.width = width;
		this.height = height;
	}
	public SimpleSprite(Color mainColor, Color innerColor, int width, int heigth) {
		this( mainColor, width, heigth );
		this.setInnerColor(innerColor);
	}


	@Override
	public int getWidth() {
		return width;
	}

	@Override
	public int getHeight() {
		return height;
	}

	@Override
	public void draw(Graphics context, int x, int y, int tick) {
		context.setColor( mainColor );
		context.fillRect( x, y, width, height );
		if( null != innerColor ) {
			context.setColor( innerColor );
			context.fillRect( x + BORDER_WIDTH, y + BORDER_WIDTH, width - 2*BORDER_WIDTH, height - 2*BORDER_WIDTH);
		}
	}
	public Color getMainColor() {
		return mainColor;
	}
	public void setMainColor(Color mainColor) {
		this.mainColor = mainColor;
	}
	public Color getInnerColor() {
		return innerColor;
	}
	public void setInnerColor(Color innerColor) {
		this.innerColor = innerColor;
	}

}
