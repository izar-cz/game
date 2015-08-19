package cz.izar.game.js;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import javax.script.Bindings;
import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import cz.izar.game.Log;
import cz.izar.game.utils.UidManager;
import cz.izar.game.world.World;

public class JsEngine {

	private boolean javaReady = false;

	private ScriptEngine engine;


	public JsEngine() {
		engine = new ScriptEngineManager().getEngineByName("nashorn");
		assert(engine != null);

		engine.getBindings(ScriptContext.ENGINE_SCOPE).put("jsEngine", this);
	}
	
	public void setJavaObjects(World world, UidManager uidManager) {
		Bindings scope = engine.getBindings(ScriptContext.ENGINE_SCOPE);
		scope.put("uidManager", uidManager);
		scope.put("world", world);
		javaReady = true;
	}

	public void start() {
		if (!javaReady) {
			throw new IllegalStateException("it is necesary to 'setJavaObject()' before starting JS engine");
		}
		evaluateFile( "cz/izar/game/js/bootstrap.js" );
	}

	public void evaluateResources(String category) {
		evaluateFile( "resources/"+category+"/basic.js" );
	}
	
	
	/**
	 * evaluates JS in a file in current scope
	 * @param path
	 */
	private void evaluateFile( String path ) {
		java.io.Reader reader;
//		int idx = path.replaceAll("\\\\", "/").lastIndexOf("/");
//		String fileName = idx >= 0
//			? path.substring(idx + 1)
//			: path;
		String fileName = path;

		Object oldFilename = engine.get(ScriptEngine.FILENAME);
		try {
			reader = getReader( path );
			engine.put(ScriptEngine.FILENAME, fileName);
			engine.eval(reader);
		} catch (IOException e) {
			Log.error(e.getMessage());
			e.printStackTrace();
		} catch (ScriptException e) {
			Log.error("JS exception: "+e.getMessage());
			e.printStackTrace();
		} finally {
			engine.put(ScriptEngine.FILENAME, oldFilename);
		}
	}
	/**
	 * TODO: premistit jinam
	 */
	private java.io.Reader getReader( String path ) throws IOException {
		URL url = JsEngine.class.getClassLoader().getResource(path);
		if (url == null) {
			throw new IllegalArgumentException("failed to locate script "+path);
		}
		return new InputStreamReader(url.openStream());
	}
	
	
	
	public void load(String path){
		evaluateFile("cz/izar/game/js/" + path + ".js");
	}
	public String getPath() {
		return (String)engine.get(ScriptEngine.FILENAME);
	}
}
