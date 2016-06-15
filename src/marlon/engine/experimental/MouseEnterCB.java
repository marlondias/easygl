package marlon.engine.experimental;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWCursorEnterCallback;

public class MouseEnterCB extends GLFWCursorEnterCallback {

	@Override
	public void invoke(long window, int entered) {
		if (entered == GLFW.GLFW_TRUE) System.out.println("Entrei!");
		else System.out.println("Sai!");
	}
	

}
