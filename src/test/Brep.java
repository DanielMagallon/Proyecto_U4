package test;

import java.awt.*;
import javax.media.j3d.*;


import javax.swing.*;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3f;

import  com.sun.j3d.utils.geometry.ColorCube;
import com.sun.j3d.utils.universe.*;
public class Brep extends JFrame{
	
	public Brep(){
		setTitle("Cubo en 3D");
		setSize(800,600);

		setLayout(new BorderLayout());
		//obtenemos la configuracion grafica propia del sistema
		GraphicsConfiguration config=SimpleUniverse.getPreferredConfiguration();
		//construimos el canvas 3d
		Canvas3D canvas=new Canvas3D(config);
		add(canvas,BorderLayout.CENTER);
		//creamos la rama de Representacion
		BranchGroup escena=crearescena();
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
	public BranchGroup crearescena(){
		BranchGroup objraiz=new BranchGroup();
		
		Point3d coTriang[]=new Point3d[]{
				//Cara 1
				new Point3d(0,-.4,0), //A
				new Point3d(0, .4, .4), //C
				new Point3d(.4,.4,-.4), //B
				
				//cara2 
				new Point3d(.4,.4,-.4), //B
				new Point3d(0, .4, .4), //C
				new Point3d(-.4,.4, -.4), //D
				
				//cara3
				new Point3d(-.4,.4, -.4), //D
				new Point3d(0, .4, .4), //C
				new Point3d(0,-.4,0), //A

				//Cara 4
				new Point3d(.4,.4,-.4), //B
				new Point3d(-.4,.4, -.4), //D
				new Point3d(0,-.4,0), //A
		};
		
		
		Color3f colores[]=new Color3f[] {
				//Cara 1
				new Color3f(1,0,0),	
				new Color3f(1,0,0),	
				new Color3f(1,0,0),	
				//cara 2
				new Color3f(0,1,0),	
				new Color3f(0,1,0),	
				new Color3f(0,1,0),	
				//cara 3 DEGRADADO GRADIENTE
				new Color3f(0,0,1),
				new Color3f(1,0,0),	
				new Color3f(0,1,0),	
				//cara 4
				new Color3f(0,0,1),
				new Color3f(0,0,1),
				new Color3f(0,0,1),

			};
		TriangleArray ta=new TriangleArray(12, GeometryArray.COORDINATES|GeometryArray.COLOR_3);
		ta.setCoordinates(0, coTriang);
		ta.setColors(0, colores);
		Shape3D S=new Shape3D(ta);
		
		//Proceso de rotacion automatica
				TransformGroup tgr=new TransformGroup();
				
		tgr.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		Alpha tiempo=new Alpha(-1, 3000);
		RotationInterpolator rot=
					new RotationInterpolator(tiempo,tgr);
		BoundingSphere limite= new BoundingSphere()	;
		rot.setSchedulingBounds(limite);
		tgr.addChild(rot);
		tgr.addChild(S);
		
		
		
		objraiz.addChild(tgr);
		
		
		
		
		
		return objraiz;
	}
	public static void main(String args[]){
		EventQueue.invokeLater(Brep::new);
	}
	}
