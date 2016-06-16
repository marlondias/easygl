package marlon.engine.experimental;

import org.lwjgl.glfw.GLFWCharCallback;
import org.lwjgl.glfw.GLFWCharModsCallback;

public class TestTextCallbacks {
	private static GLFWCharCallback charCB = new GLFWCharCallback(){
		@Override
		public void invoke(long window, int codepoint){
			// TODO Auto-generated method stub
			
		}
	};
	
	private static GLFWCharModsCallback charModsCB = new GLFWCharModsCallback(){
		@Override
		public void invoke(long window, int codepoint, int mods){
			// TODO Auto-generated method stub
			
		}
	};
	
	
	public static GLFWCharCallback getCharCB(){
		return charCB;
	}
	
	public static GLFWCharModsCallback getCharModsCB(){
		return charModsCB;
	}

}
