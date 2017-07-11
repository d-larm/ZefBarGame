package Interface;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class UI {
	
	private SpriteBatch batch;
    private BitmapFont  font;
	
	public UI() {
		 batch = new SpriteBatch();
         font = new BitmapFont(Gdx.files.internal("fonts/main.fnt"),
                         Gdx.files.internal("fonts/main.png"), false);
	}
	
	public void render(String str, int i, int j) {
		batch.begin();
	    font.setColor(Color.WHITE);
	    font.draw(batch, str, i, j);
	    batch.end();
	}
	
	public void render() {
		batch.begin();
	    font.setColor(Color.WHITE);
	    font.draw(batch, "Hello world", 25, 160);
	    batch.end();
	}
	
}
