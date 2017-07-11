package zef.bar.game.tile;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

import zef.bar.game.point.IsoPoint;

public class TileManager {
	
	private Tile[][] tileMap;
	private ShapeRenderer renderer;
	private int mapSize;
	private int tileSize;
	private float vx = 0;
	private float vy = 0;
	private IsoPoint tilePos;
	
	public TileManager(int mapSize,int tileSize){
		this.mapSize = mapSize;
		this.tileSize = tileSize;
		tileMap = new Tile[mapSize][mapSize];
		tilePos = new IsoPoint();
		renderer = new ShapeRenderer();
		renderer.setColor(Color.WHITE);
		addTiles();
	}
	
	public void addTiles(){
		for(int i=0;i<mapSize;i++){
			for(int j=0;j<mapSize;j++){
				tileMap[i][j] = new Tile(tileSize,i*tileSize,j*tileSize,renderer);
			}
		}
	}
	
	public int getTileSize() {
		return tileSize;
	}
	
	public void setProjectionMatrix(OrthographicCamera camera){
		renderer.setProjectionMatrix(camera.combined);
	}
	
	public void render(){
		renderer.begin(ShapeType.Line);
		for(int i=0;i<mapSize;i++)
			for(int j=0;j<mapSize;j++)
				tileMap[i][j].renderIso();
		renderer.end();
		translateMap(vx,vy);
		decelerate();
	}
	
	public void render(boolean selectMode){
		renderer.begin(ShapeType.Line);
		for(int i=0;i<mapSize;i++){
			for(int j=0;j<mapSize;j++){
				tileMap[i][j].renderIso();
			}
		}
		renderer.end();
		if(selectMode == false){
			translateMap(vx,vy);
			decelerate();
		}
	}
	
//	public Tile getTile(float x,float y){
//		tilePos.set(x, y);
//		int xCoord = (int)Math.abs(tilePos.getX()/tileSize);
//		int yCoord = (int)Math.abs(tilePos.getY()/tileSize);
//		return tileMap[xCoord][yCoord];
//		
//	}
	
	public Tile getTile(float x,float y){
		tilePos.set(x, y);
		for(int i=0;i<mapSize;i++)
			for(int j=0;j<mapSize;j++)
				if(tileMap[i][j].isWithinIso(tilePos.getX(),tilePos.getY()))
					return tileMap[i][j];
		return new Tile(0,renderer);
		
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
	
	public void translateMap(float dx,float dy){
		for(int i=0;i<mapSize;i++){
			for(int j=0;j<mapSize;j++){
				tileMap[i][j].translateIso(dx,dy);
			}
		}
	}

}
