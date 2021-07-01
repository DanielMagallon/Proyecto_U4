package panes.items;

import bin.shape3d.abstracts.ShapeJ3D;
import panes.PanelItem;
import static_props.AppProps;
import static_props.ImageLoader;

import javax.swing.*;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static main.Run.*;

public class Configuracion extends PanelItem
{
    public Configuracion()
    {
        super(1,10);
        JLabel lblFill;

        lblFill = AppProps.createLabelFor("Relleno",()-> {
            ShapeJ3D.filled=!ShapeJ3D.filled;
            canvas3D.shape3D.updateShapeColor();
        });
        lblFill.setIcon(ImageLoader.paintDI);

    lblFill.addMouseListener(new MouseAdapter() {
		public void mouseExited(MouseEvent e) {
			if(e.getComponent()== lblFill) {
				 lblFill.setIcon(ImageLoader.paintDI);
			}
		}
		public void mouseEntered(MouseEvent e) {
			if(e.getComponent()== lblFill) {
				 lblFill.setIcon(ImageLoader.paintRI);
			}
		}
		
	});
        add(lblFill);
    }

}
