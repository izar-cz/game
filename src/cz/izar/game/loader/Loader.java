package cz.izar.game.loader;

import cz.izar.game.js.JsEngine;

public abstract class Loader {
	
	private JsEngine jsEngine = null;

	public JsEngine getJsEngine() {
		if( null == jsEngine ) {
			throw new IllegalStateException("JsEngine not initialized");
		}
		return jsEngine;
	}
	public void setJsEngine(JsEngine jsEngine) {
		this.jsEngine = jsEngine;
	}
}
