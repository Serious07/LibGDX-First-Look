package ru.serious07.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import ru.serious07.game.input.MouseAndKeyboardInput;
import ru.serious07.game.screens.TiledWorld;
import ru.serious07.game.utilities.Vector2Int;

public class Main extends Game {
	// Game
	public OrthographicCamera gameCamera;
	public SpriteBatch spriteBatchGame;
	public ShapeRenderer shapeRenderer;
	public Viewport viewportGame;
	
	// Textures
	public Texture slot;
	
	// HUD
	public OrthographicCamera hudCamera;
	public SpriteBatch spriteBatchHUD;
	public Viewport viewportHUD;
	
	// Fonts
	public BitmapFont font;
	
	// Sizes
	public Vector2Int gameSize; 
	public Vector2Int HUDsize;
	public Vector2Int windowSize;
	
	// Debug vars
	public float mouseX;
	public float mouseY;
	public MouseAndKeyboardInput mouseAndKeyboardInput;
	
	private TiledWorld tiledWorld;
	
	@Override
	public void create() {
		InitSizes(320, 180, 3);
		InitTextures();
		
		// Window Size
		Gdx.graphics.setWindowedMode(windowSize.x, windowSize.y);
		
		// Game debug shapes
		shapeRenderer = new ShapeRenderer();
		
		// Game Camera
		spriteBatchGame = new SpriteBatch();
		
		gameCamera = new OrthographicCamera(gameSize.x, gameSize.y);
		gameCamera.position.set(0, 0, 1);
		
		viewportGame = new FitViewport(gameSize.x, gameSize.y, gameCamera);
		viewportGame.apply();
		
		// HUD Camera
		spriteBatchHUD = new SpriteBatch();
		
		hudCamera = new OrthographicCamera(HUDsize.x, HUDsize.y);
		hudCamera.position.set(hudCamera.viewportWidth/ 2, hudCamera.viewportHeight / 2, 1.0f);
		
		viewportHUD = new FitViewport(HUDsize.x, HUDsize.y, hudCamera);
        viewportHUD.apply();
        
		font = new BitmapFont(Gdx.files.internal("fonts/fontSmall10.fnt"), Gdx.files.internal("fonts/fontSmall10.png"), false);
		font.setColor(Color.RED);
		
		// Input
		mouseAndKeyboardInput = new MouseAndKeyboardInput(gameCamera);
		Gdx.input.setInputProcessor(mouseAndKeyboardInput);
		
		tiledWorld = new TiledWorld(this);
		
		mouseAndKeyboardInput.AddListenerMouseButtonPressed(tiledWorld);
		
		setScreen(tiledWorld);
	}
	
	private void InitSizes(int gameWidth, int gameHeight, int windowScale) {
		gameSize = new Vector2Int(gameWidth, gameHeight);
		windowSize = new Vector2Int(gameSize.x * windowScale, gameSize.y * windowScale);
		HUDsize = new Vector2Int(gameSize.x * windowScale, gameSize.y * windowScale);
	}
	
	private void InitTextures() {
		slot = new Texture(Gdx.files.internal("sprites/slot.png"));
	}
}
