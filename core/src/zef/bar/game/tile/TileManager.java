package zef.bar.game.tile;

import com.badlogic.gdx.Gdx;
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
	
	public void addTiles(){ //Adds tiles to the tileMap array
		for(int i=0;i<mapSize;i++){
			for(int j=0;j<mapSize;j++){
				tileMap[i][j] = new Tile(tileSize,i*tileSize,j*tileSize,renderer);
			}
		}
	}
	
	public int getTileSize() { //Gets the size of a single tile
		return tileSize;
	}
	
	public void setProjectionMatrix(OrthographicCamera camera){ //Sets the projection matrix to the camera so all objects are rendered relative to the camera coordinates
		renderer.setProjectionMatrix(camera.combined);
	}
	
	public void render(){ //Renders all tiles in the world
		renderer.begin(ShapeType.Line);
		for(int i=0;i<mapSize;i++)
			for(int j=0;j<mapSize;j++)
				tileMap[i][j].renderIso();
		renderer.end();
	}
	
	public Tile getTile(float x,float y){ //Returns the tile which contains world coordinates (x,y) 
		if(x >= 0 && y >= 0){
			int xIndex = Math.floorDiv((int)x, tileSize);
			int yIndex = Math.floorDiv((int)y, tileSize);
//			Gdx.app.log("Print", "Tile:("+xIndex+","+yIndex+")");
			return tileMap[xIndex][yIndex];
		}else
			return null;
	
//		tilePos.set(x, y);
//		for(int i=0;i<mapSize;i++)
//			for(int j=0;j<mapSize;j++)
//				if(tileMap[i][j].isWithin(tilePos.getX(),tilePos.getY())){
//					
//					Gdx.app.log("Print", "Tile Position: "+tileMap[i][j].getX()+","+tileMap[i][j].getY()+")");
//					return tileMap[i][j];
//				}
//		
//		return new Tile(0,renderer);
		
	}


}
