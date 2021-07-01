package panes.items;

import main.Run;
import panes.PanelItem;
import static_props.AppProps;
//import sun.tools.tree.NewInstanceExpression;

import javax.media.j3d.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Point3f;
import javax.vecmath.Vector3f;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class Iluminacion extends PanelItem{

    private JLabel labelAmbiente,lblDireccional,lblPunto,auxLabel,colorseleccionado;
    private JPanel panelCont;
    private static JCheckBox iluminado;
    JButton elegir;
	static Color3f c= new Color3f(1.0f,1.0f,0.2f);
	Color colordondolabel;
	 private JSlider jsliderpuntox,jsliderpuntoy;
    public static PanelAmbiente panelAmbiente;
    public static PanelDireccional panelDireccional;
    public static PanelPuntoLuz panelPuntoLuz;
    float x=0;
	float y=0;
	float z=0;

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
            colorseleccionado=new JLabel("        ");
            colorseleccionado.setOpaque(true);
            colorseleccionado.setSize(300, 200);
    		colordondolabel= Color.gray;
    		colorseleccionado.setBackground(colordondolabel);
    		JPanel ps1=new JPanel(new GridLayout(1,1));
    		TitledBorder tb1=new TitledBorder("Punto de luz eje x");
    		tb1.setTitleColor(AppProps.BG_CONTORNO);
    		tb1.setTitleJustification(TitledBorder.CENTER);
    		ps1.setBorder(tb1);
    		
    		jsliderpuntox=new JSlider(JSlider.HORIZONTAL,0,200,100);
    		jsliderpuntox.setOpaque(false);
    		jsliderpuntox.setMinorTickSpacing(10);
    		jsliderpuntox.setMajorTickSpacing(30);
    		jsliderpuntox.setPaintLabels(true);
    		jsliderpuntox.setPaintTicks(true);
    		ps1.add(jsliderpuntox);
    		JPanel ps2=new JPanel(new GridLayout(1,1));
    		TitledBorder tb2=new TitledBorder("Punto de luz eje y");
            tb2.setTitleColor(AppProps.BG_CONTORNO);
    		tb2.setTitleJustification(TitledBorder.CENTER);
    		ps2.setBorder(tb2);
    		jsliderpuntoy=new JSlider(JSlider.HORIZONTAL,0,200,100);
    		jsliderpuntoy.setMinorTickSpacing(10);
    		jsliderpuntoy.setMajorTickSpacing(30);
    		jsliderpuntoy.setPaintLabels(true);
    		jsliderpuntoy.setPaintTicks(true);
    		jsliderpuntoy.setOpaque(false);
    		ps2.add(jsliderpuntoy);
           
            elegir= new JButton("Color de iluminacion:");
  		  elegir.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent e) {
					c=elegir();
					colorseleccionado.setBackground(colordondolabel);
						panelAmbiente.light.setColor(c);
						panelDireccional.light.setColor(c);
						panelPuntoLuz.light.setColor(c);
					
					
				}
			});
  		jsliderpuntox.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				// TODO Auto-generated method stub
				//F.rotacionx(S1.getValue());
				if(jsliderpuntox.getValue()<100) {
					x=(float)-((-(float)jsliderpuntox.getValue()/100)+1);
					panelPuntoLuz.castLigth().setPosition(new Point3f(x,y,z));
				
				}else{
					x=(float)(((float)jsliderpuntox.getValue()/100)-1);
					panelPuntoLuz.castLigth().setPosition(new Point3f(x,y,z));
				}
			}
		});
  		jsliderpuntoy.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
		
				if(jsliderpuntoy.getValue()<100) {
					y=(float)-((-(float)jsliderpuntoy.getValue()/100)+1);
					panelPuntoLuz.castLigth().setPosition(new Point3f(x,y,z));
				
				}else{
					y=(float)(((float)jsliderpuntoy.getValue()/100)-1);
					panelPuntoLuz.castLigth().setPosition(new Point3f(x,y,z));
				}
			}
		});
            JPanel panelLabels = new JPanel() {{
                setLayout(new GridLayout(4, 1));
                setOpaque(false);
                setBorder(BorderFactory.createEmptyBorder(3,3,3,3));
                add(labelAmbiente);
                add(lblDireccional);
                add(lblPunto);
                add(iluminado);
            }};

            {
                elegir.setBackground(AppProps.BG_SELECTED);
                elegir.setForeground(Color.white);
                jsliderpuntox.setForeground(Color.white);
                jsliderpuntoy.setForeground(Color.white);
                ps1.setOpaque(false);
                ps2.setOpaque(false);
            }

            JPanel panelConfigs = new JPanel() {{
                setLayout(new FlowLayout());
                setOpaque(false);
                setBorder(BorderFactory.createEmptyBorder(3,3,3,3));
                add(elegir);
                add(colorseleccionado);
                add(ps1);
                add(ps2);
                
            }};

            add(panelLabels,"West");
            add(panelConfigs,"East");
            
        }

    }
    public Color3f elegir()  {
		Color initialcolor=Color.RED; 
		Color3f coloriluminacion;
		colordondolabel =JColorChooser.showDialog(this, "Escoge un color",initialcolor);
		float red= colordondolabel.getRed()/255f;
		float blue= colordondolabel.getBlue()/255f;
		float green= colordondolabel.getGreen()/255f;
		coloriluminacion= new Color3f(red,green,blue);
		
		return coloriluminacion;
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
            cbox = new JCheckBox();
            cbox.setOpaque(false);
            setBorder(BorderFactory.createEmptyBorder(20,0,0,0));
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
            color3f =c;

            cbox.setText("Habilitar luz ambiental");
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
            color3f = c;
            cbox.setText("Habilitar luz direccional");
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

            color3f = c;

            cbox.setText("Habilitar punto de luz");
            light = new PointLight();
            light.setEnable(false);
            initLight();

            positionPoint = new Point3f(-0.3f,-0.3f,0);
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
