package test;

import com.sun.j3d.utils.geometry.ColorCube;
import com.sun.j3d.utils.geometry.Sphere;
import com.sun.j3d.utils.universe.SimpleUniverse;

import javax.media.j3d.*;
import javax.swing.*;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Point3f;
import javax.vecmath.Vector3f;
import java.awt.*;

public class Iluminacion extends JFrame{

    AmbientLight ambientLight;
    DirectionalLight directionalLight;
    PointLight pointLight;



	public Iluminacion(){
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

		JPanel arriba = new JPanel();
		cb1 = new JCheckBox("Luz ambiental");
        arriba.add(cb1);
		cb1.addChangeListener(c->
		    ambientLight.setEnable(cb1.isSelected())
        );

        cb2 = new JCheckBox("Luz direccional");
        arriba.add(cb2);
        cb2.addChangeListener(c->
                directionalLight.setEnable(cb2.isSelected())
        );

        cb3 = new JCheckBox("Punto de luz");
        arriba.add(cb3);
        cb3.addChangeListener(c->
                pointLight.setEnable(cb3.isSelected())
        );

        add(arriba,"North");
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void esfera(Vector3f ubic,float tam,BranchGroup raiz)
    {
        Appearance ap = new Appearance();
        ap.setColoringAttributes(new ColoringAttributes(new Color3f(1,0,0),
                ColoringAttributes.SHADE_GOURAUD));
        ap.setPolygonAttributes(new PolygonAttributes(PolygonAttributes.POLYGON_LINE,
                PolygonAttributes.CULL_NONE,0));

        Material mat = new Material();
        ap.setMaterial(mat);

        Sphere sphere = new Sphere(tam,Sphere.GENERATE_NORMALS,50,ap);
        Transform3D pos = new Transform3D();
        pos.set(ubic);
        TransformGroup tgu = new TransformGroup(pos);
        tgu.addChild(sphere);
        raiz.addChild(tgu);
    }

    JCheckBox cb1,cb2,cb3;

    void luzAmbiental(BranchGroup raiz){
	    ambientLight = new AmbientLight(new Color3f(0,1,0));
	    ambientLight.setCapability(AmbientLight.ALLOW_STATE_WRITE);
	    BoundingSphere limit = new BoundingSphere(new Point3d(0,0,0),1);
	    ambientLight.setInfluencingBounds(limit);
	    ambientLight.setEnable(false);
	    raiz.addChild(ambientLight);
    }

    void luzDireccional(BranchGroup raiz,Color3f color, Vector3f vector3f){

        BoundingSphere limit = new BoundingSphere(new Point3d(0,0,0),1);
        directionalLight = new DirectionalLight(false,color,vector3f);
        directionalLight.setInfluencingBounds(limit);
        directionalLight.setCapability(DirectionalLight.ALLOW_STATE_WRITE);
        raiz.addChild(directionalLight);
    }

    void puntoLuz(BranchGroup raiz,Color3f color, Point3f pos,Point3f ate)
    {
        pointLight = new PointLight(false,color,pos,ate);
        pointLight.setCapability(PointLight.ALLOW_STATE_WRITE);
        BoundingSphere limite = new BoundingSphere(new Point3d(0,0,0),1);
        pointLight.setInfluencingBounds(limite);
        raiz.addChild(pointLight);
    }

	public BranchGroup crearescena(){
		BranchGroup objraiz=new BranchGroup();
        puntoLuz(objraiz,new Color3f(1,1,1), new Point3f(0,0,0), new Point3f(1,0,0));
        luzAmbiental(objraiz);
        luzDireccional(objraiz,new Color3f(1,0,0),new Vector3f(.5f,0,0));
        esfera(new Vector3f(-.4f,.35f,0),.35f,objraiz);
        esfera(new Vector3f(-.4f,-.35f,0),.35f,objraiz);
        esfera(new Vector3f(.4f,.35f,0),.35f,objraiz);
        esfera(new Vector3f(.4f,-.35f,0),.35f,objraiz);
		return objraiz;
	}
	public static void main(String args[]){
		Iluminacion obj=new Iluminacion();
	}
	}
