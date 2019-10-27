package dataplot;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.font.FontRenderContext;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class DataPlot extends JFrame {

	public static void main(String[] args) {

		JFrame frame = new DataPlot();
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
		
		setPreferredSize(new Dimension(600,500));
		
	}
	
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		
		float[] vS1 = {350.3f, 234.5f, 888.2f, 523.9f};  // Valores ao calhas para mostrar no grafico
		float[] vS2 = {250.3f, 134.5f, 576.2f, 423.9f};
		String[] xLabels = {"Trim1","Trim2","Trim3","Trim4"};  // Nomes 
		
		
		dataplot(g2,100,200,400,200,vS1,vS2,xLabels,"Home Energy Expenses");
		
		
		
	}
	
	private void dataplot(Graphics2D g2,int x,int y, int width, int height, float vS1[], float vS2[], String xLabels[], String title) {
		
		//Area of the graphic 
		
		g2.setColor(Color.BLACK);
		g2.drawRect(x, y, width, height);
		
		//Area of the plot

	//	g2.setColor(Color.black);
//		g2.drawRect(x+20, y+20, width-50, height-30);  // ONE WAY TO DO IT
		
		//OTHER WAY TO DO IT
		
		int wGap = 50;
		int hGap = 30;
		int wP = width -2 * wGap;
		int hP = height - 2 * hGap;
		
		int xP = x + wGap;
		int yP = y + hGap;
		
		g2.setColor(Color.WHITE);
		g2.fillRect(xP, yP, wP, hP);
		g2.drawRect(xP, yP, wP, hP);
		
		
		// X AXIS
		
		g2.setColor(Color.BLUE);
		
		g2.drawLine(xP, yP+hP, xP+wP, yP+hP);
		
		
		//SET THE DOTS IN THE X AXIS
		int delta = wP /4;
		for (int i = 0; i <= vS1.length; i++) {
			
			int xi = xP + i * delta;
			int yi = yP+hP;
			g2.drawLine(xP+(i*delta), yP+hP,xP+(i*delta), (yP+hP)-3);//MY THING
			
			g2.drawLine(xi, yi, xi, yi+5);
			
		}
		
		
		//FONTS
		Font axisFont = new Font("Verdana", Font.PLAIN, 10);
		Font TitleFont = new Font("Serif", Font.BOLD, 16);
		FontRenderContext frc;
		
		
		
		
		
		//BAR PART WITH THE STRING
		int barWidth = 30;
		int halfbarWidth = barWidth/2;
		int halfdelta = delta /2;
		int maxvalue = (int) Math.ceil(getMaxValue(vS1));
		float fscale = hP / (float)maxvalue; 
		
		g2.setFont(axisFont);
		frc = g2.getFontRenderContext();
		
		
		
		int yAxisDivisions = 5;
		delta = hP /yAxisDivisions;
		
		int yIncrementValue = maxvalue / yAxisDivisions;
		
		
		while (maxvalue % yAxisDivisions != 0) {
			maxvalue++;
			
		}
		

		
		for (int i = 0; i < vS1.length; i++) {
			
			g2.setColor(Color.RED);
			
			int barHeight = (int) (vS1[i] * fscale);
			
			int xBar = (xP + i * delta) + halfdelta - halfbarWidth;
			int yBar = yP+hP - barHeight;
			
			g2.fillRect(xBar, yBar, barWidth, barHeight);
			
			g2.setColor(Color.BLACK);
			
			int widthString = (int) axisFont.getStringBounds(xLabels[i],frc).getWidth();
			int heightString = (int) axisFont.getStringBounds(xLabels[i],frc).getHeight();
			
			
			
			xBar = xP + i * delta + halfdelta - widthString/2;
			yBar = yP + hP + heightString +5;
	
				g2.drawString(xLabels[i], xBar, yBar);
				
		
			
			
		}
		
		// Y AXIS
		g2.setColor(Color.GREEN);
		g2.drawLine(xP, yP, xP, yP+ hP);
	
		for (int i = 0; i <= yAxisDivisions; i++) {
			g2.setColor(Color.BLACK);
			
			int xi = xP;
			int yi = yP + hP - i * delta;
			g2.drawLine(xi, yi, xi-4, yi);
			

			String si = Integer.toString(i * yIncrementValue);
			int wS =(int) axisFont.getStringBounds(si, frc).getWidth();
			int hS =(int) axisFont.getStringBounds(si, frc).getHeight();
			xi = xi - wS -5;
			yi = yi + hS /2;
		
			g2.drawString(si, xi, yi);
			
		}
		
		
		//TITLE
		int wS =(int) TitleFont.getStringBounds(title, frc).getWidth();
		int hS =(int) TitleFont.getStringBounds(title, frc).getHeight();
		g2.setFont(TitleFont);
		
		frc = g2.getFontRenderContext();
		
		int xTitle = x + (width -wS)/2;
 		int yTitle = yP -10;
		g2.drawString(title,xTitle, yTitle);
		
		
		
		
		
		
		
		
		
	}
	
	private float getMaxValue(float v[]) {
		
		float max = 0f;
		
		for (int i = 0; i < v.length; i++) {
			
			if (v[i] > max ) {
				max = v[i];
				
			}
		
		}	
		return max;
		
	}
	
	
}
