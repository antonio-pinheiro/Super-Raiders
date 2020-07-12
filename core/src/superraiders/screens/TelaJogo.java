package superraiders.screens;

import java.util.ArrayList;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.superraiders.SuperRaiders;

import entidades.Asteroids;
import entidades.Explosao;
import entidades.InimigoPrimeiro;
import entidades.InimigoSegundo;
import entidades.PowerEnergia;
import entidades.TiroInimigo;
import entidades.TiroJogador;
import ferramentas.Colisao;
import superraiders.sounds.MenuMusic;
import superraiders.sounds.MenuMusic2;
import superraiders.sounds.MusicaFaseUm;

public class TelaJogo implements Screen {
	public static final float SPEED = 600;
	public static final float MIN_ASTEROID_APARECE = 0.3f;
	public static final float MAX_ASTEROID_APARECE = 0.6f;

	public static final float MIN_INIMIGO_APARECE = 1.0f;
	public static final float MAX_INIMIGO_APARECE = 2.0f;

	public static final float MIN_TIRO_APARECE = 4.0f;
	public static final float MAX_TIRO_APARECE = 6.0f;

	public static final float MIN_ENERGIA_APARECE = 1.0f;
	public static final float MAX_ENERGIA_APARECE = 2.0f;

	public static final int SHIP_WIDTH = -5;
	public static final int SHIP_Height = 12;

	Music music;

	Texture nave;
	Texture cenario1;
	Texture blank;

	float[] posicoes;
	float x;
	float y;
	int roll;

	float asteroidTimer;
	float inimigoTimer;
	float energiaTimer;
	float tiroTimer;

	float screenWidth = 0;
	float screenHeight = 0;

	Vector2 shipLocation = new Vector2(0, 0);
	Vector2 cursorLocation = new Vector2(0, 0);

	ArrayList<TiroInimigo> bulletManager = new ArrayList<TiroInimigo>();
	ArrayList<Asteroids> asteroid;
	ArrayList<Explosao> explosoes;
	ArrayList<InimigoPrimeiro> inimigos;
	ArrayList<InimigoSegundo> inimigos2;
	ArrayList<PowerEnergia> energia;

	Random random;
	Random random_inimigos;
	Random random_energia;
	MusicaFaseUm music3;
	Random aleatorio;
	String direcao;
	Random random_tiro;
	int direAle;

	SuperRaiders game;

	ArrayList<TiroJogador> tiros;

	BitmapFont scoreFont;

	Colisao jogadorColisao;

	float health = 1; // 1 igual cheio e 0 igual morte

	int score;

	public TelaJogo(SuperRaiders game) {

		aleatorio = new Random();
		direAle = aleatorio.nextInt(2);
		if (direAle == 0) {
			direcao = "Cima";
		} else {
			direcao = "Baixo";
		}

		this.game = game;
		y = SuperRaiders.HEIGHT;
		x = SuperRaiders.WIDTH;// /2 - SHIP_WIDTH /2 ;

		screenWidth = Gdx.graphics.getWidth();
		screenHeight = Gdx.graphics.getHeight();

		roll = 2;
		// rolls = new Animation[5];

		// TextureRegion[][] rollSpriteSheet;

		tiros = new ArrayList<TiroJogador>();
		explosoes = new ArrayList<Explosao>();
		inimigos = new ArrayList<InimigoPrimeiro>();
		inimigos2 = new ArrayList<InimigoSegundo>();

		energia = new ArrayList<PowerEnergia>();

		asteroid = new ArrayList<Asteroids>();
		scoreFont = new BitmapFont(Gdx.files.internal("fonts/score.fnt"));

		jogadorColisao = new Colisao(0, 0, SHIP_WIDTH, SHIP_Height);

		blank = new Texture("blank.png");

		score = 0;

		random = new Random();
		asteroidTimer = random.nextFloat() * (MAX_ASTEROID_APARECE - MIN_ASTEROID_APARECE) + MIN_ASTEROID_APARECE;

		random_inimigos = new Random();
		inimigoTimer = random_inimigos.nextFloat() * (MAX_INIMIGO_APARECE - MIN_INIMIGO_APARECE) + MIN_INIMIGO_APARECE;

		random_energia = new Random();
		energiaTimer = random_inimigos.nextFloat() * (MAX_ENERGIA_APARECE - MIN_ENERGIA_APARECE) + MIN_ENERGIA_APARECE;

		random_tiro = new Random();
		tiroTimer = random_tiro.nextFloat() * (MAX_TIRO_APARECE - MIN_TIRO_APARECE) + MIN_TIRO_APARECE;

		// random_posicao = new Random();
		// inimigoPosicao = random_posicao.nextFloat() * (x - y) + x;

	}

