import com.oxistudios.charlie.saving_engine.SavingController;
import com.sun.glass.ui.Timer;

public class MusicController {

	String music_config_path = "hard-coded path";

	Sound mp3Sound = Gdx.audio.newSound(gdx.files
			.internal("mp3 file path goes here"));
	Sound firstAbilitySound = Gdx.audio.newSound(gdx.files
			.interal("First ability sound file path goes here"));
	Sound secondAbilitySound = Gdx.audio.newSound(gdx.files
			.internal("Second ability sound file path goes here"));
	Sound thirdAbilitySound = Gdx.audio.newSound(gdx.files
			.internal("Third ability sound file path goes here"));

	private SavingController saving_controller;
	private String[] sound_path;

	public MusicController(SavingController saving_controller) {
		this.saving_controller = saving_controller;
	}

	public static void testSound() {
		long id = mp3Sound.loop();
		Timer.schedule(new Task() {
			@Override
			public void run() {
				mp3Sound.stop(id);
			}
		}, 5.0f);
	}

	public static void abilityOneSound() {
		long abilityOneSoundID = firstAbilitySound.loop();
		Timer.schedule(new Task() {
			public void run() {
				firstAbilitySound.stop(abilityOneSoundID);
			}
		}, 5.0f);
	}

	public static void abilityTwoSound() {
		long abilityTwoSoundID = secondAbilitySound.loop();
		Timer.schedule(new Task() {
			public void run() {
				secondAbilitySound.stop(abilityTwoSoundID);
			}
		}, 5.0f);
	}

	public static void abilityThreeSound() {
		long abilityThreeSoundID = thirdAbilitySound.loop();
		Timer.schedule(new Task() {
			public void run() {
				thirdAbilitySound.stop(abilityThreeSoundID);
			}
		}, 5.0f);
	}

	public void loadSoundConfigFile() {
		sound_path = saving_controller.read(music_config_path);
		for()
	}

	public void playEffect(int sound_id) {
		String sound_id_string = "" + sound_id;
	}
}
