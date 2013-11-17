package robotkittenearthguardians.entity;

public class CollisionLibrary {
	
	public static boolean testAABBAABB(final AABB box1, final AABB box2) {
		if(Math.abs(box1.bbPosition.getXVector() - box2.bbPosition.getXVector()) 
				> (box1.size.getXVector() + box2.size.getXVector())) return false;
		if(Math.abs(box1.bbPosition.getYVector() - box2.bbPosition.getYVector()) 
				> (box1.size.getYVector() + box2.size.getYVector())) return false;
		return true;
	}

}
