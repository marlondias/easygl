package marlon.engine.old;

import org.lwjgl.glfw.GLFW;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;

import marlon.engine.experimental.Mesh;
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

	
	public GameGraphics(){
    	cursor1 = new GLFW_Cursor(GLFW.GLFW_CROSSHAIR_CURSOR);
		janela1 = new GLFW_Window(800, 600, "Aqui estoy!", true);
    	janela1.setCursor(cursor1);
    	janela1.setPosition(10, 10);
    	janela1.setVisible(true);
    	GLFW_Lib.setContextCurrent(janela1);
    	
    	GL_ShaderCode shader1 = new GL_ShaderCode(GL_VERTEX_SHADER, "assets/shaders/vertex1.vsh");
    	GL_ShaderCode shader2 = new GL_ShaderCode(GL_FRAGMENT_SHADER, "assets/shaders/fragment1.fsh");
    	shader = new GL_ShaderProgram();
    	shader.attachShader(shader1);
    	shader.attachShader(shader2);
    	
    	glBindAttribLocation(shader.getID(), 0, "position");
    	glBindAttribLocation(shader.getID(), 1, "color");
    	shader.link();
    	
    	
    	float[] verts = new float[]{
    	    -0.5f, 0.5f, 0.0f,
    	     0.5f, 0.5f, 0.0f,
    	    -0.5f, -0.5f, 0.0f,
    	     0.5f, -0.5f, 0.0f,
    	};
    	
    	float[] colors = new float[]{
    	    1.0f, 0.3f, 0.7f,
    	    1.0f, 0.5f, 0.5f,
    	    1.0f, 0.7f, 0.3f,
    	};
    	
    	int[] indices = new int[]{ 0,1,2,2,1,3 };

    	mesh = new Mesh(verts, colors, indices);
	}
	
	
    public void render(){
    	
    	if (!janela1.getShouldClose()){
    		glClearColor(0.0f, 0.0f, 0.4f, 0.0f);
    		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

        	shader.bind();
        	
        	//Bind to the VAO
			glBindVertexArray(mesh.getVAO());
			glEnableVertexAttribArray(0);
			
			//Draw the vertices
			glDrawElements(GL_TRIANGLES, mesh.getVertexCount(), GL_UNSIGNED_INT, 0);
            
			// Restore state
			glDisableVertexAttribArray(0);
			glBindVertexArray(0);
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
