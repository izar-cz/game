package cz.izar.game.presentation;

import java.util.Map;

import org.mozilla.javascript.NativeObject;

public class SimplePresentation implements Presentation {

	private NativeObject data = null;

	public SimplePresentation(NativeObject data) {
		this.data = data;
	}


	@Override
	public Map<String, Object> getMap( String key ) {
		return null;
	}

	@Override
	public String getString( String key ) {
		Object value = data.get(key);
		if( value instanceof CharSequence ) {
			return value.toString();
		}
		return null;
	}

}
