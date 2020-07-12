package entidades;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import ferramentas.Colisao;

public class Asteroids implements Runnable {

	public static final int SPEED = 4;
	public static final int Width = 50;
	public static final int Height = 50;
	private static Texture texture;

	float x, y;
	Colisao rect;
	public boolean remove = false;

	public Asteroids(float x) {
		this.x = x;
		this.y = Gdx.graphics.getWidth();
		this.rect = new Colisao(x, y, Width, Height);

		if (texture == null)
			texture = new Texture("asteroid.png");
	}

	public void update() {
		run();
	}

	public void render(SpriteBatch batch) {
		batch.draw(texture, y, x);

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
		y -= SPEED;
		if (y < -Height)
			remove = true;

		rect.move(x, y);

	}

	public void start() {

	}

}
