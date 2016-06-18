package marlon.engine.wrappers;

import java.nio.IntBuffer;
import org.lwjgl.opengl.GL32;
import org.lwjgl.opengl.GL40;
import static org.lwjgl.opengl.GL20.*;

public class GL_ShaderProgram {
	private final int PROGRAM_ID;
	private boolean valid;
	
	
	public GL_ShaderProgram(){
		PROGRAM_ID = glCreateProgram();
		valid = (PROGRAM_ID != 0);
	}
	
	
	public int getID(){
		return PROGRAM_ID;
	}
	
	public void attachShader(GL_ShaderCode source){
		if (!valid) return;

		int type = source.getType();
		if (type != GL_VERTEX_SHADER && type != GL_FRAGMENT_SHADER && type != GL32.GL_GEOMETRY_SHADER && type != GL40.GL_TESS_CONTROL_SHADER && type != GL40.GL_TESS_EVALUATION_SHADER){
			System.err.println("Error: TYPE variable is not a valid shader type!");
			return;
		}
		
		int shaderID = glCreateShader(type); //Cria um shader do tipo informado, ou 0 em caso de erro
		if (shaderID == 0){
			//Algum erro estranho
			System.err.println("Error: shader could not be created!");
			return;
		}
		
		glShaderSource(shaderID, source.getCode());
		
		glCompileShader(shaderID);
		if (glGetShaderi(shaderID, GL_COMPILE_STATUS) == 0){
			//Exibe os erros de compilação
			System.err.println("Error: shader compilation failed!");
			System.err.println(glGetShaderInfoLog(shaderID));
			return;
		}
		
		glAttachShader(PROGRAM_ID, shaderID); //Associa um shader
	}
	
	public void detachShaders(){
		//Retira todos os shaders associados ao programa
		IntBuffer shaders = glGetAttachedShaders(PROGRAM_ID);
		for (int shad : shaders.array()){
			glDetachShader(PROGRAM_ID, shad);
		}
	}
	
	public void link(){
		//Cria um executável a partir dos shaders associados
		
		glLinkProgram(PROGRAM_ID);
		if (glGetProgrami(PROGRAM_ID, GL_LINK_STATUS) == 0){
			//Erro de link
			System.err.println("Error: shader linking failed!");
			System.err.println(glGetShaderInfoLog(PROGRAM_ID));
		}
		
		glValidateProgram(PROGRAM_ID);
		if (glGetProgrami(PROGRAM_ID, GL_VALIDATE_STATUS) == 0){
			//Erro de validação
			System.err.println("Error: shader validation failed!");
			System.err.println(glGetShaderInfoLog(PROGRAM_ID));
		}
	}
	
	public void bind(){
		//Coloca os executáveis como parte do estado atual do contexto
		glUseProgram(PROGRAM_ID);
	}

	public void unbind(){
		//Tira os executáveis do estado atual do contexto
		glUseProgram(0);
	}

	public void discard(){
		//Libera recursos e marca este programa para exclusão
		unbind();
		glDeleteProgram(PROGRAM_ID); 
	}
	
}
