package ferramentas;

public class Colisao implements Runnable {

	float x, y;
	int width, height;
	
	public Colisao (float x, float y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		
	}
	
	public void move (float x, float y) {
		this.x = x;
		this.y =y;
	}
	
	public boolean collideWith (Colisao rect) {
		return x < rect.x + rect.height && y < rect.y + rect.width && x + height > rect.x && y + width > rect.width;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
}

