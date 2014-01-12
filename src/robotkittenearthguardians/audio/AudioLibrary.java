package robotkittenearthguardians.audio;

public class AudioLibrary{
	
	public static AudioPlayer powerup = new AudioPlayer("/audio/powerup.wav");
	public static AudioPlayer explosionSound = new AudioPlayer("/audio/explosion.wav");
	public static AudioPlayer missileAmmo = new AudioPlayer("/audio/missileAmmo.wav");
	public static AudioPlayer missileLaunch = new AudioPlayer("/audio/missileLaunch.wav");
	
	public static void update() {
		powerup.volume(-10f);
		explosionSound.volume(-12f);
		missileAmmo.volume(-9f);
		missileLaunch.volume(-10f);
	}
}
