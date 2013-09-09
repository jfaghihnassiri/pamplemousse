package pong;

import org.lwjgl.opengl.GL11.*;
import org.lwjgl.opengl.*;
import org.lwjgl.*;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;

public class Pong {

	public Pong() {
		try {
			Display.setDisplayMode(new DisplayMode(640,480));
			Display.setTitle("Pong by PDM & JFN");
			Display.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
		
		// If openGL used initialize it here
		
		while(!Display.isCloseRequested()) {
			// Render
			Display.update();
			Display.sync(60);
		}
		
		Display.destroy();
	}
	
	public static void main(String[] args) {
		new Pong();
	}

}
