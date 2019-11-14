package ex6_2;

import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GraphicsConfiguration;
import java.awt.image.AreaAveragingScaleFilter;

import javax.media.j3d.Appearance;
import javax.media.j3d.Background;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.Bounds;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.Geometry;
import javax.media.j3d.PolygonAttributes;
import javax.media.j3d.Shape3D;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Color3f;

import com.sun.j3d.utils.applet.MainFrame;
import com.sun.j3d.utils.behaviors.vp.OrbitBehavior;
import com.sun.j3d.utils.universe.SimpleUniverse;

import my3dlib.Axes;
import my3dlib.Floor;



public class Ex6_2 extends Applet {
	
	BoundingSphere bounds = new BoundingSphere();

	public static void main(String[] args) {
		new MainFrame(new Ex6_2(), 640,480);

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
		
	
	Floor floor = new Floor(21,-6,6, new Color3f(Color.RED), new Color3f(Color.BLACK),true);
		//Floor floor = new Floor(21,-6,6, new Color3f(Color.GRAY), new Color3f(Color.GRAY),false);
		
		Transform3D tr = new Transform3D();
		tr.setScale(-0.1);
		TransformGroup tg = new TransformGroup(tr);
		
		
		tg.addChild(floor);
		
		root.addChild(tg);
		
		
		//Axes 
		Axes axes = new Axes(new Color3f(Color.GREEN), 20f, 1f);
		
		root.addChild(axes);
		
		
		
		//Background
		
		Background bk = new Background(new Color3f(Color.LIGHT_GRAY));
		bk.setApplicationBounds(bounds);
		
		root.addChild(bk);
		
		return root;
	}
	

}
