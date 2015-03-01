package cz.izar.game.graphics.sprite;

import java.awt.Graphics;
import java.awt.Image;


public class AnimatedSprite implements Sprite {

	private Image[] images;
	private int width;
	private int height;
	private int slownes;
	private int offsetX;
	private int offsetY;
	
	public AnimatedSprite(Image[] images) {
		this(images,1);
	}
	public AnimatedSprite(Image[] images, int slownes ) {
		this(images,slownes,0,0);
	}
	public AnimatedSprite(Image[] images, int slownes, int offsetX, int offsetY ) {
		this.images = images;
		Image image = images[0];
		width = image.getWidth(null);
		height = image.getHeight(null);
		this.slownes = slownes;
		this.offsetX = offsetX;
		this.offsetY = offsetY;
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
		
//		System.err.println("animated sprite. #fames: "+tick);
		
		int period = slownes * images.length;
		int frame = (tick % period) / slownes;
		context.drawImage( images[frame], x + offsetX, y + offsetY, null);
	}

}
