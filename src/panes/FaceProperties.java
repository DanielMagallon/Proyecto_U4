package panes;

import LabelFace.LabelFace;
import bin.handlers.LabelHandler;
import bin.shape3d.abstracts.ShapeJ3D;
import main.Run;
import static_props.AppProps;
import static_props.ImageLoader;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class FaceProperties extends JPanel
{
    private TitledBorder  titleBorder;
    public boolean closed=true;
    private JPanel panelCenter;
    private JLabel lblColorDemos,lblGradDe1,lblGradDe2,lblImagen;
    private String validExt[] = {"png", "jpg","jpeg","PNG","JPG","JPEG"};
    private FileNameExtensionFilter filter = new FileNameExtensionFilter("IMG FILES", validExt);
    private JFileChooser fileChooser = new JFileChooser();

    public FaceProperties()
    {
        setBackground(AppProps.BG_SELECTED);
        setLayout(new BorderLayout());
        titleBorder = new TitledBorder(BorderFactory.createLineBorder(Color.yellow));
        titleBorder.setTitleJustification(TitledBorder.CENTER);
        titleBorder.setTitleColor(AppProps.FG_NORMAL_TEXT);

        fileChooser.setFileFilter(filter);
        setBorder(titleBorder);
        northPanel();
        panelCenter();
    }

    private void panelCenter(){
        panelCenter = new JPanel();
        panelCenter.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        panelCenter.setOpaque(false);
        panelCenter.setPreferredSize(new Dimension(300,1200));
        panelCenter.setLayout(new GridLayout(3,1,0,10));
        add(panelCenter);
        initPanelColor();
//        addImageSelection();
//        addGradientSelection();
    }


    private void addImageSelection(){
        JPanel panel = new JPanel();
        TitledBorder titleBorder = new TitledBorder(BorderFactory.createLineBorder(Color.yellow));
        titleBorder.setTitleJustification(TitledBorder.CENTER);
        titleBorder.setTitleColor(AppProps.FG_NORMAL_TEXT);
        titleBorder.setTitle("Asignar textura imagen");
        panel.setOpaque(false);
        panel.setBorder(titleBorder);

        lblImagen = getLabelDemos(100,100,(lbl)->{

            if(fileChooser.showDialog(Run.frame,"Imagen de textura")==JFileChooser.APPROVE_OPTION){
                try {
                    BufferedImage bf = ImageIO.read(fileChooser.getSelectedFile());
//                    face3D.setTexturePaint(0,0,Run.canvas3D.getWidth(),Run.canvas3D.getHeight(),bf);
//                    lblImagen.setIcon(face3D.image);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        });

        panel.add(lblImagen);
        panel.add(getButton("Aplicar",()->{

//            if(face3D.image!=null) {
//                this.face3D.fillFace = FaceFill.IMAGE;
//                Run.canvas3D.repaint();
//            }else JOptionPane.showMessageDialog(this,"No se ha seleccionado alguna imagen",
//                    "Error 404",JOptionPane.ERROR_MESSAGE);
        }));

        JPanel center = new JPanel(new BorderLayout(1,1));
        center.add(panel);
        center.setBorder(BorderFactory.createEmptyBorder(25,0,0,0));
        center.setOpaque(false);
        panelCenter.add(center);
    }

    private void addGradientSelection(){
        JPanel panel = new JPanel();
        TitledBorder titleBorder = new TitledBorder(BorderFactory.createLineBorder(Color.yellow));
        titleBorder.setTitleJustification(TitledBorder.CENTER);
        titleBorder.setTitleColor(AppProps.FG_NORMAL_TEXT);
        titleBorder.setTitle("Asignar gradiente");
        panel.setOpaque(false);
        panel.setBorder(titleBorder);

        lblGradDe1 = getLabelDemos(50,100,(lbl)->{
            Color color = JColorChooser.showDialog(this,"Escoge el primer color",defaultFaceColor);
            if(color!=null)
            {
//                this.face3D.updateColorsGradient(true,color);
                lblGradDe1.setBackground(color);
            }
        });
        lblGradDe2 = getLabelDemos(50,100,(lbl)->{
            Color color = JColorChooser.showDialog(this,"Escoge el segundo color",defaultFaceColor);
            if(color!=null)
            {
//                this.face3D.updateColorsGradient(false,color);
                lblGradDe2.setBackground(color);
            }
        });

        panel.add(lblGradDe1);
        panel.add(lblGradDe2);
        panel.add(getButton("Aplicar",()->{
//            this.face3D.fillFace = FaceFill.GRADIENT;
//            this.face3D.updateGradient(10,10, Run.canvas3D.getWidth(),Run.canvas3D.getHeight());
            Run.canvas3D.repaint();
        }));

        JPanel center = new JPanel(new BorderLayout(1,1));
        center.add(panel);
        center.setBorder(BorderFactory.createEmptyBorder(25,0,0,0));
        center.setOpaque(false);
        panelCenter.add(center);
    }

    private JLabel getLabelDemos(int w, int h, LabelHandler handler){
        JLabel lbl = new JLabel();

        lbl.setPreferredSize(new Dimension(w,h));
        lbl.setOpaque(true);
        lbl.setCursor(AppProps.handCursor);
        lbl.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                handler.labelCallback((JLabel) mouseEvent.getComponent());
            }
        });

        return lbl;
    }

    private  JPanel panelColorS;
    private void initPanelColor(){
        JPanel panelmain = new JPanel(new BorderLayout()){{
            setPreferredSize(new Dimension(0,200));
        }};
        panelColorS = new JPanel(new BorderLayout());
        panelColorS.setPreferredSize(new Dimension(0,200));
        TitledBorder titleBorder = new TitledBorder(BorderFactory.createLineBorder(Color.yellow));
        titleBorder.setTitleJustification(TitledBorder.CENTER);
        titleBorder.setTitleColor(AppProps.FG_NORMAL_TEXT);
        titleBorder.setTitle("Asignar color");
        panelmain.setBorder(titleBorder);
        JScrollPane sc = new JScrollPane(panelColorS);
        sc.setOpaque(false);
        panelColorS.setOpaque(false);
        panelmain.setOpaque(false);
        panelColorS.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        sc.getViewport().setBackground(AppProps.CANVAS_BG);
        panelmain.add(sc);
        panelCenter.add(panelmain);

    }

    private void addColorSections(){

        int count = labelF.indexEnd - labelF.indexStart;
        panelColorS.removeAll();
        panelColorS.setLayout(new GridLayout((count & 1)==0 ? count/3 : count/3+1,3,15,5));

        for(int i=labelF.indexStart; i<=labelF.indexEnd; i++){
            JLabel lblColorDemos = getLabelDemos(100,100,(lbl)-> {
                        if(ShapeJ3D.fillPoint){
                            lbl.setBackground(Run.canvas3D.shape3D.colorList.get(ShapeJ3D.keyColor).get());
                            Run.canvas3D.shape3D.updateColorKey(Integer.parseInt(lbl.getName()));
                        }
            });
            lblColorDemos.setName(i+"");
            lblColorDemos.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    if(ShapeJ3D.fillPoint){
                        lblColorDemos.setCursor(ImageLoader.fillCorrectCursor);
                    }else lblColorDemos.setCursor(ImageLoader.fillIncCursor);
                }
            });
            lblColorDemos.setBackground(Run.canvas3D.shape3D.getRGBColor(i));
            panelColorS.add(lblColorDemos);
        }
        panelColorS.validate();
    }

    private Color defaultFaceColor;
    private LabelFace labelF;

    public void showFaceProps(LabelFace labelFace)
    {
        labelF = labelFace;
        addColorSections();
        closed=false;
    }

    private void northPanel(){
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panel.setOpaque(false);
        panel.setBorder(BorderFactory.createMatteBorder(0,0,2,0,Color.white));
        panel.add(AppProps.createLabelFor("X", this::close));
        add(panel,"North");
    }

    private JButton getButton(String text, Runnable handler){
        JButton btn = new JButton(text);
        btn.setBackground(AppProps.BG_GLOBAL);
        btn.addActionListener(a->handler.run());
        return btn;
    }

    public void updateProps(){
    }

    public void close(){
        closed=true;
        updateProps();
        Run.frame.getContentPane().remove(this);
        Run.frame.getContentPane().validate();
        Run.frame.getContentPane().repaint();
    }
}
