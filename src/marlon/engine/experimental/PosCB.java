package marlon.engine.experimental;

import org.lwjgl.glfw.GLFWCursorPosCallback;

public class PosCB extends GLFWCursorPosCallback {

	@Override
	public void invoke(long window, double xpos, double ypos) {
		System.out.println("X: " + xpos + " Y: " + ypos + " Window:" + window);
	}

}
