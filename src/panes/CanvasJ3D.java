package panes;

import bin.handlers.SelecctionListener;
import bin.shape3d.abstracts.ShapeJ3D;
import com.sun.j3d.utils.universe.SimpleUniverse;
import static_props.AppProps;

import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

import static main.Run.*;

public class CanvasJ3D extends JPanel
{
    public SelecctionListener selecctionListener;
    public ShapeJ3D shape3D;
    private boolean arrastrar=false,firstTime=true;

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
        selecctionListener = new SelecctionListener(this, () -> exportImage = false, () -> {

            if (!showViews) {
                if (firstTime) {
                    JOptionPane.showMessageDialog(this, "Para seleccionar el area debe " +
                            "mantener presionado el boton del mouse/touchpad\ny arrastrar hasta donde" +
                            " se desee obtener la imagen. Las imagenes son guardadas\nautomaticamente en la carpeta" +
                            " myimages.La cual esta en la raiz del proyecto.");
                    firstTime = false;
                }
                exportImage = true;
                selecctionListener.exporSelection = true;
                canvas.setCursor(AppProps.areaSelect);
                bufferedImage = new BufferedImage(canvas.getWidth(),
                        canvas.getHeight(), BufferedImage.TYPE_INT_RGB);
            }
        });

        AppProps.setActionPanel("RST", this, KeyEvent.VK_R, InputEvent.ALT_MASK,
                () -> {
//                    abstractShape3D.setXAngulo(0);
//                    abstractShape3D.setYAngulo(0);
//                    abstractShape3D.setZAngulo(0);
//                    abstractShape3D.reset();

                });

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

        addMouseListener(selecctionListener);
        addMouseMotionListener(selecctionListener);

        addMouseWheelListener(e -> {
            int sentRot = e.getWheelRotation();
            if (sentRot > 0) {
                shape3D.scale(1.2);
            } else {
                if (sentRot < 0) {
                    shape3D.scale(.95);
                }
            }
            repaint();
        });

        create3D();

        canvas.addMouseListener(new MouseAdapter() {
                             public void mousePressed(MouseEvent e) {
                                 if (!exportImage) {
                                     int cx = e.getX(), cy = e.getY();
                                     arrastrar = true;
                                 }
                             }

                             @Override
                             public void mouseEntered(MouseEvent mouseEvent) {
                                 canvas.requestFocus();
                             }
                         }
        );

        canvas.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                if (arrastrar) {
                    //sipuedes arrastrar la figura
                    int cx = e.getX(), cy = e.getY();
                    shape3D.mover(cx,cy);
                    repaint();
                }
            }
        });
    }

    private Canvas3D canvas;
    private void create3D()
    {
        GraphicsConfiguration config= SimpleUniverse.getPreferredConfiguration();
        canvas=new Canvas3D(config);
        add(canvas,BorderLayout.CENTER);
        BranchGroup escena=shape3D.getBranchGroup();
        escena.compile();
        SimpleUniverse su=new SimpleUniverse(canvas);
        su.getViewingPlatform().setNominalViewingTransform();
        su.addBranchGraph(escena);
    }
}
