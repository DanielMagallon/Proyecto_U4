package test;
import java.awt.*;
import javax.media.j3d.*;
import javax.swing.*;
import javax.vecmath.*;
import  com.sun.j3d.utils.geometry.ColorCube;
import com.sun.j3d.utils.geometry.GeometryInfo;
import com.sun.j3d.utils.universe.*;

class GIRepsolida extends JFrame{
    public GIRepsolida() {
        setTitle("Programa en Java3D");
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
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    public BranchGroup crearescena(){
        BranchGroup objraiz=new BranchGroup();
        GeometryInfo Gi =new GeometryInfo(GeometryInfo.POLYGON_ARRAY);
        Point3d  vertices[]= {
                new Point3d(-.3,-.4,.2),//A 0
                new Point3d(.3,-.4,.2),//B 1
                new Point3d(.3,-.4,-.2),//C 2
                new Point3d(-.3,-.4,-.2),//D 3
                new Point3d(0,.4,0),//E 4
        };
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
        int secuencia[]= {0,4,1, 1,4,2, 2,4,3, 3,4,0, 0,1,2,3};
        int secuenciae[]= {0,1,2,3,4,5,7,6,9,10,11,8,   12,13,14,15,16,17,19,18,21,22,23,20, 0,1,13,12,  0,8,20,12,  8,11,23,20, 10,11,23,22,
                9,10,22,21, 6,9,21,18,  6,7,19,18,  5,7,19,17,  4,5,17,16,  3,4,17,16,  3,2,14,15,  1,2,14,13};
        Gi.setCoordinates(veticese);
        Gi.setCoordinateIndices(secuenciae);
        int tiras[]= {3,3,3,3,4};
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
        //Proceso de rotacion
        TransformGroup tgr =new TransformGroup();
        tgr.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        Alpha tiempo =new Alpha(-1,4000);
        RotationInterpolator rot= new RotationInterpolator(tiempo, tgr);
        BoundingSphere limite=new BoundingSphere();
        rot.setSchedulingBounds(limite);
        tgr.addChild(rot);
        //la aparencia
        Appearance ap= new Appearance();
        ap.setColoringAttributes(new ColoringAttributes(new Color3f(1,0,0), ColoringAttributes.SHADE_GOURAUD));
        ap.setPolygonAttributes(new PolygonAttributes(PolygonAttributes.POLYGON_FILL, PolygonAttributes.CULL_NONE, 0));
        Shape3D S= new Shape3D(Gi.getGeometryArray(),ap);
        tgr.addChild(S);
        Background bacground =new Background(.5f,.5f,.5f);
        bacground.setApplicationBounds(limite);
        objraiz.addChild(bacground);
        objraiz.addChild(tgr);



        return objraiz;
    }
    public static void main(String args[]){
        GIRepsolida obj=new GIRepsolida();
    }
}
