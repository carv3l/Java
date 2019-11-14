package testcone;

import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GraphicsConfiguration;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.media.j3d.Appearance;
import javax.media.j3d.Background;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.Geometry;
import javax.media.j3d.GeometryArray;
import javax.media.j3d.Material;
import javax.media.j3d.PolygonAttributes;
import javax.media.j3d.Shape3D;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.media.j3d.TriangleArray;
import javax.media.j3d.TriangleFanArray;
import javax.vecmath.AxisAngle4d;
import javax.vecmath.Color3f;
import javax.vecmath.Point3f;

import com.sun.j3d.utils.applet.MainFrame;
import com.sun.j3d.utils.behaviors.vp.OrbitBehavior;
import com.sun.j3d.utils.geometry.ColorCube;
import com.sun.j3d.utils.universe.SimpleUniverse;


public class TestCone extends Applet {

	public static void main(String[] args) {
		new MainFrame(new TestCone(), 640,480);

	}
	
	public void init() {
		GraphicsConfiguration gc = SimpleUniverse.getPreferredConfiguration();
		Canvas3D cv = new Canvas3D(gc);
		
		setLayout(new BorderLayout());
		add(cv,BorderLayout.CENTER);
		
		
		BranchGroup bg = createSceneGraph();
		bg.compile();
		
		SimpleUniverse su = new SimpleUniverse(cv);
		su.getViewingPlatform().setNominalViewingTransform();
		su.addBranchGraph(bg);
		
		OrbitBehavior ob = new OrbitBehavior(cv);
		ob.setSchedulingBounds(new BoundingSphere());
		su.getViewingPlatform().setViewPlatformBehavior(ob);
	}
	
private BranchGroup createSceneGraph() {
		
		BranchGroup root = new BranchGroup();
		
		float height=2f;
		float raio =1f;
		
		Appearance ap = new Appearance();
		//ap.setMaterial(new Material());
		
		PolygonAttributes pa = new PolygonAttributes(PolygonAttributes.POLYGON_LINE,PolygonAttributes.CULL_NONE,0);
		ap.setPolygonAttributes(pa);
		Geometry geom = coneGeometry1(2f,1f,10);
		
		
		
		Shape3D shape = new Shape3D(geom, ap);
		
		Transform3D tr = new Transform3D();
		tr.setScale(0.4);
		TransformGroup tg = new TransformGroup(tr);
		
		tg.addChild(shape);
		root.addChild(tg);
		
		
		return root;
	}
	
private Geometry coneGeometry1(float h, float raio, int n) {
	
	TriangleArray ta = new TriangleArray(n*3,GeometryArray.COORDINATES);
	
	TriangleArray = new Tria
	
	Point3f apex = new Point3f(0,h,0);
	Point3f p1 = new Point3f(raio,0,0);
	
	int count = 0;
	for (int i = 1; i <= n; i++) {
		float x = (float)(raio*Math.cos(i*2*Math.PI/n));
		float z = (float)(raio*Math.sin(i*2*Math.PI/n));
		
		Point3f p2 = new Point3f(x,0,z);
		
		ta.setCoordinate(count++,apex);
		ta.setCoordinate(count++,p1);
		ta.setCoordinate(count++, p2);
		p1 = p2;		
	}
	return ta;
	
	
	
}
private Geometry coneGeometry2(float h, float raio, int n) {
	int stripCount[] = {n+2};
	TriangleFanArray ta = new TriangleFanArray(n+2,GeometryArray.COORDINATES,stripCount);
	
	Point3f apex = new Point3f(0,h,0);
	
	
	
	Point3f p1 = new Point3f(raio,0,0);
	
	int count = 0;
	for (int i = 1; i <= 3; i++) {
		float x = (float)(raio*Math.cos(i*2*Math.PI/n));
		float z = (float)(raio*Math.sin(i*2*Math.PI/n));
		
		Point3f p2 = new Point3f(x,0,z);
		
		ta.setCoordinate(count++,apex);
		ta.setCoordinate(count++,p1);
		ta.setCoordinate(count++, p2);
		p1 = p2;		
	}
	return ta;
	
	
	
}
	

}
