package zef.bar.game.tile;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import zef.bar.game.point.IsoPoint;

public class Tile {
	
	private float size;
	private IsoPoint loc;
	private ShapeRenderer sr;
	private ArrayList<IsoPoint> rect;
	private IsoPoint bottomLeft;
	private IsoPoint bottomRight;
	private IsoPoint topLeft;
	private IsoPoint topRight;
	private Color color;
	private int range = 30;
	private IsoPoint minX = new IsoPoint();
	private IsoPoint maxX = new IsoPoint();
	private IsoPoint minY = new IsoPoint();
	private IsoPoint maxY = new IsoPoint();
	
	public Tile(float size, ShapeRenderer renderer) {
		//Creates a new square tile with length size and rendered with renderer
		this.size=size;
		sr = renderer;
		rect = new ArrayList<IsoPoint>();
		loc = new IsoPoint(0,0);
		bottomLeft = new IsoPoint(loc.getX(),loc.getY());
		bottomRight = new IsoPoint((loc.getX()+size),loc.getY());
		topRight = new IsoPoint((loc.getX()+size),(loc.getY()+size));
		topLeft = new IsoPoint(loc.getX(),(loc.getY()+size));
		updateIsoRectangle();
		color = new Color(Color.WHITE);
	}
	
	
	
	public Tile(float size,float x, float y, ShapeRenderer renderer) {
		//Creates a new square tile with length size at position (x,y) and rendered with renderer
		this.size=size;
		loc = new IsoPoint(x,y);
		sr = renderer;
		rect = new ArrayList<IsoPoint>();
		bottomLeft = new IsoPoint(loc.getX(),loc.getY());
		bottomRight = new IsoPoint((loc.getX()+size),loc.getY());
		topRight = new IsoPoint((loc.getX()+size),(loc.getY()+size));
		topLeft = new IsoPoint(loc.getX(),(loc.getY()+size));
		updateIsoRectangle();
		color = new Color(Color.WHITE);
	}
	
	public void setColor(Color newColor){
		color = newColor;
	}


	public float getSize() { //Returns size of tile
		return size;
	}
	
	public void calculateRegion(){
		bottomLeft.set(loc.getX(),loc.getY());
		bottomRight.set((loc.getX()+size),loc.getY());
		topRight.set((loc.getX()+size),(loc.getY()+size));
		topLeft.set(loc.getX(),(loc.getY()+size));
		updateIsoRectangle();
	}
	
	public void updateIsoRectangle(){ //Updates rectangle in isometric plane
		rect.clear();
		rect.add(bottomLeft);
		rect.add(bottomRight);
		rect.add(topRight);
		rect.add(topLeft);
	}

	public void setSize(float size) { //Sets size of tile
		this.size = size;
	}

	public float getX() {//Gets X location of tile
		return loc.getX();
	}

	public void setX(float xLoc) {//Sets X location of tile
		loc.setX(xLoc);
		calculateRegion();
	}

	public float getY() {//Gets Y location of tile
		return loc.getY();
	}

	public void setY(float yLoc) {//Sets Y location of tile
		loc.setY(yLoc);
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
	
	public boolean isWithinIso(float x,float y){
		minX.set(bottomLeft.getX()+range,bottomLeft.getY());
		maxX.set(bottomRight.getX()-range,bottomRight.getY());
		minY.set(bottomLeft.getX(),bottomLeft.getY()+range);
		maxY.set(topLeft.getX(),topLeft.getY()-range);
		
		if(x >= minX.getIsoX() && x <= maxX.getIsoX() && y >= minY.getIsoY() && y <= maxY.getIsoY())
			return true;
		else
			return false;
	}
	
	public void renderIso(){//Renders the tile on screen on the isometric plane
		sr.setColor(color);
		sr.line(rect.get(0).getIsoX(),rect.get(0).getIsoY(), rect.get(1).getIsoX(),rect.get(1).getIsoY());
		sr.line(rect.get(1).getIsoX(),rect.get(1).getIsoY(), rect.get(2).getIsoX(),rect.get(2).getIsoY());
		sr.line(rect.get(2).getIsoX(),rect.get(2).getIsoY(), rect.get(3).getIsoX(),rect.get(3).getIsoY());
		sr.line(rect.get(3).getIsoX(),rect.get(3).getIsoY(), rect.get(0).getIsoX(),rect.get(0).getIsoY());
	}
	public void render(){//Renders the tile on screen on the Cartesian plane
		sr.rect(getX(), getY(), getSize(), getSize());
	}
	 
	
}
