package marlon.engine.experimental;

import marlon.engine.graphics3D.Mesh;

public class TestMeshes {
	
	public static Mesh getPlane(){
    	float[] verts = new float[]{
    	    -1.0f,  1.0f, 0.0f,
    	     1.0f,  1.0f, 0.0f,
    	    -1.0f, -1.0f, 0.0f,
    	     1.0f, -1.0f, 0.0f,
    	};
        	
    	float[] colors = new float[]{
    	    1.0f, 0.0f, 0.0f,
    	    0.0f, 1.0f, 0.0f,
    	    0.0f, 0.0f, 1.0f,
    	    1.0f, 1.0f, 0.0f,
    	};
        	
    	int[] indices = new int[]{ 0,1,2,2,1,3 };

    	return new Mesh(verts, colors, indices);
	}

	public static Mesh getCube(){
    	float[] verts = new float[]{
    	    -1.0f,  1.0f, -1.0f,
    	     1.0f,  1.0f, -1.0f,
    	    -1.0f, -1.0f, -1.0f,
    	     1.0f, -1.0f, -1.0f,
     	    -1.0f,  1.0f, 1.0f,
	   	     1.0f,  1.0f, 1.0f,
	   	    -1.0f, -1.0f, 1.0f,
	   	     1.0f, -1.0f, 1.0f,
    	};
        	
    	float[] colors = new float[]{
    	    0.0f, 0.0f, 0.0f,
    	    0.0f, 0.0f, 1.0f,
    	    0.0f, 1.0f, 0.0f,
    	    0.0f, 1.0f, 1.0f,
    	    1.0f, 0.0f, 0.0f,
    	    1.0f, 0.0f, 1.0f,
    	    1.0f, 1.0f, 0.0f,
    	    1.0f, 1.0f, 1.0f,
    	};
        	
    	int[] indices = new int[]{
    			0,1,3, 0,3,2, //Front
    			0,4,5, 0,5,1, //Top
    			0,6,2, 0,6,4, //Left
    			7,4,6, 7,5,4, //Back
    			7,3,1, 7,1,5, //Right
    			7,2,3, 7,6,2, //Bottom
    	};

    	return new Mesh(verts, colors, indices);
	}

}
