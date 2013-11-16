package robotkittenearthguardians.entity;

public class AABB extends Entity{

	protected Vector2Float bbPosistion, size;
	
	/**
	 * AABB constructor that creates a bounding box.
	 * @param width width of mob
	 * @param height height of mob
	 */
	public AABB(Vector2Float position, Vector2Float size) {
		this.bbPosistion = position;
		this.size = size;
	}
	
	public void update(final Vector2Float position) {
		bbPosistion.x = position.x + size.x / 2;
		bbPosistion.y = position.y + size.y / 2;
	}
}
