package superraiders.screens;
//package superraiders.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.superraiders.SuperRaiders;

import superraiders.sounds.MenuMusic;


public class MainMenu implements Screen {
	
	private static final int BOTAO_SAIR_WIDTH = 214; 
	private static final int BOTAO_SAIR_HEIGHT = 68;
	
	private static final int BOTAO_INICIAR_WIDTH = 214;
	private static final int BOTAO_INICIAR_HEIGHT = 68;
	
	private static final int BOTAO_CREDITOS_WIDTH = 214;
	private static final int BOTAO_CREDITOS_HEIGHT = 68;
	
	private static final int BOTAO_INSTRUCOES_WIDTH = 214;
	private static final int BOTAO_INSTRUCOES_HEIGHT = 68;
	
	private static final int BOTAO_SAIR_Y = 220;
	private static final int BOTAO_SAIR_X = 400;
	
	private static final int BOTAO_INICIAR_Y = 120;
	private static final int BOTAO_INICIAR_X = 400;
	
	private static final int BOTAO_CREDITOS_Y = 220;
	private static final int BOTAO_CREDITOS_X = 400;
	
	private static final int BOTAO_INSTRUCOES_Y = 280;
	private static final int BOTAO_INSTRUCOES_X = 400;
	
	Music music;
	
	
	
	
	SuperRaiders game;
	
	Texture BotaoIniciarAtivo;
	Texture BotaoIniciarInativo;
	Texture BotaoSairAtivo;
	Texture BotaoSairInativo;
	Texture BotaoCreditosAtivo;
	Texture BotaoCreditosInativo;
	Texture BotaoInstrucoesAtivo;
	Texture BotaoInstrucoesInativo;
	
	Texture menufundo;
	
	public MainMenu (SuperRaiders game) {
		this.game = game;
		BotaoIniciarAtivo = new Texture ("botaoiniciarativo.png");
		BotaoIniciarInativo = new Texture ("botaoiniciarinativo.png");
		BotaoSairAtivo = new Texture ("botaosairativo.png");
		BotaoSairInativo = new Texture ("botaosairinativo.png");
		BotaoCreditosAtivo = new Texture("botaocreditoativo.png");
		BotaoCreditosInativo = new Texture("botaocreditoinativo.png");
		BotaoInstrucoesAtivo = new Texture("botaoinstrucoesativo.png");
		BotaoInstrucoesInativo = new Texture("botaointrucoesinativo.png");
		
		menufundo = new Texture ("fundomenu.png");
	}
	
	
	@Override
	public void show() {
		
	}

	@Override
	public void render(float delta) {
		
		game.batch.begin();
		
		game.batch.draw(menufundo, 0,0);
	    
	    game.batch.draw(menufundo, 0, Gdx.graphics.getHeight());
	    
	   
		
		int x = SuperRaiders.WIDTH/3+50   - BOTAO_INICIAR_WIDTH/2 ;
		
		//if (Gdx.input.isKeyJustPressed(Keys.P)) {
			//music.pause();
				//MenuMusic.P = true;	
				//System.out.println("Apertei tecla P");
			//}
		
		//if (Gdx.input.isKeyJustPressed(Keys.O)) {
			//music.pause();
				

		//	}
		
		
		if (Gdx.input.getX()<x + BOTAO_INICIAR_WIDTH && Gdx.input.getX()>x && SuperRaiders.HEIGHT - Gdx.input.getY()< BOTAO_INICIAR_X + BOTAO_INICIAR_HEIGHT && Gdx.input.getY()>BOTAO_INICIAR_Y) {
			game.batch.draw(BotaoIniciarAtivo,x,  BOTAO_INICIAR_X, BOTAO_INICIAR_WIDTH, BOTAO_INICIAR_HEIGHT);
		
		if (Gdx.input.isTouched()) {
			game.setScreen(new TelaJogo(game));
			MenuMusic.P = true;
			//MenuMusic.tiro = true;
			//System.out.println("Apertei tecla O");
		}
		
		
		} else {
			game.batch.draw(BotaoIniciarInativo,x, BOTAO_INICIAR_X, BOTAO_INICIAR_WIDTH, BOTAO_INICIAR_HEIGHT);

		}
		
		
		x = SuperRaiders.WIDTH +300 - BOTAO_SAIR_WIDTH /2;

		if (Gdx.input.getX()<x + BOTAO_SAIR_WIDTH && Gdx.input.getX()>x && SuperRaiders.HEIGHT - Gdx.input.getY()< BOTAO_SAIR_X + BOTAO_SAIR_HEIGHT && Gdx.input.getY()>BOTAO_SAIR_Y) {
			game.batch.draw(BotaoSairAtivo,x, BOTAO_SAIR_X, BOTAO_SAIR_WIDTH, BOTAO_SAIR_HEIGHT);
		
		if (Gdx.input.isTouched()) {
				Gdx.app.exit();
		}
			
		
		} else {
		
			game.batch.draw(BotaoSairInativo, x, BOTAO_SAIR_X, BOTAO_SAIR_WIDTH, BOTAO_SAIR_HEIGHT);
		}

			
			
			x = SuperRaiders.WIDTH - 100 - BOTAO_CREDITOS_WIDTH;

			if (Gdx.input.getX()<x + BOTAO_CREDITOS_WIDTH && Gdx.input.getX()>x && SuperRaiders.HEIGHT - Gdx.input.getY()< BOTAO_CREDITOS_X + BOTAO_CREDITOS_HEIGHT && Gdx.input.getY()>BOTAO_CREDITOS_Y) {
				game.batch.draw(BotaoCreditosAtivo,x, BOTAO_CREDITOS_X, BOTAO_CREDITOS_WIDTH, BOTAO_CREDITOS_HEIGHT);
			
				if (Gdx.input.isTouched()) {
					//this.dispose();
					game.setScreen(new TelaCreditos(game));
				
					
					
			}
				
			
			} else {
				game.batch.draw(BotaoCreditosInativo, x, BOTAO_CREDITOS_X, BOTAO_CREDITOS_WIDTH, BOTAO_CREDITOS_HEIGHT);
			}		
			
			x = SuperRaiders.WIDTH + 50   - BOTAO_INSTRUCOES_WIDTH /2;

			if (Gdx.input.getX()<x + BOTAO_INSTRUCOES_WIDTH && Gdx.input.getX()>x && SuperRaiders.HEIGHT - Gdx.input.getY()< BOTAO_INSTRUCOES_X + BOTAO_INSTRUCOES_HEIGHT && Gdx.input.getY()>BOTAO_INSTRUCOES_Y) {
				game.batch.draw(BotaoInstrucoesAtivo,x, BOTAO_INSTRUCOES_X, BOTAO_INSTRUCOES_WIDTH, BOTAO_INICIAR_HEIGHT);
			
				if (Gdx.input.isTouched()) {
					//this.dispose();
					game.setScreen(new TelaInstrucoes(game));			}
				
			
			} else {
				game.batch.draw(BotaoInstrucoesInativo, x, BOTAO_INSTRUCOES_X, BOTAO_INSTRUCOES_WIDTH, BOTAO_INSTRUCOES_HEIGHT);
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
