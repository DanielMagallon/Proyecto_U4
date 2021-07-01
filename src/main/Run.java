package main;

import LabelFace.LabelFace;
import bin.shape3d.abstracts.ShapeJ3D;
import frame.DefaultFrame;
import modals.*;
import modals.NotifyImage;
import panes.CanvasJ3D;
import panes.FaceProperties;
import panes.PanelItem;
import panes.items.*;
import panes.menu.PanelMenu;
import static_props.AppProps;
import static_props.ImageLoader;

import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

public class Run
{
    public static DefaultFrame frame;
    private static JPanel panelMenus, panelMenuItem;
    private static Help modalHelp;
    private static Autores modalAutor;
    public static CanvasJ3D canvas3D;
//    public static Figura3D figura3D;

    public static NotifyImage notifyImage;

    public static boolean exportImage, showViews;
    public static BufferedImage bufferedImage;
    public static Rotacion panelRotacion = new Rotacion();
    public static Escalamiento escalamiento = new Escalamiento();
    public static Traslacion traslacion = new Traslacion();
    public static Iluminacion iluminacion;
    public static Colores colores;
    public static Caras caras;
    public static FaceProperties faceProperties = new FaceProperties();

    public static JLabel seleccionpane=new JLabel("Ver vistas");
    public static JLabel seleccionayuda=new JLabel("  AYUDA  ");
    public static JLabel seleccionautor=new JLabel("  AUTORES  ");

    static void initPanelItems(){
        JPanel panel = new JPanel(new BorderLayout()){{
            setOpaque(false);
        }};

        Configuracion configuracion = new Configuracion();
        Border border = BorderFactory.createLineBorder(Color.black);
        panelMenuItem = new JPanel(new BorderLayout());
        panelMenuItem.add(panelRotacion);
        seleccionayuda.setCursor(AppProps.handCursor);
        seleccionayuda.setForeground(Color.black);
        seleccionayuda.setBorder(border);
        seleccionayuda.addMouseListener( new MouseAdapter() {

            public void mouseClicked(MouseEvent e) {
            	 modalHelp.setVisible(true);
            }
        });
        
        seleccionautor.setCursor(AppProps.handCursor);
        seleccionautor.setForeground(Color.black);
        seleccionautor.setBorder(border);
        seleccionautor.addMouseListener( new MouseAdapter() {

            public void mouseClicked(MouseEvent e) {
            	 modalAutor.setVisible(true);
            }
        });
        
        seleccionpane.setCursor(AppProps.handCursor);
        seleccionpane.setForeground(Color.black);
        seleccionpane.addMouseListener( new MouseAdapter() {

            public void mouseClicked(MouseEvent e) {

                if(!showViews) {
                    seleccionpane.setText("Ver Dibujos");
                    seleccionpane.setForeground(AppProps.BG_CONTORNO);
                    frame.getContentPane().remove(canvas3D);
                }else {
                    seleccionpane.setText("Ver vistas");
                    seleccionpane.setForeground(Color.black);
                    frame.getContentPane().add(canvas3D);

                }
                frame.getContentPane().validate();
                frame.getContentPane().repaint();
                showViews=!showViews;
            }
        });


        iluminacion = new Iluminacion();


        canvas3D = new CanvasJ3D();
        canvas3D.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if(ShapeJ3D.fillPoint){
                    canvas3D.setCursor(ImageLoader.fillCorrectCursor);
                }else canvas3D.setCursor(null);
            }
        });
        colores = new Colores();
        caras = new Caras();
        panelMenus = new JPanel(new FlowLayout(FlowLayout.LEFT,0,0))
        {{
            setOpaque(false);
            add(seleccionpane);
            add(new PanelMenu(panelRotacion,"Rotaciones",true));
            add(new PanelMenu(escalamiento,"Escalamiento"));
            add(new PanelMenu(traslacion,"Traslacion"));
            add(new PanelMenu(iluminacion,"Iluminacion"));
            add(new PanelMenu(caras,"Caras"));
            add(new PanelMenu(configuracion,"Configuracion"));
            add(seleccionayuda);
            add(seleccionautor);

        }};



        panel.add(panelMenus,"North");
        panel.add(panelMenuItem);
        panel.setPreferredSize(new Dimension(0,100));
        panel.setBorder(BorderFactory.createLineBorder(Color.black));


        frame.getContentPane().setBackground(AppProps.BG_GLOBAL);
        frame.getContentPane().add(panel,"North");

        frame.getContentPane().add(canvas3D);
        frame.getContentPane().add(colores,"South");

//        help = new Help(frame,true);

    }

//    public static Help help;

    public static void updatePanelItem(PanelItem panelItem){
        panelMenuItem.removeAll();
        panelMenuItem.add(panelItem);
        panelMenuItem.validate();
        panelItem.repaint();
    }


    public static void init(){
        frame = new DefaultFrame("Proyecto U4 -- Java 3D").minSize(730,630);
        initPanelItems();
//        figura3D = new Figura3D();
        notifyImage = new NotifyImage(frame);
        modalHelp = new Help(frame,true);
        modalAutor = new Autores(frame,true);
        frame.setVisible(true);
        canvas3D.requestFocus();
        
   
    }

    private static LabelFace auxFace;

    public static void updateColorsFace(){
        if(auxFace!=null)
            faceProperties.showFaceProps(auxFace);
    }

    public static void faceHandler(LabelFace face)
    {
        auxFace = face;

        if(faceProperties.closed) {
            frame.getContentPane().add(faceProperties, "East");
        }
        faceProperties.showFaceProps(face);
        frame.getContentPane().validate();
        frame.getContentPane().repaint();
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(Run::init);
    }
}