	@Override
	public void show() {
		screenWidth = Gdx.graphics.getWidth();
		screenHeight = Gdx.graphics.getHeight();

		nave = new Texture("navejogador2.png");
		cenario1 = new Texture("cenario1.png");
		// ship = new Texture("inimigo3.png");

		// tirojogador = new Texture("tirojogador.png");
		// shipLocation = new Vector2(20, (screenHeight) - (nave.getHeight()));

	}

	@Override
	public void render(float delta) {

		ArrayList<TiroJogador> tirosToRemove = new ArrayList<TiroJogador>();
		for (TiroJogador tirojogador : tiros) {
			tirojogador.update(delta);
			if (tirojogador.remove)
				tirosToRemove.add(tirojogador);
		}

		if (Gdx.input.isKeyPressed(Keys.UP)) {
			y += SPEED * Gdx.graphics.getDeltaTime();

		}

		if (Gdx.input.isKeyPressed(Keys.DOWN)) {
			y -= SPEED * Gdx.graphics.getDeltaTime();
		}

		if (Gdx.input.isKeyPressed(Keys.LEFT)) {
			x -= SPEED * Gdx.graphics.getDeltaTime();
		}

		if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
			x += SPEED * Gdx.graphics.getDeltaTime();
		}

		if (Gdx.input.isKeyJustPressed(Keys.SPACE)) {
			MenuMusic2.tiro = true;
			// tiros.add(new TiroJogador(x -4));
			tiros.add(new TiroJogador(y - SHIP_WIDTH - 4));

			// SuperRaiders.manager.get("explosion.mp3", Sound.class).play();
		}

		asteroidTimer -= delta;
		if (asteroidTimer <= 0) {
			asteroidTimer = random.nextFloat() * (MAX_ASTEROID_APARECE - MIN_ASTEROID_APARECE) + MIN_ASTEROID_APARECE;
			asteroid.add(new Asteroids(random.nextInt(Gdx.graphics.getWidth() - Asteroids.Width)));
		}

		inimigoTimer -= delta;
		if (inimigoTimer <= 0) {
			inimigoTimer = random.nextFloat() * (MAX_INIMIGO_APARECE - MIN_INIMIGO_APARECE) + MIN_INIMIGO_APARECE;
			inimigos.add(new InimigoPrimeiro(random.nextInt(Gdx.graphics.getWidth() - InimigoPrimeiro.Width)));

		}

		inimigoTimer -= delta;
		if (inimigoTimer <= 0) {
			inimigoTimer = random.nextFloat() * (MAX_INIMIGO_APARECE * 2 - MIN_INIMIGO_APARECE * 2)
					+ MIN_INIMIGO_APARECE * 2;
			inimigos2.add(new InimigoSegundo(random.nextInt(Gdx.graphics.getWidth() - InimigoSegundo.Width)));

		}

		energiaTimer -= delta;
		if (energiaTimer <= 0) {
			energiaTimer = random.nextFloat() * (MAX_ENERGIA_APARECE - MIN_ENERGIA_APARECE) + MIN_ENERGIA_APARECE;
			energia.add(new PowerEnergia(random.nextInt(Gdx.graphics.getWidth() - PowerEnergia.Width)));

		}

		ArrayList<Asteroids> asteroidToRemove = new ArrayList<Asteroids>();
		for (Asteroids asteroids : asteroid) {
			asteroids.update();
			if (asteroids.remove)
				asteroidToRemove.add(asteroids);

		}

		ArrayList<Explosao> explosoesToRemove = new ArrayList<Explosao>();
		for (Explosao explosao : explosoes) {
			explosao.update(delta);

			if (explosao.remove)
				explosoesToRemove.add(explosao);
		}

		explosoes.removeAll(explosoesToRemove);
		jogadorColisao.move(y, x);

		ArrayList<InimigoPrimeiro> inimigosToRemove = new ArrayList<InimigoPrimeiro>();
		for (InimigoPrimeiro inimigo : inimigos) {
			inimigo.update(delta);
			if (inimigo.remove)
				inimigosToRemove.add(inimigo);
		}

		ArrayList<InimigoSegundo> inimigos2ToRemove = new ArrayList<InimigoSegundo>();
		for (InimigoSegundo inimigo2 : inimigos2) {
			inimigo2.update(delta);
			if (inimigo2.remove)
				inimigos2ToRemove.add(inimigo2);
		}

		ArrayList<PowerEnergia> energiaToRemove = new ArrayList<PowerEnergia>();
		for (PowerEnergia energias : energia) {
			energias.update(delta);
			if (energias.remove)
				energiaToRemove.add(energias);
		}

		jogadorColisao.move(y, x);

