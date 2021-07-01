
package bin.shape3d.abstracts;

import LabelFace.LabelFace;
import com.sun.j3d.utils.geometry.GeometryInfo;
import com.sun.j3d.utils.geometry.NormalGenerator;
import main.Run;
import static_props.AppProps;
import static_props.ImageLoader;

import javax.media.j3d.*;
import javax.swing.*;
import javax.vecmath.*;

import java.awt.*;
import java.util.ArrayList;
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

    int secuencia[]= {0,1,2,3,4,5,7,6,9,10,11,8,   12,13,14,15,16,17,19,18,21,22,23,20, 0,1,13,12,
            0,8,20,12,  8,11,23,20, 10,11,23,22, 9,10,22,21, 6,9,21,18,  6,7,19,18,
            5,7,19,17,  4,5,17,16,  3,4,17,16,  3,2,14,15,  1,2,14,13
    };

    int tiras[]={12,12,4,4,4,4,4,4,4,4,4,4,4,4};


    public JPanel caraspanel(){
        JPanel panel = new JPanel(new FlowLayout());
        panel.setOpaque(false);
        int inicio=0;
        for (int i = 0; i < tiras.length; i++) {
            LabelFace label = new LabelFace("Cara "+(i+1),it -> Run.faceHandler((LabelFace) it),
                    inicio,tiras[i]+inicio-1);
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

    private JButton btnCanc,btnAplicarTodo;
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

        cbox = new JCheckBox("Camibar colores");
        cbox.setForeground(AppProps.FG_NORMAL_TEXT);
        cbox.setOpaque(false);

        jpanel.add(btnAplicarTodo);
        jpanel.add(btnCanc);
        jpanel.add(cbox);
        colorList.keySet().forEach(this::addLblColor);

        return jpanel;
    }

    public BranchGroup getBranchGroup()
    {
        transformGroup = new TransformGroup();

        BranchGroup objraiz=new BranchGroup();

        TransformGroup tgr=new TransformGroup();
        trasnformRotator = new Transform3D();
        trasnformRotator.rotY(rotY);

        tgr.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        Alpha tiempo=new Alpha(-1, 3200);
        rot = new RotationInterpolator(tiempo,tgr);
        rot.setTransformAxis(trasnformRotator);
        BoundingSphere limite= new BoundingSphere();
        rot.setSchedulingBounds(limite);
        tgr.addChild(rot);

        {
            appearance = new Appearance();
            appearance.setCapability(Appearance.ALLOW_POLYGON_ATTRIBUTES_WRITE);
        }


        shape3D = new Shape3D();
        shape3D.setCapability(Shape3D.ALLOW_GEOMETRY_WRITE);
        shape3D.setAppearance(appearance);
        updateShapeColor();
        tgr.addChild(shape3D);


        Background background = new Background(.2f,.2f,.2f);
        background.setApplicationBounds(limite);
        objraiz.addChild(background);

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

        if(geometryInfo==null)
            geometryInfo = new GeometryInfo(GeometryInfo.POLYGON_ARRAY);
        else {
            geometryInfo.reset(GeometryInfo.POLYGON_ARRAY);

        }

//        NormalGenerator normalGenerator = new NormalGenerator();
//        normalGenerator.generateNormals(geometryInfo);

        geometryInfo.setCoordinates(vertices);
        geometryInfo.setCoordinateIndices(secuencia);
        geometryInfo.setStripCounts(tiras);
        Color3f[] cols = new Color3f[colorList.size()];
        AtomicInteger ind= new AtomicInteger();


        colorList.keySet().forEach(key->{
                  cols[ind.getAndIncrement()] = colorList.get(key);
        });

        geometryInfo.setColors(cols);
        geometryInfo.setColorIndices(secColor);
        shape3D.setGeometry(geometryInfo.getGeometryArray());

        appearance.setPolygonAttributes(new PolygonAttributes(
                filled ? PolygonAttributes.POLYGON_FILL : PolygonAttributes.POLYGON_LINE,
                PolygonAttributes.CULL_NONE,0));


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
