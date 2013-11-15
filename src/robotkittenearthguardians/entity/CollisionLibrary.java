package robotkittenearthguardians.entity;

public class CollisionLibrary {
	
	public static boolean testAABBAABB(final AABB box1, final AABB box2) {
		if(Math.abs(box1.center.x - box2.center.x) > (box1.r[0] + box2.r[0])) return false;
		if(Math.abs(box1.center.y - box2.center.y) > (box1.r[1] + box2.r[1])) return false;
		return true;
	}

}