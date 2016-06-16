package marlon.engine.wrappers;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.system.MemoryUtil.NULL;
import org.lwjgl.glfw.GLFWImage;

public class GLFW_Cursor {
	private final long MOUSE_ID;
	private boolean valid = false;
	
	
	public GLFW_Cursor(GLFWImage image, int hotspotX, int hotspotY){
		//Construtor de ponteiro customizado
		if (!GLFW_Lib.isInitialized()) GLFW_Lib.initializeGLFW(); //Verifica disponibilidade da biblioteca
		MOUSE_ID = glfwCreateCursor(image, hotspotX, hotspotY);
		valid = (MOUSE_ID != NULL);
	}
	
	public GLFW_Cursor (int shape){
		//Construtor de ponteiro padrão
		if (!GLFW_Lib.isInitialized()) GLFW_Lib.initializeGLFW(); //Verifica disponibilidade da biblioteca
		if (shape != GLFW_ARROW_CURSOR && shape != GLFW_IBEAM_CURSOR && shape != GLFW_CROSSHAIR_CURSOR && shape != GLFW_HAND_CURSOR && shape != GLFW_HRESIZE_CURSOR && shape != GLFW_VRESIZE_CURSOR){
			shape = GLFW_ARROW_CURSOR; //Shape informada não existe, usar default
		}
		MOUSE_ID = glfwCreateStandardCursor(shape);
		valid = (MOUSE_ID != NULL);
	}
	
	
	/**
	 * Destroys this mouse cursor.
	 * 
	 */
	public void destroy(){
		if (!valid) return;
		glfwDestroyCursor(MOUSE_ID);
	}

	/**
	 * Returns the unique ID number for this cursor, or NULL if this cursor is invalid.
	 * 
	 */
	public long getID(){
		return (valid) ? MOUSE_ID : NULL;
	}
	
}
