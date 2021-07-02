
package bin.shape3d.abstracts;

import LabelFace.LabelFace;
import com.sun.j3d.utils.geometry.GeometryInfo;
import com.sun.j3d.utils.geometry.NormalGenerator;
import com.sun.j3d.utils.geometry.Primitive;
import com.sun.j3d.utils.image.TextureLoader;
import main.Run;
import panes.items.Iluminacion;
import static_props.AppProps;
import static_props.ImageLoader;

import javax.media.j3d.*;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.vecmath.*;

import java.awt.*;
import java.io.File;
import java.net.MalformedURLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

import static static_props.AppProps.COORD_X;
import static static_props.AppProps.COORD_Y;

public class ShapeJ3D
{

    private  TransformGroup transformGroup;
    private Transform3D transform3D,trasnformRotator;
    private RotationInterpolator rot;
    private javax.media.j3d.Shape3D shape3D;

    private double scaleX=1,scaleY=1,scaleZ=1,rotY=45;
    private float transX=0,transY=0,transZ=0;
    private Appearance appearance;
    private GeometryInfo geometryInfo;

    public HashMap<Integer,Color3f> colorList = new HashMap<>(){{
        put(0,(new Color3f(1,0,0)));//0
        put(1,(new Color3f(0,1,0)));//1
        put(2,new Color3f(0,0,1));//2
        put(3,new Color3f(1,1,0));//3
        put(4,new Color3f(1,1,1));//3
        put(5,new Color3f(0,1,1));//4
    }};

    public int secColor[] = {0,0,0,0,0,0,0,0,0,0,0,0, 1,1,1,1,1,1,1,1,1,1,1,1, 2,2,2,2,  3,3,3,3,
            2,2,2,2,  3,3,3,3, 2,2,2,2,  3,3,3,3, 2,2,2,2,  3,3,3,3, 2,2,2,2,  3,3,3,3, 2,2,2,2,  3,3,3,3};

    public Color getRGBColor(int index){
        return colorList.get(secColor[index]).get();
    }

    private Font fontIcon = new Font(Font.MONOSPACED,Font.PLAIN,24);
    private  JPanel jpanel;
    private int indexLbl=5;

