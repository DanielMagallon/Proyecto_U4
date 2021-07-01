package panes;

import LabelFace.LabelFace;
import bin.handlers.LabelHandler;
import bin.shape3d.abstracts.ShapeJ3D;
import main.Run;
import static_props.AppProps;
import static_props.ImageLoader;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FaceProperties extends JPanel
{
    private TitledBorder  titleBorder;
    public boolean closed=true;
    private JPanel panelCenter;

    public FaceProperties()
    {
        setBackground(AppProps.BG_SELECTED);
        setLayout(new BorderLayout());
        titleBorder = new TitledBorder(BorderFactory.createLineBorder(Color.yellow));
        titleBorder.setTitleJustification(TitledBorder.CENTER);
        titleBorder.setTitleColor(AppProps.FG_NORMAL_TEXT);

        setBorder(titleBorder);
        northPanel();
        panelCenter();
    }

    private void panelCenter(){
        panelCenter = new JPanel();
        panelCenter.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        panelCenter.setOpaque(false);
        panelCenter.setPreferredSize(new Dimension(300,1200));
        panelCenter.setLayout(new GridLayout(1,1,0,10));
        add(panelCenter);
        initPanelColor();
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
        JPanel panelmain = new JPanel(new BorderLayout());
        panelColorS = new JPanel(new BorderLayout());
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
