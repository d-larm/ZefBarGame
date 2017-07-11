package zef.bar.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class GameCamera extends OrthographicCamera {
	
	private float vx = 0;
	private float vy = 0;
	private static float aspectRatio = (float) Gdx.graphics.getWidth() / (float) Gdx.graphics.getHeight();
	private FitViewport viewport;
	
	public GameCamera(){
		super(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
		viewport = new FitViewport(100, 100, this);
	}
	
	public void pan(boolean selectMode){
		if(selectMode == false){
			translate(vx,vy);
			decelerate();
		}
	}
	
	public void pan(){
		translate(vx,vy);
		decelerate();
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
	
	public void setVelocity(float vx,float vy){
		this.vx = vx;
		this.vy = vy;
	}


}
