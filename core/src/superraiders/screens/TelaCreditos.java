package superraiders.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.superraiders.SuperRaiders;

public class TelaCreditos implements Screen {
	private static final int BOTAO_VOLTAR_WIDTH = 214; 
	private static final int BOTAO_VOLTAR_HEIGHT = 68;
	
	private static final int BOTAO_VOLTAR_Y = 20;
	private static final int BOTAO_VOLTAR_X = 630;
	
	SuperRaiders game;
	Music music;

	
	Texture BotaoVoltarAtivo;
	Texture BotaoVoltarInativo;
	Texture creditos;
	

	
	public TelaCreditos (SuperRaiders game) {
		this.game=game;
			
		
		BotaoVoltarAtivo = new Texture("botaovoltarativo.png");
		BotaoVoltarInativo = new Texture("botaovoltarinativo.png");
		creditos = new Texture("creditos.png");

	}

	@Override
	public void show() {
		

}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		game.batch.begin();

		
		game.batch.draw(creditos,0,0);
		game.batch.draw(creditos, 0, Gdx.graphics.getHeight());
		
		int x = SuperRaiders.WIDTH+280   - BOTAO_VOLTAR_WIDTH+260 ;
		
		if (Gdx.input.getX()<x + BOTAO_VOLTAR_WIDTH && Gdx.input.getX()>x && SuperRaiders.HEIGHT - Gdx.input.getY()< BOTAO_VOLTAR_X + BOTAO_VOLTAR_HEIGHT && Gdx.input.getY()>BOTAO_VOLTAR_Y) {
			game.batch.draw(BotaoVoltarInativo,x,  BOTAO_VOLTAR_X, BOTAO_VOLTAR_WIDTH, BOTAO_VOLTAR_HEIGHT);
		
			if (Gdx.input.isTouched()) {
			//if (Gdx.input.isButtonPressed(Keys.A)) {
				//this.dispose();
				
				game.setScreen(new MainMenu(game));
				
				
		}
			
		} else {
			game.batch.draw(BotaoVoltarAtivo,x, BOTAO_VOLTAR_X, BOTAO_VOLTAR_WIDTH, BOTAO_VOLTAR_HEIGHT);
			
			
			
		}
		
		
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
