package panes.items;

import panes.PanelItem;
import static_props.AppProps;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import static main.Run.canvas3D;
import static static_props.AppProps.COORD_X;
import static static_props.AppProps.COORD_Y;
import static static_props.AppProps.COORD_Z;

public class Escalamiento extends  PanelItem implements ChangeListener
{
    private JSlider jScaleZ,jScaleX,jScaleY,jScaleXYZ;

    public Escalamiento()
    {
        super(1,4);
        jScaleX = AppProps.addSlider("Escalar en X",this,this,
                1,201,100,10,50 );
        jScaleY = AppProps.addSlider("Escalar en Y",this,this,
                1,201,100,10,50 );
        jScaleZ = AppProps.addSlider("Escalar en Z",this,this,
                        1,201,100,10,50 );
        jScaleXYZ = AppProps.addSlider("Escalar en XYZ",this,this,
                1,201,100,10,50 );
    }

    @Override
    public void stateChanged(ChangeEvent changeEvent) {
        {

            if(changeEvent.getSource() == jScaleXYZ)
                canvas3D.shape3D.scale((double) jScaleXYZ.getValue()/100);

            else canvas3D.shape3D.scale((double) jScaleX.getValue()/100,
                    (double) jScaleY.getValue()/100,
                    (double) jScaleZ.getValue()/100);
        }
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
                    updateJS(jScaleX,positive);
//                canvas3D.shape3D.scale();
                break;

            case COORD_Y:
                updateJS(jScaleY,positive);
                break;

            case COORD_Z:
                updateJS(jScaleZ,positive);
                break;

            default:
                updateJS(jScaleXYZ,positive);
        }
    }

    public void reset(){
//        jScale.setValue(abstractShape3D.getDistance());
    }
}
