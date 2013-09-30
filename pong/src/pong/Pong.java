package pong;

import java.awt.Font;
import java.io.InputStream;

import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.util.ResourceLoader;

import static org.lwjgl.opengl.GL11.*;

public class Pong {

    private static final int WIDTH = 640;
    private static final int HEIGHT = 480;
    private static final double SPEED = .6;
    private static boolean isRunning = true;
    private static Ball ball;
    private static Bat batL;
    private static Bat batR;
    private static String direction = "left";
    private static TrueTypeFont timesFont;

    // Main loop
    public static void main(String[] args) {
        setUpDisplay();
        setUpOpenGL();
        setUpEntities();
        setUpTimer();
        setUpFont();
        while (isRunning) {
            render();
            logic(getDelta());
            input();
            Display.update();
            Display.sync(60);
            if (Display.isCloseRequested()) {
                isRunning = false;
            }
        }
        Display.destroy();
        System.exit(0);
    }

    // Take input for the bat
    private static void input() {
    	// Left paddle
        if (Keyboard.isKeyDown(Keyboard.KEY_A)) {
            batL.setDY(-SPEED/1.5);
        } else if (Keyboard.isKeyDown(Keyboard.KEY_Z)) {
            batL.setDY(SPEED/1.5);
        } else {
            batL.setDY(0);
        }
        // Right paddle
        if (Keyboard.isKeyDown(Keyboard.KEY_UP)) {
            batR.setDY(-SPEED/1.5);
        } else if (Keyboard.isKeyDown(Keyboard.KEY_DOWN)) {
            batR.setDY(SPEED/1.5);
        } else {
            batR.setDY(0);
        }
        // New ball
        if (Keyboard.isKeyDown(Keyboard.KEY_R)) {
            ball = new Ball(WIDTH / 2 - 10 / 2, HEIGHT / 2 - 10 / 2, 10, 10);
            // Set initial ball velocity'
            if(direction == "left") {
            	direction = "right";
            	ball.setDX(SPEED);
            	ball.setDY(SPEED/4);
            }
            else if(direction == "right") {
            	direction = "left";
            	ball.setDX(-SPEED);
            	ball.setDY(SPEED/4);
            }
        }
        // Exit
        if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
        	isRunning = false;
        }
     }

    private static long lastFrame;

    private static long getTime() {
        return (Sys.getTime() * 1000) / Sys.getTimerResolution();
    }

    private static void setUpFont() {
    	// load a default java font
    	Font awtFont = new Font("Times New Roman", Font.BOLD, 24);
    	// TODO timesFont = new TrueTypeFont(awtFont, false);
    }
    
    private static int getDelta() {
        long currentTime = getTime();
        int delta = (int) (currentTime - lastFrame);
        lastFrame = getTime();
        return delta;
    }

    private static void setUpDisplay() {
        try {
            Display.setDisplayMode(new DisplayMode(WIDTH, HEIGHT));
            Display.setTitle("Pong");
            Display.create();
        } catch (LWJGLException e) {
            e.printStackTrace();
            Display.destroy();
            System.exit(1);
        }
    }

    private static void setUpOpenGL() {
    	// Open GL canvas and view initialization
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        glOrtho(0, WIDTH, HEIGHT, 0, 1, -1);
        glMatrixMode(GL_MODELVIEW);
    }

    private static void setUpEntities() {
    	// X, Y, Width, Height
        batL = new Bat(10, HEIGHT / 2 - 80 / 2, 10, 80);
        batR = new Bat(WIDTH-20, HEIGHT / 2 - 40, 10, 80);
        ball = new Ball(WIDTH / 2 - 10 / 2, HEIGHT / 2 - 10 / 2, 10, 10);
        // Set initial ball velocity
        ball.setDX(-SPEED);
        ball.setDY(SPEED/4);
    }

    private static void setUpTimer() {
        lastFrame = getTime();
    }

    private static void render() {
        glClear(GL_COLOR_BUFFER_BIT);
        ball.draw();
        batL.draw();
        batR.draw();
        //TODO Color.white.bind();
        //TODO font.drawString(20, 20, "Player 1", Color.yellow );
    }

    private static void logic(int delta) {
        ball.update(delta);
        batL.update(delta);
        batR.update(delta);
        // Left or right bounce
        if ((ball.getX() <= batL.getX() + batL.getWidth() && ball.getX() >= batL.getX() && 
        		ball.getY() >= batL.getY() && ball.getY() <= batL.getY() + batL.getHeight()) 
        		|| (ball.getX() >= batR.getX() - batR.getWidth() && ball.getX() <= batR.getX() && 
        		ball.getY() >= batR.getY() && ball.getY() <= batR.getY() + batR.getHeight()) ) {
        	// Set velocity of the ball
            ball.setDX(-ball.getDX());
        }
        else if (HEIGHT <= ball.getY()+10 || ball.getY()<= 0) {
        	ball.setDY(-ball.getDY());
        }
    }

    private static class Bat extends AbstractMoveableEntity {

        public Bat(double x, double y, double width, double height) {
            super(x, y, width, height);
        }

        @Override
        public void draw() {
            glRectd(x, y, x + width, y + height);
        }
    }

    private static class Ball extends AbstractMoveableEntity {

        public Ball(double x, double y, double width, double height) {
            super(x, y, width, height);
        }

        @Override
        public void draw() {
            glRectd(x, y, x + width, y + height);
        }
    }
}