package static_props;

import LabelFace.LabelFace;
import bin.handlers.LabelHandler;
import panes.PanelItem;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;

public class AppProps
{
    public static final int COORD_X =0, COORD_Y =1, COORD_Z =2;

    public static Cursor handCursor = new Cursor(Cursor.HAND_CURSOR);
    public static Cursor areaSelect = new Cursor(Cursor.CROSSHAIR_CURSOR);

    public static Color BG_GLOBAL = new Color(0xD2D0D0);
    public static Color BG_SELECTED = new Color(0x382B2B);
    public static Color FG_SELECTED = new Color(0xFFFFFF);
    public static Color BG_CONTORNO = new Color(0xF83A00);
    public static Color BG_NOT_SELECTED = null;

    public static Color CANVAS_BG = new Color(0x382B2B);
    public static Color CANVAS_STROKE = new Color(0x000000);


    public static Color BG_NOTIFY = new Color(0x292963);
    public static Color BG_BTN_NOTIFY = new Color(0x3C3CCD);

    public static Color FG_NORMAL_TEXT = new Color(0xFFFFFF);

    public static JLabel createLabelFor(String text, LabelHandler handler)
    {
        JLabel lbl = new JLabel(text);
        lbl.setCursor(handCursor);
        lbl.setHorizontalAlignment(JLabel.CENTER);
        lbl.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent mouseEvent) {
                handler.labelCallback(
                        (JLabel) mouseEvent.getComponent()
                );
            }
        });
        lbl.setForeground(FG_NORMAL_TEXT);
        return lbl;
    }

    public static JLabel createLabelFor(String text, Runnable handler)
    {
        JLabel lbl = new JLabel(text);
        lbl.setCursor(handCursor);
        lbl.setHorizontalAlignment(JLabel.CENTER);
        lbl.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent mouseEvent) {
                handler.run();
            }
        });
        lbl.setForeground(FG_NORMAL_TEXT);
        return lbl;
    }

    public static void setActionPanel(String KEY,JPanel panel,int caracter,int accel,Runnable handler){
        Action event = new AbstractAction(KEY) {
            @Override
            public void actionPerformed(ActionEvent e) {
                handler.run();
            }
        };
        panel.getInputMap().put(
                KeyStroke.getKeyStroke(caracter,accel), KEY);
        panel.getActionMap().put(KEY, event);
    }

    public static JSlider addSlider(String title, ChangeListener handler, JPanel p, int min, int max, int val,
                                    int minorTickS, int majorTickS)
    {
        JPanel ps1=new JPanel(new GridLayout(1,1));
        ps1.setOpaque(false);
        TitledBorder tb1=new TitledBorder(title);
        tb1.setBorder(BorderFactory.createLineBorder(AppProps.BG_CONTORNO));
        tb1.setTitleColor(AppProps.FG_SELECTED);
        tb1.setTitleJustification(TitledBorder.CENTER);
        ps1.setBorder(tb1);
        JSlider S1=new JSlider(JSlider.HORIZONTAL,min,max,val);
        S1.setForeground(AppProps.FG_SELECTED);
        S1.setOpaque(false);
        S1.setMinorTickSpacing(minorTickS);
        S1.setMajorTickSpacing(majorTickS);
        S1.setPaintLabels(true);
        S1.setPaintTicks(true);
        S1.addChangeListener(handler);
        ps1.add(S1);

        p.add(ps1);

        return S1;
    }
}
