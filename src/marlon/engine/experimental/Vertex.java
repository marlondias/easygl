package marlon.engine.experimental;

public class Vertex {
	private final float[] coordinates = new float[3];

	public Vertex(float x, float y, float z){
		coordinates[0] = (x >= -1 && x <= 1) ? x : 0;
		coordinates[1] = (y >= -1 && y <= 1) ? y : 0;
		coordinates[2] = (z >= -1 && z <= 1) ? z : 0;
	}
	
	public float[] getVertex(){
		return coordinates;
	}

}
