package ru.serious07.game.utilities;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;

public class ConvertCoords {
	public static Vector2Int screenToTileMap(OrthographicCamera gameCamera, Vector3 point, 
												int tileWidth, int tileHeight, Vector2Int gameSize) {
	    Vector3 worldCoords = gameCamera.unproject(point);
	    worldCoords.x += gameSize.x / 2;
	    worldCoords.y += gameSize.y / 2;
	    
	    worldCoords.x /= tileWidth;
	    worldCoords.y /= tileHeight;
	    
	    Vector2Int result = new Vector2Int(worldCoords);
	    
	    System.out.println("x: " + result.x + " y: " + result.y);
	    
	    return result;
	}
	
	public static Vector2Int screenToTileMap(OrthographicCamera gameCamera, int screenX, int screenY, 
												int tileWidth, int tileHeight, Vector2Int gameSize) {
		return screenToTileMap(gameCamera, new Vector3(screenX, screenY, 1), tileWidth, tileHeight, gameSize);
	}
	
	public static Vector3 screenToWorldPos(OrthographicCamera gameCamera, Vector3 point) {
		return gameCamera.unproject(point);
	}
	
	public static Vector3 screenToWorldPos(OrthographicCamera gameCamera, int screenX, int screenY) {
		return screenToWorldPos(gameCamera, new Vector3(screenX, screenY, 1));
	}
}
