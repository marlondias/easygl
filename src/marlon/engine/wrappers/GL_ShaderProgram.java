package marlon.engine.wrappers;

import java.nio.IntBuffer;
import org.lwjgl.opengl.GL20;

public class GL_ShaderProgram {
	private final int PROGRAM_ID;
	
	
	public GL_ShaderProgram(){
		PROGRAM_ID = GL20.glCreateProgram();
	}
	
	
	public void attachShader(GL_Shader shader){
		//Associa um shader
		GL20.glAttachShader(PROGRAM_ID, shader.getID());
	}
	
	public void detachShader(GL_Shader shader){
		//Retira o shader informado
		GL20.glDetachShader(PROGRAM_ID, shader.getID());
	}
	
	public void detachShaders(){
		//Retira todos os shaders associados ao programa
		IntBuffer shaders = GL20.glGetAttachedShaders(PROGRAM_ID);
		for (int shad : shaders.array()){
			GL20.glDetachShader(PROGRAM_ID, shad);
		}
	}
	
	public void link(){
		//Cria um executável a partir dos shaders associados
		GL20.glLinkProgram(PROGRAM_ID);
		//TODO: exibir erros de link
	}
	
	public void use(){
		//Coloca os executáveis como parte do estado atual do contexto
		GL20.glUseProgram(PROGRAM_ID);
	}

	public void delete(){
		//Marca este programa para exclusão
		GL20.glDeleteProgram(PROGRAM_ID);
	}
	
	public int getID(){
		return PROGRAM_ID;
	}
}
