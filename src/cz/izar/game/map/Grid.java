package cz.izar.game.map;

import cz.izar.game.Log;

public class Grid<T extends Localized<Coordinates>> {
	private final int width;
	private final int height;
	protected final T[][] items;

	public Grid( int width, int height ) {
		this.width = width;
		this.height = height;
		items = (T[][]) new Localized[width][height];
	}
	
	
	public void remove(T item) throws GridIndexOutOfBoundsException {
		Coordinates location = item.getLocation();
		if(null != location) {
			try {
				if (items[location.x][location.y] != item) {
					throw new IllegalStateException("corrupted grid data");
				}
				item.setLocation(null);
				items[location.x][location.y] = null;
			} catch (ArrayIndexOutOfBoundsException ex) {
				throw new GridIndexOutOfBoundsException(ex, location);
			}
		} else {
			Log.warn("Trying to remove dislocated item");
		}
	}
	
	@Deprecated
	public void setAt(int x, int y, T item) throws GridIndexOutOfBoundsException {
		setAt(new Coordinates(x,y),item);
	}
	
	public void setAt(Coordinates location, T item) throws GridIndexOutOfBoundsException {
		try {
			if (null != items[location.x][location.y]) {
				throw new IllegalStateException("Trying to overwrite item in grid");
			}
			items[location.x][location.y] = item;
			item.setLocation(location);
		} catch (ArrayIndexOutOfBoundsException ex) {
			throw new GridIndexOutOfBoundsException(ex, location);
		}
	}

	@Deprecated
	public T getAt(int x, int y) throws GridIndexOutOfBoundsException {
		return getAt(new Coordinates(x,y));
	}

	public T getAt(Coordinates location) throws GridIndexOutOfBoundsException {
		assert null != location;
		T item;
		try {
			item = items[location.x][location.y];
		} catch (ArrayIndexOutOfBoundsException ex) {
			throw new GridIndexOutOfBoundsException(ex, location);
		}
//		if ( null == tile ) {
//			tiles[x][y] = tile = this.createDefaultChild();
//		}
		assert item != null : "null item in grid at "+location.x+", "+location.y;
		return item;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

}
