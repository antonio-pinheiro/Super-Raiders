package entidades;

import com.badlogic.gdx.graphics.Texture;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.Animation;

public class Explosao {

	public static final float FRAME_LENGTH = 0.2f;
	public static final int OFFSET = 8;
	public static final int SIZE = 32;

	private static Animation<TextureRegion> anim = null;

	float x, y;
	float statetime;

	public boolean remove = false;

	public Explosao(float x, float y) {
		this.x = x - OFFSET;
		this.y = y - OFFSET;
		statetime = 0;

		if (anim == null)
			anim = new Animation<TextureRegion>(FRAME_LENGTH,
					TextureRegion.split(new Texture("explosion.png"), SIZE, SIZE)[0]);
	}

	public void update(float deltaTime) {
		statetime += deltaTime;
		if (anim.isAnimationFinished(statetime))
			remove = true;
	}

	public void render(SpriteBatch batch) {
		batch.draw(anim.getKeyFrame(statetime), y, x);
	}



	public void run() {
		
	}
}
