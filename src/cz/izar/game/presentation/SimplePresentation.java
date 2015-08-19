package cz.izar.game.presentation;

import java.util.Map;

import jdk.nashorn.api.scripting.JSObject;

public class SimplePresentation implements Presentation {

	private JSObject data = null;

	public SimplePresentation(JSObject data) {
		this.data = data;
	}


	@Override
	public Map<String, Object> getMap( String key ) {
		return null;
	}

	@Override
	public String getString( String key ) {
		Object value = data.getMember(key);
		if( value instanceof CharSequence ) {
			return value.toString();
		}
		return null;
	}

}
