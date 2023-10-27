package ru.serious07.game.utilities;

import com.badlogic.gdx.math.Vector3;

public class Vector2Int {
	public int x = 0;
	public int y = 0;
	
	public Vector2Int(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public Vector2Int(float x, float y) {
		this.x = (int) x;
		this.y = (int) y;
	}
	
	public Vector2Int(Vector3 vector) {
		this.x = (int) vector.x;
		this.y = (int) vector.y;
	}
	
	public Vector2Int() {
		
	}
}
