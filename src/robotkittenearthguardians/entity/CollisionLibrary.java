package robotkittenearthguardians.entity;

public class CollisionLibrary {
	
	public static boolean testAABBAABB(final AABB box1, final AABB box2) {
		if(Math.abs(box1.bbPosistion.x - box2.bbPosistion.x) > (box1.size.x + box2.size.x)) return false;
		if(Math.abs(box1.bbPosistion.y - box2.bbPosistion.y) > (box1.size.y + box2.size.y)) return false;
		return true;
	}

}
