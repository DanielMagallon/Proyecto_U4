package panes;

import bin.shape3d.abstracts.ShapeJ3D;
import com.sun.j3d.utils.universe.SimpleUniverse;
import panes.items.Iluminacion;
import static_props.AppProps;

import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.*;

import static main.Run.*;

public class CanvasJ3D extends JPanel
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    public ShapeJ3D shape3D;

    public CanvasJ3D() {
        setDoubleBuffered(true);
        setLayout(new BorderLayout());
        TitledBorder titledBorder = new TitledBorder("Lienzo de dibujo");
        titledBorder.setTitleColor(AppProps.FG_NORMAL_TEXT);
        titledBorder.setTitleJustification(TitledBorder.CENTER);
        titledBorder.setBorder(BorderFactory.createLineBorder(AppProps.BG_CONTORNO));
        setBorder(titledBorder);
        setBackground(AppProps.CANVAS_BG);
        shape3D = new ShapeJ3D();

        AppProps.setActionPanel("SX", this, KeyEvent.VK_X, InputEvent.CTRL_MASK,
                () -> escalamiento.updateScaleValue(AppProps.COORD_X,true));

        AppProps.setActionPanel("SY", this, KeyEvent.VK_Y, InputEvent.CTRL_MASK,
                () -> escalamiento.updateScaleValue(AppProps.COORD_Y,true));

        AppProps.setActionPanel("SZ", this, KeyEvent.VK_Z, InputEvent.CTRL_MASK,
                () -> escalamiento.updateScaleValue(AppProps.COORD_Z,true));


        AppProps.setActionPanel("SX-", this, KeyEvent.VK_X, InputEvent.CTRL_MASK|InputEvent.SHIFT_MASK,
                () -> escalamiento.updateScaleValue(AppProps.COORD_X,false));

        AppProps.setActionPanel("SY-", this, KeyEvent.VK_Y, InputEvent.CTRL_MASK|InputEvent.SHIFT_MASK,
                () -> escalamiento.updateScaleValue(AppProps.COORD_Y,false));

        AppProps.setActionPanel("SZ-", this, KeyEvent.VK_Z, InputEvent.CTRL_MASK|InputEvent.SHIFT_MASK,
                () -> escalamiento.updateScaleValue(AppProps.COORD_Z,false));


        AppProps.setActionPanel("SXYZ", this, KeyEvent.VK_PLUS, InputEvent.CTRL_MASK,
                () -> escalamiento.updateScaleValue(AppProps.COORD_Z|AppProps.COORD_Y|AppProps.COORD_X,true));

        AppProps.setActionPanel("SXYZ-", this, KeyEvent.VK_MINUS, InputEvent.CTRL_MASK,
                () -> escalamiento.updateScaleValue(AppProps.COORD_Z|AppProps.COORD_Y|AppProps.COORD_X,false));

        AppProps.setActionPanel("RX", this, KeyEvent.VK_X, InputEvent.ALT_MASK,
                () -> panelRotacion.actionKey("X"));

        AppProps.setActionPanel("RY", this, KeyEvent.VK_Y, InputEvent.ALT_MASK,
                () -> panelRotacion.actionKey("Y"));

        AppProps.setActionPanel("RZ", this, KeyEvent.VK_Z, InputEvent.ALT_MASK,
                () -> panelRotacion.actionKey("Z"));

        create3D();

    }

    private Canvas3D canvas;

    private void create3D()
    {
            GraphicsConfiguration config = SimpleUniverse.getPreferredConfiguration();
            canvas = new Canvas3D(config);
            canvas.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (ShapeJ3D.fillPoint) {
                        shape3D.backgroundGlobal.setColor(
                                shape3D.colorList.get(ShapeJ3D.keyColor)
                        );
                    }
                }
            });
            add(canvas, BorderLayout.CENTER);
            BranchGroup escena = shape3D.getBranchGroup();
            escena.addChild(Iluminacion.panelAmbiente.castLigth());
            escena.addChild(Iluminacion.panelDireccional.castLigth());
            escena.addChild(Iluminacion.panelPuntoLuz.castLigth());
            escena.compile();
            SimpleUniverse su = new SimpleUniverse(canvas);
            su.getViewingPlatform().setNominalViewingTransform();
            su.addBranchGraph(escena);

    }
}
