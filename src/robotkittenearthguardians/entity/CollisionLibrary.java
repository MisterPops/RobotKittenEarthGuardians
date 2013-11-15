package robotkittenearthguardians.entity;

public class CollisionLibrary {
	
	public static boolean testAABBAABB(final AABB box1, final AABB box2) {
		if(Math.abs(box1.center.x - box2.center.x) > (box1.size.x + box2.size.x)) return false;
		if(Math.abs(box1.center.y - box2.center.y) > (box1.size.y + box2.size.y)) return false;
		return true;
	}

}
