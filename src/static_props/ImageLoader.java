package static_props;

import java.awt.*;
import java.io.IOException;
import java.util.Objects;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;



public class ImageLoader {

    public static ImageIcon escposiDI = getImageIcon("material/escposi36px.png");
    public static ImageIcon escposiRI= getImageIcon("material/escposi26px.png");

    public static ImageIcon escseleciDI = getImageIcon("material/escseleci36px.png");
    public static ImageIcon escseleciRI = getImageIcon("material/escseleci26px.png");
    
    public static ImageIcon paintDI = getImageIcon("material/paint36px.png");
    public static ImageIcon paintRI = getImageIcon("material/paint26px.png");

    public static ImageIcon reset = getImageIcon("material/restaurar36px.png");
    public static ImageIcon resetR = getImageIcon("material/restaurar26px.png");

    public static Cursor removeCursor = Toolkit.getDefaultToolkit().createCustomCursor(
                getSprite("cursors/delete.png"),new Point(0,0),"Remove"
            );
    public static Cursor fillCorrectCursor = Toolkit.getDefaultToolkit().createCustomCursor(
            getSprite("cursors/fillCorr.png"),new Point(0,0),"Remove"
    );
    public static Cursor fillIncCursor = Toolkit.getDefaultToolkit().createCustomCursor(
            getSprite("cursors/fillInc.png"),new Point(0,0),"Remove"
    );

   

  

    public static ImageIcon getImageIcon(String path)
    {
        return new ImageIcon(Objects.requireNonNull(ImageLoader.class.getResource("/rsc/" + path)));
    }
    public static Image getSprite(String path)
    {
        try {

            return ImageIO.read(Objects.requireNonNull(ImageLoader.class.getResource("/rsc/" + path)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
