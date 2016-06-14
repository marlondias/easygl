package marlon.engine.old;

import static org.lwjgl.glfw.GLFW.glfwPollEvents;

public class GameLogic {
	
	public GameLogic(){
		//Something..
	}
	
    public void update(){
		glfwPollEvents(); //Polls for any window events such as the window closing etc.
    }
	
}
