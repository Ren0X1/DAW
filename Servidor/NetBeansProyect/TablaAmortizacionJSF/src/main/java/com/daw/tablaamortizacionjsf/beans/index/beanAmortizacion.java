package com.daw.tablaamortizacionjsf.beans.index;

import java.util.ArrayList;
import java.util.Locale;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

public class beanAmortizacion {
    private int inper;
    private double dcapital, dtae, dcuota;
    private String speriodicidad = "ano";
    private String idioma;
    private double[][] tablaFinal = null;
    
    public beanAmortizacion() {
        dcapital = 300000;
        dtae = 2;
        inper = 10;
    }

    public int getInper() {
        return inper;
    }

    public void setInper(int inper) {
        this.inper = inper;
    }

    public double getDcapital() {
        return dcapital;
    }

    public void setDcapital(double dcapital) {
        this.dcapital = dcapital;
    }

    public double getDtae() {
        return dtae;
    }

    public void setDtae(double dtae) {
        this.dtae = dtae;
    }

    public double getDcuota() {
        return dcuota;
    }

    public void setDcuota(double dcuota) {
        this.dcuota = dcuota;
    }

    public String getSperiodicidad() {
        return speriodicidad;
    }

    public void setSperiodicidad(String speriodicidad) {
        this.speriodicidad = speriodicidad;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public double[][] getTablaFinal() {
        return tablaFinal;
    }

    public void setTablaFinal(double[][] tablaFinal) {
        this.tablaFinal = tablaFinal;
    }
    
    public void calcular(int np) {
        if (!speriodicidad.equals("ano")) {
            dcuota = CalculoCuota(dcapital,(dtae/100)/12,inper*12);
        } else {
            dcuota = CalculoCuota(dcapital,(dtae/100)/1,inper*1);
        }
        if (tablaFinal==null) {
            tablaFinal = new double[(int)np][4];
        }
    }
    private double CalculoCuota(double cap, double i, int np) {
        return cap*i*Math.pow((1+i),np)/(Math.pow((1+i),np)-1);
    }
    
    public void pintarCuadro() {
        int np = inper;
        double capPendiente = dcapital;
        double i=(dtae/100)/1;
        if (!speriodicidad.equals("ano")) {
            np=np*12;
            i=(dtae/100)/12;
        }
        calcular(np);
        double cuota = dcuota;
        
        for (int f=0;f!=np;f++) {
            double interesRecibo = capPendiente*i;
            double capAmortizado = cuota-interesRecibo;
            capPendiente = capPendiente-capAmortizado;
            tablaFinal[f][0] = interesRecibo;
            tablaFinal[f][1] = capAmortizado;
            tablaFinal[f][2] = capPendiente;
            tablaFinal[f][3] = f+1;
        }
    }
    
    public ArrayList getListaIdiomas() {
        ArrayList lista = new ArrayList();
        lista.add(new SelectItem("es", "Español"));
        lista.add(new SelectItem("en_GB", "Inglés GB"));
        lista.add(new SelectItem("en_US", "Inglés US"));
        return lista;
    }
    
    public void cambioIdioma(ValueChangeEvent evento) {
        FacesContext contexto = FacesContext.getCurrentInstance();
        if (evento.getNewValue().equals("en")) {
            contexto.getViewRoot().setLocale(Locale.ENGLISH);
        } else if(evento.getNewValue().equals("en_GB")) {
            contexto.getViewRoot().setLocale(Locale.UK);
        } else if(evento.getNewValue().equals("en_US")) {
            contexto.getViewRoot().setLocale(Locale.US);
        } else {
            contexto.getViewRoot().setLocale(Locale.getDefault());
        }
    }
}
