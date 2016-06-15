package marlon.engine.wrappers;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.system.MemoryUtil.NULL;
import org.lwjgl.glfw.GLFWCursorEnterCallback;
import org.lwjgl.glfw.GLFWCursorPosCallback;
import org.lwjgl.glfw.GLFWMouseButtonCallback;
import org.lwjgl.glfw.GLFWScrollCallback;
import org.lwjgl.glfw.GLFWWindowIconifyCallback;

//Encapsula uma janela GLFW
public class GLFWWindow_Wrapper {
	private final long WINDOW_ID; //Valor retonado por createWindow()
	private boolean valid; //Se a criação ocorreu normalmente
	//private int width, height; //Dimensões da janela
	//private int positionX, positionY; //Posição da janela
	//private boolean resizeable = false; //Se permite redimensionamento
	//private String title; //Título dessa janela
	
	
	public GLFWWindow_Wrapper(int width, int height, String title, boolean resizeable){
		//Evita erros comuns
		if (width < 1) width = 1;
		if (height < 1) height = 1;
		if (title == null) title = "(null)";
		
		//Verifica a disponibilidade da biblioteca
		if (!GLFW_Wrapper.isInitialized()) GLFW_Wrapper.initializeGLFW();
		
		glfwWindowHint(GLFW_RESIZABLE, ((resizeable) ? GLFW_TRUE : GLFW_FALSE));
		
		//Lembrete: Nem sempre os atributos informados correspondem aos atributos da janela criada.
		WINDOW_ID = glfwCreateWindow(width, height, title, NULL, NULL);
		valid = (WINDOW_ID != NULL);
	}
	
	
	/**
	 * Destroys this window and its context. After this method, no further callbacks will be called for this window.
	 * If this window's context is CURRENT on the main thread, it is detached before being destroyed.
	 * 
	 * This function must only be called from the main thread.
	 * 
	 * Warning: The context of the specified window must not be current on any other thread when this function is called.
	 * 
	 */
	public void destroy(){
		if (!valid) return;
		glfwDestroyWindow(WINDOW_ID);
	}

	public void minimize(){
		if(!valid){
			throw new IllegalStateException("Unable to minimize/iconify, GLFW window does not exist!");
		}
		glfwIconifyWindow(WINDOW_ID);
	}
	
	public void restore(){
		if(!valid){
			throw new IllegalStateException("Unable to restore, GLFW window does not exist!");
		}
		glfwRestoreWindow(WINDOW_ID);
	}
	
	/**
	 * Sets the position, in screen coordinates, of the upper-left corner of the client area of this window. 
	 * If the window is in fullscreen mode, nothing happens.
	 * 
	 * Obs: Must be called on main thread. 
	 * 
	 * Throws IllegalStateException if this window was not successfully created.
	 * 
	 */
	public void setPosition(int x, int y){
		if(!valid){
			throw new IllegalStateException("Unable to set position, GLFW window does not exist!");
		}
		else glfwSetWindowPos(WINDOW_ID, x, y);
	}

	public void getPosition(){
		if(!valid){
			throw new IllegalStateException("Unable to access position, GLFW window does not exist!");
		}
		System.err.println("Não fiz!");
	}
	
	/**
	 * Sets the size, in screen coordinates, of the client area of this window.
	 * 
	 * For fullscreen windows, this method updates the resolution of its desired video mode
	 * and switches to the video mode CLOSEST to it, without affecting the window's context.
	 * The window manager may put limits on what sizes are allowed.
	 * 
	 * Obs: Must be called on main thread
	 * 
	 * Throws IllegalStateException if this window was not successfully created.
	 * 
	 */
	public void setSize(int width, int height){
		if (width < 1 || height < 1) return;
		
		if (valid){
			glfwSetWindowSize(WINDOW_ID, width, height);
			//TODO: armazenar novas dimensões
		}
		else{
			throw new IllegalStateException("Unable to change size, GLFW window does not exist!");
		}
	}

