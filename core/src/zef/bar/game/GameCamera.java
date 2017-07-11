package zef.bar.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class GameCamera extends OrthographicCamera {
	
	private float vx = 0;
	private float vy = 0;
	private static float aspectRatio = (float) Gdx.graphics.getWidth() / (float) Gdx.graphics.getHeight(); //Gets the aspect ratio of the device
	private FitViewport viewport;
	
	public GameCamera(){
		super(Gdx.graphics.getWidth(),Gdx.graphics.getHeight()); //Creates new camera of screen width and height
		viewport = new FitViewport(1920, 1080, this); //Sets a fixed viewport size of 1080p
	}
	
	public void pan(boolean selectMode){ //Pans camera around the world depending on the mode
		if(selectMode == false){
			translate(vx,vy);
			decelerate();
		}
	}
	
	public void pan(){ //Pans camera around the world regardless of the mode
		translate(vx,vy);
		decelerate();
	}
	
	public float getVelocityX(){ //Gets the X velocity of the camera
		return vx;
	}
	
	public float getVelocityY(){ //Gets the Y coordinates of the camera
		return vy;
	}
	
	
	
	public void decelerate(){ //Decelerates the camera velocity
		vx *= 0.9f;
		if(vx < 0.05f && vx > -0.05f)
			vx = 0;
		
		vy *= 0.9f;
		if(vy < 0.05f && vy > -0.05f)
			vy = 0;
		
	}
	
	public void setVelocity(float vx,float vy){ //Sets the X and Y velocities of the camera
		this.vx = vx;
		this.vy = vy;
	}


}
