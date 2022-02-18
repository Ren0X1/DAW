package com.daw.validarformjsf.POJOS;

public class Nif {
    
    private int nif_number;
    private String nif_letter;
    
    public Nif() {
    }
    
    public Nif(int n,String l) {
        this.nif_number = n;
        this.nif_letter = l;
    }

    public int getNif_number() {
        return nif_number;
    }

    public void setNif_number(int nif_number) {
        this.nif_number = nif_number;
    }

    public String getNif_letter() {
        return nif_letter;
    }

    public void setNif_letter(String nif_letter) {
        this.nif_letter = nif_letter;
    }

    @Override
    public String toString() {
        return ""+nif_number+nif_letter;
    }
}
