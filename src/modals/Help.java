package modals;

import static_props.AppProps;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

public class Help extends JDialog
{
    private File file;

    public Help(Frame frame, boolean b) {
        super(frame, b);
        setTitle("......:::::::AYUDA::::::......");

        file = new File(System.getProperty("user.dir")+"/Ayuda.pdf");
        JLabel lbl = new JLabel("Para la ayuda abrir PDF indexado en el correo (o click aqui)");
        lbl.setCursor(AppProps.handCursor);
        lbl.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {

                    Desktop.getDesktop().open(file);
                } catch (Exception ignored) {
                    JOptionPane.showMessageDialog(null,"Hubo un error al abrir el PDF de ayuda.\n" +
                            "Pruebe abriendo el archivo llamado Ayuda.pdf\nlocalizado en la ruta donde" +
                            " esta el jar.","Error al leer el archivo",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        lbl.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        lbl.setForeground(Color.blue);
        add(lbl);
        setResizable(false);
        pack();
        this.setLocationRelativeTo(null);
    }

}
