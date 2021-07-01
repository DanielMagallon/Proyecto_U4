package static_props;

import java.awt.*;
import java.io.IOException;
import java.util.Objects;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;



public class ImageLoader {

    public static ImageIcon paintDI = getImageIcon("material/paint36px.png");
    public static ImageIcon paintRI = getImageIcon("material/paint26px.png");

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
