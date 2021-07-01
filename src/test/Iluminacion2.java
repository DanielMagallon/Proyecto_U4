package test;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.media.j3d.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.vecmath.*;
import  com.sun.j3d.utils.geometry.ColorCube;
import com.sun.j3d.utils.geometry.GeometryInfo;
import com.sun.j3d.utils.geometry.NormalGenerator;
import com.sun.j3d.utils.geometry.Sphere;
import com.sun.j3d.utils.universe.*;

public class Iluminacion2 extends JFrame implements ItemListener{
	AmbientLight ambiental;
	DirectionalLight luzd;
	PointLight pluz;
	JCheckBox cb1,cb2,cb3;
	JButton elegircolor;
	JLabel vercolor;
	Color colordondolabel;
	Color3f coloriluminacion=new Color3f(1.0f,1.0f,.0f);
	GeometryInfo Gi;
	BranchGroup escena;
	public Iluminacion2() {
		setTitle("Programa en Java3D");
		setSize(800,600);
		JPanel arr=new JPanel();
		elegircolor = new JButton("Selecciona Color");
		vercolor=new JLabel("        ");
		vercolor.setOpaque(true);
		vercolor.setSize(300, 200);
		colordondolabel= Color.gray;
		vercolor.setBackground(colordondolabel);
		arr.add(elegircolor);
		arr.add(vercolor);
		cb1= new JCheckBox("Luz Ambiental");
		arr.add(cb1);
		cb2= new JCheckBox("Luz Direccional");
		arr.add(cb2);
		cb3= new JCheckBox("Punto de luz");
		arr.add(cb3);
		
		
		add(arr,BorderLayout.NORTH);
		cb1.addItemListener(this);
		cb2.addItemListener(this);
		cb3.addItemListener(this);
		
		
		elegircolor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				elegir();
				vercolor.setBackground(colordondolabel);
                ambiental.setColor(coloriluminacion);
                luzd.setColor(coloriluminacion);
                pluz.setColor(coloriluminacion);
			}
		});
		
		//obtenemos la configuracion grafica propia del sistema
		GraphicsConfiguration config=SimpleUniverse.getPreferredConfiguration();
		//construimos el canvas 3d
		Canvas3D canvas=new Canvas3D(config);
		add(canvas,BorderLayout.CENTER);
		//creamos la rama de Representacion
		escena=crearescena();
		//se compila la escena para optimizar la ejecucion
		escena.compile();
		//Usamos simple Universe para simplificar la rama de representacion
		SimpleUniverse su=new SimpleUniverse(canvas);
		//retrazamos el punto de vision para poder ver los objetos
		su.getViewingPlatform().setNominalViewingTransform();
		//anadimos la rama de contenido a la raiz del arbol
		su.addBranchGraph(escena);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	public void elegir() {
		Color initialcolor=Color.RED; 
		colordondolabel =JColorChooser.showDialog(this, "Escoge un color",initialcolor);
		float red= colordondolabel.getRed()/255f;
		float blue= colordondolabel.getBlue()/255f;
		float green= colordondolabel.getGreen()/255f;
		coloriluminacion= new Color3f(red,green,blue);
		
		
	}
	public void ponerEsfera(Vector3f ubic, float tam,Color3f color,BranchGroup raiz) {
		//Aparencia con poligino relleno
				Appearance ap= new Appearance();
				ap.setColoringAttributes(new ColoringAttributes(color, ColoringAttributes.SHADE_GOURAUD));
				ap.setPolygonAttributes(new PolygonAttributes(PolygonAttributes.POLYGON_LINE, PolygonAttributes.CULL_NONE, 0));
				Material mat=new Material();
				ap.setMaterial(mat);
				Sphere Sp = new Sphere(tam,Sphere.GENERATE_NORMALS,50,ap);
				Transform3D pos= new Transform3D();
				pos.set(ubic);
				TransformGroup tgu=new TransformGroup(pos);
				tgu.addChild(Sp);
				raiz.addChild(tgu);
	}
	public void ponerLuzAmbiental(BranchGroup raiz, Color3f color) {
		ambiental = new AmbientLight(color);
		ambiental.setCapability(AmbientLight.ALLOW_STATE_WRITE);
		ambiental.setCapability(AmbientLight.ALLOW_COLOR_WRITE);
		BoundingSphere limite =new BoundingSphere(new Point3d(0,0,0),1);
		ambiental.setInfluencingBounds(limite);
		ambiental.setEnable(false);
		
		raiz.addChild(ambiental);
		
	};
	public void ponerLuzDireccional(Color3f color,Vector3f ubic,BranchGroup raiz) {
		BoundingSphere limite =new BoundingSphere(new Point3d(0,0,0),1);
		luzd= new DirectionalLight(false, color,ubic);
		luzd.setInfluencingBounds(limite);
		luzd.setCapability(DirectionalLight.ALLOW_STATE_WRITE);
        luzd.setCapability(DirectionalLight.ALLOW_COLOR_WRITE);
		raiz.addChild(luzd);
	}
	public void ponerPuntoLuz(Color3f color, Point3f pos,Point3f at, BranchGroup raiz ){
		pluz = new PointLight(color,pos,at);
		pluz.setCapability(PointLight.ALLOW_STATE_WRITE);
        pluz.setCapability(PointLight.ALLOW_COLOR_WRITE);
		BoundingSphere limite =new BoundingSphere(new Point3d(0,0,0),1);
		pluz.setInfluencingBounds(limite);
		pluz.setEnable(false);
		raiz.addChild(pluz);
		
	}

    BranchGroup objraiz=new BranchGroup();

    public void DibujarletraE(GeometryInfo Gi,BranchGroup raiz,Vector3f ubic,Color3f color){
		Point3d veticese[]= {
				new Point3d(-.20,.30,.06), //A 0
				new Point3d(.20,.30,.06),//B 1
				new Point3d(.20,.18,.06),//c 2
				new Point3d(-.10,.18,.06),//D 3
				new Point3d(-.10,.06,.06),//E 4
				new Point3d(.15,.06,.06),//F 5
				new Point3d(-.10,-.06,.06),//G 6
				new Point3d(.15,-.06,.06),//H 7
				new Point3d(-.20,-.30,.06),//I 8
				new Point3d(-.10,-.18,.06),//J 9
				new Point3d(.20,-.18,.06),//K 10
				new Point3d(.20,-.30,.06),//L 11
				new Point3d(-.20,.30,-.06),//A' 12
				new Point3d(.20,.30,-.06),//B' 13
				new Point3d(.20,.18,-.06),//C' 14
				new Point3d(-.10,.18,-.06),//D' 15
				new Point3d(-.10,.06,-.06),//E' 16
				new Point3d(.15,.06,-.06),//F' 17
				new Point3d(-.10,-.06,-.06),//G' 18
				new Point3d(.15,-.06,-.06),//H' 19
				new Point3d(-.20,-.30,-.06),//I' 20
				new Point3d(-.10,-.18,-.06),//J' 21
				new Point3d(.20,-.18,-.06),//K' 22
				new Point3d(.20,-.30,-.06)//L' 23

				};
				int secuenciae[]= {0,1,2,3,4,5,7,6,9,10,11,8,   12,13,14,15,16,17,19,18,21,22,23,20, 0,1,13,12,  0,8,20,12,  8,11,23,20, 10,11,23,22,
									9,10,22,21, 6,9,21,18,  6,7,19,18,  5,7,19,17,  4,5,17,16,  3,4,17,16,  3,2,14,15,  1,2,14,13};
				Gi.setCoordinates(veticese);
				Gi.setCoordinateIndices(secuenciae);
				int tirase[]= {12,12,4,4,4,4,4,4,4,4,4,4,4,4};
				Gi.setStripCounts( tirase);
				Color3f colores[]= {
				new Color3f(1,0,0),//0
				new Color3f(0,1,0),//1
				new Color3f(0,0,1),//2
				new Color3f(1,1,0),//3
									};
				int seccolores[]= {0,0,0,0,0,0,0,0,0,0,0,0, 1,1,1,1,1,1,1,1,1,1,1,1, 2,2,2,2,  3,3,3,3, 2,2,2,2,  3,3,3,3, 2,2,2,2,  3,3,3,3, 2,2,2,2,  3,3,3,3, 2,2,2,2,  3,3,3,3, 2,2,2,2,  3,3,3,3};
				Gi.setColors(colores);
				Gi.setColorIndices(seccolores);
				
				
				//Aparencia con poligino relleno
				Appearance ap= new Appearance();
				ap.setColoringAttributes(new ColoringAttributes(color, ColoringAttributes.SHADE_GOURAUD));
				ap.setPolygonAttributes(new PolygonAttributes(PolygonAttributes.POLYGON_FILL, PolygonAttributes.CULL_NONE, 0));
				Material mat=new Material();
				ap.setMaterial(mat);
				
				NormalGenerator NG = new NormalGenerator();
				NG.generateNormals(Gi);
				Shape3D Sp= new Shape3D(Gi.getGeometryArray(),ap);
				
				//Sphere Sp = new Sphere(tam,Sphere.GENERATE_NORMALS,50,ap);
				Transform3D pos= new Transform3D();
				pos.set(ubic);
				TransformGroup tgu=new TransformGroup(pos);
				tgu.addChild(Sp);
				raiz.addChild(tgu);

	}
	public BranchGroup crearescena(){
		objraiz.setCapability(BranchGroup.ALLOW_CHILDREN_WRITE);
		Gi =new GeometryInfo(GeometryInfo.POLYGON_ARRAY);
		
		ponerLuzAmbiental(objraiz,coloriluminacion);
		ponerLuzDireccional(coloriluminacion, new Vector3f(-.5f,0,0), objraiz);
		ponerPuntoLuz(coloriluminacion, new Point3f(0,0,0), new Point3f(1,0,0),objraiz );
		//ponerEsfera(new Vector3f(-.4f,.35f,0f), .3f, new Color3f(1,1,0), objraiz);
		//ponerEsfera(new Vector3f(-.4f,-.35f,0f), .3f, new Color3f(1,0,0), objraiz);
		//ponerEsfera(new Vector3f(.4f,.35f,0f), .3f, new Color3f(1,0,0), objraiz);
		//ponerEsfera(new Vector3f(.4f,-.35f,0f), .3f, new Color3f(1,0,0), objraiz);
		DibujarletraE(Gi,objraiz,new Vector3f(-.1f,.10f,0f), new Color3f(1,1,0));
		
		return objraiz;
	}
	public static void main(String args[]){
		EventQueue.invokeLater(Iluminacion2::new);
	}
	@Override
	public void itemStateChanged(ItemEvent e) {
	if(cb1.isSelected()) {
		ambiental.setEnable(true);
	}else {
		ambiental.setEnable(false);
	}
	
	if(cb2.isSelected()) {
		luzd.setEnable(true);
	}else {
		luzd.setEnable(false);
	}
	
	if(cb3.isSelected()) {
		pluz.setEnable(true);
	}else {
		pluz.setEnable(false);
	}
		
	}
}
