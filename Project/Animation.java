package animation;
import java.awt.BasicStroke;
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
import java.awt.geom.GeneralPath;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Rectangle2D.Float;
import java.awt.image.renderable.RenderableImage;
import java.io.Console;
import java.util.Random;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
class MyJPanel extends JPanel implements Runnable, KeyListener{
	
	int ball_size = 23;
	int type = 1;
	int dimension = 800;
	Shape player_object = playerobj(200,200,ball_size);
	Shape obj2 = randomObj(dimension,dimension);
	Shape obj3 = null;
	int deltaX, deltaY;
	int actualX, actualY;
	int time= 1200;
	int score = 1;
	JLabel label = new JLabel();
	
	
	
	

	AffineTransform at = new AffineTransform();
	
	
	public MyJPanel () {
		
		setPreferredSize(new Dimension(dimension,dimension));
		setFocusable(true);
		Thread thread = new Thread(this);
		thread.start();
		addKeyListener(this);
		
		label.setText("");
		add(label);
		
		randomObj(dimension,dimension);

	//obj3 = new Triangle(200, 20);
	

		
	}
	
public void paintComponent(Graphics g) {
	
	super.paintComponent(g);
	
	Graphics2D g2 = (Graphics2D) g;
	
	RenderingHints rh = new RenderingHints(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
	g2.setRenderingHints(rh);
	g2.setStroke(new BasicStroke(3));
	
		
	g2.drawLine(30, 20,getWidth()-20,20); //x
	g2.drawLine(30, getHeight()-20,30,20); //y
	g2.drawLine(getWidth()-20, 20,getWidth()-20,getHeight()-20); //y-1
	g2.drawLine(30, getHeight()-20,getWidth()-20,getHeight()-20);  //x-1
	
//	g2.drawRect(30,20,3, getHeight()-40);
//	g2.drawRect(getWidth()-20,20, 3, getHeight()-40);
//	g2.drawRect(30, getHeight()-20, getWidth()-50, 3);
	
	
	//at.setToIdentity();
//	at.rotate(Math.toRadians(angle));
	
	player_object = at.createTransformedShape(player_object);
	
	obj3 = at.createTransformedShape(obj3);
	
	//obj2 = at.createTransformedShape(randomObj());
	//obj2 = randomObj();
	//at.translate(400,400);

	
	g2.setColor(Color.RED);
	g2.fill(player_object);
	g2.setColor(Color.GREEN);
	g2.fill(obj2);
	//g2.setColor(Color.BLUE);
	//g2.fill(obj3);


}


public void run() {
	
	while (true) {	
		
		collision();
		
		repaint();
		
		//JOptionPane.showMessageDialog(this, ""+score);
		try {
			Thread.sleep(time/60);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}


public void update() {
	
	at.setToTranslation(deltaX, deltaY);
	player_object= at.createTransformedShape(player_object);
	
	int posx = (int)player_object.getBounds().getX();
	int posy = (int)player_object.getBounds().getY();
	
	playerobj(posx, posy, ball_size);
	
	deltaX = 0;
	deltaY = 0;
	
	repaint();
	
}


public void collision() {
	
	int posx = (int)player_object.getBounds().getX();
	int posy = (int)player_object.getBounds().getY();
	                                                                                                                                                                                                                                                                                         
	
	if (player_object.intersects(30, 20, getWidth()-20, 10)) { //X
		deltaY = 6;
		type= 1;
		update();
		//JOptionPane.showMessageDialog(this, "Collison collision ");
		
	} if (player_object.intersects(30,20,10, getHeight()-40)) { //Y - LEFT
		deltaX = 6;
		update();
		type=4;
		//JOptionPane.showMessageDialog(this, "Collison side");
		
	} if (player_object.intersects(getWidth()-20,20, 10, getHeight()-40)) { // 1-Y - RIGHT
		deltaX = -6;
		update();
		//JOptionPane.showMessageDialog(this, "Collison otyher");
		type=3;
		
	}if (player_object.intersects(30, getHeight()-20, getWidth()-50, 10)) { //1-X
		deltaY = -6;
		update();
		//JOptionPane.showMessageDialog(this, "Collison down");
		type=2;
		
	}
	if (player_object.intersects(obj2.getBounds().getMinX(),obj2.getBounds().getMinY(),20,20)) {
		
		
		obj2 = randomObj(getWidth()-30,getHeight()-50);
		
		//ball_size -=1;
		time -=20;
		score +=30;
		

		label.setText(""+score);
		
		
	}
	
	player_object = playerobj(posx,posy,ball_size);
	
	repaint();
	
}

public Shape randomObj(int x,int y){
	int randposX = (int)(Math.random() * ((x - 50) + 30)) + 50;
	int randposY = (int)(Math.random() * ((y - 50) + 30)) + 50;
	//JOptionPane.showMessageDialog(this, ""+ randpos);
	
	obj2 = new Ellipse2D.Float(randposX, randposY, 20,20 );
	
	return obj2;
	
}



public Shape playerobj(int positionx,int positiony,int size){
	//JOptionPane.showMessageDialog(this, ""+type);
	Shape object = null;
	switch (type) {
	case 1:
		object = new Ellipse2D.Float(positionx,positiony , size,size);
		break;
	case 2:
		object = new Rectangle2D.Float(positionx,positiony,size,size);
		break;
	case 3:
		object = new Triangle(positionx, positiony, size); //new Triangle(positionx,20);
		
		break;
	case 4:
		object = new Ellipse2D.Float(positionx,positiony ,size,size);
		
		break;
	default:
		break;
	}	
	return object;
	
}



@Override
public void keyPressed(KeyEvent e) {
	//JOptionPane.showMessageDialog(this, "intro");
	switch (e.getKeyCode()) {
	case KeyEvent.VK_RIGHT:
		deltaX = 5;
		break;
	case KeyEvent.VK_LEFT:
		deltaX = -5;
		break;
	case KeyEvent.VK_UP:
		deltaY = -5;
		break;
	case KeyEvent.VK_DOWN:
		deltaY = 5;
		break;
	case KeyEvent.VK_Q:
		//JOptionPane.showMessageDialog(this, "ol");
		type =2;
		
		break;
	}
	update();
	
	
}

@Override
public void keyTyped(KeyEvent e) {

	
}




@Override
public void keyReleased(KeyEvent e) {
	// TODO Auto-generated method stub
	
}

}
