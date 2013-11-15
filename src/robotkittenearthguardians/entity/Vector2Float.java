package robotkittenearthguardians.entity;

public class Vector2Float {
	public float x, y;
	
	public Vector2Float() {
		x = 0.0f;
		y = 0.0f;
	}
	
	public void addToVector(Vector2Float vector) {
		this.x += vector.x;
		this.y += vector.y;
	}
}
