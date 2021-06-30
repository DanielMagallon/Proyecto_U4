package modals;

import bin.SaveImage;
import static_props.AppProps;

import javax.swing.*;
import java.awt.*;

import static main.Run.bufferedImage;

public class NotifyImage extends JDialog
{

    private JTextField txtName;

    public NotifyImage(JFrame frame)
    {
        super(frame,"Guardando imagen",true);
        getContentPane().setLayout(new BorderLayout());
        getContentPane().setBackground(AppProps.BG_NOTIFY);

        JLabel lbl = new JLabel("Nombre de la imagen:");
        lbl.setForeground(AppProps.FG_NORMAL_TEXT);
        txtName = new JTextField(15);
        txtName.addActionListener(a->accept());
        JPanel panelData = new JPanel(new FlowLayout(FlowLayout.CENTER)){{
            setOpaque(false);
            add(lbl);
            add(txtName);
        }};
        getContentPane().add(panelData,"Center");
        JButton btnAcep = new JButton("Aceptar"){{
            setBackground(AppProps.BG_BTN_NOTIFY);
            setForeground(AppProps.FG_NORMAL_TEXT);
            addActionListener(a->accept());
        }};
        JButton btnCancel = new JButton("Cancelar"){{
            setBackground(AppProps.BG_BTN_NOTIFY);
            setForeground(AppProps.FG_NORMAL_TEXT);
            addActionListener(a->{
                NotifyImage.this.dispose();
            });
        }};

        JPanel btns = new JPanel(new FlowLayout(FlowLayout.CENTER)){{
            setOpaque(false);
            add(btnAcep);
            add(btnCancel);
        }};


        getContentPane().add(btns,"South");
        pack();

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width-this.getWidth()-10,dim.height-this.getHeight()-50);
    }

    private void accept(){

        if(txtName.getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(null,
                    "Ingrese un nombre valido","Nombre no valido",
                    JOptionPane.ERROR_MESSAGE);
        }

        else if(SaveImage.existImage(txtName.getText())){
            JOptionPane.showMessageDialog(null,
                    "Ingrese otro nombre","La imagen ya existe",
                    JOptionPane.ERROR_MESSAGE);
        }
        else {
            SaveImage.saveImage(bufferedImage, txtName.getText());
            NotifyImage.this.dispose();
        }
    }
}
