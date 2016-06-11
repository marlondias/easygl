package marlon.engine.old;

import static org.lwjgl.glfw.GLFW.glfwSwapBuffers;

import java.util.LinkedList;

import marlon.engine.GLFWWindow_Wrapper;

public class GameGraphics {
	private static final LinkedList<GLFWWindow_Wrapper> windows = new LinkedList<>();
	
    
    public static void addWindow(GLFWWindow_Wrapper window){
    	if (window != null) windows.add(window);
    }
    
    public static void render(){
    	//Blitting
    	for (GLFWWindow_Wrapper w : windows){
    		glfwSwapBuffers(w.getID());
    	}
    }

}
