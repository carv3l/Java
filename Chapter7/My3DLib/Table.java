package my3dlib;

import javax.media.j3d.Appearance;
import javax.media.j3d.Group;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Vector3d;

import com.sun.j3d.utils.geometry.Box;
import com.sun.j3d.utils.geometry.Cylinder;

public class Table extends Group{
	
	public Table(Appearance app) {
		
		// Table TOP
		Box top = new Box(0.5f, 0.05f, 0.5f, app);
		
		Transform3D tr = new Transform3D();
		tr.setTranslation(new Vector3d(0f,0.05f+0.5f,0f));
		TransformGroup tg = new TransformGroup(tr);
		tg.addChild(top);
		
		this.addChild(tg);
	
		//First LEG

		Cylinder leg = new Cylinder(0.05f,0.5f,app);
		tr.setTranslation(new Vector3d(0.4f,0.25f,0.4f));
		tg = new TransformGroup(tr);
		tg.addChild(leg);
		this.addChild(tg);
		//Sec LEG

				leg = new Cylinder(0.05f,0.5f,app);
				tr.setTranslation(new Vector3d(0.4f,0.25f,-0.4f));
				tg = new TransformGroup(tr);
				tg.addChild(leg);
				this.addChild(tg);
				//ThirdLEG

				leg = new Cylinder(0.05f,0.5f,app);
				tr.setTranslation(new Vector3d(-0.4f,0.25f,-0.4f));
				tg = new TransformGroup(tr);
				tg.addChild(leg);
				this.addChild(tg);
				//Four LEG

				leg = new Cylinder(0.05f,0.5f,app);
				tr.setTranslation(new Vector3d(-0.4f,0.25f,0.4f));
				tg = new TransformGroup(tr);
				tg.addChild(leg);
				this.addChild(tg);
		
	}

}
