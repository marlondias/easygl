package marlon.engine.old;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL11;
import marlon.engine.wrappers.GLFWCursor_Wrapper;
import marlon.engine.wrappers.GLFWWindow_Wrapper;
import marlon.engine.wrappers.GLFW_Wrapper;

public class GameGraphics {
	private GLFWWindow_Wrapper janela1;
	private GLFWCursor_Wrapper cursor1;

	
	public GameGraphics(){
    	cursor1 = new GLFWCursor_Wrapper(GLFW.GLFW_CROSSHAIR_CURSOR);
    	
		janela1 = new GLFWWindow_Wrapper(800, 600, "Aqui estoy!", true);
    	janela1.setPosition(10, 10);
    	janela1.setVisible(true);
    	janela1.setCursor(cursor1);

    	
    	GLFW_Wrapper.setContextCurrent(janela1);
    	
    	GL11.glMatrixMode(GL11.GL_PROJECTION);
    	GL11.glLoadIdentity();
    	GL11.glOrtho(0, 800, 0, 600, 1, -1);
    	GL11.glMatrixMode(GL11.GL_MODELVIEW);
	}
	
	
    public void render(){
    	//Inicia qualquer buffer ou whatever..
    	
        try {
            //Passa por todas as MGameScreens e desenha
        	
        	if (!janela1.getShouldClose()){
            	GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT); //Clear the screen and depth buffer
            	GL11.glColor3f(0.5f,0.5f,1.0f); //Set the color of the quad (R,G,B,A)
            	
            	//Draw quad
            	GL11.glBegin(GL11.GL_QUADS);
            	    GL11.glVertex2f(100,100);
            	    GL11.glVertex2f(100+200,100);
            	    GL11.glVertex2f(100+200,100+200);
            	    GL11.glVertex2f(100,100+200);
            	GL11.glEnd();
            	
                GLFW.glfwSwapBuffers(janela1.getID());
        	}

        	//Blitting
       	}
    	finally {
    		//Liberar recursos temporários
    	}

    }
    
    public void showFPS(int updates, int frames){
    	janela1.setTitle("UPS: " + updates + " FPS: " + frames);
    }
    
	
	public boolean shouldTerminate(){
		return janela1.getShouldClose();
	}
	
	public void terminate(){
		GLFW_Wrapper.terminateGLFW();
	}
	
}
