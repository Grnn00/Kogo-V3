package com.metanit;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.swing.ImageIcon;
import java.awt.Image;
public class MainGui extends JFrame {

    JFrame frame;
    ImageIcon homeIcon = new ImageIcon("dnd.jpg");
    Image img = homeIcon.getImage();
    Image mod = img.getScaledInstance(50,50,Image.SCALE_SMOOTH);
    ImageIcon icon = new ImageIcon(mod);
    JLabel pos = new JLabel(icon);
    JLabel lab = new JLabel("sbusbbu");


    private JButton AddButton = new JButton("Add");
    private JButton DoneButton = new JButton("Done");
    private JButton DispButton = new JButton("Display");


    public MainGui() {

        frame = new JFrame("Kogo Game");
       frame.setBounds(300,300,300,400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pos.setBounds(10,10,100,100);


        frame.add(pos);
        frame.add(lab);
       // frame.pack();
        frame.setVisible(true);




    }
}