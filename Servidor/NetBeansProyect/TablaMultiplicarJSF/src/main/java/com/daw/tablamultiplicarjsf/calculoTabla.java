package com.daw.tablamultiplicarjsf;

public class calculoTabla {
    
    private int numTabla;
    private String[][] tablaMultiplicar;

    public calculoTabla() {
        tablaMultiplicar = new String[10][3];
    }

    public int getNumTabla() {
        return numTabla;
    }

    public void setNumTabla(int numTabla) {
        this.numTabla = numTabla;
    }

    public String[][] getTablaMultiplicar() {
        return tablaMultiplicar;
    }

    public void setTablaMultiplicar(String[][] tablaMultiplicar) {
        this.tablaMultiplicar = tablaMultiplicar;
    }

    public void calcularTabla() {
        for(int x=1;x!=11;x++) {
            tablaMultiplicar[x-1][0] = numTabla+" * "+x;
            tablaMultiplicar[x-1][1] = "=";
            tablaMultiplicar[x-1][2] = ""+(numTabla*x);
        }
    }
}
