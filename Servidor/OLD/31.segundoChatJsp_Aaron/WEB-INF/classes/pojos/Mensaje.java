package pojos;

import java.io.*;
import java.util.*;

public class Mensaje {
    // ATRIBUTOS
    private String color;
    private String nick;
    private String texto;
    private Date hora;

    // CONSTRUCTORES
    public Mensaje() {
        hora = new Date();
    }
    
    // GETTER && SETTER
    public String getColor() {
        return this.color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getNick() {
        return this.nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getTexto() {
        return this.texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public Date getHora() {
        return this.hora;
    }

    public void setHora(Date hora) {
        this.hora = hora;
    }
    
    

    // TO STRING
    @Override
    public String toString() {
        return getNick() + ": " + getTexto();
    }

}