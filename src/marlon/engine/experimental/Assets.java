package marlon.engine.experimental;

import static org.lwjgl.opengl.GL20.GL_FRAGMENT_SHADER;
import static org.lwjgl.opengl.GL20.GL_VERTEX_SHADER;

import marlon.engine.wrappers.GL_ShaderCode;

public class Assets {
	public static final GL_ShaderCode vertShader1 = new GL_ShaderCode(GL_VERTEX_SHADER, "assets/shaders/vertex1.vsh");
	public static final GL_ShaderCode fragShader1 = new GL_ShaderCode(GL_FRAGMENT_SHADER, "assets/shaders/fragment1.fsh");


}
