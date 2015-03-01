package cz.izar.game;

import cz.izar.game.graphics.MapView;
import cz.izar.game.loader.WorldLoader;


public class Application {
	
	private Engine engine;
	private MapView view;

	public Application() {
	}
	
	public void run() {
		getEngine().gameLoop();
	}
	
	public Engine getEngine() {
		return engine;
	}

	public void setEngine(Engine engine) {
		this.engine = engine;
	}
	private void setView(MapView view) {
		this.view = view;
	}

	public static void main(String[] args) {
//		JsLoader test = new JsLoader();
//		test.test();
		
		Application app = new Application();
		Engine engine = new Engine( app );
		MapView view = new MapView();
		
		engine.load( new WorldLoader() );
		app.setEngine(engine);
		app.setView(view);
		app.run();
	}

	public void draw(Environment world) {
		if( null != view ) {
			view.draw(world);
		}
		// TODO Auto-generated method stub
		
	}

	
	
	/// STATIC METHODS
}
