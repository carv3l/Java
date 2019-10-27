package ex3_3;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Ex3_3 extends JFrame {

	public static void main(String[] args) {
		JFrame frame = new Ex3_3();
		frame.setTitle("Exercicio 3.3");
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		
		JPanel panel = new MyPanel();
		frame.getContentPane().add(panel);
		
		frame.pack();
		frame.setVisible(true);
		
		

		
	}
	
	
	

}

class MyPanel extends JPanel{
	
	
	public MyPanel() {
		
		setPreferredSize(new Dimension(400,400));
		setBackground(Color.CYAN);
		
		
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		
		/*
		g2.translate(100, 100);
		AffineTransform at = new AffineTransform();
		at.setToTranslation(100, 100);
		g2.setTransform(at);
		
			*/
		
		
		g2.translate(200, 200); //Positioning the shape in the center
		flower1(g2);
		flower2(g2);
	}
	private void flower1(Graphics2D g2) {
		
		int n = 8;
		int r = 23;
		
		//Center
		Shape c = new Ellipse2D.Float(-20,-20,40,40);
		g2.setColor(Color.RED);
		g2.fill(c);
		
		//Petal
		Shape p = new Ellipse2D.Float(-20,0,40,100);
		
		
		AffineTransform at = new AffineTransform();
		at.setToTranslation(0, r);
		
		p = at.createTransformedShape(p);
		g2.setColor(Color.BLUE);
		g2.fill(p);
		
		for (int i = 0; i < n-1; i++) {
			at.setToRotation(Math.toRadians(360/n));
			p = at.createTransformedShape(p);
					g2.fill(p);
		}
	}
	
	private void flower2(Graphics2D g2) {
		
		int n = 8;
		int r = 23;
				
		//Petal
		Shape p = new Ellipse2D.Float(-20,0,40,100);
		AffineTransform at = new AffineTransform();
		at.setToTranslation(0, r);
		
		p = at.createTransformedShape(p);
		g2.setColor(Color.GREEN);
		g2.fill(p);
		
		for (int i = 0; i < n-1; i++) {
			at.setToRotation(Math.toRadians(360/n));
			p = at.createTransformedShape(p);
					g2.fill(p);
		}
		
	}
	
	
	
}