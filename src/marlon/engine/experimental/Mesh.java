package marlon.engine.experimental;

import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;

public class Mesh {
	private final int VAO_ID;
	private final int VBO_ID;
	private final int vertexCount;
	
	
	public Mesh(float[] vertices){
		vertexCount	= vertices.length / 3;
		
		FloatBuffer	verticesBuffer = BufferUtils.createFloatBuffer(vertices.length);
		verticesBuffer.put(vertices).flip();
		
		VAO_ID = glGenVertexArrays();
		glBindVertexArray(VAO_ID);
		
		VBO_ID = glGenBuffers();
		glBindBuffer(GL_ARRAY_BUFFER, VBO_ID);
		glBufferData(GL_ARRAY_BUFFER, verticesBuffer, GL_STATIC_DRAW);
		
		glVertexAttribPointer(0, 3, GL_FLOAT, false, 0, 0);
		
		glBindBuffer(GL_ARRAY_BUFFER, 0);
		glBindVertexArray(0);
	}
	
	
	public int getVertexCount(){
		return vertexCount;
	}
	
	public int getVAO(){
		return VAO_ID;
	}
	
	public void discard(){
		glDisableVertexAttribArray(0);

		//Delete the VBO
		glBindBuffer(GL_ARRAY_BUFFER, 0);
		glDeleteBuffers(VBO_ID);
	
		//Delete the VAO
		glBindVertexArray(0);
		glDeleteVertexArrays(VAO_ID);
	}

}
