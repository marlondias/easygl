package marlon.engine.wrappers;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL32;
import org.lwjgl.opengl.GL40;

public class GL_ShaderCode {
	private final String SOURCE_CODE;
	private final int TYPE;
	
	
	public GL_ShaderCode(int type, String fileName){
		if (type != GL20.GL_VERTEX_SHADER && type != GL20.GL_FRAGMENT_SHADER && type != GL32.GL_GEOMETRY_SHADER && type != GL40.GL_TESS_CONTROL_SHADER && type != GL40.GL_TESS_EVALUATION_SHADER){
			//Tipo não é válido
		}
		TYPE = type;
		SOURCE_CODE = loadFromFile(fileName);
	}
	
	
	public String getCode(){
		return SOURCE_CODE;
	}
	
	public int getType(){
		return TYPE;
	}

	private static String loadFromFile(String filename){
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