	/**
	 * Defines the visibility of this window. TRUE = visible, FALSE = hidden.
	 * If the window is in fullscreen mode, nothing happens.
	 * 
	 * Obs: Must be called on main thread
	 * 
	 * Throws IllegalStateException if this window was not successfully created.
	 * 
	 */
	public void setVisible(boolean visible){
		if (valid){
			if (visible) glfwShowWindow(WINDOW_ID); //TODO: apenas thread principal!
			else glfwHideWindow(WINDOW_ID); //TODO: apenas thread principal!
		}
		else{
			throw new IllegalStateException("Unable change visibility, GLFW window does not exist!");
		}
	}

	/**
	 * Changes the title of this window. If the string is NULL, nothing changes.
	 * 
	 * Obs: Must be called on main thread
	 * 
	 * Throws IllegalStateException if this window was not successfully created.
	 * 
	 */
	public void setTitle(String title){
		if (title == null) return;
		if (valid) glfwSetWindowTitle(WINDOW_ID, title);
		else{
			throw new IllegalStateException("Unable change title, GLFW window does not exist!");
		}
	}
	
	/**
	 * Defines limits for resizing this window. If the window is not resizable, nothing changes.
	 * If the window is in fullscreen mode, the changes will take effect once it is windowed.
	 * 
	 * Obs: Must be called on main thread
	 * 
	 * Throws IllegalStateException if this window was not successfully created.
	 * 
	 */
	public void setSizeLimits(int minWidth, int minHeight, int maxWidth, int maxHeight){
		if (!valid){
			throw new IllegalStateException("Unable change limits, GLFW window does not exist!");
		}
		if (minWidth < 1 || minHeight < 1 || minWidth > maxWidth || minHeight > maxHeight) return;
		glfwSetWindowSizeLimits(WINDOW_ID, minWidth, minHeight, maxWidth, maxHeight);
	}
	
	/**
	 * Sets the value of the close flag of this window. This can be used to override 
	 * the user's attempt to close the window, or to signal that it should be closed.
	 * 
	 * This function may be called from any thread. Access is not synchronized.
	 * 
	 * Throws IllegalStateException if this window was not successfully created.
	 * 
	 */
	public void setShouldClose(boolean value){
		if (!valid){
			throw new IllegalStateException("Unable set flag, GLFW window does not exist!");
		}
		else glfwSetWindowShouldClose(WINDOW_ID, ((value) ? GLFW_TRUE : GLFW_FALSE));
	}
	
	/**
	 * Returns the value of the close flag of this window.
	 * Can be called from any thread, but is not syncronized.
	 * 
	 * Throws IllegalStateException if this window was not successfully created.
	 * 
	 * @return true if this window is about to be closed.
	 * 
	 */
	public boolean getShouldClose(){
		if (!valid){
			throw new IllegalStateException("Unable access flag, GLFW window does not exist!");
		}
		return (glfwWindowShouldClose(WINDOW_ID) == GLFW_TRUE);
	}

	/**
	 * Returns a number that identifies this window in GLFW functions.
	 * 
	 * Throws IllegalStateException if this window was not successfully created.
	 * 
	 * @return unique ID for this window.
	 */
	public long getID(){
		if (!valid){
			throw new IllegalStateException("Unable access ID, GLFW window does not exist!");
		}
		return WINDOW_ID;
	}


	public void setWindowCallback(GLFWWindowIconifyCallback callback){
		if (!valid){
			throw new IllegalStateException("Unable to set callback function, GLFW window does not exist!");
		}
		
		//TODO: Todos os callbacks, sobrecarregar
		//glfwSetWindowIconifyCallback()
		//glfwSetWindowPosCallback()
		//glfwSetWindowRefreshCallback()
		//glfwSetWindowSizeCallback()
		//glfwSetWindowFocusCallback()
		//glfwSetWindowCloseCallback()
	}

	
	
	/**
	 * Sets the cursor as current for this window. 
	 * 
	 * Throws IllegalStateException if this window was not successfully created.
	 * 
	 */
	public void setCursor(GLFWCursor_Wrapper cursor){
		if (!valid){
			throw new IllegalStateException("Unable set cursor, GLFW window does not exist!");
		}
		if (cursor != null) glfwSetCursor(WINDOW_ID , cursor.getID());
	}

	/**
	 * Removes the current cursor of this window.
	 * 
	 * Throws IllegalStateException if this window was not successfully created.
	 * 
	 */
	public void removeCursor(){
		if (!valid){
			throw new IllegalStateException("Unable remove cursor, GLFW window does not exist!");
		}
		glfwSetCursor(WINDOW_ID , NULL);
	}

