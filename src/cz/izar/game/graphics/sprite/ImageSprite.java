package cz.izar.game.graphics.sprite;

import java.awt.Graphics;
import java.awt.Image;

public class ImageSprite implements Sprite {

	private Image image;
	private int offsetX;
	private int offsetY;

	public ImageSprite(Image image, int offsetX, int offsetY) {
		this.image = image;
		this.offsetX = offsetX;
		this.offsetY = offsetY;
	}
	public ImageSprite(Image image) {
		this(image,0,0);
	}

	@Override
	public int getWidth() {
		return image.getWidth(null);
	}

	@Override
	public int getHeight() {
		return image.getHeight(null);
	}

	@Override
	public void draw(Graphics context, int x, int y, int tick) {
		context.drawImage(image, x + offsetX, y + offsetY, null);
	}
}
