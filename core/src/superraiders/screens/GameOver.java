package superraiders.screens;

import java.sql.DriverManager;
import java.sql.SQLException;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.utils.Align;
import com.mygdx.superraiders.SuperRaiders;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

import ferramentas.ConexaoUtil;

public class GameOver implements Screen {

	private static final int BANNER_WIDTH = 720;
	private static final int BANNER_HEIGHT = 480;

	SuperRaiders game;

	int score, highscore;

	Texture gameover;
	BitmapFont scoreFont;
	// String url = "jdbc:mysql://0.0.0.0:3306/superraiders";
	// String nome = "antonio";
	// String senha = "masterzx";

	public GameOver(SuperRaiders game, int score) {
		this.game = game;
		this.highscore = score;

		// Pegar o maior score de um arquivo de save
		com.badlogic.gdx.Preferences prefs = Gdx.app.getPreferences("superraiders");
		this.highscore = prefs.getInteger("highscore", score);

		// Se a pontuação atual for maior que o highscore anterior, o jogo sobrescreve o
		// arquivo gerado anteriormente
		if (score > highscore) {
			prefs.putInteger("highscore", score);
			prefs.flush();

			// Insere os dados referentes aos pontos, no mysql
			try {
				Connection conexao = ConexaoUtil.getInstance().getConnection();
				String sql = "INSERT INTO pontuacao(score, highscore) VALUES (?, ?)";
				PreparedStatement statement = (PreparedStatement) conexao.clientPrepareStatement(sql);
				// score, insere os dados na tabela score identificada pelo número 1
				// highscore, insere os dados na tabela highscore identificada pelo número 2
				statement.setInt(1, score);
				statement.setInt(2, highscore);
				statement.execute();
				conexao.close();

				System.out.println("High Score Salvo");

			} catch (Exception e) {
				e.printStackTrace();

			}
		}

	}

	{
		gameover = new Texture("perdeu.png");
		scoreFont = new BitmapFont(Gdx.files.internal("fonts/score.fnt"));
	}

	// try {

	// } catch (Exception e) {
	// e.printStackTrace();
	// }

	@Override
	public void show() {

	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		game.batch.begin();

		game.batch.draw(gameover, Gdx.graphics.getWidth() / 2 - BANNER_WIDTH / 2,
				Gdx.graphics.getHeight() - BANNER_HEIGHT - 15, BANNER_WIDTH, BANNER_HEIGHT);

		GlyphLayout scoreLayout = new GlyphLayout(scoreFont, "Score: \n" + score, Color.WHITE, 0, Align.right, false);
		GlyphLayout highscoreLayout = new GlyphLayout(scoreFont, "Highscore: \n" + highscore, Color.WHITE, 0,
				Align.right, false);

		scoreFont.draw(game.batch, scoreLayout, Gdx.graphics.getWidth() / 2 - scoreLayout.width / 2,
				Gdx.graphics.getHeight() - BANNER_HEIGHT - 15 * 2);
		scoreFont.draw(game.batch, highscoreLayout, Gdx.graphics.getWidth() / 2 - highscoreLayout.width / 2,
				Gdx.graphics.getHeight() - BANNER_HEIGHT - scoreLayout.height - 15 * 3);

		GlyphLayout tryAgainLayout = new GlyphLayout(scoreFont, "Tentar Novamente?");
		GlyphLayout mainMenuLayout = new GlyphLayout(scoreFont, "Menu Principal");

		float tryAgainX = Gdx.graphics.getWidth() / 2 - tryAgainLayout.width / 2;
		float tryAgainY = Gdx.graphics.getWidth() / 2 - tryAgainLayout.height / 2;
		float mainMenuX = Gdx.graphics.getWidth() / 2 - mainMenuLayout.width / 2;
		float mainMenuY = Gdx.graphics.getWidth() / 2 - mainMenuLayout.height / 2 - tryAgainLayout.height - 15;

		float touchX = Gdx.input.getX(), touchY = Gdx.graphics.getHeight() - Gdx.input.getY();

		if (Gdx.input.isTouched()) {
			if (touchX > tryAgainX && touchX < tryAgainX + tryAgainLayout.width
					&& touchY > tryAgainY - tryAgainLayout.height && touchY < tryAgainY) {
				this.dispose();
				game.batch.end();
				game.setScreen(new TelaJogo(game));
				return;

			}

			if (touchX > mainMenuX && touchX < mainMenuX + mainMenuLayout.width
					&& touchY > mainMenuY - mainMenuLayout.height && touchY < mainMenuY) {
				this.dispose();
				game.batch.end();
				game.setScreen(new MainMenu(game));
				return;
			}

		}

		scoreFont.draw(game.batch, tryAgainLayout, tryAgainX, tryAgainY);
		scoreFont.draw(game.batch, mainMenuLayout, mainMenuX, mainMenuY);

		game.batch.end();

	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

}
