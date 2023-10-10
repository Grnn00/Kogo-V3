package com.metanit;

public class Kogo {

    private Float soldi;
    
    Kogo(){
        soldi=Float.valueOf("0");
    }


    public Float getSoldi() {
        return soldi;
    }



    public void setSoldi(Float soldi) {
        this.soldi = soldi;
    }



    public Kogo(Float soldi) {
        this.soldi = soldi;
    }

    public void Rubare(Kogo k,Float float1) {
        k.setSoldi(k.getSoldi()+float1);
    }

}
