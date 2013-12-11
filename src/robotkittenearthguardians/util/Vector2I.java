package robotkittenearthguardians.util;

public class Vector2I {
	
	private int x, y;
	
	/**
	 * When constructing a new vector, sets the x/y
	 * perams to the perams from the given vector
	 * @param vector the vector used to set this vector's perams
	 */
	public Vector2I(Vector2I vector) {
		this.x = vector.getXVector();
		this.y = vector.getYVector();
	}
	
	/**
	 * Sets x and y to the variables given when constructing
	 * a new vector.
	 * @param x x variable to be set
	 * @param y y variable to be set
	 */
	public Vector2I(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Sets x and y to zero if no variables given when constructing
	 * a new vector.
	 */
	public Vector2I() {
		this.x = 0;
		this.y = 0;
	}
	
	/**
	 * Sets the vector the given vector.
	 * @param vector Vector used to set this.vector
	 */
	public void setVector(Vector2I vector) {
		this.x = vector.getXVector();
		this.y = vector.getYVector();
	}
	
	/**
	 * Adds to the vector using variables from another
	 * vector.
	 * @param vector Vector used to add to this.vector
	 */
	public void addToVector(Vector2I vector) {
		this.x += vector.getXVector();
		this.y += vector.getYVector();
	}
	
	public void addToXVector(float x) {
		this.x += x;
	}
	
	public void addToYVector(float y) {
		this.y += y;
	}
	
	/**
	 * Sets the x param of this vector.
	 * @param x the param to set x with
	 */
	public void setXVector(int x) {
		this.x = x;
	}
	
	/**
	 * Sets the y param of this vector.
	 * @param y the param to set y with
	 */
	public void setYVector(int y) {
		this.y = y;
	}
	
	/**
	 * Gets the x param of the vector.
	 * @return the x param of the vector
	 */
	public int getXVector() {
		return x;
	}
	
	/**
	 * Gets the y param of the vector.
	 * @return the y param of the vector
	 */
	public int getYVector() {
		return y;
	}
}
