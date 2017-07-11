package zef.bar.game.objects;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class ObjectManager {
	private Player player;
	private ArrayList<Objects> objects;
	private ShapeRenderer renderer = new ShapeRenderer();
	private float vx = 0;
	private float vy = 0;
	private int tileSize;
	
	public ObjectManager(){ //Instantiates the object list
		objects = new ArrayList<Objects>();
	}
	
	public ObjectManager(Player player, int tileSize){
		renderer.setColor(Color.RED); //Sets color of objects to red
		this.player = player;
		objects = new ArrayList<Objects>();
	}
	
	public Player getPlayer(){ //Gets the player object
		return player;
	}
	
	public void render(){ //Renders all the objects in the world
		renderer.begin(ShapeType.Filled);
		player.render(renderer);
		for(int i=0;i<objects.size();i++)
			objects.get(i).render(renderer);
		renderer.end();	
	}
	
	public void setProjectionMatrix(OrthographicCamera camera){ //Sets the projection matrix to the camera so all objects are rendered relative to the camera coordinates
		renderer.setProjectionMatrix(camera.combined);
	}
	
	public void addObject(Objects newObject){
		objects.add(newObject);
	}
	
		

}
