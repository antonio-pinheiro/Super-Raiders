package superraiders.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import superraiders.sounds.MenuMusic;

public class Parallax {
	public static final int DEFAULT_SPEED = 80;
	public static final int ACCELERATION = 200;
	public static final int GOAL_REACH_ACCELERATION = 200;

	Texture image;
	Music music;
	float x1, x2;
	int speed;
	int goalSpeed;
	float imageScale;
	boolean speedFixed;

	public Parallax() {

		image = new Texture("cenario1.png");

		x1 = 0;
		x2 = image.getWidth();
		speed = 0;
		goalSpeed = DEFAULT_SPEED;
		imageScale = 0;
 	}

	public void updateandRender(float deltaTime, SpriteBatch batch) {
		if (speed < goalSpeed) {
			speed += GOAL_REACH_ACCELERATION * deltaTime;
			if (speed > goalSpeed)
				speed = goalSpeed;

		} else if (speed > goalSpeed) {
			speed -= GOAL_REACH_ACCELERATION * deltaTime;
			if (speed < goalSpeed)
				speed = goalSpeed;
		}

		if (!speedFixed)
			speed += ACCELERATION * deltaTime;

		x2 -= speed * deltaTime;
		x1 -= speed * deltaTime;

		if (x2 + image.getWidth() <= 0)
			x2 = x1 + image.getWidth();

		if (x1 + image.getWidth() <= 0)
			x1 = x2 + image.getWidth();
		batch.draw(image, x2, 0, Gdx.graphics.getWidth(), image.getHeight());
		batch.draw(image, x1, 0, Gdx.graphics.getWidth(), image.getHeight());

	}

	public void resize(int width, int height) {
		imageScale = height / image.getHeight();
	}

	public void setSpeed(int goalSpeed) {
		this.goalSpeed = goalSpeed;
	}

	public void setSpeedFixed(boolean speedFixed) {
		this.speedFixed = speedFixed;
	}

	public void start() {
		// TODO Auto-generated method stub

	}
}
