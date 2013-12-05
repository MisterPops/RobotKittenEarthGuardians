package robotkittenearthguardians.entity;

public class HealthBar {
	private float maxHealth;
	private float realHealth;
	
	public HealthBar(float maxHealth) {
		this.maxHealth = maxHealth;
		this.realHealth = maxHealth;
	}
	
	public void update(float realHealth) {
		this.realHealth = realHealth;
	}
	
	public float getMaxHealth() {
		return maxHealth;
	}
	
	public float getRealHealth() {
		return realHealth;
	}
}
