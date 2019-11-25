package my3dlib;

import javax.media.j3d.Appearance;
import javax.media.j3d.Geometry;
import javax.media.j3d.Group;
import javax.media.j3d.IndexedQuadArray;
import javax.media.j3d.Shape3D;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.swing.RootPaneContainer;
import javax.vecmath.Point3f;
import javax.vecmath.Vector3d;

import com.sun.j3d.utils.geometry.Cylinder;
import com.sun.j3d.utils.geometry.GeometryInfo;
import com.sun.j3d.utils.geometry.NormalGenerator;

public class Lamp extends Group {
	
	
	public Lamp(Appearance app) {
		
		Transform3D tr = new Transform3D();
		
		
		//Base
		tr.set(new Vector3d(0f,0.025f,0f));
		Cylinder base = new Cylinder(0.1f,0.05f,Cylinder.GENERATE_NORMALS,30,1,app);
		TransformGroup tg = new TransformGroup(tr);
		
		tg.addChild(base);
		this.addChild(tg);
		
		
		
		//Corpo
		tr = new Transform3D();
		tr.set(new Vector3d(0f,0.55f,0f));
		Cylinder body = new Cylinder(0.025f,1.0f,app);
		tg = new TransformGroup(tr);
		tg.addChild(body);
		this.addChild(tg);
				
		//TOP
		
		Lampshade lampshade = new Lampshade(80,app);
		tr = new Transform3D();
		tr.setScale(0.2);
		tr.setTranslation(new Vector3d(0f,0.95f,0f));
		tg = new TransformGroup(tr);
		tg.addChild(lampshade);
		this.addChild(tg);
		
				
				
		
	}
	
	class Lampshade extends Shape3D{
		
		public Lampshade(int n,Appearance app) { //n corresponde ao numero total de "fatias"
			
			int totalVertices = 4 * n;
			int totalIndices = 16 * n;
			
			IndexedQuadArray iqa = new IndexedQuadArray(totalVertices,IndexedQuadArray.COORDINATES,totalIndices);
			
			Transform3D tr = new Transform3D();
			tr.rotY(2*Math.PI /n);
			//Initial 4 vertices 
			Point3f[] pts = {new Point3f(1f,0f,0f),new Point3f(1.1f,0f,0f),new Point3f(1.1f-0.5f,0.7f,0f),new Point3f(1f -0.5f,0.7f,0f)};
			int index = 0;
			for (int j = 0; j < n; j++) {
				for (int i = 0; i < 4; i++) {
				iqa.setCoordinate(index++ ,pts[i]);
				tr.transform(pts[i]);
				}
			}
			
			int a = 0;
			int b = 1;
			int c = 2;
			int d = 3;
			
			int e,f,g,h;
			index = 0;
			for (int j = 0; j < n; j++) {
				
				e = (a + 4 )% totalVertices;
				f = (b + 4 )% totalVertices;
				g = (c + 4 )% totalVertices;
				h = (d + 4 )% totalVertices;
			
				
				//Back Face
				iqa.setCoordinateIndex(index++, a);
				iqa.setCoordinateIndex(index++, d);
				iqa.setCoordinateIndex(index++, h);
				iqa.setCoordinateIndex(index++, e);

				//Front Face
				iqa.setCoordinateIndex(index++, b);
				iqa.setCoordinateIndex(index++, f);
				iqa.setCoordinateIndex(index++, g);
				iqa.setCoordinateIndex(index++, c);
				
				//Back Face
				iqa.setCoordinateIndex(index++, c);
				iqa.setCoordinateIndex(index++, g);
				iqa.setCoordinateIndex(index++, h);
				iqa.setCoordinateIndex(index++, d);
				
				//Front Face
				iqa.setCoordinateIndex(index++, a);
				iqa.setCoordinateIndex(index++, e);
				iqa.setCoordinateIndex(index++, f);
				iqa.setCoordinateIndex(index++, b);
				
				a = e;
				b = f;
				c = g;
				d = h;
				
				
			}
			GeometryInfo geom = new GeometryInfo(iqa);		
			NormalGenerator ng = new NormalGenerator();
			ng.generateNormals(geom);
			
			this.setGeometry(geom.getGeometryArray());
			this.setAppearance(app);
			
		}
	}

}
