package animation ;

import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;
import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class Triangle implements Shape {

	GeneralPath triangle_path;
	
	public Triangle(float posx, float s) { // w = width, h = height ( x,y ) = starting position
		triangle_path = new GeneralPath();
	
		
		triangle_path.moveTo(0.0f, -s);
		triangle_path.lineTo(s, s);
		triangle_path.lineTo(-s, s);
		triangle_path.closePath();
	   
	}
	
	
	@Override
	public boolean contains(Point2D arg0) {
	
		return triangle_path.contains(arg0);
	}

	@Override
	public boolean contains(Rectangle2D arg0) {
		return triangle_path.contains(arg0);
	}

	@Override
	public boolean contains(double arg0, double arg1) {
		return triangle_path.contains(arg0,arg1);
	}

	@Override
	public boolean contains(double arg0, double arg1, double arg2, double arg3) {
		return triangle_path.contains(arg0, arg1, arg2, arg3);
	}

	@Override
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return triangle_path.getBounds();
	}

	@Override
	public Rectangle2D getBounds2D() {
		// TODO Auto-generated method stub
		return triangle_path.getBounds2D();
	}

	@Override
	public PathIterator getPathIterator(AffineTransform arg0) {
		// TODO Auto-generated method stub
		return triangle_path.getPathIterator(arg0);
	}

	@Override
	public PathIterator getPathIterator(AffineTransform arg0, double arg1) {
		// TODO Auto-generated method stub
		return triangle_path.getPathIterator(arg0,arg1);
	}

	@Override
	public boolean intersects(Rectangle2D arg0) {
		// TODO Auto-generated method stub
		return triangle_path.intersects(arg0);
	}

	@Override
	public boolean intersects(double arg0, double arg1, double arg2, double arg3) {
		// TODO Auto-generated method stub
		return triangle_path.intersects(arg0,arg1,arg2,arg3);
	}

}