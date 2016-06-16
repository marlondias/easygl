package marlon.engine.experimental;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWWindowCloseCallback;
import org.lwjgl.glfw.GLFWWindowFocusCallback;
import org.lwjgl.glfw.GLFWWindowIconifyCallback;
import org.lwjgl.glfw.GLFWWindowPosCallback;
import org.lwjgl.glfw.GLFWWindowRefreshCallback;
import org.lwjgl.glfw.GLFWWindowSizeCallback;

public class DebugWindowCallbacks {
	private static GLFWWindowCloseCallback closeCB = new GLFWWindowCloseCallback(){
		@Override
		public void invoke(long window){
			System.out.println("Window " + window + " must close!");
		}
	};
	
	private static GLFWWindowFocusCallback focusCB = new GLFWWindowFocusCallback(){
		@Override
		public void invoke(long window, int focused){
			if (focused == GLFW.GLFW_TRUE) System.out.println("Window " + window + " has focus!");
			else System.out.println("Window " + window + " lost focus!");
		}
	};

	private static GLFWWindowPosCallback positionCB = new GLFWWindowPosCallback(){
		@Override
		public void invoke(long window, int xpos, int ypos){
			System.out.println("Window moved to " + xpos + ", " + ypos);
		}
	};

	private static GLFWWindowSizeCallback sizeCB = new GLFWWindowSizeCallback(){
		@Override
		public void invoke(long window, int width, int height){
			System.out.println("Window size is now " + width + "x" + height);
		}
	};
	
	private static GLFWWindowRefreshCallback refreshCB = new GLFWWindowRefreshCallback(){
		@Override
		public void invoke(long window) {
			System.out.println("Window " + window + " was refreshed!");
		}
	};
	
	private static GLFWWindowIconifyCallback iconifyCB = new GLFWWindowIconifyCallback(){
		@Override
		public void invoke(long window, int iconified){
			if (iconified == GLFW.GLFW_TRUE) System.out.println("Window was iconified!");
			else System.out.println("Window was restored");
		}
	};

	
	public static GLFWWindowCloseCallback getCloseCB(){
		return closeCB;
	}
	
	public static GLFWWindowFocusCallback getFocusCB(){
		return focusCB;
	}
	
	public static GLFWWindowPosCallback getPositionCB(){
		return positionCB;
	}
	
	public static GLFWWindowSizeCallback getSizeCB(){
		return sizeCB;
	}

	public static GLFWWindowRefreshCallback getRefreshCB(){
		return refreshCB;
	}

	public static GLFWWindowIconifyCallback getIconifyCB(){
		return iconifyCB;
	}

}
