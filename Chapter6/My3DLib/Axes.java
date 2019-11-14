package my3dlib;

import javax.media.j3d.Appearance;
import javax.media.j3d.ColoringAttributes;
import javax.media.j3d.LineArray;
import javax.media.j3d.LineAttributes;
import javax.media.j3d.Shape3D;
import javax.swing.text.AttributeSet.ColorAttribute;
import javax.vecmath.Color3f;
import javax.vecmath.Point3f;

public class Axes extends Shape3D {
	
	
	
	public Axes(Color3f color, float thickness, float lenght) {
		
		LineArray geom= new LineArray(6, LineArray.COORDINATES);
		
		geom.setCoordinate(0, new Point3f(0,0,0));
		
		geom.setCoordinate(0, new Point3f(0,0,0));
		geom.setCoordinate(1, new Point3f(lenght,0,0));
		
		geom.setCoordinate(2, new Point3f(0,0,0));
		geom.setCoordinate(3, new Point3f(0,lenght,0));
		
		geom.setCoordinate(4, new Point3f(0,0,0));
		geom.setCoordinate(5, new Point3f(0,0,lenght));
		
		Appearance ap = new Appearance();
		ColoringAttributes ca = new ColoringAttributes(color, ColoringAttributes.SHADE_FLAT);
		ap.setColoringAttributes(ca);
		
		LineAttributes la= new LineAttributes(thickness,LineAttributes.PATTERN_SOLID,true);
		ap.setLineAttributes(la);
		
		
		
		this.setGeometry(geom);
		this.setAppearance(ap);
		
		
	}

}
