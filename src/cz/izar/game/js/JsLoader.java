package cz.izar.game.js;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ScriptableObject;

//import javax.script.ScriptEngine;
//import javax.script.ScriptEngineManager;
//import javax.script.ScriptException;

/**
 * @deprecated
 * @author izar
 */
public class JsLoader {
	
	private Context context;
	private Scriptable scope = null;
	
	public JsLoader() {
		System.out.println("<JsLoader construct>");
		context = Context.enter();
		// Initialize the standard objects (Object, Function, etc.)
		// This must be done before scripts can be executed. Returns
		// a scope object that we use in later calls.
		scope = context.initStandardObjects();
//			// Exit from the context.
//			Context.exit();
		init();
		System.out.println("</JsLoader construct>");
	}
	
	private void init() {
		java.io.Reader reader;
		try {
			reader = getReader( "cz/izar/game/js/engine.js" );
			context.evaluateReader(scope, reader, "engine.js", 1, null);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public JsLoader setObject(Object object, String name) {
		Object wrappedObject = Context.javaToJS(object, scope);
		ScriptableObject.putProperty(scope, name, wrappedObject);
		return this;
	}

//	/**
//	 * @deprecated
//	 */
//	public List<NativeObject> loadConfigs( String name, String category ) {
//		Object rawData = loadResource( name, category );
//		List<NativeObject> list = new LinkedList<NativeObject>();
//		if( rawData instanceof Iterable ) {
//			for( Object item : (Iterable<?>)rawData ) {
//				if( item instanceof NativeObject ) {
//					list.add((NativeObject)item);
//				} else {
//					throw new JsConfigurationException("Each top-level array member in Resource '"+name+"' must be an object.");
//				}
//			}
//		} else {
//			throw new JsConfigurationException("Resource '"+name+"' must contain an array.");
//		}
//		return list;
//	}

	public Object loadResource( String name, String category ){
		java.io.Reader reader;
		try {
			reader = getResourceReader( name, category );
			return context.evaluateReader(scope, reader, name+".js", 1, null);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public java.io.Reader getResourceReader( String name, String category ) throws IOException {
		return getReader( "resources/"+category+"/"+name+".js" );
	}

	private java.io.Reader getReader( String path ) throws IOException {
		URL url = this.getClass().getClassLoader().getResource(path);
		if (url == null) {
			throw new IllegalArgumentException("failed to locate script "+path);
		}
		return new InputStreamReader(url.openStream());
	}
	
	
//	public void test() {
//	System.out.println("<TEST>");
//
//	Object result = loadResource("test","objects");
//	if(null != result) {
//		Object data = new Object(){
//			public int foo = 43;
//		};
//		
//		Object value = Context.javaToJS(data, scope);
//		ScriptableObject.putProperty(scope, "boo", value);
//
//		
//		
//		Event event = new Event("click");
//		event.setData("raw data");
//		
//		result = context.evaluateString(scope, "getHandler('PARAM')", "inline.js", 42, null);
//		EventHandler handler = (EventHandler)Context.jsToJava(result, EventHandler.class );
//		
//		System.out.println(handler);
//		
//		System.out.println(event);
//		if ( handler instanceof EventHandler ) {
//			handler.handle(event);
//			System.out.println(event);
//		}
//		
////		context.
////		context.evaluateReader(scope, reader, name+".js", 1, null);
////		javax.script.ScriptEngine foo;
////		foo.
//	};
//	
//	System.out.println("</TEST>");
//}
}
