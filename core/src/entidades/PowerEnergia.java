package entidades;


	import com.badlogic.gdx.Gdx;
	import com.badlogic.gdx.graphics.Texture;
	import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g3d.particles.influencers.ColorInfluencer.Random;

import ferramentas.Colisao;

	public class PowerEnergia {
		
		public static final int SPEED = 250;
		public static final int Width = 60;
		public static final int Height = 15;
		private static Texture texture3;
		//private static Texture texture2;
		
		float x, y;
		float energia_Posicao;
		
		Colisao rect;
		//Random random_posicao;
		
		
		
		
		public boolean remove = false;
		
		
		
		
		public PowerEnergia (float x) {
			this.x = x;
			this.y = Gdx.graphics.getWidth();
			this.rect = new Colisao (x, y, Height, Width);
			
			if (texture3 == null)
				texture3 = new Texture("energia.png");
			
			//if (texture2 == null)
				//texture2 = new Texture("inimigo2.png");
		}

		public void update (float deltaTime) {
			y -= SPEED * deltaTime;
			if (y < - Width)
				remove = true;
			rect.move(x,  y);
			
		}
			
			
		
		
		
		public void render (SpriteBatch batch) {
			batch.draw (texture3, y, x);
		//	batch.draw (texture2, y, x);

			
			
		}
			
			public Colisao getColisao () {
				return rect;
				
				
			}
			public float getX () {
				return x;
			}
			
			public float getY () {
				return y;
			}
	
			}
		


