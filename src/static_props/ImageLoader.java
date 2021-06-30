package static_props;

import java.awt.Image;
import java.io.IOException;
import java.util.Objects;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;



public class ImageLoader {
	public static ImageIcon corazonDI = getImageIcon("material/corazon36px.png");
    public static ImageIcon corazonRI = getImageIcon("material/corazon26px.png");

    public static ImageIcon cuboDI = getImageIcon("material/cubo36px.png");
    public static ImageIcon cuboDR = getImageIcon("material/cubo26px.png");

    public static ImageIcon estructuraDI = getImageIcon("material/estructura36px.png");
    public static ImageIcon estructuraRI = getImageIcon("material/estructura26px.png");

    public static ImageIcon escposiDI = getImageIcon("material/escposi36px.png");
    public static ImageIcon escposiRI= getImageIcon("material/escposi26px.png");

    public static ImageIcon escseleciDI = getImageIcon("material/escseleci36px.png");
    public static ImageIcon escseleciRI = getImageIcon("material/escseleci26px.png");
    
    public static ImageIcon paintDI = getImageIcon("material/paint36px.png");
    public static ImageIcon paintRI = getImageIcon("material/paint26px.png");

    public static ImageIcon refX = getImageIcon("material/refx36px.png");
    public static ImageIcon refXR = getImageIcon("material/refx26px.png");

    public static ImageIcon refY = getImageIcon("material/refy36px.png");
    public static ImageIcon refYR = getImageIcon("material/refy26px.png");

    public static ImageIcon reset = getImageIcon("material/restaurar36px.png");
    public static ImageIcon resetR = getImageIcon("material/restaurar26px.png");

    public static ImageIcon refz = getImageIcon("material/refz36px.png");
    public static ImageIcon refzR = getImageIcon("material/refz26px.png");

   

  

   

  

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
