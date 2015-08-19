package cz.izar.game.event;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import cz.izar.game.Log;

public class SimpleObservable implements Observable {

	private Map<String, List<Listener>> listeners = null;
	
	
	private Object parent;
	public SimpleObservable(Object parent) {
		this.parent = parent;
	}
	@Override
	public String toString() {
		return null != parent
			? parent.toString()
			: super.toString();
	}

	@Override
	public synchronized boolean on(String type, Listener listener) {
		if (null == listener) {
			throw new NullPointerException();
		}

		Log.info("* "+this.toString()+" on "+type+"("+listener.toString()+")");
		if (null == listeners) {
			listeners = new HashMap<String, List<Listener>>();
		}
		List<Listener> typeListeners = listeners.get(type);
		if (null != typeListeners) {
			return typeListeners.contains(listener)
				? false
				: typeListeners.add(listener);
		} else {
			typeListeners = new Vector<Listener>();
			listeners.put(type, typeListeners);
			return typeListeners.add(listener);
		}
	}

	@Override
	public synchronized boolean off(Listener listener) {
		Log.info("* "+this.toString()+" off("+listener.toString()+")");
		if (null == listeners) {
			return false;
		}
		for (Map.Entry<String, List<Listener>> entry: listeners.entrySet()){
			entry.getValue().remove(listener);
		}
		return false;
	}

	@Override
	public synchronized void off(String type) {
		Log.info("* "+this.toString()+" off("+type+")");
		if (null == listeners) {
			return;
		}
		List<Listener> typeListeners = listeners.get(type);
		if (null != typeListeners) {
			typeListeners.clear();
		}
	}

	@Override
	public synchronized void off() {
		Log.info("* "+this.toString()+" off()");
		if (null == listeners) {
			return;
		}
		listeners.clear();
	}


	@Override
	public void dispatch(Event event) {
		Log.info("* "+this.toString()+" dispatch event "+event.toString());
		if (null == listeners) {
			return;
		}
		String type = event.getType();

		Object[] arrLocal;
		synchronized (this) {
			List<Listener> typeListeners = listeners.get(type);
			if (null == typeListeners) {
				return;
			}
			arrLocal = typeListeners.toArray();
		}
		
		for (int i = arrLocal.length-1; i>=0; i--) {
			((Listener)arrLocal[i]).handle(event);
		}
	}

}
