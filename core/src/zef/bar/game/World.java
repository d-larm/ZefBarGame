package zef.bar.game;


import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import zef.bar.game.objects.ObjectManager;
import zef.bar.game.objects.Player;
import zef.bar.game.point.IsoPoint;
import zef.bar.game.tile.*;

public class World implements InputProcessor, GestureListener{
	
	private boolean playerSelectMode = false;
	private TileManager tileManager;
	private ObjectManager objManager;
	GestureDetector gestureDetector;
	private IsoPoint touchPoint;
	private GameCamera camera;
	private int WORLD_SIZE = 10;
	
	private int TILE_SIZE=(int)(1024);
	Vector3 touchPos;
	private float vx = 0;
	private float vy = 0;
	private ArrayList<Tile> selectedTiles = new ArrayList<Tile>();
	
	
	public World(){
		tileManager = new TileManager(WORLD_SIZE,TILE_SIZE);
		gestureDetector = new GestureDetector(this);
		Gdx.input.setInputProcessor(this);
		Player player = new Player(0,0, TILE_SIZE);
		objManager = new ObjectManager(player, TILE_SIZE);
		touchPoint = new IsoPoint();
		camera = new GameCamera();
		camera.translate(player.getIsoX(),player.getIsoY());
		touchPos = new Vector3();
		
		
	}
	
	public void render(){
		camera.update();
		camera.pan(playerSelectMode);
		tileManager.setProjectionMatrix(camera);
		tileManager.render();
		objManager.setProjectionMatrix(camera);
		objManager.render();
	}

	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		touchPos.set(screenX,screenY,0);
		camera.unproject(touchPos);
		touchPoint.set(touchPos.x, touchPos.y);
		if(objManager.getPlayer().isWithinIso(touchPos.x,touchPos.y)){
			playerSelectMode = true;
			tileManager.getTile(touchPos.x, touchPos.y).setColor(Color.BLUE);
			Gdx.app.log("Print", "World:("+(int)(touchPos.x)+","+(int)(touchPos.y)+"), Screen:("+screenX+","+screenY+")");
			Gdx.app.log("Print", "IsoWorld:("+(int)(touchPoint.getIsoX())+","+(int)(touchPoint.getIsoY())+")");
			Gdx.app.log("Print", "Select Mode Enabled");
		}
		
		return true;

	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		playerSelectMode = false;
		Gdx.app.log("Print", "Select Mode Disabled");
		for(int i=0;i<selectedTiles.size();i++)
			selectedTiles.get(i).setColor(Color.WHITE);
		return true;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		float deltaX = Gdx.input.getDeltaX();
	    float deltaY = Gdx.input.getDeltaY();
   
	    touchPos.set(screenX,screenY,0);
		camera.unproject(touchPos);
		
	    if(deltaX != 0 || deltaY != 0){
//	    	tileManager.setVelocity(deltaX, -deltaY);
//	    	objManager.setVelocity(deltaX, -deltaY);
	    	camera.setVelocity(-deltaX,deltaY);
	    } 
	    if(playerSelectMode){
	    	Tile currentTile = tileManager.getTile(touchPos.x, touchPos.y);
	    	currentTile.setColor(Color.BLUE);
	    	selectedTiles.add(currentTile);
	    }
	    return true;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(float x, float y, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean tap(float x, float y, int count, int button) {
		// TODO Auto-generated method stub
//		touchPos.set(x,y,0);
//		camera.unproject(touchPos);
//		touchPoint.set(touchPos.x, touchPos.y);
//		if(objManager.getPlayer().isWithinIso(touchPos.x,touchPos.y)){
//			playerSelectMode = true;
//			objManager.getPlayer().translate(256,256);
//		}
//		tileManager.getTile(touchPos.x, touchPos.y).setColor(Color.BLUE);
//		Gdx.app.log("Print", "World:("+(int)(touchPos.x)+","+(int)(touchPos.y)+"), Screen:("+x+","+y+")");
//		Gdx.app.log("Print", "IsoWorld:("+(int)(touchPoint.getIsoX())+","+(int)(touchPoint.getIsoY())+")");
//		Gdx.app.log("Print", "Select Mode Enabled");
		return true;
	}

	@Override
	public boolean longPress(float x, float y) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean fling(float velocityX, float velocityY, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean pan(float x, float y, float deltaX, float deltaY) {
		// TODO Auto-generated method stub
		touchPos.set(x,y,0);
		camera.unproject(touchPos);
		
	    if(deltaX != 0 || deltaY != 0){
//	    	tileManager.setVelocity(deltaX, -deltaY);
//	    	objManager.setVelocity(deltaX, -deltaY);
	    	camera.setVelocity(-deltaX,deltaY);
	    }
	    return true;
	}

	@Override
	public boolean panStop(float x, float y, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean zoom(float initialDistance, float distance) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void pinchStop() {
		// TODO Auto-generated method stub
		
	}
	
	public void setVelocity(float vx,float vy){
		this.vx = vx;
		this.vy = vy;
	}
	
	public float getVelocityX(){
		return vx;
	}
	
	public float getVelocityY(){
		return vy;
	}

}
