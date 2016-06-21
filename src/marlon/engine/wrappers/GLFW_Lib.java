package marlon.engine.wrappers;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.system.MemoryUtil.NULL;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;

public class GLFW_Lib {
	private static GLFWErrorCallback errorCallback = GLFWErrorCallback.createPrint(System.err); //Callback padrão
	private static boolean initialized = false;
	private static GLFWVidMode defaultVideoMode; //VideoMode padrão do sistema (antes da aplicação rodar)


	/**
	 * Check if GLWF is already initialized.
	 */
	public static boolean isInitialized(){
		return initialized;
	}
	
	/**
	 * Initialize the GLFW library, if not done yet.
	 * Throws an IllegalStateException if unable to initialize.
	 */
	public static void initializeGLFW(){
		if (initialized){
			System.err.println("GLFW is already initialized!");
			return;
		}
		glfwSetErrorCallback(errorCallback);
		if(!glfwInit()){
			throw new IllegalStateException("Unable to initialize GLFW");
		}
		defaultVideoMode = GLFW.glfwGetVideoMode(GLFW.glfwGetPrimaryMonitor());
		initialized = true;
		System.out.println("GLFW was successfully initialized!");
	}

	/**
	 * Terminate the GLFW library, reseting configurations and releasing any resources.
	 */
	public static void terminateGLFW(){
		glfwTerminate();
		errorCallback.free();
		initialized = false;
		System.out.println("GLFW was terminated!");
	}
	
	/**
	 * Create the OpenGL context, which is needed for rendering.
	 * 
	 * @param window must be a valid Window object
	 */
	public static void setContextCurrent(GLFW_Window window){
		if (window == null) return;
		
		final long window_ID = window.getID();
		if(window_ID == NULL) {
			throw new RuntimeException("Unable to set context, GLFW window does not exist!");
		}
		else {
			glfwMakeContextCurrent(window_ID);
			GL.createCapabilities(); //Binding
		}
	}

	/**
	 * Gets the default VideoMode used by the system before GLFW's initialization.
	 * @return GLFWVidMode of the primary monitor.
	 */
	public static GLFWVidMode getDefaultVidMode(){
		return defaultVideoMode;
	}

	/**
	 * Resets all window hints to their default values.
	 * 
	 */
	public static void resetWindowHints(){
		glfwDefaultWindowHints();
	}

}
