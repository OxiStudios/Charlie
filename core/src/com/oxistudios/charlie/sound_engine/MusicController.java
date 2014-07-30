import com.sun.glass.ui.Timer;

public class MusicController {
	Sound mp3Sound = Gdx.audio.newSound(gdx.files.internal("mp3 file goes here"));

	public static void main(String[] args) {
		long id = mp3Sound.loop();
		Timer.schedule(new Task{
			public void run(){
				mp3Sound.stop(id);
			}
		}, 5.0f);
	}
}
