package ru.serious07.game.input;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;

import ru.serious07.game.innterfaces.listners.input.keyboard.KeyPressed;
import ru.serious07.game.innterfaces.listners.input.keyboard.KeyReleased;
import ru.serious07.game.innterfaces.listners.input.mouse.MouseButtonPressed;
import ru.serious07.game.innterfaces.listners.input.mouse.MouseButtonRelesed;
import ru.serious07.game.innterfaces.listners.input.mouse.MouseMoved;
import ru.serious07.game.utilities.ConvertCoords;

public class MouseAndKeyboardInput implements InputProcessor{
	// Mouse
	// Mouse buttons
	private List<MouseButtonPressed> mouseButtonPressedListeners = new ArrayList<MouseButtonPressed>();
	private List<MouseButtonRelesed> mouseButtonReleasedListeners = new ArrayList<MouseButtonRelesed>();
	
	// Mouse moved
	private List<MouseMoved> mouseMovedListeners = new ArrayList<MouseMoved>();
	
	// Keyboard
	// Keyboard keys
	private List<KeyPressed> keyPressedListeners = new ArrayList<KeyPressed>();
	private List<KeyReleased> keyReleasedListeners = new ArrayList<KeyReleased>();
	
	public float mouseXWorldPos;
	public float mouseYWorldPos;
	
	private OrthographicCamera camera;
	
	// Listeners
	// Left Mouse Button Pressed
	public void AddListenerMouseButtonPressed(MouseButtonPressed toAdd) {
		mouseButtonPressedListeners.add(toAdd);
	}
	
	public void RemoveListenerMouseButtonPressed(MouseButtonPressed toRemove) {
		mouseButtonPressedListeners.remove(toRemove);
	}
	
	// Mouse Button Released
	public void AddListenerMouseButtonRelesed(MouseButtonRelesed toAdd) {
		mouseButtonReleasedListeners.add(toAdd);
	}
	
	public void RemoveListenerMouseButtonRelesed(MouseButtonRelesed toRemove) {
		mouseButtonReleasedListeners.remove(toRemove);
	}
	
	// Mouse Moved
	public void AddListenerMouseMoved(MouseMoved toAdd) {
		mouseMovedListeners.add(toAdd);
	}
	
	public void RemoveListenerMouseMoved(MouseMoved toRemove) {
		mouseMovedListeners.remove(toRemove);
	}
	
	// Keyboard key pressed
	public void AddListenerKeyboardKeyPressed(KeyPressed toAdd) {
		keyPressedListeners.add(toAdd);
	}
	
	public void RemoveListenerKeyboardKeyRelesed(KeyPressed toRemove) {
		keyPressedListeners.remove(toRemove);
	}
	
	// Keyboard key released
	public void AddListenerKeyboardKeyRelesed(KeyReleased toAdd) {
		keyReleasedListeners.add(toAdd);
	}
	
	public void RemoveListenerKeyboardKeyReleased(KeyReleased toRemove) {
		keyReleasedListeners.remove(toRemove);
	}
	
	// Constructor
	public MouseAndKeyboardInput(OrthographicCamera camera) {
		this.camera = camera;
	}
	
	@Override
	public boolean keyDown(int keycode) {
		for(KeyPressed kp : keyPressedListeners) {
			kp.keyPressed(keycode);
		}
		
		return true;
	}

	@Override
	public boolean keyUp(int keycode) {
		for(KeyReleased kr : keyReleasedListeners) {
			kr.keyRelesed(keycode);
		}
		
		return true;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		for (MouseButtonPressed mbp : mouseButtonPressedListeners) {
			mbp.mouseButtonPressed(button, screenX, screenY);
		}
		
		return true;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		for (MouseButtonRelesed mbr : mouseButtonReleasedListeners) {
			mbr.mouseButtonRelesed(button, screenX, screenY);
		}
		
		return false;
	}

	@Override
	public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		Vector3 worldPos = ConvertCoords.screenToWorldPos(camera, screenX, screenY);
		
		mouseXWorldPos = worldPos.x;
		mouseYWorldPos = worldPos.y;
		return true;
	}

	@Override
	public boolean scrolled(float amountX, float amountY) {
		// TODO Auto-generated method stub
		return false;
	}
}
