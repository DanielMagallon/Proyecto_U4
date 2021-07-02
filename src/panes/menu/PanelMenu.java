package panes.menu;

import main.Run;
import panes.PanelItem;
import static_props.AppProps;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PanelMenu extends JPanel
{
    private JLabel lblMenu;
    private static PanelMenu auxPanel;
    private PanelItem panelItem;

    public PanelMenu(PanelItem panelItem,String title)
    {
        this(panelItem,title,false);
    }


    public PanelMenu(PanelItem panelItem, String title, boolean selected)
    {
        this.panelItem = panelItem;
        setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.BLACK));
        setLayout(new FlowLayout(FlowLayout.LEFT));
        lblMenu = new JLabel(title);
        add(lblMenu);
        lblMenu.setCursor(AppProps.handCursor);
        lblMenu.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                updateSelection();
                Run.updatePanelItem(PanelMenu.this.panelItem);
            }
        });
        if(selected){
            updateSelection();
        }
    }

    public void updateSelection()
    {
        if(auxPanel !=null){
            auxPanel.lblMenu.setForeground(Color.black);
            auxPanel.setBackground(AppProps.BG_NOT_SELECTED);
            Run.canvas3D.requestFocus();
        }

        auxPanel = this;
        auxPanel.lblMenu.setForeground(AppProps.FG_SELECTED);
        auxPanel.setBackground(AppProps.BG_SELECTED);

    }
}
