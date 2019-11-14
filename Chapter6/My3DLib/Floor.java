package my3dlib;

import javax.media.j3d.Appearance;
import javax.media.j3d.PolygonAttributes;
import javax.media.j3d.QuadArray;
import javax.media.j3d.Shape3D;
import javax.vecmath.Color3f;
import javax.vecmath.Point3f;

public class Floor extends Shape3D{
	
	
	
	public Floor(int divisions,float min, float max,Color3f color1, Color3f color2,boolean solid) {
	
		
		int m = divisions;
		float a = min;
		float b = max;
		float divx = (b-a) /m;
		
		
		int n = divisions;
		float c = min;
		float d = max;
		float divz = (d-c) /n;
		
		int totalPts = m * n * 4;
		
		Point3f[] pts = new Point3f[totalPts];
		Color3f[] col = new Color3f[totalPts];
		
		int index = 0;
		boolean invert = true;
		
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				//Vertex 0
			float x = a + i * divx;
			float z = c + j * divz;
			float y = 0;
			pts[index] = new Point3f(x,y,z);
			col[index] = (invert ? color1 : color2);
			index++;
			
			//Vertex1
			x = a + i * divx;
			z = c + (j+1) * divz;
			y = 0;
			pts[index] = new Point3f(x,y,z);
			col[index] = (invert ? color1 : color2);
			index++;
			
			// Vertex 2
			x = a + (i-1) * divx;
			z = c + (j+1) * divz;
			y = 0;
			pts[index] = new Point3f(x,y,z);
			col[index] = (invert ? color1 : color2);
			index++;
			
			//Vertex 3 
			x = a + (i-1) * divx;
			z = c + j * divz;
			y = 0;
			pts[index] = new Point3f(x,y,z);
			col[index] = (invert ? color1 : color2);
			index++;
			
			
			invert = !invert;
			
			}
			
		}
		
		
		QuadArray geom = new QuadArray(totalPts, QuadArray.COORDINATES | QuadArray.COLOR_3);
		
		geom.setCoordinates(0,pts);
		geom.setColors(0, col);
		
		Appearance ap = new Appearance();
		
		PolygonAttributes pa = new PolygonAttributes();
		
		if (solid) {
			pa.setPolygonMode(PolygonAttributes.POLYGON_FILL);
		}else {
			pa.setPolygonMode(PolygonAttributes.POLYGON_LINE);
		}
		
		
		pa.setCullFace(PolygonAttributes.CULL_NONE);
		ap.setPolygonAttributes(pa);

		this.setGeometry(geom);
		this.setAppearance(ap);
		
		
		
	}
	

}
