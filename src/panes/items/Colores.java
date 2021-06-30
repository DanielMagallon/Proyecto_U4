package panes.items;

import main.Run;
import panes.PanelItem;
import static_props.AppProps;

import javax.swing.*;
import java.awt.*;

public class Colores extends PanelItem
{
    public Colores() {
        super(1, 1);
        this.setBorder(BorderFactory.createEmptyBorder(15, 0, 5, 0));
        this.add(Run.canvas3D.shape3D.getPanelColor());
    }
}
