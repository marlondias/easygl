package marlon.engine.old;

import java.nio.FloatBuffer;
import org.lwjgl.BufferUtils;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import marlon.engine.experimental.Mesh;
import marlon.engine.experimental.Vertex;
import marlon.engine.wrappers.GLFW_Cursor;
import marlon.engine.wrappers.GLFW_Window;
import marlon.engine.wrappers.GL_ShaderCode;
import marlon.engine.wrappers.GL_ShaderProgram;
import marlon.engine.wrappers.GLFW_Lib;

public class GameGraphics {
	private GLFW_Window janela1;
	private GLFW_Cursor cursor1;
	private GL_ShaderProgram shader;
	private Mesh mesh;
	private int vaoId;

	
	public GameGraphics(){
    	cursor1 = new GLFW_Cursor(GLFW.GLFW_CROSSHAIR_CURSOR);
		janela1 = new GLFW_Window(800, 600, "Aqui estoy!", true);
    	janela1.setCursor(cursor1);
    	janela1.setPosition(10, 10);
    	janela1.setVisible(true);
    	GLFW_Lib.setContextCurrent(janela1);
    	
    	GL_ShaderCode shader1 = new GL_ShaderCode(GL20.GL_VERTEX_SHADER, "assets/shaders/vertex1.vsh");
    	GL_ShaderCode shader2 = new GL_ShaderCode(GL20.GL_FRAGMENT_SHADER, "assets/shaders/fragment1.fsh");
    	shader = new GL_ShaderProgram();
    	shader.attachShader(shader1);
    	shader.attachShader(shader2);
    	shader.link();
    	
    	GL20.glBindAttribLocation(shader.getID(), 0, "position");
    	
    	
    	float[]	vertices = new float[]{
			0.0f, 0.5f,	0.0f,
			-0.5f, -0.5f, 0.0f,
			0.5f, -0.5f, 0.0f
		};
    	FloatBuffer	verticesBuffer = BufferUtils.createFloatBuffer(vertices.length);
    	verticesBuffer.put(vertices).flip();
    	
    	vaoId =	GL30.glGenVertexArrays();
    	GL30.glBindVertexArray(vaoId);
    	
    	int vboId = GL15.glGenBuffers();
    	GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER,	vboId);
    	GL15.glBufferData(GL15.GL_ARRAY_BUFFER,	verticesBuffer,	GL15.GL_STATIC_DRAW);
    	
    	GL20.glVertexAttribPointer(0, 3, GL11.GL_FLOAT, false, 0, 0);
    	
    	//Unbind VBO and VAO
    	GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
    	GL30.glBindVertexArray(0);
    	

    	float[] verticesTriangulo = new float[]{
    	    -0.5f, -0.5f, 0.0f,
    	    0.0f, 0.6f, 0.0f,
    	    0.5f, -0.5f, 0.0f
    	};
    	mesh = new Mesh(verticesTriangulo);
	}
	
	
    public void render(){
    	
    	if (!janela1.getShouldClose()){
    		GL11.glClearColor(0.0f, 0.0f, 0.4f, 0.0f);
    		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);

        	shader.bind();
        	
        	//Bind to the VAO
			GL30.glBindVertexArray(vaoId);
			GL20.glEnableVertexAttribArray(0);
			
			//Draw the vertices
			GL11.glDrawArrays(GL11.GL_TRIANGLES, 0, 3);
            
			// Restore state
			GL20.glDisableVertexAttribArray(0);
			GL30.glBindVertexArray(0);
			shader.unbind();
			
            GLFW.glfwSwapBuffers(janela1.getID());
    	}

    }
    
    public void showFPS(int updates, int frames){
    	janela1.setTitle("UPS: " + updates + " FPS: " + frames);
    }
    
	
	public boolean shouldTerminate(){
		return janela1.getShouldClose();
	}
	
	public void terminate(){
		if (shader != null) shader.discard();
		if (mesh != null) mesh.discard();
		GLFW_Lib.terminateGLFW();
	}
	
}
