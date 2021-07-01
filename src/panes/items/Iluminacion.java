package panes.items;

import main.Run;
import panes.PanelItem;
import static_props.AppProps;

import javax.media.j3d.*;
import javax.swing.*;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Point3f;
import javax.vecmath.Vector3f;
import java.awt.*;

public class Iluminacion extends PanelItem{

    private JLabel labelAmbiente,lblDireccional,lblPunto,auxLabel;
    private JPanel panelCont;
    private static JCheckBox iluminado;

    public static PanelAmbiente panelAmbiente;
    public static PanelDireccional panelDireccional;
    public static PanelPuntoLuz panelPuntoLuz;

    public Iluminacion() {
        super(new BorderLayout());

        {
            panelAmbiente = new PanelAmbiente();
            panelDireccional = new PanelDireccional();
            panelPuntoLuz = new PanelPuntoLuz();
            panelCont = new JPanel(new BorderLayout());
            panelCont.setOpaque(false);
            panelCont.add(panelAmbiente);
            add(panelCont);
        }

        {
            iluminado = new JCheckBox("Iluminacion On");
            iluminado.setOpaque(false);
            iluminado.setForeground(AppProps.FG_NORMAL_TEXT);
            iluminado.addChangeListener(c->{
                    Run.canvas3D.shape3D.updateShapeColor();
            });

            labelAmbiente = AppProps.createLabelFor("Ambiental", () -> {

                auxLabel.setForeground(AppProps.FG_NORMAL_TEXT);
                labelAmbiente.setForeground(AppProps.BG_CONTORNO);
                auxLabel=labelAmbiente;
                panelCont.removeAll();
                panelCont.add(panelAmbiente);
                panelCont.validate();
                panelCont.repaint();
            });
            labelAmbiente.setForeground(AppProps.BG_CONTORNO);
            auxLabel=labelAmbiente;

            lblDireccional = AppProps.createLabelFor("Direccional", () -> {

                auxLabel.setForeground(AppProps.FG_NORMAL_TEXT);
                lblDireccional.setForeground(AppProps.BG_CONTORNO);
                auxLabel=lblDireccional;
                panelCont.removeAll();
                panelCont.add(panelDireccional);
                panelCont.validate();
                panelCont.repaint();
            });

            lblPunto = AppProps.createLabelFor("Punto luz", () -> {
                auxLabel.setForeground(AppProps.FG_NORMAL_TEXT);
                lblPunto.setForeground(AppProps.BG_CONTORNO);
                auxLabel=lblPunto;
                panelCont.removeAll();
                panelCont.add(panelPuntoLuz);
                panelCont.validate();
                panelCont.repaint();
            });

            labelAmbiente.setBorder(BorderFactory.createLineBorder(Color.white));
            JPanel panelLabels = new JPanel() {{
                setLayout(new GridLayout(4, 1));
                setOpaque(false);
                setBorder(BorderFactory.createEmptyBorder(3,3,3,3));
                add(labelAmbiente);
                add(lblDireccional);
                add(lblPunto);
                add(iluminado);
            }};

            add(panelLabels,"West");
        }

    }

    public static boolean isLigth(){
        return iluminado.isSelected();
    }

     public static abstract class PanelIlum extends JPanel{
        protected JCheckBox cbox;
        protected BoundingSphere limite;
        protected double xpoint,ypoint,zpoint,radius;
        protected Color3f color3f;
        protected Light light;

         public PanelIlum() {
            setOpaque(false);
            cbox = new JCheckBox("Habilitar");
            cbox.setOpaque(false);
            cbox.setForeground(AppProps.FG_NORMAL_TEXT);
            add(cbox);
            cbox.addChangeListener(c->
             {
                 light.setEnable(cbox.isSelected());

             }

            );
            limite = new BoundingSphere();
         }

         protected void initLight(){
             light.setCapability(Light.ALLOW_STATE_WRITE);
             light.setCapability(Light.ALLOW_COLOR_WRITE);
             light.setCapability(Light.ALLOW_BOUNDS_WRITE);
             light.setCapability(DirectionalLight.ALLOW_DIRECTION_WRITE);
             light.setCapability(PointLight.ALLOW_ATTENUATION_WRITE);
             light.setCapability(PointLight.ALLOW_POSITION_WRITE);
         }
         public abstract Light castLigth();

     }

      public static class PanelAmbiente extends PanelIlum{

        public PanelAmbiente() {
            super();
            radius=1;
            color3f = new Color3f(0.2f,0.2f,0.2f);

            light = new AmbientLight();
            initLight();
            light.setColor(color3f);

            limite.setCenter(new Point3d(xpoint,ypoint,zpoint));
            limite.setRadius(radius);

            light.setInfluencingBounds(limite);
        }

          public  AmbientLight castLigth(){
            return (AmbientLight) light;
          }
    }

    public static class PanelDireccional extends PanelIlum{

        Vector3f direction;
        float xdir,ydir,zdir;

        public PanelDireccional() {
            super();
            radius=1;
            color3f = new Color3f(0.5f,0.5f,0);

            light = new DirectionalLight();
            initLight();
            light.setColor(color3f);

            xdir = -.5f;
            direction = new Vector3f(xdir,ydir,zdir);
            castLigth().setDirection(direction);

            limite.setCenter(new Point3d(xpoint,ypoint,zpoint));
            limite.setRadius(radius);

            light.setInfluencingBounds(limite);
        }

        public DirectionalLight castLigth(){
            return (DirectionalLight) light;
        }
    }

    public static class PanelPuntoLuz extends PanelIlum{

        Point3f attenuattionPoint,positionPoint;

        public PanelPuntoLuz() {
            super();
            radius=1;
            xpoint=0.4;
            ypoint=0.3;

            color3f = new Color3f(0,0,0);

            light = new PointLight();
            light.setEnable(false);
            initLight();

            positionPoint = new Point3f(0,0,0);
            attenuattionPoint = new Point3f(1,0,0);

            light.setColor(color3f);
            castLigth().setPosition(positionPoint);
            castLigth().setAttenuation(attenuattionPoint);

            limite.setCenter(new Point3d(xpoint,ypoint,zpoint));
            limite.setRadius(radius);
            light.setInfluencingBounds(limite);
        }

        public PointLight castLigth(){
            return (PointLight) light;
        }
    }
}
