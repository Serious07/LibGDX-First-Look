package ru.serious07.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.MapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;

import ru.serious07.game.Main;
import ru.serious07.game.innterfaces.listners.input.mouse.MouseButtonPressed;
import ru.serious07.game.input.CameraControl;
import ru.serious07.game.utilities.ConvertCoords;
import ru.serious07.game.utilities.Vector2Int;

public class TiledWorld extends ScreenAdapter implements MouseButtonPressed {
	private Main game;
	
	private Texture allTiles;
	private TextureRegion textureGrass;
	private TextureRegion textureDirt;
	private TextureRegion textureStone;
	
	private TiledMap tiledMap;
	private MapLayers layers;
	private TiledMapTileLayer layer1;
	
	private MapRenderer mapRenderer;
	
	// Cells
	private Cell cellGrass;
	private Cell cellDirt;
	private Cell cellStone;
	
	private CameraControl cameraControl;
	
	private OrthographicCamera camera;
	
	public static final int tileWidth = 32;
	public static final int tileHeight = 32;
	
	public TiledWorld(Main main) {
		game = main;
		
		camera = game.gameCamera;
	}

	@Override
	public void show() {
		allTiles = new Texture(Gdx.files.internal("sprites/tiles.png"));
		textureGrass = new TextureRegion(allTiles, tileWidth * 0 + 2, tileHeight * 0 + 2, tileWidth, tileHeight);
		textureDirt = new TextureRegion(allTiles, tileWidth * 1 + 6, tileHeight * 0 + 2, tileWidth, tileHeight);
		textureStone = new TextureRegion(allTiles, tileWidth * 2 + 10, tileHeight * 0 + 2, tileWidth, tileHeight);
		
		tiledMap = new TiledMap();
		
		layers = tiledMap.getLayers();
		
		layer1 = new TiledMapTileLayer(100, 100, tileWidth, tileHeight);
		
		cellGrass = new Cell();
		cellGrass.setTile(new StaticTiledMapTile(textureGrass));
		layer1.setCell(0, 0, cellGrass);
		
		cellDirt = new Cell();
		cellDirt.setTile(new StaticTiledMapTile(textureDirt));
		layer1.setCell(1, 0, cellDirt);
		
		cellStone = new Cell();
		cellStone.setTile(new StaticTiledMapTile(textureStone));
		layer1.setCell(2, 0, cellStone);
		
		layers.add(layer1);
		
		mapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
		
		// Camera controls
		cameraControl = new CameraControl(game.gameCamera, 1000, mapRenderer);
		game.mouseAndKeyboardInput.AddListenerKeyboardKeyPressed(cameraControl);
		game.mouseAndKeyboardInput.AddListenerKeyboardKeyRelesed(cameraControl);
		
		camera.position.set(new Vector3(game.gameSize.x / 2, (game.gameSize.y / 2) * 1, 0)); 
	}
	
	@Override
	public void render(float delta) {
		ScreenUtils.clear(165f/256f, 194f/256f, 209f/256f, 1);
		
		// Game
		cameraControl.update(delta);
		
		mapRenderer.setView(camera.combined, 0, 0, 320, 180);
		mapRenderer.render();
		
		camera.update();
		
		// HUD
		game.hudCamera.update();
		game.spriteBatchHUD.setProjectionMatrix(game.hudCamera.combined);
		game.spriteBatchHUD.begin();
		game.font.draw(game.spriteBatchHUD, "FPS=" + Gdx.graphics.getFramesPerSecond(), 10, game.hudCamera.viewportHeight - 10);
		game.font.draw(game.spriteBatchHUD, "Cursor World x: " + game.mouseAndKeyboardInput.mouseXWorldPos + " y: " + 
						game.mouseAndKeyboardInput.mouseYWorldPos, 10, game.hudCamera.viewportHeight - 30);
		game.font.draw(game.spriteBatchHUD, "Camera Pos x: " + game.gameCamera.position.x + " y: " + 
				game.gameCamera.position.y, 10, game.hudCamera.viewportHeight - 50);
		game.spriteBatchHUD.end();
	}
	
	@Override
	public void dispose () {
		game.shapeRenderer.dispose();
		game.spriteBatchGame.dispose();
		game.spriteBatchHUD.dispose();
	}
	
	@Override
    public void resize(int width, int height) {
		game.viewportHUD.update(width, height);
    }
	
	@Override
	public void mouseButtonPressed(int buttonId, int screenX, int scrrenY) {
		if(buttonId == Input.Buttons.LEFT) {
			Vector2Int tiledPos = ConvertCoords.screenToTileMap(game.gameCamera, screenX, scrrenY, tileWidth, tileHeight, game.gameSize);
			
			layer1.setCell(tiledPos.x, tiledPos.y, cellDirt);
		}
	}
}