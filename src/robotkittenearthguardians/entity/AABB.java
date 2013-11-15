package robotkittenearthguardians.entity;

public class AABB extends Entity{

	protected Vector2Float center, size;
	
	/**
	 * AABB constructor that creates a bounding box.
	 * @param width width of mob
	 * @param height height of mob
	 */
	public AABB(Vector2Float center, Vector2Float size) {
		this.center = center;
		this.size = size;
	}
	
	public void update(final Vector2Float position) {
		center.x = position.x;
		center.y = position.y;
	}
}
