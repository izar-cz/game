package cz.izar.game.graphics.sprite;

import java.awt.Graphics;

public interface Sprite {

	/**
	 * Get the width of the drawn sprite
	 * 
	 * @return The width in pixels of this sprite
	 */
	public int getWidth();

	/**
	 * Get the height of the drawn sprite
	 * 
	 * @return The height in pixels of this sprite
	 */
	public int getHeight();
	
	/**
	 * Draw the sprite onto the graphics context provided
	 * 
	 * @param context The graphics context on which to draw the sprite
	 * @param x The x location at which to draw the sprite
	 * @param y The y location at which to draw the sprite
	 * @param tick elapsed time indicator
	 */
	public void draw(Graphics context, int x, int y, int tick);
	
}
