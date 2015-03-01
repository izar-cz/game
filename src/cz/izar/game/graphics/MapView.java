package cz.izar.game.graphics;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

import cz.izar.game.Environment;
import cz.izar.game.Log;
import cz.izar.game.entity.Being;
import cz.izar.game.entity.Entity;
import cz.izar.game.entity.FeaturefulEntity;
import cz.izar.game.entity.action.Action;
import cz.izar.game.entity.action.MoveAction;
import cz.izar.game.graphics.sprite.EntitySprite;
import cz.izar.game.graphics.sprite.Sprite;
import cz.izar.game.map.Direction;
import cz.izar.game.map.Map;
import cz.izar.game.map.OffsetCoordinates;
import cz.izar.game.map.Tile;


public class MapView extends Canvas {
	
	public static final int DRAW_TILE_DIMENSION = 32;
	public static final int DRAW_OFFSET_X  = 16;
	public static final int DRAW_OFFSET_Y  = 16;
	public static final int GRAPHICS_PERIOD = 147;

	private static final long serialVersionUID = 3745999805403709107L;

	private BufferStrategy strategy;

	public MapView( ) {
		// add a key input system (defined below) to our canvas
		// so we can respond to key pressed
		addKeyListener(new KeyInputHandler());
		
		
		// create a frame to contain our game
		JFrame container = new JFrame("Izi Game");
		
		// get hold the content of the frame and set up the resolution of the game
		JPanel panel = (JPanel) container.getContentPane();
		panel.setPreferredSize(new Dimension(672,672));
		panel.setLayout(null);
		
		// setup our canvas size and put it into the content of the frame
		setBounds(0,0,672,672);
		panel.add(this);
		
		// Tell AWT not to bother repainting our canvas since we're
		// going to do that our self in accelerated mode
		setIgnoreRepaint(true);
		
		// finally make the window visible 
		container.pack();
//		container.setResizable(false);
		container.setVisible(true);
		
		// add a listener to respond to the user closing the window. If they
		// do we'd like to exit the game
		container.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		
		// add a key input system (defined below) to our canvas
		// so we can respond to key pressed
		addKeyListener(new KeyInputHandler());
		
		// request the focus so key events come to us
		requestFocus();

		// create the buffering strategy which will allow AWT
		// to manage our accelerated graphics
		createBufferStrategy(2);
		strategy = getBufferStrategy();

	}
	
	public void draw( Environment world ) {
		// Get hold of a graphics context for the accelerated 
		// surface and blank it out
		Graphics2D context = (Graphics2D) strategy.getDrawGraphics();
		context.setColor(Color.black);
		context.fillRect(0,0,672,672);
//		context.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		Map map = world.getMap();
		long tick = world.getTick();

		int graphicsTick = (int)tick % GRAPHICS_PERIOD;
		Tile tile;

		for (int x = 0 ; x < map.getWidth() ; x++) {
			for (int y = 0 ; y < map.getHeight() ; y++) {
				tile = map.getAt(x,y);
				drawTile( tile, context, x, y, graphicsTick + 13 * x + y*x );
			}
		}

		List<? extends Entity> entities; // = world.getEntities();
		for (int y = 0 ; y < map.getHeight() ; y++) {
			for (int x = 0 ; x < map.getWidth() ; x++) {
				entities = map.getAt(x,y).getEntities();
				for(Entity entity:entities ){
					drawEntity(entity, context, x, y, graphicsTick);
				}
			}
		}

		// finally, we've completed drawing so clear up the graphics
		// and flip the buffer over
		context.dispose();
		strategy.show();
	}
	
	private void drawEntity(Entity entity, Graphics2D context, int x, int y, int graphicsTick) {
//		int offsetX = DRAW_OFFSET_X + entity.getLocation().x * DRAW_TILE_DIMENSION
//				+ (int)(entity.getOffsetX() * DRAW_TILE_DIMENSION);
//		int offsetY = DRAW_OFFSET_Y + entity.getLocation().y * DRAW_TILE_DIMENSION
//				+ (int)(entity.getOffsetY() * DRAW_TILE_DIMENSION);

		int offsetX = DRAW_OFFSET_X + entity.getLocation().x * DRAW_TILE_DIMENSION;
		int offsetY = DRAW_OFFSET_Y + entity.getLocation().y * DRAW_TILE_DIMENSION;

		EntitySprite indicator = new EntitySprite(Color.RED, DRAW_TILE_DIMENSION);
		indicator.draw(context, offsetX, offsetY, graphicsTick);
		OffsetCoordinates entityOffset = entity.getOffset();

		offsetX += (int)(entityOffset.x * DRAW_TILE_DIMENSION);
		offsetY += (int)(entityOffset.y * DRAW_TILE_DIMENSION);
		
		
		getEntitySprite(entity,entity).draw(context, offsetX, offsetY, graphicsTick);
		
		if (entity instanceof FeaturefulEntity) {
			List<Entity> inventory = ((FeaturefulEntity)entity).getInventory();
			for (Entity item : inventory) {
				getEntitySprite(item,entity).draw(context, offsetX, offsetY, graphicsTick);
			}
		}

		String description = entity.toString();
		context.setColor(Color.BLACK);
		context.drawString(description, offsetX, offsetY-1);
		context.setColor(Color.WHITE);
		context.drawString(description, offsetX, offsetY);
	}
	
	private Sprite getEntitySprite(Entity entity, Entity actor) {
		String name = entity.getPresentation().getString("dynamicSprite");
		if( null != name && actor instanceof Being ) {
			Direction dir = ((Being) actor).getDirection();
			int dirIndex = null != dir
				? dir.graphicsIndex()
				: 0;
			Action action = ((Being) actor).getAction();
			if( action instanceof MoveAction ) {
				name += "-"+dirIndex;
			} else {
				name += "-0-"+dirIndex;
			}
		} else {
			name = entity.getPresentation().getString("sprite");
		}
		return SpriteStore.getInstance().getSprite(name);
	}

	private void drawTile(Tile tile, Graphics2D context, int x, int y, int graphicsTick) {
		int offsetX = DRAW_OFFSET_X + x * DRAW_TILE_DIMENSION;
		int offsetY = DRAW_OFFSET_Y + y * DRAW_TILE_DIMENSION;
		getSpriteForTile( tile ).draw(context, offsetX, offsetY, graphicsTick );
	}

	private Sprite getSpriteForTile( Tile tile ) {
		// TODO: optimize
		String spriteName = tile.getPresentation().getString("sprite");
		if ( null == spriteName ) {
			Log.warn("tile '" + tile.getTypeName() + "' doesn't have information about sprite!");
			spriteName = "tiles01-2-2";
		}
		return SpriteStore.getInstance().getSprite(spriteName);
	}
	
	private class KeyInputHandler extends KeyAdapter {
		@Override
		public void keyTyped(KeyEvent e) {
			// if we hit escape, then quit the game
			if (e.getKeyChar() == 27) {
				System.exit(0);
			}
		}
	}
	
}
