package entidades;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import ferramentas.Colisao;
import superraiders.sounds.MenuMusic;

public class InimigoPrimeiro implements Runnable {

	public static final int SPEED = 3;
	public static final int Width = 60;
	public static final int Height = 15;
	private static final Texture batch = null;
	private static Texture texture;
	private static Texture texture2;
	// private static Texture texture2;

	float x, y;
	float inimigo_Posicao;

	Vector2 shipLocation = new Vector2(0, 0);
	// TiroInimigo testBullet = new TiroInimigo(shipLocation, new Vector2(10,0));

	ArrayList<TiroInimigo> bulletManager = new ArrayList<TiroInimigo>();

	Colisao rect;
	Random random_posicao;
	Music music2;

	String direcao;
	Random aleatorio;
	int direAle;

	public boolean remove = false;

	public InimigoPrimeiro(float x) {
		this.x = x;
		this.y = Gdx.graphics.getWidth();
		this.rect = new Colisao(x, y, Width, Height);

		if (texture == null)
			texture = new Texture("inimigo1.png");

		if (texture2 == null)
			texture2 = new Texture("tirojogador.png");

	}

	public void update(float deltaTime) {
		run();

	}

	public void render(SpriteBatch batch) {
		batch.draw(texture, y, x);
		batch.draw(texture2, y * SPEED, x);

	}

	public Colisao getColisao() {
		return rect;

	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	@Override
	public void run() {
		y -= SPEED; // * deltaTime;
		movimentosVerticais();
		if (y < -Height)
			remove = true;

		rect.move(x, y);
	}

	public void start() {
		// TODO Auto-generated method stub

	}

	public void andarBaixo() {
		if (x > 0) {
			x -= SPEED * Gdx.graphics.getDeltaTime() * 30;
			// TiroInimigo mybullet = new TiroInimigo(new Vector2(0,20), shipLocation);
			// bulletManager.add(mybullet);

		} else if (x <= 0) {
			x = 0;
			direcao = "Cima";
		}
	}

	public void andarCima() {
		if (x + Height < Gdx.graphics.getHeight()) {
			x += SPEED * Gdx.graphics.getDeltaTime() * 30;
		} else if (x + Height >= Gdx.graphics.getHeight()) {
			x = Gdx.graphics.getHeight() - Height;
			direcao = "Baixo";
		}
	}

	public void movimentosVerticais() {
		if (direcao == "Cima") {
			andarCima();
		} else {
			andarBaixo();
		}
	}
}
