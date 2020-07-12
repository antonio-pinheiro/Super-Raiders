package superraiders.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.superraiders.SuperRaiders;

public class TelaInstrucoes implements Screen {
	private static final int BOTAO_VOLTAR_WIDTH = 214; 
	private static final int BOTAO_VOLTAR_HEIGHT = 68;
	
	private static final int BOTAO_VOLTAR_Y = 20;
	private static final int BOTAO_VOLTAR_X = 650;
	
	SuperRaiders game;

	Texture BotaoVoltarAtivo;
	Texture BotaoVoltarInativo;
	Texture instrucoes;
	

	
	public TelaInstrucoes (SuperRaiders game) {
		this.game=game;
			
		BotaoVoltarAtivo = new Texture("botaovoltarativo.png");
		BotaoVoltarInativo = new Texture("botaovoltarinativo.png");
		instrucoes = new Texture("instrucoes.png");

	}

	@Override
	public void show() {
		

}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
game.batch.begin();

		
		game.batch.draw(instrucoes,0,0);
		
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
