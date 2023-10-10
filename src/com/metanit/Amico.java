package com.metanit;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;
import java.io.File;
import java.io.FileReader;

public class Amico {

    private String Nome,Cognome,hobby;
    private float saldo;
    private BigDecimal n;
    Random rand = new Random();
    File file = new File("amici.txt");

    String[] hobbies = {
        "Sport",
        "Musica",
        "Cucina",
        "Lettura",
        "Arte",
        "Cinema",
        "Teatro",
        "Viaggi",
        "Giochi",
        "Tecnologia",
        "Negozi","Social","Famiglia","Amici","Altro"
    };

private static final DecimalFormat df = new DecimalFormat("0.00", new DecimalFormatSymbols(Locale.US));

    Amico(String nome, String cognome) {
        this.Nome = nome;
        this.Cognome = cognome;
        this.hobby = hobbies[rand.nextInt(hobbies.length)];
        this.n= new BigDecimal(rand.nextFloat() * 200);
        this.saldo=Float.parseFloat(df.format(this.n));  
         
    }

    Amico(String nome, String cognome, String hobby,float saldo){
        this.Nome = nome;
        this.Cognome = cognome;
        this.hobby = hobby;
        this.n= new BigDecimal(saldo);
        this.saldo=Float.parseFloat(df.format(this.n));  
        System.out.println("palle sus");
    }

    Amico() {
        Nome = "nome";
        Cognome = "cognome";
        hobby = hobbies[rand.nextInt(hobbies.length)];
        saldo = rand.nextFloat() * 200;
    }


    public float getSaldo() {
        return saldo;
    }
    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }
    public String getHobby() {
        return hobby;
    }
    public void setHobby(String h){
        this.hobby = h;
    }
    public String getCognome() {
        return Cognome;
    }
    public String getNome() {
        return Nome;
    }
    
    public void ToStampa(){
    System.out.println("Nome: "+Nome); 
    System.out.println("Cognome: "+ Cognome);  
    System.out.println("Hobby: "+hobby);
    System.out.println("Saldo: "+saldo);
    }


    // genero un numero da sotrarre dalla persona e settare il nuovo saldol il return serve solo per far avere il valore alla classe kogo
    public Float Perdita(Amico a) throws IOException {
        Random rand = new Random();
        Float f,r,s= rand.nextFloat() *50;
        r=a.getSaldo()-s;
        if(r >=0 ){
            a.setSaldo(r);
            return s;
        }
        // qua sarebbe figo se a dipendere da quanto va in negativo succedono diverse reazioni dell amico come    rissa denuncia o robe cosi
        else{
            f=a.getSaldo();
            a.setSaldo(0);
            return f;
        }
        
            
    }

    // aggiunge un amico nel apposito file 
    public void AddToFile(Amico am,Boolean condition) {
            try( BufferedReader br = new BufferedReader(new FileReader(file));
            FileWriter fil = new FileWriter("amici.txt",condition);           
            BufferedWriter bf = new BufferedWriter(fil);
            PrintWriter wr = new PrintWriter(bf);)  {
            if(!file.exists())
                file.createNewFile();

            if(br.readLine()==null)
                wr.printf("Nome\t\tCognome\t\tHobby\t\tSaldo");

            wr.printf("\n%s\t\t%s\t\t%s\t\t%.2f", am.getNome(), am.getCognome(), am.getHobby(), am.getSaldo());
            
            } catch (Exception e) {
                System.out.println("dio porco"  + e);
            }
    }

    //elimina un amico dalla lista e dal file
    public void DeleteFromFile(Amico am) throws IOException{
        File tempFile = new File("temp.txt");

            try( BufferedReader br = new BufferedReader(new FileReader(file));
            FileWriter fil = new FileWriter("temp.txt",false);           
            BufferedWriter bf = new BufferedWriter(fil);
            PrintWriter wr = new PrintWriter(bf);)  {
                if(!file.exists())
                    file.createNewFile();                   
                    String line;
                    while ((line = br.readLine()) != null) {
                        // Salta la riga di testo
                        if(line.contains(am.getCognome()) && line.contains(am.getNome())){
                            
                        }
                        else
                            wr.println(line);                      
                    }                
        }

        try( BufferedReader br = new BufferedReader(new FileReader(tempFile));
            FileWriter fil = new FileWriter("amici.txt",false);           
            BufferedWriter bf = new BufferedWriter(fil);
            PrintWriter wr = new PrintWriter(bf);){

                if(!file.exists())
                    file.createNewFile();                   
                    String line;
                    while ((line = br.readLine()) != null) {
                            wr.println(line);                      
                    }                
            }
            tempFile.delete();

    }

 
    // va a modificare il valore saldo di un certo amico e riscrive il file modificato
    // sarebbe meglio tenere conto delle modifiche fatte e tenere un file che tiene il log delle azioni fatte 
    public void Update(Amico am) throws IOException {
       
       File tempFile = new File("temp.txt");

            try( BufferedReader br = new BufferedReader(new FileReader(file));
            FileWriter fil = new FileWriter("temp.txt",false);           
            BufferedWriter bf = new BufferedWriter(fil);
            PrintWriter wr = new PrintWriter(bf);)  {
                if(!file.exists())
                    file.createNewFile();                   
                    String line;
                    while ((line = br.readLine()) != null) {
                       // Modifica la riga di testo
                       if(line.contains(am.getCognome()) && line.contains(am.getNome())){
                            // Scrivi la riga di testo modificata sul file
                            wr.printf("%s\t\t%s\t\t%s\t\t%.2f\n", am.getNome(),am.getCognome(),am.getHobby(),am.getSaldo());
                       
                    }
                    else
                        wr.println(line);   
                    }                
        }

        try( BufferedReader br = new BufferedReader(new FileReader(tempFile));
            FileWriter fil = new FileWriter("amici.txt",false);           
            BufferedWriter bf = new BufferedWriter(fil);
            PrintWriter wr = new PrintWriter(bf);){

                if(!file.exists())
                    file.createNewFile();                   
                    String line;
                    while ((line = br.readLine()) != null) {
                            wr.println(line);                      
                    }                
            }
            tempFile.delete();
    }

    public void cleanFile() throws IOException{
        try( 
            FileWriter fil = new FileWriter("amici.txt",false);){
                fil.close();
            }
    }

    public ArrayList<String> getNames() throws IOException{
        ArrayList<String> names = new ArrayList<String>();
        
        BufferedReader br = new BufferedReader(new FileReader(file));

        String line,n,c,res;
        int i=0;
            while ((line = br.readLine()) != null) {
            i++;
            String s[]= line.split("\t\t");  
           
            n = s[0];
            c = s[1];
            res=n+" "+c;
            //System.out.println("S: "+res.trim() );
            if(i!=1){
                names.add(res);
            }
                
           
                
        }
            return names;
    }


    
}
