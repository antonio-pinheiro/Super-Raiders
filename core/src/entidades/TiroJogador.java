package entidades;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.superraiders.SuperRaiders;

import ferramentas.Colisao;
import superraiders.screens.TelaJogo;

public class TiroJogador implements Runnable {

	public static final int SPEED = 600;
	public static final int DEFAULT_Y = 333; // Aqui é onde fica a posição do tiro, é necessário mexer em algo aqui...
	// public static final int DEFAULT_X = 333;
	public static final int POSICAO = 60;
	public static final int WIDTH = -5;
	public static final int HEIGHT = 12;

	private static Texture texture;

	float x, y;
	Colisao rect;
	Music music;

	public boolean remove = false;

	public TiroJogador(float x) {
		this.x = x;
		this.y = DEFAULT_Y;

		this.rect = new Colisao(x, y, HEIGHT, WIDTH);

		if (texture == null)
			texture = new Texture("tirojogador.png");
	}

	public void update(float deltaTime) {

		y += SPEED * Gdx.graphics.getDeltaTime();
		if (y < -POSICAO + y)
			remove = true;
		rect.move(x, y);

	}

	public void render(SpriteBatch batch) {

		batch.draw(texture, y, x);
	}

	public Colisao getColisao() {

		return rect;

	}

	@Override
	public void run() {

	}
}

/*
 * public Vector2 bulletLocation = new Vector2(0, 0); public Vector2
 * bulletVelocity = new Vector2(0, 0); public static final int WIDTH = 3; public
 * static final int HEIGHT = 12;
 * 
 * public TiroJogador (Vector2 sentLocation, Vector2 sentVelocity) {
 * 
 * 
 * bulletLocation = new Vector2(sentLocation.x, sentLocation.y); bulletVelocity
 * = new Vector2(sentVelocity.x, sentVelocity.y); }
 * 
 * public void Update() { bulletLocation.x += bulletVelocity.x; bulletLocation.y
 * += bulletVelocity.y; rect.move(x, y);
 * 
 * }
 * 
 * float x, y; Colisao rect;{
 * 
 * this.rect = new Colisao(x, y, WIDTH, HEIGHT);
 * 
 * }
 * 
 * public Colisao getColisao() { return rect; }
 */
