package test;

import java.awt.*;
import javax.media.j3d.*;
import javax.swing.*;
import javax.vecmath.*;
import  com.sun.j3d.utils.geometry.ColorCube;
import com.sun.j3d.utils.universe.*;
public class MiPropiaGeo extends JFrame{
	
	public  MiPropiaGeo(){
		setTitle("Programa en java3d");
		setSize(800,600);

        System.out.println("Init");
		//obtenemos la configuracion grafica propia del sistema
		GraphicsConfiguration config=SimpleUniverse.getPreferredConfiguration();
        System.out.println("Got javaconf");
		//construimos el canvas 3d
		Canvas3D canvas=new Canvas3D(config);
		add(canvas);
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
		Point3d fig[]=new Point3d[] {
				new Point3d(-.3,-.25,.20),//A 0
				new Point3d(.3,-.25,.20),//B  1
				new Point3d(-.2,.25,.15),//C 2
				new Point3d(.20,.25,.15),//D   3
				new Point3d(-.3,-.25,-.20),//A' 4
				new Point3d(.3,-.25,-.20),//B' 5
				new Point3d(-.2,.25,-.15),//C' 6
				new Point3d(.20,.25,-.15) //D'  7
				
		};
		int sec[]= {0,1,1,3,3,2,2,0,  4,5,5,7,7,6,6,4, 0,4,1,5,2,6,3,7};
		Color3f vecCols[]=new Color3f[] {new Color3f(1,0,0),new Color3f(0,1,0),
				                         new Color3f(0,0,1)
				
		                                };
		int secCols[]= {0,0,0, 1,1,1, 2,2,2 ,0,0,0, 1,1,1, 2,2,2, 0,0,0, 1,1,1};
		IndexedLineArray fw=new IndexedLineArray(fig.length, GeometryArray.COORDINATES|GeometryArray.COLOR_3, sec.length);
		fw.setCoordinates(0, fig);
		fw.setCoordinateIndices(0, sec);
		fw.setColors(0, vecCols);
		fw.setColorIndices(0,secCols);
		Shape3D S=new Shape3D(fw);
		TransformGroup tgr=new TransformGroup();
		tgr.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		Alpha tiempo= new Alpha(-1,5000);//tiempo de la rotacion 
		RotationInterpolator rot =new RotationInterpolator(tiempo,tgr);
		//se establecen limites
		BoundingSphere limite= new BoundingSphere();
		rot.setSchedulingBounds(limite);
		tgr.addChild(rot);
		tgr.addChild(S);
		objraiz.addChild(tgr);
		return objraiz;
	}
	public static void main(String args[]){
		 MiPropiaGeo obj=new  MiPropiaGeo();
	}
	}
