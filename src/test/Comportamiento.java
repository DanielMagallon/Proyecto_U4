package test;
import java.awt.*;
import javax.media.j3d.*;
import javax.swing.*;
import javax.vecmath.*;
import  com.sun.j3d.utils.geometry.ColorCube;
import com.sun.j3d.utils.universe.*;
public class Comportamiento extends JFrame{
	
	public Comportamiento(){
		setTitle("Comportamiento en java3D");
		setSize(800,600);
		
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
		t3.set(new Vector3d(-.4f,0f,0f)); //traslacion
		t1.mul(t3);
		
		
		TransformGroup tgr =new TransformGroup();// padre de tg
		tgr.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);///rotacion de la figura automatica
		TransformGroup tg= new TransformGroup(t1);
		tgr.addChild(tg);
		objraiz.addChild(tgr); 
		//objraiz <- tgr <- tg <- colorCube  JERQUIA DEL ARBOL
		tg.addChild(new ColorCube(.2));
		
		//PROCESO DE ROTACION AUTOMATICA
		Alpha tiempo= new Alpha(-1,3000);//tiempo de la rotacion 
		RotationInterpolator rot =new RotationInterpolator(tiempo,tgr);
		//se establecen limites
		BoundingSphere limite= new BoundingSphere();
		rot.setSchedulingBounds(limite);
		//integrar rotacion automatica
		tgr.addChild(rot);
		return objraiz;
	}
	public static void main(String args[]){
		Comportamiento obj=new Comportamiento();
	}
}
