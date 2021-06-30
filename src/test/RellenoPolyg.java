package test;

import com.sun.j3d.utils.geometry.ColorCube;
import com.sun.j3d.utils.geometry.GeometryInfo;
import com.sun.j3d.utils.universe.SimpleUniverse;

import javax.media.j3d.*;
import javax.swing.*;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3f;
import java.awt.*;

public class RellenoPolyg extends JFrame{
	public RellenoPolyg(){
		setTitle("Programa en java3d");
		setSize(800,600);
		
		//obtenemos la configuracion grafica propia del sistema
		GraphicsConfiguration config=SimpleUniverse.getPreferredConfiguration();
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

		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public BranchGroup crearescena(){
		BranchGroup objraiz=new BranchGroup();
        Point3d[]  vertices = {
                new Point3d(-.3,.1,0),
                new Point3d(-.3,-.2,0),
                new Point3d(-.1,-.2,0),
                new Point3d(-.1,-.1,0),
                new Point3d(.1,-.1,0),
                new Point3d(.1,-.2,0),
                new Point3d(.3,-.2,0),
                new Point3d(.3,.3,0),
                new Point3d(0,.3,0),
                new Point3d(0,.1,0),
        };
        int sec[] = {0,1,2,3,4,5,6,7,8,9};
        int tiras[] = {10};

        GeometryInfo gi = new GeometryInfo(GeometryInfo.POLYGON_ARRAY);
        gi.setCoordinates(vertices);
        gi.setCoordinateIndices(sec);
        gi.setStripCounts(tiras);

        Appearance ap = new Appearance();
        ap.setPolygonAttributes(new PolygonAttributes(PolygonAttributes.POLYGON_LINE,
                PolygonAttributes.CULL_NONE,0));

        Shape3D S = new Shape3D(gi.getGeometryArray(),ap);
        objraiz.addChild(S);

		return objraiz;
	}
	public static void main(String args[]){
		RellenoPolyg obj=new RellenoPolyg();
	}
	}
