package ip2019;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.color.ColorSpace;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ColorConvertOp;
import java.awt.image.WritableRaster;

import javax.annotation.processing.ProcessingEnvironment;
import javax.imageio.ImageIO;
import javax.naming.spi.DirStateFactory.Result;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import moveshape.Heart;



public class IP2019 extends JFrame  implements ActionListener{

	ImgPanel imgSrc,imgDst;
	
	JFileChooser fc = new JFileChooser("c:/Users/Dário Ribeiro/Pictures");
	
	public static void main(String[] args)  {

		JFrame frame = new IP2019();
		frame.setTitle("Dataplot");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		
		frame.pack();
		frame.setVisible(true);

	}
	public IP2019() {
		JMenuBar mb = new JMenuBar();
		setJMenuBar(mb);
		
		JMenu menu = new JMenu("File");
		
		JMenuItem mi = new JMenuItem("Open image");
		mi.addActionListener(this);
		menu.add(mi);
		mi = new JMenuItem("Save image");
		menu.add(mi);
		mi = new JMenuItem("Copy image");
		menu.add(mi);
		mi = new JMenuItem("Exit");
		menu.add(mi);
		mb.add(menu);
		
		menu = new JMenu("Processing");
		mi = new JMenuItem("RGB To Gray I");
		mi.addActionListener(this);
		menu.add(mi);
		mi = new JMenuItem("RGB To Gray II");
		mi.addActionListener(this);
		menu.add(mi);
		mb.add(menu);
		
		
		imgSrc = new ImgPanel();
		imgDst = new ImgPanel();
		Container cp = this.getContentPane();
		cp.setLayout(new FlowLayout());
		cp.add(imgSrc);
		cp.add(imgDst);
		
		
		
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String menuitem = e.getActionCommand();
		if (menuitem.equals("Open image")) {
			//JOptionPane.showMessageDialog(this, menuitem);
			int result = fc.showOpenDialog(this);
			if (result == JFileChooser.APPROVE_OPTION) {
				try {
					imgSrc.setImage(ImageIO.read(fc.getSelectedFile()));
					pack();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				
				
			//
				
			}
			
			
		}else if(menuitem.equals("Save image")){
			
		}else if(menuitem.equals("Copy image")){
			
		}else if (menuitem.equals("Exit")) {
			
		}else {
			processing(menuitem);		
			}
		
	}
	private void processing(String menuitem) {
		BufferedImageOp op = null;
		
		if (menuitem.equals("RGB To Gray I")) {
			op = new ColorConvertOp(ColorSpace.getInstance(ColorSpace.CS_GRAY), null);
		}else if (menuitem.equals("RGB To Gray II")) {
		imgDst.setImage(MyRGBToGray(imgSrc.getImage()));
		pack();
		
	}
		if (op != null) {
			imgDst.setImage(op.filter(imgSrc.getImage(), null));
			pack();
			
		}
		
	}

	private BufferedImage MyRGBToGray(BufferedImage imgIN) {
		//Criamos uma imagem de saida igual á imagem de entrada , vazia mas com as mesmas dimensois, pois é necessario inicializar a variavel
		BufferedImage imgOUT = new BufferedImage(imgIN.getWidth(),imgIN.getHeight(),imgIN.getType());
		
		WritableRaster rasterImgIN = imgIN.getRaster();
		WritableRaster rasterImgOUT = imgOUT.getRaster();
		
		int[] rgba = new int[4];
		
		for (int x = 0; x < imgIN.getWidth(); x++) {
			for (int y = 0; y < imgIN.getHeight(); y++) {
				rasterImgIN.getPixel(x, y, rgba);
				
				int gray =(int)((rgba[0] + rgba[1] + rgba[2])/3f);
				rgba[0] = rgba[1] = rgba[2] = gray;
				rasterImgOUT.setPixel(x, y, rgba);
			}
			
		}
			
			
		return imgOUT;
	}
	
	

}
class ImgPanel extends JPanel {
	
BufferedImage image = null;
	
	public ImgPanel () {
		
		setPreferredSize(new Dimension(256,256));
		this.setFocusable(true);

	}
	
public void paintComponent(Graphics g) {
	
	super.paintComponent(g);
	
	Graphics2D g2 = (Graphics2D) g;
	
	if (image != null) {
		g2.drawImage(image,0,0,this);
	}else
		g2.drawRect(0, 0, getWidth()-1, getHeight()-1);
		
	
	
}

public void setImage(BufferedImage bi) {
image = bi;
setPreferredSize(new Dimension(bi.getWidth(),bi.getHeight()));
repaint();
}

public BufferedImage getImage() {
return image;
}




}
