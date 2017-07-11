package zef.bar.game;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import Interface.UI;
import zef.bar.game.tile.TileManager;


public class Game implements ApplicationListener{
	ShapeRenderer sr;
	private World world;
	private UI GUI;
	
	@Override
	public void create() {
		// TODO Auto-generated method stub
		sr = new ShapeRenderer();
		sr.setColor(Color.WHITE);
		world = new World();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render() {
		Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT );
//		GUI.render("zefBar", 100,100);
		world.render(); //Renders the world
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
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	
}
