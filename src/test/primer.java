package test;
import java.awt.*;
import javax.media.j3d.*;
import javax.swing.*;
import javax.vecmath.*;
import com.sun.j3d.utils.geometry.*;
import com.sun.j3d.utils.universe.*;
public class primer extends JFrame{
	public primer(){
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
		Transform3D t1= new Transform3D();
		t1.rotX(45);
		Transform3D t2= new Transform3D();
		t2.rotY(45);
		t1.mul(t2);// rotx 45+ roty 45
		Transform3D t3 = new Transform3D();
		t3.set(new Vector3f(.3f,0f,0f)); //traslacion
		t1.mul(t3);
        Transform3D t4 = new Transform3D();
        t4.setScale(new Vector3d(1.5,1.5,1.5));
        t1.mul(t4);
		TransformGroup tg= new TransformGroup(t1);
		objraiz.addChild(tg);
		tg.addChild(new ColorCube(.3));
		return objraiz;
	}
	public static void main(String args[]){
		primer obj=new primer();
	}
	}
