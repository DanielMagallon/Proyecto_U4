package panes.items;

import main.Run;
import panes.PanelItem;

import javax.swing.*;

public class Caras extends PanelItem
{
    public Caras()
    {
        super(1,1);
        this.setBorder(BorderFactory.createEmptyBorder(15,0,5,0));
        this.add(Run.canvas3D.shape3D.caraspanel());
    }
}
