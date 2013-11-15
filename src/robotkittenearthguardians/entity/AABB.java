package robotkittenearthguardians.entity;

public class AABB extends Entity{

	protected Vector2Float center;
	public float r[];
	
	/**
	 * AABB constructor that creates a bounding box.
	 * @param width width of mob
	 * @param height height of mob
	 */
	public AABB(final float width, final float height) {
		center = new Vector2Float();
		r = new float[2];
		r[0] = width * 0.5f;
		r[1] = height * 0.5f;
	}
	
	public void update(final Vector2Float position) {
		center.x = position.x;
		center.y = position.y;
	}
}
