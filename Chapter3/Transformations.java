package transformation;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.net.CookieHandler;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Transformations extends JFrame{

	public static void main(String[] args) {
		
		JFrame frame = new Transformations();
		frame.setTitle("Transformations");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new MyPanel();
		frame.getContentPane().add(panel);
		
		frame.pack();
		frame.setVisible(true);
		
		

	}

}

class MyPanel extends JPanel{
	public MyPanel() {
		
		setPreferredSize(new Dimension(400, 400));
		setBackground(new Color(200,200,200));
	}
	
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D) g;
		
		int w = getWidth();
		int h = getHeight();
		drawAxis(g2, Color.RED);
		
		
		//g2.translate(100, 100);
	
		AffineTransform at = new AffineTransform(g2.getTransform());
		
		g2.translate(w/2, h/2);
		g2.rotate(Math.toRadians(30));
		drawAxis(g2, Color.BLUE);
		Shape r = new Rectangle2D.Double(-50,-50, 100,100);
		g2.setColor(Color.BLUE);
		g2.fill(r);
		
		g2.setTransform(at);
		
		g2.rotate(Math.toRadians(30));
		g2.translate(w/2, h/2);
		drawAxis(g2, Color.GREEN);
		r = new Rectangle2D.Double(-50,-50, 100,100);
		g2.setColor(Color.GREEN);
		g2.fill(r);
		
		
		
	}
	private void drawAxis(Graphics2D g2,Color c) {
		g2.setColor(c);
		g2.drawLine(-400, 0, 400, 0);
		g2.drawLine(0, -400, 0, 400);
		
		
	}
	
}
