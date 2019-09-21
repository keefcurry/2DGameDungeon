package com.dungeon.game.graphics;

/**
 * 
 * @author rtyre
 * @date September 20, 2019 "7:16pm"
 */
public class Screen {

	private int width, height;
	public int[] pixels;
	
	public Screen(int width, int height) { 
		this.width = width;
		this.height = height;
		pixels = new int[width * height];
	}
}
