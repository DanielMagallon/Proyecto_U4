package temp;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.net.URL;
import java.util.function.UnaryOperator;
import javax.swing.*;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Point3f;
import javax.vecmath.Vector3f;
import javax.media.j3d.*;
import  com.sun.j3d.utils.geometry.*;
import com.sun.j3d.utils.image.TextureLoader;
import com.sun.j3d.utils.universe.*;
import java.net.*;

public class Proyecto extends JFrame implements ItemListener {
    AmbientLight ambiental;
    PointLight pluz;
    JCheckBox cb1,cb2, cb3,cb4;
    DirectionalLight luzd;
    Shape3D S;
    BranchGroup objraiz;
    Texture2D textura;
    Boolean ban=false;
    public Proyecto()
    {
        setTitle("Programa en Java3D");
        setSize(900, 700);
        JPanel arr=new JPanel();
        cb1=new JCheckBox("Luz ambiental");
        arr.add(cb1);
        cb1.addItemListener(this);
        cb2=new JCheckBox("Luz Direccional");
        arr.add(cb2);
        cb2.addItemListener(this);
        cb3=new JCheckBox("Activar Colores");
        arr.add(cb3);
        cb3.addItemListener(this);
        cb4=new JCheckBox("Activar textura");
        arr.add(cb4);
        cb4.addItemListener(this);
        add(arr,BorderLayout.NORTH);


        //obtenemos la configuracion grafica propia del sistema
        GraphicsConfiguration config = SimpleUniverse.getPreferredConfiguration();

        //construimos el canvas 3d
        Canvas3D canvas = new Canvas3D(config);
        add(canvas,BorderLayout.CENTER);

        //creamos la rama de Representacion
        BranchGroup escena = crearEscena();

        //se compila la escena para optimizar la ejecucion
        escena.compile();

        //Usamos simple Universe para simplificar la rama de representacion
        SimpleUniverse su = new SimpleUniverse(canvas);

        //retrazamos el punto de vision para poder ver los objetos
        su.getViewingPlatform().setNominalViewingTransform();

        //anadimos la rama de contenido a la raiz del arbol
        su.addBranchGraph(escena);

        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public ImageComponent2D cargarImg(URL r){

        TextureLoader loader= new TextureLoader(r, this);
        ImageComponent2D img=loader.getImage();
        return img;
    }


    public void ponerFig( Vector3f ubic, BranchGroup raiz) {

        Appearance ap= new Appearance();
        ap.setColoringAttributes(new ColoringAttributes(new Color3f(.5f,.5f,0), ColoringAttributes.SHADE_GOURAUD));
        ap.setPolygonAttributes(new PolygonAttributes(PolygonAttributes.POLYGON_LINE,
                PolygonAttributes.CULL_NONE,0));
        Material mat=new Material();
        ap.setMaterial(mat);
        //Sphere Sp =new Sphere(tam,Sphere.GENERATE_NORMALS,50,ap);
        Point3d vertices[]={
                new Point3d(-.4,.6,0.1), //0
                new Point3d(-.4,.5,0.1),//1
                new Point3d(-.1,.5,0.1),//2
                new Point3d(-.1,-.18,0.1),//3
                new Point3d(-.15,-.2,0.1),//4
                new Point3d(-.2,-.2,0.1),//5
                new Point3d(-.28,-.18,0.1),//6
                new Point3d(-.3,-.1,0.1),//7
                new Point3d(-.3,0.05,0.1),//8
                new Point3d(-.44,0.05,0.1),//9
                new Point3d(-.45,-.13,0.1),//10
                new Point3d(-.41,-.22,0.1),//11
                new Point3d(-.37,-.3,0.1),//12
                new Point3d(-.26,-.35,0.1),//13
                new Point3d(-.14,-.35,0.1),//14
                new Point3d(-.05,-.34,0.1),//15
                new Point3d(.04,-.3,0.1),//16
                new Point3d(.1,-.2,0.1),//17
                new Point3d(.1,.5,0.1),//18
                new Point3d(.4,.5,0.1),//19
                new Point3d(.4,.6,0.1),//20
                new Point3d(-.4,.6,0.1),//21

                new Point3d(-.4,.6,-0.1), //22
                new Point3d(-.4,.5,-0.1),//23
                new Point3d(-.1,.5,-0.1),//24
                new Point3d(-.1,-.18,-0.1),//25
                new Point3d(-.15,-.2,-0.1),//26
                new Point3d(-.2,-.2,-0.1),//27
                new Point3d(-.28,-.18,-0.1),//28
                new Point3d(-.3,-.1,-0.1),//29
                new Point3d(-.3,0.05,-0.1),//30
                new Point3d(-.44,0.05,-0.1),//31
                new Point3d(-.45,-.13,-0.1),//32
                new Point3d(-.41,-.22,-0.1),//33
                new Point3d(-.37,-.3,-0.1),//34
                new Point3d(-.26,-.35,-0.1),//35
                new Point3d(-.14,-.35,-0.1),//36
                new Point3d(-.05,-.34,-0.1),//37
                new Point3d(.04,-.3,-0.1),//38
                new Point3d(.1,-.2,-0.1),//39
                new Point3d(.1,.5,-0.1),//40
                new Point3d(.4,.5,-0.1),//41
                new Point3d(.4,.6,-0.1),//42
                new Point3d(-.4,.6,-0.1),//43
        };
        Point3f vertice2[]={
                new Point3f((float)-.4,(float).6,(float)0.1), //0
                new Point3f((float)-.4,(float).5,(float)0.1),//1
                new Point3f((float)-.1,(float).5,(float)0.1),//2
                new Point3f((float)-.1,(float)-.18,(float)0.1),//3
                new Point3f((float)-.15,(float)-.2,(float)0.1),//4
                new Point3f((float)-.2,(float)-.2,(float)0.1),//5
                new Point3f((float)-.28,(float)-.18,(float)0.1),//6
                new Point3f((float)-.3,(float)-.1,(float)0.1),//7
                new Point3f((float)-.3,(float)0.05,(float)0.1),//8
                new Point3f((float)-.44,(float)0.05,(float)0.1),//9
                new Point3f((float)-.45,(float)-.13,(float)0.1),//10
                new Point3f((float)-.41,(float)-.22,(float)0.1),//11
                new Point3f((float)-.37,(float)-.3,(float)0.1),//12
                new Point3f((float)-.26,(float)-.35,(float)0.1),//13
                new Point3f((float)-.14,(float)-.35,(float)0.1),//14
                new Point3f((float)-.05,(float)-.34,(float)0.1),//15
                new Point3f((float).04,(float)-.3,(float)0.1),//16
                new Point3f((float).1,(float)-.2,(float)0.1),//17
                new Point3f((float).1,(float).5,(float)0.1),//18
                new Point3f((float).4,(float).5,(float)0.1),//19
                new Point3f((float).4,(float).6,(float)0.1),//20
                new Point3f((float)-.4,(float).6,(float)0.1),//21

                new Point3f((float)-.4,(float).6,(float)-0.1), //22
                new Point3f((float)-.4,(float).5,(float)-0.1),//23
                new Point3f((float)-.1,(float).5,(float)-0.1),//24
                new Point3f((float)-.1,(float)-.18,(float)-0.1),//25
                new Point3f((float)-.15,(float)-.2,(float)-0.1),//26
                new Point3f((float)-.2,(float)-.2,(float)-0.1),//27
                new Point3f((float)-.28,(float)-.18,(float)-0.1),//28
                new Point3f((float)-.3,(float)-.1,(float)-0.1),//29
                new Point3f((float)-.3,(float)0.05,(float)-0.1),//30
                new Point3f((float)-.44,(float)0.05,(float)-0.1),//31
                new Point3f((float)-.45,(float)-.13,(float)-0.1),//32
                new Point3f((float)-.41,(float)-.22,(float)-0.1),//33
                new Point3f((float)-.37,(float)-.3,(float)-0.1),//34
                new Point3f((float)-.26,(float)-.35,(float)-0.1),//35
                new Point3f((float)-.14,(float)-.35,(float)-0.1),//36
                new Point3f((float)-.05,(float)-.34,(float)-0.1),//37
                new Point3f((float).04,(float)-.3,(float)-0.1),//38
                new Point3f((float).1,(float)-.2,(float)-0.1),//39
                new Point3f((float).1,(float).5,(float)-0.1),//40
                new Point3f((float).4,(float).5,(float)-0.1),//41
                new Point3f((float).4,(float).6,(float)-0.1),//42
                new Point3f((float)-.4,(float).6,(float)-0.1),//43
        };

        int sec[]= {0,1,23,22, 1,2,24,23, 2,3,25,24, 3,4,26,25, 4,5,27,26, 5,6,28,27, 6,7,29,28, 7,8,30,29, 8,9,31,30, 9,10,32,31, 10,11,33,32,
                11,12,34,33, 12,13,35,34, 13,14,36,35, 14,15,37,36, 15,16,38,37, 16,17,39,38, 17,18,40,39, 18,19,41,40, 19,20,42,41, 20,21,43,42,
                0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21, 22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43};

        int tiras[]={4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,22,22};
        GeometryInfo gi =new GeometryInfo(GeometryInfo.POLYGON_ARRAY);
        gi.setCoordinates(vertices);
        gi.setCoordinateIndices(sec);
        gi.setStripCounts(tiras);
        Color3f colores[]={
                new Color3f(1,0,0), //0
                new Color3f(0,0,1),  //1
                new Color3f(0,0,1),  //2
                new Color3f(1,1,0),  //3
                new Color3f(1,1,1),  //4
                new Color3f(0,1,1),  //5

        };
        int secColor[]={4,2,3,1, 4,2,3,1, 4,2,3,1, 4,2,3,1, 4,2,3,1, 1,2,3,4, 4,2,3,1,
                4,2,3,1, 4,2,3,1, 4,2,3,1, 4,2,3,1, 4,2,3,1, 4,2,3,1,
                4,2,3,1, 4,2,3,1, 4,2,3,1, 4,2,3,1, 4,2,3,1, 4,2,3,1, 4,2,3,1, 4,2,3,1,

                0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
                1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
        gi.setColors(colores);
        gi.setColorIndices(secColor);
        //fondo
        URL ruta = null;
        try {
            ruta = new URL("file:/home/daniel/Pictures/Wallpapers/dog.jpeg");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
//        URL ruta=getClass().getResource("/Unidad4/rec/f3.jpg");
        ImageComponent2D imf=cargarImg(ruta);
        Background f=new Background(imf);
        BoundingSphere limite=new BoundingSphere();
        f.setApplicationBounds(limite);


        //Cargar textura
        gi.setTextureCoordinates(vertice2);
        gi.setTextureCoordinateIndices(sec);
        try {
            ruta = new URL("file:/home/daniel/Pictures/Wallpapers/dark.png");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
//        ruta=getClass().getResource("/Unidad4/rec/wood.jpg");
        ImageComponent2D im1=cargarImg(ruta);
        //im1.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
         textura=new Texture2D(Texture2D.BASE_LEVEL,Texture.RGB,im1.getWidth(),im1.getHeight());
         textura.setCapability(Texture2D.ALLOW_ENABLE_WRITE);
         textura.setCapability(Texture2D.ALLOW_IMAGE_WRITE);
        textura.setImage(0,im1);
        textura.setEnable(ban);


        //rotaion
        TransformGroup tgr=new TransformGroup();
        tgr.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        Alpha tiempo = new Alpha(-1,5000);
        RotationInterpolator rot =new RotationInterpolator( tiempo,tgr);

        rot.setSchedulingBounds(limite);
        tgr.addChild(rot);
        tgr.addChild(f);

        Transform3D pos=new Transform3D();
        pos.set(ubic);
        TransformGroup tgu=new TransformGroup(pos);
        // ap.setColoringAttributes(new ColoringAttributes(new Color3f(1,0,0), ColoringAttributes.SHADE_GOURAUD));
        ap.setPolygonAttributes(new PolygonAttributes(PolygonAttributes.POLYGON_FILL,
                PolygonAttributes.CULL_NONE,0));
        ap.setTexture(textura);
        //luz
        NormalGenerator normalGenerator = new NormalGenerator();
        normalGenerator.generateNormals(gi);


        S=new Shape3D(gi.getGeometryArray(),ap);
        tgr.addChild(S);


        raiz.addChild(tgr);


    }
    public void ponerLuzAmb(BranchGroup raiz){
        ambiental= new AmbientLight(new Color3f(1.0f,1.0f,1.0f));
        ambiental.setCapability(AmbientLight.ALLOW_STATE_WRITE);
        BoundingSphere limite=new BoundingSphere(new Point3d(0,0,0),1);
        ambiental.setInfluencingBounds(limite);
        ambiental.setEnable(false);
        raiz.addChild(ambiental);
    }
    public void ponerLuzDirec(Color3f color, Vector3f ubic,BranchGroup raiz){
        BoundingSphere limite=new BoundingSphere(new Point3d(0,0,0),1);
        luzd=new DirectionalLight(false, color,ubic);
        luzd.setInfluencingBounds(limite);
        luzd.setCapability(DirectionalLight.ALLOW_STATE_WRITE);
        raiz.addChild(luzd);
    }
    public void ponerPuntoluz(Color3f c, Point3f pos, Point3f at, BranchGroup raiz){
        BoundingSphere limite=new BoundingSphere(new Point3d(0,0,0),1);
        pluz=new PointLight(c,pos,at);
        pluz.setCapability(PointLight.ALLOW_STATE_WRITE);
        pluz.setInfluencingBounds(limite);
        pluz.setEnable(false);
        raiz.addChild(pluz);
    }
    public BranchGroup crearEscena(){

         objraiz = new BranchGroup();


        ponerLuzAmb(objraiz);
        ponerLuzDirec(new Color3f(0,0,1),new Vector3f(.6f,0,0), objraiz);
        ponerPuntoluz(new Color3f(1,1,1 ),new Point3f(-.6f,-.5f,0f),new Point3f(1,0,0),objraiz);

        ponerFig(new Vector3f(), objraiz);


        return objraiz;
    }

    public static void main(String args[])
    {
        new Proyecto();
    }


    public void itemStateChanged(ItemEvent itemEvent) {
        if(cb1.isSelected()){
            ambiental.setEnable(true);
        }else
            ambiental.setEnable(false);
        if(cb2.isSelected()){
            luzd.setEnable(true);
        }else
            luzd.setEnable(false);

        if(cb3.isSelected()){
            pluz.setEnable(true);
        }else
            pluz.setEnable(false);
        if(cb4.isSelected()){
            textura.setEnable(true);
            ban=true;

        }else{
            textura.setEnable(false);
            ban=false;
        }

    }
}