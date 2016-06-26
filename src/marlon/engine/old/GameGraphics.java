package marlon.engine.old;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWWindowSizeCallback;
import org.joml.Matrix4f;
import marlon.engine.experimental.Assets;
import marlon.engine.experimental.TestMeshes;
import marlon.engine.graphics3D.GameItem;
import marlon.engine.graphics3D.Mesh;
import marlon.engine.graphics3D.Transformation;
import marlon.engine.wrappers.GLFW_Cursor;
import marlon.engine.wrappers.GLFW_Window;
import marlon.engine.wrappers.GL_ShaderProgram;
import marlon.engine.wrappers.GLFW_Lib;

public class GameGraphics {
	private int width = 800;
	private int height = 600;
	private GLFW_Window janela1;
	private GLFW_Cursor cursor1;
	private GL_ShaderProgram shader;
	private Mesh mesh;
	
	private float FOV = (float)Math.toRadians(60.0);
	private Transformation transformation = new Transformation();
	public GameItem[] gameItems = new GameItem[1];

	
	public GameGraphics(){
		GLFW.glfwWindowHint(GLFW.GLFW_OPENGL_PROFILE, GLFW.GLFW_OPENGL_CORE_PROFILE);
		
		janela1 = new GLFW_Window(width, height, "Aqui estoy!", true);
    	cursor1 = new GLFW_Cursor(GLFW.GLFW_CROSSHAIR_CURSOR);
    	janela1.setCursor(cursor1);
    	janela1.setPosition(10, 10);
    	janela1.setVisible(true);
    	GLFW_Lib.setContextCurrent(janela1);
    	glEnable(GL_DEPTH_TEST);
    	
    	shader = new GL_ShaderProgram();
    	shader.attachShader(Assets.vertShader1);
    	shader.attachShader(Assets.fragShader1);
    	
    	glBindAttribLocation(shader.getID(), 0, "position");
    	glBindAttribLocation(shader.getID(), 1, "color");
    	shader.link();
    	

    	mesh = TestMeshes.getCube();
    	
    	gameItems[0] = new GameItem(TestMeshes.getCube());
    	gameItems[0].setPosition(0, 0, -10);
    	
    	shader.createUniform("projectionMatrix");
    	shader.createUniform("worldMatrix");
	}
	
	
    public void render(){
    	float rotation = gameItems[0].getRotation().y + 1.0f;
    	if	(rotation > 360) rotation = 0;
    	gameItems[0].setRotation(rotation,rotation,rotation);
    	
    	if (!janela1.getShouldClose()){
    		glClearColor(0.0f, 0.0f, 0.4f, 0.0f);
    		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
    		
        	shader.bind();

        	//Atualiza a matriz projeção
        	Matrix4f projectionMatrix = transformation.getProjectionMatrix(FOV, width, height, 0.01f, 1000f);
        	shader.setUniform("projectionMatrix", projectionMatrix);
        	
        	for (GameItem item : gameItems){
        		Matrix4f worldMatrix = transformation.getWorldMatrix(
        				item.getPosition(),	item.getRotation(), item.getScale());
        		
        		shader.setUniform("worldMatrix", worldMatrix);
        		
        		item.getMesh().render();
        	}
        	
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
