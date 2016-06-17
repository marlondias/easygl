package marlon.engine.wrappers;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL32;
import org.lwjgl.opengl.GL40;

public class GL_Shader {
	private final int SHADER_ID; //Valor retonado por createShader
	private boolean valid; //Se a criação ocorreu normalmente
	
	
	public GL_Shader(int type, String sourceCode){
		if (type != GL20.GL_VERTEX_SHADER && type != GL20.GL_FRAGMENT_SHADER && type != GL32.GL_GEOMETRY_SHADER && type != GL40.GL_TESS_CONTROL_SHADER && type != GL40.GL_TESS_EVALUATION_SHADER){
			//Não é válido
			//TODO: Throw algo..
		}
		
		SHADER_ID = GL20.glCreateShader(type); //Cria um shader do tipo informado, ou 0 em caso de erro
		valid = (SHADER_ID != 0);
		
		setSourceCode(loadFromFile(sourceCode));
	}

	private void setSourceCode(String code){
		if (!valid) return;
		//TODO: verificar existencia desse objeto shader
		
		GL20.glShaderSource(SHADER_ID, code);
		GL20.glCompileShader(SHADER_ID);
		if (!wasCompiled()){
			//Exibe os erros de compilação
			System.err.println("Shader compilation error!");
			System.err.println(GL20.glGetShaderInfoLog(SHADER_ID));
		}
	}

	public int getID(){
		return SHADER_ID;
	}
	
	public int getType(){
		//returns GL_VERTEX_SHADER if shader is a vertex shader object, GL_GEOMETRY_SHADER if 
		//shader is a geometry shader object, and GL_FRAGMENT_SHADER if shader is a fragment shader object.
		return (GL20.glGetShaderi(SHADER_ID, GL20.GL_SHADER_TYPE));
	}
	
	public boolean wasCompiled(){
		//returns GL_TRUE if the last compile operation on shader was successful, and GL_FALSE otherwise.
		return (GL20.glGetShaderi(SHADER_ID, GL20.GL_COMPILE_STATUS) != 0);
	}
	
	public boolean shouldDelete(){
		//returns GL_TRUE if shader is currently flagged for deletion, and GL_FALSE otherwise.
		return (GL20.glGetShaderi(SHADER_ID, GL20.GL_DELETE_STATUS) != 0);
	}
	
	public static String loadFromFile(String filename){
		FileReader fr = null;
		try {
			fr = new FileReader(filename);
		} catch (FileNotFoundException e1) {e1.printStackTrace();}
		
		String sourceCode = "";
		
		try(BufferedReader br = new BufferedReader(fr)) {
		    StringBuilder sb = new StringBuilder();
		    String line = br.readLine();
		    while (line != null) {
		        sb.append(line);
		        sb.append(System.lineSeparator());
		        line = br.readLine();
		    }
		    sourceCode = sb.toString();
		} catch (IOException e) {e.printStackTrace();}
		
		return sourceCode;
	}

}