	/**
	 * Changes the behavior of this window's cursor.
	 * GLFW_CURSOR_NORMAL is the default.
	 * GLFW_CURSOR_HIDDEN makes the cursor invisible only when it is over the window.
	 * GLFW_CURSOR_DISABLED makes the cursor invisible and it's movement inside the window is unlimited (stuck in the window).
	 * 
	 * Throws IllegalStateException if this window was not successfully created.
	 * 
	 */
	public void setMouseMode(int mode){
		if (!valid){
			throw new IllegalStateException("Unable to set mouse mode, GLFW window does not exist!");
		}
		
		if (mode == GLFW_CURSOR_HIDDEN) glfwSetInputMode(WINDOW_ID, GLFW_CURSOR, GLFW_CURSOR_HIDDEN);
		else if (mode == GLFW_CURSOR_DISABLED) glfwSetInputMode(WINDOW_ID, GLFW_CURSOR, GLFW_CURSOR_DISABLED);
		else glfwSetInputMode(WINDOW_ID, GLFW_CURSOR, GLFW_CURSOR_NORMAL);
	}

	/**
	 * Sets a callback function for when the cursor enters/leaves this window's limits.
	 * 
	 * Throws IllegalStateException if this window was not successfully created.
	 * 
	 */
	public void setMouseCallBack(GLFWCursorEnterCallback callback){
		if (!valid){
			throw new IllegalStateException("Unable to set mouse callback, GLFW window does not exist!");
		}
		if (callback != null) glfwSetCursorEnterCallback(WINDOW_ID, callback);
	}

	/**
	 * Sets a callback function for scroll wheel interaction (mouse or touchpad).
	 * 
	 * Throws IllegalStateException if this window was not successfully created.
	 * 
	 */
	public void setMouseCallBack(GLFWScrollCallback callback){
		if (!valid){
			throw new IllegalStateException("Unable to set mouse callback, GLFW window does not exist!");
		}
		if (callback != null) glfwSetScrollCallback(WINDOW_ID, callback);
	}

	/**
	 * Sets a callback function for mouse buttons interaction.
	 * 
	 * Throws IllegalStateException if this window was not successfully created.
	 * 
	 */
	public void setMouseCallBack(GLFWMouseButtonCallback callback){
		if (!valid){
			throw new IllegalStateException("Unable to set mouse callback, GLFW window does not exist!");
		}
		if (callback != null) glfwSetMouseButtonCallback(WINDOW_ID, callback);
	}

	/**
	 * Sets a callback function for cursor movements, while the mouse is over this window. 
	 * 
	 * Throws IllegalStateException if this window was not successfully created.
	 * 
	 */
	public void setMouseCallBack(GLFWCursorPosCallback callback){
		if (!valid){
			throw new IllegalStateException("Unable to set mouse callback, GLFW window does not exist!");
		}
		if (callback != null) glfwSetCursorPosCallback(WINDOW_ID, callback);
	}

	
	//glfwGetCursorPos(window, &xpos, &ypos);
	
	//glfwSetFramebufferSizeCallback()
	//glfwSetKeyCallback()
	//int state = glfwGetMouseButton(window, GLFW_MOUSE_BUTTON_LEFT);
	
	//TODO: verificar se são necessários
	//glfwGetWindowUserPointer()
	//glfwSetWindowUserPointer()
	//glfwSwapBuffers()
	//glfwGetWindowSize() -- screen coordinates
	//glfwGetWindowMonitor()
	//glfwGetWindowPos()
	//glfwGetWindowFrameSize() -- meh
	//glfwGetWindowAttrib() -- varios retornos
	//glfwGetFramebufferSize() -- nice
	//glfwFocusWindow() 
	
	//TODO: Versão 3.2
	//glfwSetWindowAspectRatio()
	//glfwSetWindowMonitor()
	//glfwSetWindowIcon() -- Fazer duas versões: setDefault, setIcon
	//glfwMaximizeWindow()
	
	//TODO: Ideia - Usar focusCallback para mudar saturação de janelas sem foco, usar filtro R,G,B para
	//diferenes monitores. That would be awsome *_*
	

}
