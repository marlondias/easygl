package marlon.engine.graphics3D;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import org.lwjgl.BufferUtils;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;

public class Mesh {
	private final int VAO_ID; //Identificador único desse Vertex Array Object
	private final int vertexCount;
	private final int verticesVBO;
	private final int colorsVBO;
	private final int indicesVBO;
	
	
	public Mesh(float[] vertices, float[] colors, int[] indices){
		
		vertexCount	= indices.length;
		
		VAO_ID = glGenVertexArrays();
		glBindVertexArray(VAO_ID);
		
			//Buffer com todos os vértices (sem repetições)
			FloatBuffer	verticesBuffer = BufferUtils.createFloatBuffer(vertices.length);
			verticesBuffer.put(vertices).flip();
			verticesVBO = glGenBuffers();
			glBindBuffer(GL_ARRAY_BUFFER, verticesVBO);
			glBufferData(GL_ARRAY_BUFFER, verticesBuffer, GL_STATIC_DRAW);
			glVertexAttribPointer(0, 3, GL_FLOAT, false, 0, 0);
			
			//Buffer com todas as cores
			FloatBuffer	colorsBuffer = BufferUtils.createFloatBuffer(colors.length);
			colorsBuffer.put(colors).flip();
			colorsVBO = glGenBuffers();
			glBindBuffer(GL_ARRAY_BUFFER, colorsVBO);
			glBufferData(GL_ARRAY_BUFFER, colorsBuffer, GL_STATIC_DRAW);
			glVertexAttribPointer(1, 3, GL_FLOAT, false, 0, 0);
			
			//Buffer que indica a sequência de acesso aos vértices
			IntBuffer indicesBuffer = BufferUtils.createIntBuffer(indices.length);
			indicesBuffer.put(indices).flip();
			indicesVBO = glGenBuffers();
			glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, indicesVBO);
			glBufferData(GL_ELEMENT_ARRAY_BUFFER, indicesBuffer, GL_STATIC_DRAW);
			
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
		//Disable the attribute arrays
		glDisableVertexAttribArray(0);
		glDisableVertexAttribArray(1);

		//Delete the VBOs
		glBindBuffer(GL_ARRAY_BUFFER, 0);
		glDeleteBuffers(verticesVBO);
		glDeleteBuffers(colorsVBO);
		glDeleteBuffers(indicesVBO);
	
		//Delete the VAO
		glBindVertexArray(0);
		glDeleteVertexArrays(VAO_ID);
	}
	
	public void render(){
    	//Bind the VAO
		glBindVertexArray(VAO_ID);
		glEnableVertexAttribArray(0);
		glEnableVertexAttribArray(1);
		
		//Draw the vertices
		glDrawElements(GL_TRIANGLES, vertexCount, GL_UNSIGNED_INT, 0);
        
		// Restore state
		glDisableVertexAttribArray(0);
		glDisableVertexAttribArray(1);
		glBindVertexArray(0);
	}

}
