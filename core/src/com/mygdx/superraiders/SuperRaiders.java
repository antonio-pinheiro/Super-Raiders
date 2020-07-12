package com.mygdx.superraiders;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import superraiders.screens.MainMenu;
import superraiders.screens.Parallax;
import superraiders.screens.TelaCreditos;
import superraiders.screens.TelaJogo;
import superraiders.sounds.MenuMusic;
import superraiders.screens.TelaJogo;

public class SuperRaiders extends Game implements Runnable {

	public static final int WIDTH = 720;
	public static final int HEIGHT = 480;
	public static AssetManager manager;
	Music music;
	Music music2;

	public SpriteBatch batch;
	public Parallax parallax;

	@Override
	public void create() {

		batch = new SpriteBatch();
		this.parallax = new Parallax();
		this.setScreen(new MainMenu(this));
		manager = new AssetManager();
		manager.load("explosion.mp3", Sound.class);

	}

	@Override
	public void render() {
		super.render();
	}

	@Override
	public void dispose() {

	}

	public void run() {

		try {

			music = Gdx.audio.newMusic(Gdx.files.internal("menu.mp3"));
			music.setLooping(false);
			music.setVolume(1.0f);

			music2 = Gdx.audio.newMusic(Gdx.files.internal("fase.mp3"));
			music2.setLooping(false);
			music2.setVolume(1.0f);

			while (MenuMusic.P != false) {

				// Thread.sleep(100);true
			}

			music.play();

			while (MenuMusic.P != true) {
				System.out.println("THREAD 0");

				Thread.sleep(100);
			}

			music.pause();
			music2.play();

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void start() {
		// TODO Auto-generated method stub

	}

}
