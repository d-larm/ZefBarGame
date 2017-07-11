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
	
	public ObjectManager(){
		objects = new ArrayList<Objects>();
	}
	
	public ObjectManager(Player player, int tileSize){
		renderer.setColor(Color.RED);
		this.player = player;
		objects = new ArrayList<Objects>();
	}
	
	public Player getPlayer(){
		return player;
	}
	
	public void render(){
		renderer.begin(ShapeType.Filled);
		player.render(renderer);
		for(int i=0;i<objects.size();i++)
			objects.get(i).render(renderer);
		renderer.end();	
		translateObjects(vx,vy);
		decelerate();
	}
	
	public void render(boolean selectMode){
		renderer.begin(ShapeType.Filled);
		player.render(renderer);
		for(int i=0;i<objects.size();i++)
			objects.get(i).render(renderer);
		renderer.end();
		if(selectMode == false){
			translateObjects(vx,vy);
			decelerate();
		}
	}
	
	public void setProjectionMatrix(OrthographicCamera camera){
		renderer.setProjectionMatrix(camera.combined);
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
	
	
	public void decelerate(){ //Decelerates map velocity over time
		vx *= 0.9f;
		if(vx < 0.05f && vx > -0.05f)
			vx = 0;
		
		vy *= 0.9f;
		if(vy < 0.05f && vy > -0.05f)
			vy = 0;
		
	}
	
	public void translateObjects(float dx,float dy){
		player.translateIso(dx, dy);
		for(int i=0;i<objects.size();i++)
			objects.get(i).translateIso(dx,dy);
	}
		

}
