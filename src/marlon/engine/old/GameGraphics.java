package marlon.engine.old;

import org.lwjgl.opengl.*;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import org.lwjgl.glfw.GLFW;
import marlon.engine.experimental.DebugInputCallbacks;
import marlon.engine.experimental.DebugWindowCallbacks;
import marlon.engine.experimental.TestTextCallbacks;
import marlon.engine.wrappers.GLFW_Cursor;
import marlon.engine.wrappers.GLFW_Window;
import marlon.engine.wrappers.GL_Shader;
import marlon.engine.wrappers.GL_ShaderProgram;
import marlon.engine.wrappers.GLFW_Lib;

public class GameGraphics {
	private GLFW_Window janela1;
	private GLFW_Cursor cursor1;
	private int vertexbuffer;
	private int vertexArrayID;

	
	public GameGraphics(){
    	cursor1 = new GLFW_Cursor(GLFW.GLFW_CROSSHAIR_CURSOR);
		janela1 = new GLFW_Window(800, 600, "Aqui estoy!", true);
    	janela1.setCursor(cursor1);
    	janela1.setPosition(10, 10);
    	janela1.setVisible(true);
    	GLFW_Lib.setContextCurrent(janela1);
    	
    	vertexArrayID = GL30.glGenVertexArrays();
    	GL30.glBindVertexArray(vertexArrayID);
    	
    	// An array of 3 vectors which represents 3 vertices
    	float g_vertex_buffer_data[] = {
    	   -1.0f, -1.0f, 0.0f,
    	   1.0f, -1.0f, 0.0f,
    	   0.0f,  1.0f, 0.0f,
    	};
    	FloatBuffer fbuff = FloatBuffer.wrap(g_vertex_buffer_data);

    	vertexbuffer = GL15.glGenBuffers(); // Generate 1 buffer, put the resulting identifier in vertexbuffer
    	GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vertexbuffer);
    	GL15.glBufferData(GL15.GL_ARRAY_BUFFER, fbuff, GL15.GL_STATIC_DRAW); // Give our vertices to OpenGL

    	
    	GL_Shader shader1 = new GL_Shader(GL20.GL_VERTEX_SHADER, "assets/shaders/vertex1.vsh");
    	GL_Shader shader2 = new GL_Shader(GL20.GL_FRAGMENT_SHADER, "assets/shaders/fragment1.fsh");
    	GL_ShaderProgram shaderProg = new GL_ShaderProgram();
    	GL20.glBindAttribLocation(shaderProg.getID(), 0, "position");
    	shaderProg.attachShader(shader1);
    	shaderProg.attachShader(shader2);
    	shaderProg.link();
    	shaderProg.use();
	}
	
	
    public void render(){
    	
    	if (!janela1.getShouldClose()){
    		GL11.glClearColor(0.0f, 0.0f, 0.4f, 0.0f);
    		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);

    		
        	// 1rst attribute buffer : vertices
    		GL20.glEnableVertexAttribArray(0);
        	GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vertexbuffer);
    		GL20.glVertexAttribPointer(0, 3, GL11.GL_FLOAT, false, 0, 0);
    		
        	// Draw the triangle !
    		GL11.glDrawArrays(GL11.GL_TRIANGLES, 0, 3); // Starting from vertex 0; 3 vertices total -> 1 triangle
        	GL20.glDisableVertexAttribArray(0);
    		
        	
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
		GLFW_Lib.terminateGLFW();
	}
	
}
