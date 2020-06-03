package TI.PR;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;


public class client
{
    public static void main(String args[]) throws IOException
    {
        JFrame frame = new JFrame();
        JPanel panel = (JPanel) frame.getContentPane();
        JLabel label = new JLabel();
        frame.setVisible(true);
        DatagramSocket socket = new DatagramSocket();
        InetAddress address = InetAddress.getLocalHost();
        byte buf[] = new byte[64000];
        DatagramPacket packet;
        while (true)
        {
            packet = new DatagramPacket(buf,buf.length,address,4445);
            socket.send(packet);
            packet = new DatagramPacket(buf,buf.length);
            socket.receive(packet);
            label.setIcon(new ImageIcon(packet.getData()));
            panel.removeAll();
            panel.add(label);
            frame.pack();
        }
    }
} 