package panes.items;

import panes.PanelItem;
import static_props.AppProps;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import static main.Run.canvas3D;
import static static_props.AppProps.*;

public class Traslacion extends  PanelItem implements ChangeListener
{
    private JSlider jTransZ,jTransX,jTransY;

    public Traslacion()
    {
        super(1,3);
        jTransX = AppProps.addSlider("Trasladar en X",this,this,
                0,200,100,10,50 );
        jTransY = AppProps.addSlider("Trasladar en Y",this,this,
                0,200,100,10,50 );
        jTransZ = AppProps.addSlider("Trasladar en Z",this,this,
                        0,200,100,10,50 );
      
    }

    @Override
    public void stateChanged(ChangeEvent changeEvent) {
        {
            if(changeEvent.getSource() == jTransX)
                canvas3D.shape3D.mover(COORD_X,convertCoord(jTransX,jTransX.getValue()>=100));

            else if(changeEvent.getSource() == jTransY)
                canvas3D.shape3D.mover(COORD_Y,convertCoord(jTransY,jTransY.getValue()>=100));

            else
                canvas3D.shape3D.mover(COORD_Z,convertCoord(jTransZ,jTransZ.getValue()>=100));
        }
    }

    private float convertCoord(JSlider js,boolean positive){
        return positive ? ((float)js.getValue()-100)/100 : -(100-(float)js.getValue())/100;
    }

    private void updateJS(JSlider js,boolean positive){
        int val = js.getValue();
        if(positive) {
            if(val==201)
                return;
        }else{
            if(val==1)
                return;
        }
        js.setValue(val + (positive ? 5 : -5));
    }

    public void updateScaleValue(int coord,boolean positive){
        switch (coord)
        {
            case COORD_X:
                    updateJS(jTransX,positive);
//                canvas3D.shape3D.scale();
                break;

            case COORD_Y:
                updateJS(jTransY,positive);
                break;

            case COORD_Z:
                updateJS(jTransZ,positive);
                break;

        }
    }

    public void reset(){
//        jTrans.setValue(abstractShape3D.getDistance());
    }
}
