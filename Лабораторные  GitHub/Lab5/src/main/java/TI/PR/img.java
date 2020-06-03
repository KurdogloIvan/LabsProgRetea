package TI.PR;

import org.imgscalr.Scalr;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

public class img {
    public static void main(String[] arg) throws IOException, AWTException {
        BufferedImage before = new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
        ImageIO.write(before,"jpg",new File("before.jpg"));
        BufferedImage after = Scalr.resize(before, Scalr.Method.QUALITY,1024,768);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ImageIO.write(after,"jpg",bos);
        ImageIO.write(after,"jpg",new File("after.jpg"));
        byte [] arr = bos.toByteArray();
        System.out.println(arr.length);
    }

}
