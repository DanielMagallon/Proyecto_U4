package panes;

import static_props.AppProps;

import javax.swing.*;
import java.awt.*;

public abstract class PanelItem extends JPanel
{
    public PanelItem()
    {
        setBackground(AppProps.BG_SELECTED);
        setLayout(new FlowLayout(FlowLayout.CENTER));
    }

    public PanelItem(LayoutManager layoutManager) {
        setLayout(layoutManager);
    }

    public PanelItem(int r, int c)
    {
        setBackground(AppProps.BG_SELECTED);
        setLayout(new GridLayout(r,c));
    }
}
