package animation;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.renderable.RenderableImage;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class Animation extends JFrame{

	public static void main(String[] args) {
	JFrame frame = new Animation();
	frame.setTitle("Animation");
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	JPanel panel = new MyJPanel();
	
	frame.getContentPane().add(panel);
	
	frame.pack();
	frame.setVisible(true);

	}

}
class MyJPanel extends JPanel implements Runnable {
	
	Shape obj1 = null;
	Shape obj2 = null;
	Shape obj3 = null;
	float angle = 0f;
	

	AffineTransform at = new AffineTransform();
	
	
	
	public MyJPanel () {
		
		setPreferredSize(new Dimension(400,400));
		
		Thread thread = new Thread(this);
		thread.start();

		
	}
	
public void paintComponent(Graphics g) {
	
	super.paintComponent(g);
	
	Graphics2D g2 = (Graphics2D) g;
	
	RenderingHints rh = new RenderingHints(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
	g2.setRenderingHints(rh);
	
	
	
	obj1 = new Rectangle2D.Float(-100,-10,200,20);
	obj2 = new Rectangle2D.Float(-10,-100,20,200);
	
	at.setToIdentity();
	at.translate(200,200);
	at.rotate(Math.toRadians(angle));
	
	obj1 = at.createTransformedShape(obj1);
	obj2 = at.createTransformedShape(obj2);
	
	
	g2.setColor(Color.RED);
	g2.fill(obj1);
	g2.fill(obj2);

}
public void run() {
	
	while (true) {	
		
		angle = (angle + 0.5f) % 360;
		repaint();
		try {
			Thread.sleep(1000/60);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}

}
