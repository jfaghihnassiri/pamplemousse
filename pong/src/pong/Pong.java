package pong;

import static org.lwjgl.opengl.GL11.*;
import org.lwjgl.opengl.*;
import org.lwjgl.*;

public class Pong {

	public Pong() {
		try {
			Display.setDisplayMode(new DisplayMode(640,480));
			Display.setTitle("Pong by PDM & JFN");
			Display.create();
		} 
		catch (LWJGLException e) {
			e.printStackTrace();
		}
		
		// If openGL used initialize it here
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, 640, 480, 0, 1, -1);
		glMatrixMode(GL_MODELVIEW);
		
		while(!Display.isCloseRequested()) {
			// Render
			
			// Clear before new frames
			glClear(GL_COLOR_BUFFER_BIT); // if 3d bitwise with GL_DEPTH_BUFFER_BIT
			
			// Create a quadrilateral
			glBegin(GL_QUADS);
				glVertex2i(400,400); // Upper left
				glVertex2i(450,400); // Upper right
				glVertex2i(450,450); // Bottom right
				glVertex2i(400,450); // Bottom left
			glEnd();
			
			// Create a line
			glBegin(GL_LINES);
				glVertex2i(100,100);
				glVertex2i(200,200);
			glEnd();
				
			// Send changes?
			Display.update();
			
			// Determine FPS
			Display.sync(60);
		}
		
		Display.destroy();
	}
	
	public static void main(String[] args) {
		new Pong();
	}

}
