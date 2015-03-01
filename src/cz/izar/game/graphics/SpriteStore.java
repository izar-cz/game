package cz.izar.game.graphics;

//import java.awt.Color;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.imageio.ImageIO;

import cz.izar.game.graphics.sprite.AnimatedSprite;
import cz.izar.game.graphics.sprite.ImageSprite;
import cz.izar.game.graphics.sprite.Sprite;

//import cz.izar.game.World;

public class SpriteStore {

	private HashMap<String, Sprite> spritesByName = new HashMap<>();
//	private HashMap<Integer, Sprite> spritesById = new HashMap<>();
	
	public Sprite getSprite(String name) {
		if ( ! containsSprite(name) ) {
			loadSprite( name );
		}
		return spritesByName.get(name);
	}
	
	public void list() {
		for (Entry<String, Sprite> entry : spritesByName.entrySet() ) {
//			entry.getValue();
			System.err.println( "SpS: " + entry.getKey() );
		}
	}
	
	public void register( Sprite sprite, String name ) {
		spritesByName.put(name,sprite);
	}
	
	public boolean containsSprite( String name ) {
		return spritesByName.containsKey(name);
	}
	
	private void loadSprite( String name ) {
		BufferedImage sourceImage = loadResource( name ) ;
		if ( null != sourceImage ) {
			// create an accelerated image of the right size to store our sprite in
			GraphicsConfiguration gc = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();
			Image image = gc.createCompatibleImage(sourceImage.getWidth(),sourceImage.getHeight(),Transparency.TRANSLUCENT);
			
			// draw our source image into the accelerated image
			image.getGraphics().drawImage(sourceImage,0,0,null);
			
			// create a sprite, add it the cache then return it
			register( new ImageSprite(image), name );
		}
	}
	
	private void loadSpritesGrid( String path, String name, int gridWidth, int gridHeight) {
		loadSpritesGrid( path, name, gridWidth, gridHeight, 0, 0 );
	}
	private void loadSpritesGrid( String path, String name, int gridWidth, int gridHeight, int frameOffsetX, int frameOffsetY ) {
		BufferedImage sourceImage = loadResource( path ) ;
		if ( null != sourceImage ) {
			// create an accelerated image of the right size to store our sprite in
			GraphicsConfiguration gc = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();

			int cols = sourceImage.getWidth() / gridWidth;
			int rows = sourceImage.getHeight() / gridHeight;
			
			for (int x = 0 ; x < cols ; x++) {
				for (int y = 0 ; y < rows ; y++) {
					Image image = gc.createCompatibleImage( gridWidth, gridHeight, Transparency.TRANSLUCENT );
					int offsetX = - x * gridWidth;
					int offsetY = - y * gridHeight;
					
					// draw our source image into the accelerated image
					image.getGraphics().drawImage(sourceImage, offsetX, offsetY, null);
					
					register( new ImageSprite(image, frameOffsetX, frameOffsetY), name+"-"+x+"-"+y );
				}
			}
		}
	}

	private void loadAnimatedSpritesGrid( String path, String name, int gridWidth, int gridHeight, int delay ) {
		loadAnimatedSpritesGrid( path, name, gridWidth, gridHeight, delay, 0, 0 );
	}
	private void loadAnimatedSpritesGrid( String path, String name, int gridWidth, int gridHeight, int delay, int frameOffsetX, int frameOffsetY ) {
		BufferedImage sourceImage = loadResource( path ) ;
		if ( null != sourceImage ) {
			// create an accelerated image of the right size to store our sprite in
			GraphicsConfiguration gc = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();

			int cols = sourceImage.getWidth() / gridWidth;
			int rows = sourceImage.getHeight() / gridHeight;
			
			for (int y = 0 ; y < rows ; y++) {
				Image[] images = new Image[cols];
				
				for (int x = 0 ; x < cols ; x++) {
					int offsetX = - x * gridWidth;
					int offsetY = - y * gridHeight;
					
					Image image = gc.createCompatibleImage( gridWidth, gridHeight, Transparency.TRANSLUCENT );
					// draw our source image into the accelerated image
					image.getGraphics().drawImage(sourceImage, offsetX, offsetY, null);
					images[x] = image;
				}
				register( new AnimatedSprite(images, delay, frameOffsetX, frameOffsetY), name+"-"+y );
			}
		}
	}
	
	
	private BufferedImage loadResource( String name ) {
		String path = "\\resources\\"+name+".png";
		try {
			URL url = this.getClass().getClassLoader().getResource(path);
			
			if (url == null) {
				System.err.println("failed to locate image "+name);
				return null;
			}
			return ImageIO.read(url);
		} catch (IOException e) {
			System.err.println("failed to read image "+name);
			return null;
		}
	}


	// singleton
	private static class InstanceHolder {
		private static final SpriteStore INSTANCE = new SpriteStore();
	}

	public static SpriteStore getInstance() {
		return InstanceHolder.INSTANCE;
	}

	
	private SpriteStore() {
		loadSpritesGrid("tiles01","tiles01",32,32);
		loadSpritesGrid("bird","bird",32,32);
		loadAnimatedSpritesGrid("bird","bird",32,32,1);
		loadAnimatedSpritesGrid("smallani","smallani",32,32,2);
		loadSpritesGrid("trees","trees",64,64,-16,-32);
		loadAnimatedSpritesGrid("graphics\\objects\\campfire","campfire",64,64,1,-16,-32);
		
		loadSpritesGrid("character\\walkcycle\\BODY_skeleton","skeleton",64,64,-16,-32);
		loadAnimatedSpritesGrid("character\\walkcycle\\BODY_skeleton","skeleton",64,64,1,-16,-32);
		
//		.png

		loadSpritesGrid("character/walkcycle/WEAPON_shield_cutout_body","shield",64,64,-16,-32);
		loadAnimatedSpritesGrid("character/walkcycle/WEAPON_shield_cutout_body","shield",64,64,1,-16,-32);
	}

}