		for (TiroJogador tirojogador : tiros) { // PAREI AQUI
			for (Asteroids asteroids : asteroid) {
				if (tirojogador.getColisao().collideWith(asteroids.getColisao())) {
					tirosToRemove.add(tirojogador);
					asteroidToRemove.add(asteroids);
					explosoes.add(new Explosao(asteroids.getX(), asteroids.getY()));
					score += 100;

				}

			}
		}

		for (TiroJogador tirojogador : tiros) { // PAREI AQUI
			for (InimigoPrimeiro inimigo : inimigos) {
				if (tirojogador.getColisao().collideWith(inimigo.getColisao())) {
					tirosToRemove.add(tirojogador);
					inimigosToRemove.add(inimigo);
					explosoes.add(new Explosao(inimigo.getX(), inimigo.getY()));
					score += 1000;
				}

			}
		}

		for (TiroJogador tirojogador : tiros) { // PAREI AQUI
			for (InimigoSegundo inimigo2 : inimigos2) {
				if (tirojogador.getColisao().collideWith(inimigo2.getColisao())) {
					tirosToRemove.add(tirojogador);
					inimigos2ToRemove.add(inimigo2);
					explosoes.add(new Explosao(inimigo2.getX(), inimigo2.getY()));
					score += 1000;

				}

			}
		}

		for (Asteroids asteroids : asteroid) {
			if (asteroids.getColisao().collideWith(jogadorColisao)) {
				asteroidToRemove.add(asteroids);
				health -= 0.1;

				if (health <= 0) {
					this.dispose();
					game.setScreen(new GameOver(game, score));
					return;
				}

			}
		}

		for (InimigoPrimeiro inimigo : inimigos) {
			if (inimigo.getColisao().collideWith(jogadorColisao)) {
				inimigosToRemove.add(inimigo);
				health -= 0.1;

				if (health <= 0) {
					this.dispose();
					game.setScreen(new GameOver(game, score));
					return;
				}

			}
			for (InimigoSegundo inimigo2 : inimigos2) {
				if (inimigo2.getColisao().collideWith(jogadorColisao)) {
					inimigos2ToRemove.add(inimigo2);
					health -= 0.1;

					if (health <= 0) {
						this.dispose();
						game.setScreen(new GameOver(game, score));
						return;
					}
				}

			}
		}

		for (PowerEnergia energias : energia) {
			if (energias.getColisao().collideWith(jogadorColisao)) {
				energiaToRemove.add(energias);
				health += 0.2;

			}
		}

		inimigos.removeAll(inimigosToRemove);

		inimigos2.removeAll(inimigos2ToRemove);

		asteroid.removeAll(asteroidToRemove);

		tiros.removeAll(tirosToRemove);

		energia.removeAll(energiaToRemove);

		game.batch.begin();

		game.parallax.updateandRender(delta, game.batch);
		game.batch.draw(nave, x, y);
		// game.batch.draw(ship, shipLocation.x, shipLocation.y);

		if (this.x < 1) {
			x = 1;
		}

		if (this.x > 1100) {
			x = 1100;
		}

		if (this.y < 1) {
			y = 1;
		}

		if (this.y > 650) {
			y = 650;
		}

		GlyphLayout scoreLayout = new GlyphLayout(scoreFont, "" + score);
		scoreFont.draw(game.batch, scoreLayout, Gdx.graphics.getWidth() / 2 - scoreLayout.width / 2,
				Gdx.graphics.getHeight() - scoreLayout.height - 10);

		for (Asteroids asteroids : asteroid) {
			asteroids.render(game.batch);
		}

		for (TiroJogador tirojogador : tiros) {
			tirojogador.render(game.batch);
		}

		for (Explosao explosao : explosoes) {
			explosao.render(game.batch);
		}

		for (InimigoPrimeiro inimigo : inimigos) {
			inimigo.render(game.batch);
		}

		for (InimigoSegundo inimigo2 : inimigos2) {
			inimigo2.render(game.batch);
		}

		for (PowerEnergia energias : energia) {
			energias.render(game.batch);
		}

		if (health > 0.6f)
			game.batch.setColor(Color.GREEN);
		else if (health > 0.2f)

			game.batch.setColor(Color.YELLOW);

		game.batch.setColor(Color.RED);

		game.batch.draw(blank, 0, 0, Gdx.graphics.getHeight() * health, 30);
		game.batch.setColor(Color.WHITE);

		game.batch.end();

	}

	// {
	// InimigoPrimeiro inimigo1 = new InimigoPrimeiro(0);
	// Thread t3 = new Thread (inimigo1);
	// t3.start();
	// }

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	// @Override
	// public void resume() {
	// TODO Auto-generated method stub

	// }

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

}
