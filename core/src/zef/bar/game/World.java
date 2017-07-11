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

public class World implements InputProcessor{
	
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
		tileManager = new TileManager(WORLD_SIZE,TILE_SIZE); //Creates a new tile manager with WORLD_SIZE*WORLD_SIZE tiles of size TILE_SIZE
		Gdx.input.setInputProcessor(this); //Sets the input processor to read inputs
		Player player = new Player(0,0, TILE_SIZE); //Creates a new player
		objManager = new ObjectManager(player, TILE_SIZE); //Creates a new object manager
		touchPoint = new IsoPoint(); // Converts the touch point coordinates into an IsoPoint
		camera = new GameCamera(); //Instantiates a new Game Camera
		camera.translate(player.getIsoX(),player.getIsoY()); //Translates the camera to the player's position
		touchPos = new Vector3(); //Vector containing the camera's projection coordinates
		
		
	}
	
	public void render(){
		camera.update(); //Updates the camera
		camera.pan(playerSelectMode); //Pans the camera depending on the selection mode
		tileManager.setProjectionMatrix(camera); //Sets the projection matrix of rendered tiles to the camera
		tileManager.render();
		objManager.setProjectionMatrix(camera); //Sets the projection matrix of rendered objects to the camera
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
		touchPos.set(screenX,screenY,0); //Gets the touch position vector (Z coordinate is 0)
		camera.unproject(touchPos); //Unprojects the touch position into world coordinates
		touchPoint.set(touchPos.x, touchPos.y); //Sets the touch point in the world as an Isometric Point
		if(objManager.getPlayer().isWithinIso(touchPos.x,touchPos.y)){ //Checks whether the player was touched
			playerSelectMode = true; //Switches to select mode
			tileManager.getTile(touchPos.x, touchPos.y).setColor(Color.BLUE); //Highlights selected tile
			Gdx.app.log("Print", "World:("+(int)(touchPos.x)+","+(int)(touchPos.y)+"), Screen:("+screenX+","+screenY+")");
			Gdx.app.log("Print", "IsoWorld:("+(int)(touchPoint.getIsoX())+","+(int)(touchPoint.getIsoY())+")");
			Gdx.app.log("Print", "Select Mode Enabled");
		}
		
		return true;

	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		playerSelectMode = false; //Switches to panning mode
		Gdx.app.log("Print", "Select Mode Disabled");
		for(int i=0;i<selectedTiles.size();i++) //Unselected selected tiles when select mode 
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
		
	    if(deltaX != 0 || deltaY != 0){ //Sets the velocity of the camera to the delta movements whilst panning
	    	camera.setVelocity(-deltaX,deltaY); 
	    } 
	    if(playerSelectMode){ //Highlights selected tiles while the touch is down and add them to an array of selected tiles
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
