package ex3_4;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.TexturePaint;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class ex3_4 extends JFrame {

	public static void main(String[] args) {

		JFrame frame = new ex3_4();
		frame.setTitle("Dataplot");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new MyJPanel();
		
		frame.getContentPane().add(panel);
		
		frame.pack();
		frame.setVisible(true);
		
		
		
		

	}
	
}
class MyJPanel extends JPanel{
	
	public MyJPanel () {
		
		setPreferredSize(new Dimension(400,400));
		setBackground(Color.WHITE);
		
	}
	
public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		
		Font font = new Font("Serif", Font.BOLD, 300);
		FontRenderContext frc = g2.getFontRenderContext();
		
		
		Shape shapeN = font.createGlyphVector(frc, "N").getOutline();
		Shape shapeY = font.createGlyphVector(frc, "Y").getOutline();
	
		
		Area area = new Area(shapeN);
		area.add(new Area(shapeY));
		
		int wS= area.getBounds().width;
		int hS = area.getBounds().height;
		int x = area.getBounds().x;
		int y = area.getBounds().y;
		
		AffineTransform at = new AffineTransform();
		at.setToTranslation(0, -y);
		
		//Shape s = at.createTransformedShape(area);
		area = area.createTransformedArea(at);	
		
		
		y = 0;
		//GradientPaint gp = new GradientPaint(x, y,Color.RED,wS, hS ,Color.GREEN);
		
		
		TexturePaint tp = new TexturePaint(, anchor);
		
		g2.setPaint(gp);
		g2.translate((400-wS)/2, (400-hS)/2);

		g2.draw(area);
		g2.fill(area);
		
		//g2.setColor(Color.BLACK);
	//	g2.drawLine(area.getBounds().x,area.getBounds().y , wS, hS);
		
		
		
		
		
	}

BufferedImage readImage(String imagafilename) {
	BufferedImage bi = null;
	URL url = null;
	
	try {
		url = getClass().getClassLoader().getResource(imagafilename);
				bi =  ImageIO.read(url);
	}catch (IOException e) {
		// TODO: handle exception
	}
	
	
	
	
	
	
	return;
}



}
	
