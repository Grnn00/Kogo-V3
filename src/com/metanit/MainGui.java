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


public class MainGui extends JFrame {
    //private JLabel immagineLabel;
    private JButton pulsante1, pulsante2, pulsante3,saveButton,loadButton;
    private JLabel gifLabel;


    public MainGui() {

        this.setTitle("Interfaccia GIF");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 600);
        this.setLayout(null);
        
        Container conteiner = this.getContentPane();

        JLabel immagineLabel = new JLabel();
        ImageIcon image = new ImageIcon("dnd.jpg");
        ImageIcon resimage = resizeImageIcon(image, 100, 100);
        immagineLabel.setIcon(resimage);
        immagineLabel.setBounds(10, 10,100,100);
        conteiner.add(immagineLabel);

        JLabel saldoLabel = new JLabel("SAS");
       // saldoLabel.setFont(new Font("Arial", Font.BOLD, 12));
        saldoLabel.setBounds(10,120,50,10);
        conteiner.add(saldoLabel);

        gifLabel = new JLabel("dddddvwx");
        gifLabel.setBounds(370, 20, 400, 600);
        conteiner.add(gifLabel);

        JPanel pulsantiPanel = new JPanel();
        pulsantiPanel.setBounds(0, 0, getWidth(), getHeight());
        pulsantiPanel.setLayout(null);

        pulsante1 = new JButton("Pulsante 1");
        pulsante2 = new JButton("Pulsante 2");
        pulsante3 = new JButton("Pulsante 3");
        
       // pulsante1.setPreferredSize(new Dimension(80, 20));
        pulsante1.setBounds(10,150,80,20);
        pulsante2.setBounds(100,150,80,20);
        pulsante3.setBounds(190,150,80,20);  

        pulsante1.addActionListener(new PulsanteListener());
        pulsante2.addActionListener(new PulsanteListener());
        pulsante3.addActionListener(new PulsanteListener());

        pulsantiPanel.add(pulsante1);
        pulsantiPanel.add(pulsante2);
        pulsantiPanel.add(pulsante3);
        conteiner.add(pulsantiPanel);

        JComboBox cb=new JComboBox();
        cb.setBounds(10, 180,100,30);       
        cb.addItem("1");
        cb.addItem("33");
        cb.setSelectedIndex(0);
        conteiner.add(cb);

        JPanel savePanel = new JPanel();
        savePanel.setBounds(0, 0, getWidth(), getHeight());
        savePanel.setLayout(null);

        saveButton= new JButton("Save");
        loadButton = new JButton("Load Save");
        saveButton.setBounds(10,250,100,20);
        loadButton.setBounds(10,290,100,20);
        savePanel.add(saveButton);
        savePanel.add(loadButton);
        conteiner.add(savePanel);
        
       

        this.setVisible(true);
    }

    private class PulsanteListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource().equals(pulsante1)) {
                playGIF("src/com/metanit/gif1.gif");
            } else if (e.getSource().equals(pulsante2)) {
                playGIF("src/com/metanit/gif2.gif");
            } else if (e.getSource().equals(pulsante3)) {
                //playGIF("path_to_gif3.gif");
                System.out.println("PALLE");
            }
        }
    }

    private void playGIF(String path) {
        try {
            ImageIcon gifIcon = new ImageIcon(path);
            gifLabel.setIcon(gifIcon);
            gifLabel.setVisible(true);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

        public static ImageIcon resizeImageIcon(ImageIcon originalIcon, int width, int height) {
            Image originalImage = originalIcon.getImage();
            Image resizedImage = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            return new ImageIcon(resizedImage);
        }


}