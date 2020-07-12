package com.mygdx.superraiders.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
//import com.mygdx.superraiders.SuperRaiders;
import com.mygdx.superraiders.SuperRaiders;

import entidades.Asteroids;
import entidades.InimigoPrimeiro;
import entidades.InimigoSegundo;
import entidades.TiroJogador;
import ferramentas.Colisao;
import superraiders.screens.Parallax;
import superraiders.screens.TelaJogo;
import superraiders.sounds.MusicaFaseUm;

public class DesktopLauncher {
	public static void main (String[] arg) {
		
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		new LwjglApplication(new SuperRaiders(), config);
		config.foregroundFPS = 60;
		config.width = SuperRaiders.WIDTH;
		config.height = SuperRaiders.HEIGHT;
		//config.resizable = false;
		config.title = "Super Raiders Smaug";
		config.useGL30 = true;
		config.width = 1280;
		config.height = 720;
		
		SuperRaiders music = new SuperRaiders();
		Thread t1 = new Thread (music);
		t1.start();
		
		MusicaFaseUm music3 = new MusicaFaseUm();
		Thread t2 = new Thread (music3);
		t2.start();
		
		Asteroids asteroid = new Asteroids(0);
		Thread t4 = new Thread (asteroid);
		t4.start();
		
		InimigoSegundo inimigo2 = new InimigoSegundo(0);
		Thread t5= new Thread (inimigo2);
		t5.start();
		
		InimigoPrimeiro inimigoprimeiro = new InimigoPrimeiro(0);
		Thread t6 = new Thread (inimigoprimeiro);
		t6.start();
	
	}
	
	
	
		
	
	
}
	
	
	




