package com.metanit;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class MainGui extends JFrame {
    //private JLabel immagineLabel;
    private JButton pulsante1, pulsante2, pulsante3,saveButton,loadButton;
    private JLabel gifLabel,saldoLabel,dateLabel,immagineLabel;
    private JComboBox cb;
    private Calendar date;
    Amico amico = new Amico();
    Kogo kogo = new Kogo();
    ArrayList<Amico> amici = new ArrayList<Amico>();
    private File saves =new File("saves.csv");
    private static final DecimalFormat df = new DecimalFormat("0.00");
    


    public MainGui() throws IOException {

        this.setTitle("Interfaccia GIF");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 600);
        this.setLayout(null);
        
        Container conteiner = this.getContentPane();

        immagineLabel = new JLabel();
        ImageIcon image = new ImageIcon("dnd.jpg");
        ImageIcon resimage = resizeImageIcon(image, 100, 100);
        immagineLabel.setIcon(resimage);
        immagineLabel.setBounds(10, 10,100,100);
        conteiner.add(immagineLabel);

    // Date set and formating
        date = Calendar.getInstance(); 
        date.set(2023,0,20); 
       //date.setFirstDayOfWeek(2);
       int month =date.get(Calendar.MONTH)+1;
        String dateStr = date.get(Calendar.DAY_OF_MONTH) + "/" + month + "/" + date.get(Calendar.YEAR);
    // Data Label config
        dateLabel = new JLabel();
        dateLabel.setText(dateStr);   
        dateLabel.setBounds(120,10,80,10);
        conteiner.add(dateLabel);
        
        
    //Saldo LAbel COnfiguration and displey  
        saldoLabel =new JLabel();    
        saldoLabel.setText(""+kogo.getSoldi()%.2f);
       // saldoLabel.setFont(new Font("Arial", Font.BOLD, 12));
        saldoLabel.setBounds(10,120,50,10);
        conteiner.add(saldoLabel);

    //Gif Label configuration 
        gifLabel = new JLabel();
        gifLabel.setText("sadfggh");
        gifLabel.setBounds(370, 20, 400, 600);
        conteiner.add(gifLabel);

        JPanel pulsantiPanel = new JPanel();
        pulsantiPanel.setBounds(0, 0, getWidth(), getHeight());
        pulsantiPanel.setLayout(null);

        pulsante1 = new JButton("Pulsante 1");
        pulsante2 = new JButton("Pulsante 2");
        pulsante3 = new JButton("Pulsante 3");
        
       
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

    //Configuration of the ComboBox
        cb = new JComboBox();
        cb.setBounds(10, 180,100,30);           
        addItems(cb);
        cb.setSelectedIndex(0);
        conteiner.add(cb);
        cb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                // Get the selected item
                int i =cb.getItemCount();
                //controlla se si seleziona aggiungi e solo in quel caso fa fare il inserimento
                if( cb.getSelectedItem().equals(cb.getItemAt(i-1)) ){
                    String nome,cognome,str=JOptionPane.showInputDialog("balls");
                    int space1 = str.indexOf(" ");
                    nome=str.substring(0, space1);
                    cognome=str.substring(space1);
                    Amico a = new Amico(nome,cognome);
                    a.AddToFile(a,true);
                    String message =a.getNome()+" "+a.getCognome()+"\n E interessato a: " + a.getHobby();
                    JOptionPane.showMessageDialog(null,message,"Amico",JOptionPane.PLAIN_MESSAGE);
                     try {
                        addItems(cb);
                        ArrayAmici();
                    } catch (IOException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }   
                    
                    repaint();
                } 
            }
        });


        JPanel savePanel = new JPanel();
        savePanel.setBounds(0, 0, getWidth(), getHeight());
        savePanel.setLayout(null);

        saveButton= new JButton("Save");
        loadButton = new JButton("Load Save");
        saveButton.setBounds(10,250,100,20);
        loadButton.setBounds(10,290,100,20);

        saveButton.addActionListener(new PulsanteListener() );
        loadButton.addActionListener(new PulsanteListener() );
        
        savePanel.add(saveButton);
        savePanel.add(loadButton);
        conteiner.add(savePanel);
        ArrayAmici();
        System.out.println(amici);

        this.setVisible(true);
        
    }
   
   

    private class PulsanteListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource().equals(pulsante1)) {
                playGIF("src/com/metanit/gif1.gif");
                // qua si chiama la funzione per rubare e per farlo devo reperire il ogetto del amico il cui nominativo e preso dal item selezionato nella combo box
                // al momento del click sull pulsasnte            
                try {
                    kogo.Rubare(kogo, amico.Perdita(FindAmico(cb.getSelectedItem().toString()) ));
                    amico.Update(FindAmico(cb.getSelectedItem().toString()) );
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                saldoLabel.setText(df.format(kogo.getSoldi())+"$");
                ChangeData(1);

            } else if (e.getSource().equals(pulsante2)) {
                playGIF("src/com/metanit/gif2.gif");
            } else if (e.getSource().equals(pulsante3)) {
                //playGIF("path_to_gif3.gif");
                System.out.println("PALLE");
            } else if(e.getSource().equals(saveButton)){
                SaveOnFIle();
                
            }
            else if(e.getSource().equals(loadButton)){
                try {
                    LoadSave();
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
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

    public void addItems(JComboBox cb) throws IOException{
        ArrayList<String> names = amico.getNames();

        cb.removeAllItems();
        for ( String nas : names ){
            //System.out.println("Nas:"+nas);
            cb.addItem((String) nas);
        }
        cb.addItem("Aggiungi");      
    }


    public void ArrayAmici() throws IOException{
        File file = new File("amici.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line;
        int i=0;
        BigDecimal n;
        amici.clear();
            while ((line = br.readLine()) != null) {
            i++;
             if(i!=1){
                String s[]= line.split("\t\t");
                Amico a = new Amico(s[0],s[1]);
                a.setHobby(s[2]);
                n= new BigDecimal(Float.parseFloat(s[3].replace(",",".")));
                //n.setScale(2, RoundingMode.HALF_UP);
                //System.out.println(n.floatValue());
                a.setSaldo(n.floatValue());
                
                amici.add(a);
            }
             
            }   
    }

    public Amico FindAmico(String s){
        Amico b= amici.get(0);
        for(Amico a :amici){
            if( s.contains(a.getCognome())){   
                b=a;
            }
        }
        return b;
    }

    public void ChangeData(int n){              
        date.add(Calendar.DAY_OF_MONTH, n);
        int month =date.get(Calendar.MONTH)+1;
        String dateStr = date.get(Calendar.DAY_OF_MONTH) + "/" + month + "/" + date.get(Calendar.YEAR);
        dateLabel.setText(dateStr);
        repaint();
    }

    public void SaveOnFIle(){
         try(
            BufferedReader br = new BufferedReader(new FileReader(saves));
            FileWriter fil = new FileWriter("saves.csv",true);           
            BufferedWriter bf = new BufferedWriter(fil);
            PrintWriter wr = new PrintWriter(bf);)  {
            if(!saves.exists()) {
                saves.createNewFile();
            }
            String save = dateLabel.getText()+",";
            save +=df.format(kogo.getSoldi()).replace(",", ".");

            for(Amico a : amici){
                save +=","+a.getNome()+",";
                save +=a.getCognome()+",";
                save +=a.getHobby()+",";
                save +=df.format(a.getSaldo()).replace(",", ".");
            }
            wr.printf(save+"\n");


        }catch (Exception e) {
        System.out.println("dio porco"  + e);
        } 
    } 


    public void LoadSave () throws IOException{
        
        JComboBox saveBox = new JComboBox();
        try(
            BufferedReader br = new BufferedReader(new FileReader(saves));){
                
            String line,date,money,ob,sc="";
            
            while ((line = br.readLine()) != null) {
                
                String s[]= line.split(",");  
                date=s[0];
                money=s[1];
                ob = s[0 ] + " " + s[1];
                
                saveBox.addItem(ob);
            }
            String g;
            amico.cleanFile();
            int d= JOptionPane.showConfirmDialog(null, saveBox, "Fav Sports",
            JOptionPane.OK_CANCEL_OPTION);
            if(d==JOptionPane.OK_OPTION) 
                sc= (String)saveBox.getSelectedItem();
            System.out.println(sc);
            
            while ((g = br.readLine()) != null) {
                System.out.println(g);
                String s[]= line.split(",");
                System.out.println(s[0]+"      "+s[1]);
                if(sc.equals(s[0]+" "+s[1])) {  
                    dateLabel.setText(s[0]);
                    kogo.setSoldi(Float.parseFloat(s[1]));

                        int i=2;
                        while(i<s.length){
                            Amico a=new Amico(s[i],s[i+1],s[i+2],Float.parseFloat(s[i+3]));
                            a.AddToFile(a, true);
                            i=i+4;
                        }
                }
            }
            repaint();
            }
    }   
    

}