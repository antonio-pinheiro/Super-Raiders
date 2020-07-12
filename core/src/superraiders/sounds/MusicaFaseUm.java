package superraiders.sounds;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Music;
import com.mygdx.superraiders.SuperRaiders;
import superraiders.sounds.MenuMusic;

import superraiders.screens.TelaJogo;

public class MusicaFaseUm implements Runnable {
	Music music3;
	TelaJogo tirojogador;

//SuperRaiders game;
	@Override

	public void run() {
		music3 = Gdx.audio.newMusic(Gdx.files.internal("explosion.mp3"));
		music3.setLooping(false);
		//music3.setVolume(0.3f);
		MenuMusic2.tiro = false;

		while (true) {

			if (MenuMusic2.tiro != true) {
				try {
					Thread.sleep(35);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("THREAD TIRO ");
			}
			else {
				music3.setVolume(0.1f);
				music3.play();
				MenuMusic2.tiro = false;
			}
			
		}


	}

	public void start() {
		// TODO Auto-generated method stub
	}

}
