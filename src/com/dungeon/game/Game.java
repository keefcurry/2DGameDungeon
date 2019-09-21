package com.dungeon.game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

import com.dungeon.game.graphics.Screen;//This imports the Screen.java

public class Game extends Canvas implements Runnable{
	private static final long serialVersionUID = 1L;
	/**
	 * @author rtyre
	 * 2D tile based game this is a 
	 * test case so i can be better
	 * for my software engineering class
	 */
	public static int width = 300; //this sets the width of the window
	public static int height = width / 16 * 9; //this auto audjust the height so that the A/R is 16/9
	public static int scale = 3; //this sets the scale

	
	private Thread thread; //This is basically a process in a process
	private JFrame frame;
	private boolean running = false;
	private Screen screen;
	
	//This creates an image object
	private BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	
	//raster is a datastructure that represents a rectangular array of pixels
	//this is an arry of pixels
	
	/**
	 * DataBufferInt - contact the image and get the image pixels an get the rasta
	 * getRasta - return an array of pixels that we can write our color to
	 * getDataBuffer - return the DataBuffer of the rasta
	 * getData - this will convert it to data that will go into an array of integers(pixels)
	 */
	private int[] pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();
	
	/**
	 * Creates the Game constructor
	 */
	public Game(){
		Dimension size = new Dimension(width * scale, height * scale);
		setPreferredSize(size);
		
		screen = new Screen(width, height);//instantiated the new screen
		
		frame = new JFrame();
		
	}
	/**
	 * sychronized **
	 */
	public synchronized void start() { //preventing thread interferences and memory consistency errors
		running = true;
		thread = new Thread(this, "Game");
		thread.start();
	}
	
	public synchronized void stop(){
		running = false;
		try{
		thread.join();//this ends the thread 
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	} 
	
	/**
	 * September 9, 2019
	 * 			 1:24 PM
	 */
	
	
	/**because the Game class extends runnable it will automatically
	 * call the run() method
	 * 
		java.lang.Runnable
		@FunctionalInterface
	 */
	public void run() { //this is the running method
		while (running) {
			//System.out.println("Running..."); //this is to show in the console that the game is running
			/**
			 * use tick(); or update(); this just 
			 */
			
			update();
			render();
		}
	}
	
	public void update() {
		
	}
	
		//Buffer - a buffer is a temporary storage system, so when you 
		//render an image it stores it and saves it for when the game needs the frame.
	public void render() {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) { //if the buffer strategy doesnt get created, then you create it
			createBufferStrategy(3); //the number 3 means it creates triple buffering
			return;					//, so when you backup frame gets displayed (2) it will also have a backup	
		}
		
		//apply data to the buffer "bs"
		//this is creating a link to graphics to drawing graphics to the screen.
		Graphics g = bs.getDrawGraphics();
		/**
		 * You input all of your graphics inbetween the g object and g.dispose();
		 */
		//###################
		
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, getWidth(), getHeight());
		
		//###################
		g.dispose(); //this dispose of all the graphics
		bs.show(); //this will make the next available buffer visible
	}
	public static void main(String[] args){
		Game game = new Game();
		game.frame.setResizable(false);//sets the window so that its not resizable
		game.frame.setTitle("Pixel Dungeon v1.2"); //sets the title of the game to Rain
		game.frame.add(game); //add an instance of the game to the game.frame because our class extends Canvas
		game.frame.pack(); //pack sets the size of the frame to be the same size of the coponent
		game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //This enables the exit button
		game.frame.setLocationRelativeTo(null); //this puts the location of the frame in the center of the screen
		game.frame.setVisible(true); //enables you to see the frame of the game
		
		game.start();
	}
}
