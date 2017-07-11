package zef.bar.game.point;

public class IsoPoint{ //Class for isometric and Cartesian point
	float x;
	float y;
	float isoX;
	float isoY;
	
	public IsoPoint(float x, float y){
		//Sets points on Cartesian plane
		this.x = x;
		this.y = y;
		calcIsoPoint(); //Calculates the point on the isometric plane
	}
	public IsoPoint(IsoPoint point){
		x = point.getX();
		y = point.getY();
		calcIsoPoint();
	}
	public IsoPoint(){
		x = 0;
		y = 0;
		calcIsoPoint();
	}
	
	public float getIsoX(){
		//Returns the x location on the isometric plane
		return isoX;
	}
	public float getIsoY(){
		//Returns the y location on the isometric plane
		return isoY;
	}
	public float getX(){
		//Returns the x location on the Cartesian plane
		return x;
	}
	
	public float getY(){
		//Returns the y location on the Cartesian plane
		return y;
	}

	public void translateIso(float transX,float transY){
		//Translates the point on the isometric plane
		isoX += transX;
		isoY += transY;
		calcCartPoint();
	}
	
	public void translate(float transX, float transY){
		//Translates the point on the Cartesian plane
		x += transX;
		y+= transY;
		calcIsoPoint();
	}
	

	public void calcIsoX(){
		//Calculates the x location in the isometric plane using Cartesian coordinates
		isoX = x - y;
	}
	
	public void calcIsoY(){
		//Calculates the y location in the isometric plane using Cartesian coordinates
		isoY  = (x+y)/2;
	}
	
	public void calcIsoPoint(){
		//Calculates the location in the isometric plane using Cartesian coordinates
		calcIsoX();
		calcIsoY();
	}
	public void calcCartX(){
		//Calculates x the location in the Cartesian plane using isometric coordinates
		x = (2 * isoY + isoX) / 2;
	}
	public void calcCartY(){
		//Calculates the y location in the Cartesian plane using isometric coordinates
		y = (2 * isoY - isoX) / 2;
	}
	
	public void calcCartPoint(){
		//Calculates the location in the Cartesian plane using isometric coordinates
		calcCartX();
		calcCartY();
	}
	
	public void setX(float newX){
		//Sets the x location in the Cartesian plane
		x = newX;
		calcIsoPoint();
	}
	
	public void setY(float newY){
		//Sets the y location in the Cartesian plane
		y = newY;
		calcIsoPoint();
	}
	
	public void setIsoX(float newX){
		//Sets the x location in the isometric plane
		isoX = newX;
		calcCartPoint();
	}
	public void setIsoY(float newY){
		//Sets the y location in the isometric plane
		isoY = newY;
		calcCartPoint();
	}
	
	public void set(float newX, float newY){
		//Sets the location in the Cartesian plane
		setX(newX);
		setY(newY);
		calcIsoPoint();
	}
	

	

}
