package com.metanit;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.swing.ImageIcon;
import java.awt.Image;


public class MainGui extends JFrame {

    JFrame frame;
   /* ImageIcon homeIcon = new ImageIcon("dnd.jpg");
    Image img = homeIcon.getImage();
    Image mod = img.getScaledInstance(50,50,Image.SCALE_SMOOTH);
    ImageIcon icon = new ImageIcon(mod);
    JLabel pos = new JLabel(icon);
    JLabel lab = new JLabel("sbusbbu");*/


    private JButton AddButton = new JButton("Add");
    private JButton DoneButton = new JButton("Done");
    private JButton DispButton = new JButton("Display");


    public MainGui() {

        frame = new JFrame("Kogo Game");
       frame.setBounds(300,300,400,400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //pos.setBounds(10,10,100,100);

        JInternalFrame frame1 = new JInternalFrame("Frame 1", false, false, true, true);
        frame1.setSize(200, 150);
        frame1.setLocation(0, 0);
        frame1.setVisible(true);

        try {
            BufferedImage image = ImageIO.read(new File("dnd.jpg"));
            Image mod = image.getScaledInstance(100,100,Image.SCALE_SMOOTH);
            JLabel label = new JLabel(new ImageIcon(mod));
            label.setBounds(10,10,100,100);

            frame1.add(label, BorderLayout.PAGE_START);
        } catch (IOException e) {
            e.printStackTrace();
        }
        JInternalFrame frame2 = new JInternalFrame("Frame 2", false, false, true, true);
        frame2.setSize(200, 150);
        frame2.setLocation(200, 0);
        frame2.setVisible(true);

        add(frame1);
        add(frame2);

        //frame.add(pos);
       // frame.add(lab);
       // frame.pack();
       // frame.setVisible(true);




    }
}