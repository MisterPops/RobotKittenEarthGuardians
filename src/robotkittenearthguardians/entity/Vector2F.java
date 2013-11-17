package robotkittenearthguardians.entity;

public class Vector2F {
	private float x, y;
	
	public Vector2F(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public Vector2F() {
		this.x = 0.0f;
		this.y = 0.0f;
	}
	
	/**
	 * Sets the vector the given vector.
	 * @param vector Vector used to set this.vector
	 */
	public void setVector(Vector2F vector) {
		this.x = vector.getXVector();
		this.y = vector.getYVector();
	}
	
	/**
	 * Adds to the vector using variables from another
	 * vector.
	 * @param vector Vector used to add to this.vector
	 */
	public void addToVector(Vector2F vector) {
		this.x += vector.getXVector();
		this.y += vector.getYVector();
	}
	
	/**
	 * Sets the x param of this vector.
	 * @param x the param to set x with
	 */
	public void setXVector(float x) {
		this.x = x;
	}
	
	/**
	 * Sets the y param of this vector.
	 * @param y the param to set y with
	 */
	public void setYVector(float y) {
		this.y = y;
	}
	
	/**
	 * Gets the x param of the vector.
	 * @return the x param of the vector
	 */
	public float getXVector() {
		return x;
	}
	
	/**
	 * Gets the y param of the vector.
	 * @return the y param of the vector
	 */
	public float getYVector() {
		return y;
	}
}
