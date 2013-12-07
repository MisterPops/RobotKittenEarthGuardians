package robotkittenearthguardians.level;

public class LevelBuilder extends GameMaster{
	protected int mob2Spawn;
	protected int numOfMobs;
	protected int timer;
	
	public LevelBuilder(int mob2Spawn, int numOfMobs, int timer) {
		this.mob2Spawn = mob2Spawn;
		this.numOfMobs = numOfMobs;
		this.timer = timer;
	}
	
	public int getTimer() {
		return timer;
	}
	
	public int getNumOfMobs() {
		return numOfMobs;
	}
	
	public int getMob2Spawn() {
		return mob2Spawn;
	}
}
