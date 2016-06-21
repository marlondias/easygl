package marlon.engine.experimental;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWCursorEnterCallback;
import org.lwjgl.glfw.GLFWCursorPosCallback;
import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.glfw.GLFWMouseButtonCallback;
import org.lwjgl.glfw.GLFWScrollCallback;

public class DebugInputCallbacks {
	private static GLFWCursorEnterCallback cursorEnterCB = new GLFWCursorEnterCallback(){
		@Override
		public void invoke(long window, boolean entered){
			if (entered) System.out.println("Estou dentro da janela " + window + "!");
			else System.out.println("Estou fora da janela!");
		}
	};
	
	private static GLFWCursorPosCallback cursorPositionCB = new GLFWCursorPosCallback(){
		@Override
		public void invoke(long window, double xpos, double ypos){
			System.out.println("Position: " + (int)xpos + ", " + (int)ypos);
		}
	};

	private static GLFWMouseButtonCallback mouseButtonCB = new GLFWMouseButtonCallback(){
		@Override
		public void invoke(long window, int button, int action, int mods) {
			String txt = "Mouse button";
			
			switch (button) {
			case GLFW.GLFW_MOUSE_BUTTON_1:
				txt += " 1 was";
				break;
			case GLFW.GLFW_MOUSE_BUTTON_2:
				txt += " 2 was";
				break;
			case GLFW.GLFW_MOUSE_BUTTON_3:
				txt += " 3 was";
				break;
			default:
				txt += " ? was";
				break;
			}
			
			switch (action) {
			case GLFW.GLFW_PRESS:
				txt += " pressed";
				break;
			case GLFW.GLFW_RELEASE:
				txt += " released";
				break;
			case GLFW.GLFW_REPEAT:
				txt += " repeated";
				break;
			default:
				break;
			}
			
			if (mods != 0){
				if ((mods & GLFW.GLFW_MOD_CONTROL) != 0) txt += " + CTRL";
				if ((mods & GLFW.GLFW_MOD_ALT) != 0) txt += " + ALT";
				if ((mods & GLFW.GLFW_MOD_SHIFT) != 0) txt += " + SHIFT";
				if ((mods & GLFW.GLFW_MOD_SUPER) != 0) txt += " + SUPER";
			}

			System.out.println(txt + "!");
		}
	};
	
	private static GLFWScrollCallback scrollCB = new GLFWScrollCallback(){
		@Override
		public void invoke(long window, double xoffset, double yoffset) {
			String txt = "Mouse scrolling";
			if (yoffset > 0) txt += " up";
			else if (yoffset < 0) txt += " down";
			else if (xoffset > 0) txt += " left";
			else if (xoffset < 0) txt += " right";
			System.out.println(txt + "!");
		}
	};
	
	private static GLFWKeyCallback keyCB = new GLFWKeyCallback(){
		@Override
		public void invoke(long window, int key, int scancode, int action, int mods){
			String txt = "Key ";
			txt += key;

			switch (action){
			case GLFW.GLFW_PRESS:
				txt += " was pressed";
				break;
			case GLFW.GLFW_RELEASE:
				txt += " was released";
				break;
			case GLFW.GLFW_REPEAT:
				txt += " was repeated";
				break;
			default:
				break;
			}
			
			if (mods != 0){
				if ((mods & GLFW.GLFW_MOD_CONTROL) != 0) txt += " + CTRL";
				if ((mods & GLFW.GLFW_MOD_ALT) != 0) txt += " + ALT";
				if ((mods & GLFW.GLFW_MOD_SHIFT) != 0) txt += " + SHIFT";
				if ((mods & GLFW.GLFW_MOD_SUPER) != 0) txt += " + SUPER";
			}
			
			System.out.println(txt + "!");
		}
	};


	
	public static GLFWCursorEnterCallback getEnterCB(){
		return cursorEnterCB;
	}

	public static GLFWCursorPosCallback getCursorPositionCB(){
		return cursorPositionCB;
	}
	
	public static GLFWMouseButtonCallback getMouseButtonCB(){
		return mouseButtonCB;
	}
	
	public static GLFWScrollCallback getScrollCB(){
		return scrollCB;
	}
	
	public static GLFWKeyCallback getKeyCB(){
		return keyCB;
	}
	
}
