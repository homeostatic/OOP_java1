package model;

/**
 * Block is our abstract class which can be extend to wall or path.
 *
 */
public abstract class Block {
    boolean passable;

	/**
	 * Returns passable.
	 * @return the passable
	 */
	public boolean isPassable() {
		return passable;
	}

	/**
	 * Sets passable to passable.
	 * @param passable the passable to set
	 */
	public void setPassable(boolean passable) {
		this.passable = passable;
	}
    
}
