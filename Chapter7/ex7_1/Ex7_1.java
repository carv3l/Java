package ex7_1;
import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GraphicsConfiguration;
import java.awt.image.AreaAveragingScaleFilter;

import javax.media.j3d.AmbientLight;
import javax.media.j3d.Appearance;
import javax.media.j3d.Background;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.Bounds;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.ColoringAttributes;
import javax.media.j3d.Geometry;
import javax.media.j3d.Material;
import javax.media.j3d.PointLight;
import javax.media.j3d.PolygonAttributes;
import javax.media.j3d.Shape3D;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.swing.table.TableColumn;
import javax.vecmath.Color3f;
import javax.vecmath.Point3f;
import javax.vecmath.Vector3d;

import com.sun.j3d.utils.applet.MainFrame;
import com.sun.j3d.utils.behaviors.vp.OrbitBehavior;
import com.sun.j3d.utils.universe.SimpleUniverse;

import my3dlib.Axes;
import my3dlib.Floor;
import my3dlib.Lamp;
import my3dlib.Table;



public class Ex7_1 extends Applet {
	
	BoundingSphere bounds = new BoundingSphere();

	public static void main(String[] args) {
		new MainFrame(new Ex7_1(), 800,800);

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
		ob.setSchedulingBounds(bounds);
		su.getViewingPlatform().setViewPlatformBehavior(ob);
	}
	
private BranchGroup createSceneGraph() {
		
		BranchGroup root = new BranchGroup();
		//Axes
		root.addChild(new Axes(new Color3f(Color.RED),3,0.5f));
		
		//Floor
		root.addChild(new Floor(11, -1, 1, new Color3f(Color.DARK_GRAY), new Color3f(Color.WHITE), true));
		
		
		//Tables
		Appearance app = new Appearance();
		app.setMaterial(new Material());

	//	ColoringAttributes ca = new ColoringAttributes();
		//ca.setColor(new Color3f(Color.RED));
		
		//PolygonAttributes pa = new PolygonAttributes();
		//pa.setPolygonMode(PolygonAttributes.POLYGON_LINE);
		//pa.setCullFace(PolygonAttributes.CULL_NONE);
		
	//	app.setPolygonAttributes(pa);
		//app.setColoringAttributes(ca);
		
		
		Table table = new Table(app);
		
		
		
		
		
		Transform3D tr = new Transform3D();
		tr.setScale(0.5);
		tr.setTranslation(new Vector3d(0.5f,0f,0f));
		TransformGroup tg = new TransformGroup(tr);
		tg.addChild(table);
		root.addChild(tg);
		
		//LAMP
		Lamp lamp = new Lamp(app);
		tr = new Transform3D();
		tr.setScale(0.5);	
		tg = new TransformGroup(tr);
		tg.addChild(lamp);
		root.addChild(tg);
		
		
		
		
		
		Background bk = new Background(new Color3f(Color.BLACK));
		bk.setApplicationBounds(bounds);
		root.addChild(bk);
		
		AmbientLight ablight = new AmbientLight(true,new Color3f(Color.WHITE));
		ablight.setInfluencingBounds(bounds);
		root.addChild(ablight);
		
		PointLight ptLight = new PointLight(new Color3f(Color.YELLOW),new Point3f(3f,3f,3f), new Point3f(1f,0f,0f));
		ptLight.setInfluencingBounds(bounds);
		root.addChild(ptLight);
		
		return root;
}

}


		