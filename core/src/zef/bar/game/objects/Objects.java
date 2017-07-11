package zef.bar.game.objects;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

import zef.bar.game.point.IsoPoint;

public class Objects {
	protected IsoPoint loc;
	protected float width;
	protected float height;
	private IsoPoint bottomLeft;
	private IsoPoint bottomRight;
	private IsoPoint topLeft;
	private IsoPoint topRight;
	private int tileSize;
	private ArrayList<IsoPoint> rect;
	
	public Objects(float x,float y, int tileSize){
		loc = new IsoPoint(x*tileSize,y*tileSize);
		this.tileSize=tileSize;
		width = tileSize;
		height = tileSize;
		bottomLeft = new IsoPoint(loc.getX(),loc.getY());
		bottomRight = new IsoPoint((loc.getX()+width),loc.getY());
		topRight = new IsoPoint((loc.getX()+width),(loc.getY()+height));
		topLeft = new IsoPoint(loc.getX(),(loc.getY()+height));
		rect = new ArrayList<IsoPoint>();
		calculateRegion();
		
	}
	
	
	public void calculateRegion(){
		bottomLeft.set(loc.getX(),loc.getY());
		bottomRight.set((loc.getX()+width),loc.getY());
		topRight.set((loc.getX()+width),(loc.getY()+height));
		topLeft.set(loc.getX(),(loc.getY()+height));
		updateIsoRectangle();
	}
	
	public void updateIsoRectangle(){ //Updates rectangle in isometric plane
		rect.clear();
		rect.add(bottomLeft);
		rect.add(bottomRight);
		rect.add(topRight);
		rect.add(topLeft);
	}
	
	public boolean isWithin(float x,float y){
		if(x > bottomLeft.getX() && x < bottomRight.getX() && y > bottomLeft.getY() && y < topLeft.getY())
			return true;
		else
			return false;
	}
	
	public boolean isWithinIso(float x,float y){
		if(x >= bottomLeft.getIsoX() && x <= bottomRight.getIsoX() && y >= bottomLeft.getIsoY() && y <= topLeft.getIsoY())
			return true;
		else
			return false;
	}
	
	public float getX(){
		return loc.getX();
	}
	
	public float getY(){
		return loc.getY();
	}
	
	public float getIsoX(){
		return loc.getIsoX();
	}
	
	public float getIsoY(){
		return loc.getIsoY();
	}
	
	public void setX(float x){
		loc.setX(x);
		calculateRegion();
	}
	
	public void setY(float y){
		loc.setY(y);
		calculateRegion();
	}
	
	public void translate(float dx,float dy){
		loc.translate(dx, dy);
		calculateRegion();
	}
	
	public void translateIso(float dx,float dy){
		loc.translateIso(dx, dy);
		calculateRegion();
		
	}
	
	public void render(ShapeRenderer sr){
		calculateRegion();
		sr.setColor(Color.RED);
		sr.line(rect.get(0).getIsoX(),rect.get(0).getIsoY(), rect.get(1).getIsoX(),rect.get(1).getIsoY());
		sr.line(rect.get(1).getIsoX(),rect.get(1).getIsoY(), rect.get(2).getIsoX(),rect.get(2).getIsoY());
		sr.line(rect.get(2).getIsoX(),rect.get(2).getIsoY(), rect.get(3).getIsoX(),rect.get(3).getIsoY());
		sr.line(rect.get(3).getIsoX(),rect.get(3).getIsoY(), rect.get(0).getIsoX(),rect.get(0).getIsoY());
	}
}
