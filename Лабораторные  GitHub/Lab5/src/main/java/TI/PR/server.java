package TI.PR;
import org.imgscalr.Scalr;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class server
{

    public static void main(String[] args) throws IOException, AWTException, InterruptedException {
        InetAddress address;
        int port;
        DatagramSocket socket = new DatagramSocket(4445);
        byte [] buffer = new byte[64000];
        DatagramPacket packet = null;
        while (true)
        {
            BufferedImage image = new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            BufferedImage after = Scalr.resize(image, Scalr.Method.QUALITY,800,600);
            ImageIO.write(after, "jpg", bos );
            packet = new DatagramPacket(buffer,buffer.length);
            socket.receive(packet);
            address = packet.getAddress();
            port = packet.getPort();
            buffer = bos.toByteArray();
            packet = new DatagramPacket(buffer, buffer.length, address,port);
            socket.send(packet);
            socket.close();
        }
    }

} 
