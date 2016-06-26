package marlon.engine.graphics3D;

import org.joml.Vector3f;

public class GameItem {
	private final Mesh mesh;
	private final Vector3f position;
	private final Vector3f rotation;
	//private final Vector3f scale;
	private float scale;
	
	
	public GameItem(Mesh mesh){
		this.mesh = mesh;
		position = new Vector3f(0,0,0);
		rotation = new Vector3f(0,0,0);
		scale = 1.0f;
	}
	
	
	public void setPosition(float x, float y, float z){
		//Changes the world coordinates of this item
		position.x = x;
		position.y = y;
		position.z = z;
	}
	
	public Vector3f getPosition(){
		return position;
	}

	public void setRotation(float x, float y, float z){
		//Changes the world coordinates of this item
		rotation.x = x;
		rotation.y = y;
		rotation.z = z;
	}
	
	public Vector3f getRotation(){
		return rotation;
	}
	
	public void setScale(float scale){
		this.scale = scale;
	}
	
	public float getScale(){
		return scale;
	}

	public Mesh getMesh(){
		return mesh;
	}

}
