/*
**±≥æ∞Õº∆¨º”‘ÿ
 */

import javax.swing.*;
import java.awt.*;

public class ImagePancel extends JPanel {
	private ImageIcon icon;
	private java.awt.Image img;
	
	public ImagePancel(){
		icon=new ImageIcon(SystemMainFrame.class.getResource("/1.png" ));
		img=icon.getImage();  
	}
	public void paintComponent(Graphics g)
	{   
		super.paintComponent(g);  
		g.drawImage(img,0,0,null );
	}   
}

