package entidades;

import com.badlogic.gdx.math.Vector2;

import ferramentas.Colisao;

public class TiroInimigo {
	public Vector2 bulletLocation = new Vector2(0, 0);
	private Vector2 bulletVelocity = new Vector2(0, 0);

	public static final int WIDTH = 3;
	public static final int HEIGHT = 12;

	public TiroInimigo(Vector2 sentLocation, Vector2 sentVelocity) {

		bulletLocation = new Vector2(sentLocation.x, sentLocation.y);
		bulletVelocity = new Vector2(sentVelocity.x, sentVelocity.y);
	}

	public void Update() {
		bulletLocation.x += bulletVelocity.x;
		bulletLocation.y += bulletVelocity.y;
		rect.move(x, y);

	}

	float x, y;
	Colisao rect;
	{

		this.rect = new Colisao(x, y, WIDTH, HEIGHT);

	}

	public Colisao getColisao() {
		return rect;
	}
}