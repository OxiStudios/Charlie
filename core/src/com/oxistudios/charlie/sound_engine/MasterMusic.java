public class MasterMusic {
	private SavingController saving_controller;
	private String music_config_path = "hard coded path to sound file";

	private String sound_location;
	private String[] sound_data;
	private int place;
	private int sound_id;

	public MasterMusic(SavingController saving_controller, String sound_location) {
		this.saving_controller = saving_controller;
		this.sound_location = sound_location;

		place = 0;
	}

	public void readSound() {
		sound_data = saving_controller.read(sound_location);
	}

	public Array<String> getSoundHeaderBlock() {
		Array<String> soundHeader = new Array<String>();

		for (String data : sound_data) {
			if (data != "END_SOUND_HEADER") {
				soundHeader.add(data);
			} else {
				break;
			}
			place++;
		}
		return soundHeader;
	}

	public Array<String> getSoundDataBlock() {
		Array<String> data = new Array<String>();

		for (String single_data : sound_data) {
			place--;
			if (place != 0 && single_data != "END_DATA") {
				data.add(single_data);
			} else {
				break;
			}
		}
		return data;
	}

	/*
	 * public TextureAtlas getTextureAtlas(Array<String> soundHeader){ for(int i
	 * = 0; i < soundHeader.size; i++){ if(soundHeader.get(i) ==
	 * "TEXTURE_ATLAS_LOCATION"){ return new
	 * TextureAtlas(Gdx.files.external(header.get(i + 1))); } } return null; }
	 */
	public HashMap<Integer, String> configureSounds(int sound_id,
			String sound_config_path) {

		sound_path = saving_controller.read(music_config_path);

	}

	public HashMap<Object, Integer> loadSounds(Array<String> soundHeader,
			String sound_config_path) {

		HashMap<Object, Integer> sounds = new HashMap<Object, Integer>();

		for (int i = 0; i < soundHeader.size; i++) {
			if (soundHeader.get(i) == "SOUNDS") {
				break;
			}
		}
		for (int j = i; j < soundHeader.size - i; j++) {
			if (soundHeader.get(j) != "END_SOUNDS") {
			}
		}
		return sounds;
	}

	public void playSound(int sound_id) {
		String sound_id_string = " " + sound_id;

	}

}
