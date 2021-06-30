package modals;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class Help extends JDialog
{
	Image img;
	Font Fuente;
	JScrollPane scrollPane = new JScrollPane();
	JLabel lblContent;
    public Help(Frame frame, boolean b) {
        super(frame, b);
        setTitle("......:::::::AYUDA::::::......");
        setSize(900,636);
       add(new JLabel("Hello"));
       this.setLocationRelativeTo(null);
        setResizable(false);
    }
    
    public void paint(Graphics g){
    	//super.paintComponent(g);
       super.paint(g);
        Dimension tamanio = getSize();
        URL ruta = getClass().getResource("/rsc/material/ayuda.png");
        img=new ImageIcon(ruta).getImage();
        g.drawImage(img, 0, 0,tamanio.width, tamanio.height, null);
      
       
    }

}
