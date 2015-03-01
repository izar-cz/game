package cz.izar.game.js;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ScriptableObject;

import cz.izar.game.Log;
import cz.izar.game.entity.manager.EntityManager;
import cz.izar.game.map.TileManager;
import cz.izar.game.utils.UidManager;
import cz.izar.game.world.World;

public class JsEngine {

	private boolean javaReady = false;
	
	private Context context;
	private Scriptable scope = null;

	public JsEngine() {
		System.out.println("<JsEngine construct>");
//		jsLoader = new JsLoader();
		context = Context.enter();
		scope = context.initStandardObjects();
		System.out.println("</JsEngine construct>");
	}
	
	public void setJavaObjects(World world, TileManager tileManager, EntityManager entityManager, UidManager uidManager) {
		assert tileManager != null;
		assert entityManager != null;
		setJavaObject(tileManager,"tileManager");
		setJavaObject(entityManager,"entityManager");
		setJavaObject(uidManager,"uidManager");
		setJavaObject(world,"world");
		javaReady = true;
	}
	
	public void start() {
		if (!javaReady) {
			throw new IllegalStateException("it is necesary to 'setJavaObject()' before starting JS engine");
		}
		evaluateFile( "cz/izar/game/js/engine.js" );
	}
	public void evaluateResources(String category) {
		evaluateFile( "resources/"+category+"/basic.js" );
	}
	
	
	
	
	
	
	private void setJavaObject(Object object, String name) {
		Object wrappedObject = Context.javaToJS(object, scope);
		ScriptableObject.putProperty(scope, name, wrappedObject);
	}
	
	
	
	/**
	 * evaluates JS in a file in current scope
	 * @param path
	 */
	private void evaluateFile( String path ) {
		java.io.Reader reader;
		int idx = path.replaceAll("\\\\", "/").lastIndexOf("/");
		String fileName = idx >= 0
			? path.substring(idx + 1)
			: path;

		try {
			reader = getReader( path );
			context.evaluateReader(scope, reader, fileName, 1, null);
		} catch (IOException e) {
			Log.error(e.getMessage());
			e.printStackTrace();
		}
	}
	/**
	 * TODO: premistit jinam
	 */
	private java.io.Reader getReader( String path ) throws IOException {
		URL url = this.getClass().getClassLoader().getResource(path);
		if (url == null) {
			throw new IllegalArgumentException("failed to locate script "+path);
		}
		return new InputStreamReader(url.openStream());
	}
}