    Point3d vertices[]= {
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

    Point3f verticesf[]= {
            new Point3f(-.20f,.30f,.06f), //A 0
            new Point3f(.20f,.30f,.06f),//B 1
            new Point3f(.20f,.18f,.06f),//c 2
            new Point3f(-.10f,.18f,.06f),//D 3
            new Point3f(-.10f,.06f,.06f),//E 4
            new Point3f(.15f,.06f,.06f),//F 5
            new Point3f(-.10f,-.06f,.06f),//G 6
            new Point3f(.15f,-.06f,.06f),//H 7
            new Point3f(-.20f,-.30f,.06f),//I 8
            new Point3f(-.10f,-.18f,.06f),//J 9
            new Point3f(.20f,-.18f,.06f),//K 10
            new Point3f(.20f,-.30f,.06f),//L 11
            new Point3f(-.20f,.30f,-.06f),//A' 12
            new Point3f(.20f,.30f,-.06f),//B' 13
            new Point3f(.20f,.18f,-.06f),//C' 14
            new Point3f(-.10f,.18f,-.06f),//D' 15
            new Point3f(-.10f,.06f,-.06f),//E' 16
            new Point3f(.15f,.06f,-.06f),//F' 17
            new Point3f(-.10f,-.06f,-.06f),//G' 18
            new Point3f(.15f,-.06f,-.06f),//H' 19
            new Point3f(-.20f,-.30f,-.06f),//I' 20
            new Point3f(-.10f,-.18f,-.06f),//J' 21
            new Point3f(.20f,-.18f,-.06f),//K' 22
            new Point3f(.20f,-.30f,-.06f)//L' 23

    };



    int secuencia[]= {0,1,2,3,4,5,7,6,9,10,11,8,   12,13,14,15,16,17,19,18,21,22,23,20, 0,1,13,12,
            0,8,20,12,  8,11,23,20, 10,11,23,22, 9,10,22,21, 6,9,21,18,  6,7,19,18,
            5,7,19,17,  4,5,17,16,  3,4,17,16,  3,2,14,15,  1,2,14,13
    };

    int tiras[]={12,12,4,4,4,4,4,4,4,4,4,4,4,4};

    public LabelFace labelMain;

    public JPanel caraspanel(){
        JPanel panel = new JPanel(new FlowLayout());
        panel.setOpaque(false);
        int inicio=0;
        for (int i = 0; i < tiras.length; i++) {
            LabelFace label = new LabelFace("Cara "+(i+1),it -> Run.faceHandler((LabelFace) it),
                    inicio,tiras[i]+inicio-1);
            if(labelMain==null){
                labelMain=label;
            }
            inicio+=tiras[i];
            label.setBorder(BorderFactory.createEmptyBorder(3,10,3,10));
            panel.add(label);
        }

        return panel;
    }

    public static boolean fillPoint;
    public static int keyColor;

    private void addLblColor(int key){
        JLabel lbl = AppProps.createLabelFor("",(label)->{
            if(cbox.isSelected()){
                fillPoint=false;
                btnAplicarTodo.setEnabled(false);
                btnCanc.setEnabled(false);

                Color color = JColorChooser.showDialog(null,"Seleccione un color",
                        Color.black);

                if(color!=null) {
                    int k = Integer.parseInt(label.getName());

                    colorList.get(k).set(new Color3f(color.getRed() / 255f, color.getGreen() / 255f,
                            color.getBlue() / 255f));
                    label.setBackground(color);

                    Run.updateColorsFace();
                    updateShapeColor();
                }
            }else{
                fillPoint=true;
                cbox.setEnabled(false);
                btnAplicarTodo.setEnabled(true);
                btnCanc.setEnabled(true);
                keyColor = Integer.parseInt(label.getName());
                if(Run.faceProperties.closed)
                    Run.faceHandler(labelMain);
            }
        },labelHander->{
            if(cbox.isSelected()){
                labelHander.setCursor(ImageLoader.fillIncCursor);
            }else {
                labelHander.setCursor(AppProps.handCursor);
            }
        });

        lbl.setName(key+"");
        lbl.setOpaque(true);
        lbl.setBackground(colorList.get(key).get());
        lbl.setPreferredSize(new Dimension(30,30));
        jpanel.add(lbl);
    }

    private JButton btnCanc,btnAplicarTodo, remText;
    private JCheckBox cbox;

    public JPanel getPanelColor(){
        jpanel = new JPanel(new FlowLayout());
        jpanel.setOpaque(false);
        btnCanc = new JButton("Cancelar pintado");
        btnAplicarTodo = new JButton("Aplicar color completamente");
        btnAplicarTodo.setEnabled(false);
        btnAplicarTodo.setBackground(AppProps.BG_SELECTED);
        btnAplicarTodo.setForeground(AppProps.FG_NORMAL_TEXT);
        btnAplicarTodo.addActionListener(a->{
            fillPoint=false;
            btnAplicarTodo.setEnabled(false);
            btnCanc.setEnabled(false);
            cbox.setEnabled(true);
            Arrays.fill(secColor, keyColor);
            updateShapeColor();
        });
        btnCanc.setBackground(AppProps.BG_SELECTED);
        btnCanc.setForeground(AppProps.FG_NORMAL_TEXT);
        btnCanc.setEnabled(false);
        btnCanc.addActionListener(a->
        {
            fillPoint=false;
            btnAplicarTodo.setEnabled(false);
            btnCanc.setEnabled(false);
            cbox.setEnabled(true);
        });

        cbox = new JCheckBox("Cambiar colores");
        cbox.setForeground(AppProps.FG_NORMAL_TEXT);
        cbox.setOpaque(false);

        jpanel.add(btnAplicarTodo);
        jpanel.add(btnCanc);
        jpanel.add(cbox);
        colorList.keySet().forEach(this::addLblColor);
        JSeparator sep = new JSeparator(JSeparator.VERTICAL);
        sep.setBackground(Color.white);
        jpanel.add(sep);

        JButton apliarTexturaImagen = new JButton("Textura imagen");
        apliarTexturaImagen.setBackground(AppProps.BG_SELECTED);
        apliarTexturaImagen.setForeground(AppProps.FG_NORMAL_TEXT);
        apliarTexturaImagen.addActionListener(a->{
            if(fileChooser.showDialog(null,"Seleccione imagen")==JFileChooser.APPROVE_OPTION){
                if(createTexture(fileChooser.getSelectedFile()));
                    remText.setEnabled(true);

            }
        });

        remText = new JButton("Remover textura");
        remText.setBackground(AppProps.BG_SELECTED);
        remText.setForeground(AppProps.FG_NORMAL_TEXT);
        remText.addActionListener(a->{
            texturaImage=false;
            updateShapeColor();
            remText.setEnabled(false);
        });
        remText.setEnabled(false);


        jpanel.add(apliarTexturaImagen);
        jpanel.add(remText);

        return jpanel;
    }

    Texture2D texture2D;
    private boolean createTexture(File file){
        texturaImage = true;
        ImageComponent2D im1 = null;
        try {
            im1 = cargar(file);
        } catch (MalformedURLException e) {
            return false;
        }
        texture2D = new Texture2D(Texture2D.BASE_LEVEL,
                Texture.RGB,im1.getWidth(),im1.getHeight());
        texture2D.setImage(0,im1);
        texture2D.setEnable(true);
        updateShapeColor();
        return true;
    }

    ImageComponent2D cargar(File url) throws MalformedURLException {
        TextureLoader textureLoader ;
            textureLoader = new TextureLoader(url.toURI().toURL(), Run.canvas3D);
            return textureLoader.getImage();
    }

    private boolean texturaImage;
    private JFileChooser fileChooser = new JFileChooser();
    {
        fileChooser.setFileFilter(new FileNameExtensionFilter("Imagenes",
                "png","PNG","jpeg","jpg","JPG","JPEG"));
    }
    public Background backgroundGlobal;

    public BranchGroup getBranchGroup()
    {
        transformGroup = new TransformGroup();

        BranchGroup objraiz=new BranchGroup();

        TransformGroup tgr=new TransformGroup();
        trasnformRotator = new Transform3D();
        trasnformRotator.rotY(rotY);

        tgr.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        Alpha tiempo=new Alpha(-1, 5000);
        rot = new RotationInterpolator(tiempo,tgr);
        rot.setTransformAxis(trasnformRotator);
        BoundingSphere limite= new BoundingSphere();
        rot.setSchedulingBounds(limite);
        tgr.addChild(rot);

        {
            appearance = new Appearance();
            appearance.setCapability(Appearance.ALLOW_POLYGON_ATTRIBUTES_WRITE);
            appearance.setCapability(Appearance.ALLOW_MATERIAL_WRITE);
            appearance.setCapability(Appearance.ALLOW_TEXTURE_WRITE);
            appearance.setCapability(Appearance.ALLOW_TEXTURE_ATTRIBUTES_WRITE);
            Material mat=new Material();
            appearance.setMaterial(mat);
        }


        shape3D = new Shape3D();
        shape3D.setCapability(Shape3D.ALLOW_APPEARANCE_OVERRIDE_WRITE);
        shape3D.setCapability(Shape3D.ALLOW_GEOMETRY_WRITE);
        shape3D.setAppearanceOverrideEnable(true);
        updateShapeColor();
        tgr.addChild(shape3D);


        backgroundGlobal = new Background(.2f,.2f,.2f);
        backgroundGlobal.setApplicationBounds(limite);
        backgroundGlobal.setCapability(Background.ALLOW_COLOR_WRITE);
        objraiz.addChild(backgroundGlobal);

        transformGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        transformGroup.addChild(tgr);

        objraiz.addChild(transformGroup);
        return objraiz;
    }

    public void updateColorKey(int index){
        secColor[index] = keyColor;
        updateShapeColor();
    }

    public static boolean filled=true;
    public void updateShapeColor(){

        if(geometryInfo==null) {
            geometryInfo = new GeometryInfo(GeometryInfo.POLYGON_ARRAY);
            shape3D.setAppearance(appearance);
        }
        else {
            geometryInfo.reset(GeometryInfo.POLYGON_ARRAY);

        }


        Color3f[] cols = new Color3f[colorList.size()];
        AtomicInteger ind= new AtomicInteger();


        colorList.keySet().forEach(key->{
                  cols[ind.getAndIncrement()] = colorList.get(key);
        });

        appearance.setPolygonAttributes(new PolygonAttributes(
                filled ? PolygonAttributes.POLYGON_FILL : PolygonAttributes.POLYGON_LINE,
                PolygonAttributes.CULL_NONE,0));

        geometryInfo.setCoordinates(vertices);
        geometryInfo.setCoordinateIndices(secuencia);
        geometryInfo.setStripCounts(tiras);
        if(texturaImage){
            geometryInfo.setTextureCoordinates(verticesf);
            geometryInfo.setTextureCoordinateIndices(secuencia);
            appearance.setTexture(texture2D);

        }else{

            appearance.setTexture(null);
        }

        geometryInfo.setColors(cols);
        geometryInfo.setColorIndices(secColor);

        if(Iluminacion.isLigth()) {
            NormalGenerator NG = new NormalGenerator();
            NG.generateNormals(geometryInfo);
        }
        shape3D.setGeometry(geometryInfo.getGeometryArray());
    }

    public void mover(int coord, float val){
        switch (coord)
        {

            case COORD_X:
                transX = val;
                break;

            case COORD_Y:
                transY = val;
                break;

            default:
                transZ = val;

        }

        updateTransforms();
    }



    public void updateTransforms(){
        Transform3D transform3D1 = new Transform3D();
        transform3D  = new Transform3D();

        transform3D1.setScale(new Vector3d(scaleX,scaleY,scaleZ));
        transform3D.set(new Vector3f(transX,transY,transZ));

        transform3D.mul(transform3D1);
        transformGroup.setTransform(transform3D);
    }

    public void rotacion(double val, int rot){
        switch (rot){
            case COORD_X:
                trasnformRotator.rotX(val);
                break;
            case COORD_Y:
                trasnformRotator.rotY(val);
                break;
            default:
                trasnformRotator.rotZ(val);
                break;
        }

        this.rot.setTransformAxis(trasnformRotator);
    }

    public void scale(double facx,double facy,double facz){
        scaleX = facx;
        scaleY = facy;
        scaleZ = facz;
        updateTransforms();
    }

    public void scale(double fac){
        scaleZ = scaleX = scaleY = fac;
        updateTransforms();
    }
}
