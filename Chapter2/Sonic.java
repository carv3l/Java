package Sonic;

import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;



public class Sonic extends JFrame implements ActionListener{

	int j = 4;
	
    JButton button4 = new JButton("Button 4");

	public Sonic() {
		setSize(600,700);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		
		setLayout(new GridLayout(40, 0));
        add(new JLabel());
        add(button4);
        button4.addActionListener(this);
	}
	public void actionPerformed(ActionEvent ae)
	{
	if(ae.getActionCommand().equals("Button 4")){
	j+=2;
	this.repaint();
	
	}}
	

	public static void main(String[] args) {
		new Sonic();
		
	}
	
	public void paint(Graphics g) {
		
		
		Random rand = new Random();
		
		int x1 = 10; // x starting position
		int y1 = 400; // y starting position 
		int x2 = 50; 
		int y2 = 50;
		g.drawLine(0, 400, 700,400);
		
		for(int i=0;i<=1000;i+=j) {
			x2=i;
			g.drawLine(x1, y1, x2, y2);
			
			x1=x2;
			y1=y2;
						
			int random = rand.nextInt(400 + 100) + 100 ;
			y2 = random;
	
		}
		
        Point p2 = button4.getLocation();
        p2.x += button4.getWidth() / 5;
        p2.y += button4.getHeight() / 5;
    
	}
	
	


}
