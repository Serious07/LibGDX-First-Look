package ru.serious07.game.input;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapRenderer;
import com.badlogic.gdx.math.Vector3;

import ru.serious07.game.innterfaces.listners.input.keyboard.KeyPressed;
import ru.serious07.game.innterfaces.listners.input.keyboard.KeyReleased;

public class CameraControl implements KeyPressed, KeyReleased {
	private OrthographicCamera camera;
	
	private MapRenderer mapRenderer;
	private float speed;
	
	private boolean isUpKeyPressed = false;
	private boolean isDownKeyPressed = false;
	private boolean isLeftKeyPressed = false;
	private boolean isRightKeyPressed = false;
	
	private Vector3 cameraDirection;
	
	public CameraControl(OrthographicCamera gameCamera, float speed, MapRenderer mapRenderer) {
		camera = gameCamera;
		this.speed = speed;
		this.mapRenderer = mapRenderer;
	}
	
	public void update(float dt) {
		cameraDirection = new Vector3(0, 0, 0);
		
		if(isUpKeyPressed) {
			cameraDirection.add(new Vector3(0, speed * dt, 0));
		}
		
		if(isDownKeyPressed) {
			cameraDirection.add(new Vector3(0, speed * dt * -1, 0));
		}
		
		if(isLeftKeyPressed) {
			cameraDirection.add(new Vector3(speed * dt * -1, 0, 0));
		}
		
		if(isRightKeyPressed) {
			cameraDirection.add(new Vector3(speed * dt, 0, 0));
		}
		
		camera.position.add(cameraDirection);
	}
	
	@Override
	public void keyRelesed(int keyCode) {
		if(keyCode == Input.Keys.UP) {
			isUpKeyPressed = false;
		}
		if(keyCode == Input.Keys.DOWN) {
			isDownKeyPressed = false;
		}
		if(keyCode == Input.Keys.LEFT) {
			isLeftKeyPressed = false;
		}
		if(keyCode == Input.Keys.RIGHT) {
			isRightKeyPressed = false;
		}
	}

	@Override
	public void keyPressed(int keyCode) {
		if(keyCode == Input.Keys.UP) {
			isUpKeyPressed = true;
		}
		if(keyCode == Input.Keys.DOWN) {
			isDownKeyPressed = true;
		}
		if(keyCode == Input.Keys.LEFT) {
			isLeftKeyPressed = true;
		}
		if(keyCode == Input.Keys.RIGHT) {
			isRightKeyPressed = true;
		}
	}
}