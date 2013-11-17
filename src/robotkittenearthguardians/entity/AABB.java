package robotkittenearthguardians.entity;

public class AABB extends Entity{

	protected Vector2F bbPosistion, size;
	
	/**
	 * AABB constructor that creates a bounding box.
	 * @param width width of mob
	 * @param height height of mob
	 */
	public AABB(Vector2F position, Vector2F size) {
		this.bbPosistion = position;
		this.size = size;
	}
	
	public void update(final Vector2F position) {
		bbPosistion.setXVector(position.getXVector() + size.getXVector() / 2);
		bbPosistion.setYVector(position.getYVector() + size.getYVector() / 2);
	}
}
