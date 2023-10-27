package ru.serious07.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.ScreenUtils;

import ru.serious07.game.Main;

public class GameScreen extends ScreenAdapter {
	// Main class
	private Main game;
	
	public GameScreen(Main game) {
		this.game = game;
	}
	
	@Override
	public void show() {
		
	}
	
	@Override
	public void render(float delta) {
		ScreenUtils.clear(165f/256f, 194f/256f, 209f/256f, 1);
		
		// Game
		game.gameCamera.update();
		
		game.shapeRenderer.setProjectionMatrix(game.gameCamera.combined);
		game.shapeRenderer.setAutoShapeType(true);
		game.shapeRenderer.begin();
		game.shapeRenderer.setColor(Color.RED);
		
		for(int x = -320; x <= 320; x += 10) {
			game.shapeRenderer.line(x, 180, x, -180);
		}
		
		for(int y = -180; y <= 180; y += 10) {
			game.shapeRenderer.line(-320, y, 320, y);
		}
		
		game.shapeRenderer.end();
		
		game.spriteBatchGame.setProjectionMatrix(game.gameCamera.combined);
		game.spriteBatchGame.begin();
		game.spriteBatchGame.draw(game.slot, 0, 0);
		
		game.spriteBatchGame.end();
		
		// HUD
		game.hudCamera.update();
		game.spriteBatchHUD.setProjectionMatrix(game.hudCamera.combined);
		game.spriteBatchHUD.begin();
		game.font.draw(game.spriteBatchHUD, "FPS=" + Gdx.graphics.getFramesPerSecond(), 10, game.hudCamera.viewportHeight - 10);
		game.font.draw(game.spriteBatchHUD, "x: " + game.mouseAndKeyboardInput.mouseXWorldPos + " y: " + 
						game.mouseAndKeyboardInput.mouseYWorldPos, 10, game.hudCamera.viewportHeight - 30);
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
}