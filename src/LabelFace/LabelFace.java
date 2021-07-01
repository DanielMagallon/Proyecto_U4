package LabelFace;

import bin.handlers.LabelHandler;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static static_props.AppProps.*;

public class LabelFace extends JLabel
{
    public int indexStart,indexEnd;
    public static LabelFace labelAux;

    public LabelFace(String text, LabelHandler handler,int start, int end) {
        super(text);
        indexEnd = end;
        indexStart = start;
        setCursor(handCursor);
        setHorizontalAlignment(JLabel.CENTER);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent mouseEvent) {

                if(labelAux!=null)
                    labelAux.setForeground(FG_NORMAL_TEXT);

                LabelFace.this.setForeground(BG_CONTORNO);
                labelAux=LabelFace.this;

                handler.labelCallback(LabelFace.this);
            }
        });
        setForeground(FG_NORMAL_TEXT);
    }

    @Override
    public String toString() {
        return String.format("From %d to %d\n",indexStart,indexEnd);
    }
}
