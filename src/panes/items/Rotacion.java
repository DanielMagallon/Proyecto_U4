package panes.items;

import bin.shape3d.abstracts.ShapeJ3D;
import panes.PanelItem;
import static_props.AppProps;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


import static static_props.AppProps.COORD_X;
import static static_props.AppProps.COORD_Y;
import static static_props.AppProps.COORD_Z;
import static main.Run.*;

public class Rotacion extends PanelItem
{
    public JLabel s1,s2,s3;
    private int rotZ=5;

    public Rotacion()
    {
        super(1,3);

        s1=AppProps.createLabelFor("Rotacion en eje X",()->canvas3D.shape3D.rotacion(10, COORD_X));
        s2=AppProps.createLabelFor("Rotacion en eje Y",()->canvas3D.shape3D.rotacion(10, COORD_Y));
        s3=AppProps.createLabelFor("Rotacion en eje Z",this::updateZRot);
        add(s1);
        add(s2);
        add(s3);
    }

    public void actionKey(String coord){
        switch (coord.toLowerCase()){
            case "x":
                canvas3D.shape3D.rotacion(10, COORD_X);
                break;

            case "y":
                canvas3D.shape3D.rotacion(10, COORD_Y);
                break;

            default:
                updateZRot();
        }
    }

    private void updateZRot(){
        canvas3D.shape3D.rotacion(rotZ+=5, COORD_Z);
        if(rotZ==100)
            rotZ=5;
    }
}
