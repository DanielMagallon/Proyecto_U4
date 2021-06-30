package test;

import com.sun.j3d.utils.geometry.ColorCube;
import com.sun.j3d.utils.geometry.Sphere;
import com.sun.j3d.utils.image.TextureLoader;
import com.sun.j3d.utils.universe.SimpleUniverse;

import javax.media.j3d.*;
import javax.swing.*;
import javax.vecmath.Color3f;
import javax.vecmath.Vector3f;
import java.awt.*;
import java.net.URL;

public class Textura extends JFrame{
	public Textura(){
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

	ImageComponent2D cargar(URL url){
        TextureLoader textureLoader = new TextureLoader(url,this);
        ImageComponent2D img = textureLoader.getImage();
        return img;
    }

	public BranchGroup crearescena(){
		BranchGroup objraiz=new BranchGroup();

		URL urlF = getClass().getResource("/test/fondo.jpg");
		ImageComponent2D imgf = cargar(urlF);
		Background F = new Background(imgf);
		BoundingSphere limite = new BoundingSphere();
		F.setApplicationBounds(limite);

        URL url = getClass().getResource("/test/dark.png");
        ImageComponent2D im1 = cargar(url);

        Texture2D texture2D = new Texture2D(Texture2D.BASE_LEVEL,
                Texture.RGB,im1.getWidth(),im1.getHeight());

        texture2D.setImage(0,im1);
        texture2D.setEnable(true);
        Appearance appearance = new Appearance();
        appearance.setColoringAttributes(new ColoringAttributes(new Color3f(0,0,1),
                ColoringAttributes.SHADE_GOURAUD));
        appearance.setPolygonAttributes(new PolygonAttributes(
                PolygonAttributes.POLYGON_FILL,PolygonAttributes.CULL_NONE,0
        ));
        appearance.setTexture(texture2D);
        Sphere sphere = new Sphere(.5f,50,50,appearance);

        TransformGroup tgr=new TransformGroup();
        tgr.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        Alpha tiempo=new Alpha(-1, 4000);
        RotationInterpolator rot=
                new RotationInterpolator(tiempo,tgr);
        rot.setSchedulingBounds(limite);
        tgr.addChild(rot);
        tgr.addChild(sphere);
        objraiz.addChild(F);
        objraiz.addChild(tgr);
		return objraiz;
	}
	public static void main(String args[]){
		Textura obj=new Textura();
	}
	}
