package bin;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SaveImage
{
    public static String path = System.getProperty("user.dir")+"/myimages";

    public static boolean existImage(String name){
        File root = new File(path);

        if(root.exists())
        {
            File file = new File(path+"/"+name+".png");

            return file.exists();
        }
        root.mkdir();
        return false;
    }

    public static void saveImage(BufferedImage bf,String name)
    {
        File file = new File(path+"/"+name+".png");
        try {
            ImageIO.write(bf, "png", file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
