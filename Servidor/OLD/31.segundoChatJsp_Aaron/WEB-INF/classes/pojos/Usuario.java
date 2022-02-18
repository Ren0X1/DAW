package pojos;

import java.io.*;
import java.util.*;

public class Usuario {
    // ATRIBUTOS
	private String nick;
    private String color;
    private Date horaConexion;

    // CONSTRUCTORES
    public Usuario() {
        horaConexion = new Date();
    }    

    // GETTER && SETTER
    public String getNick() {
        return this.nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getColor() {
        return this.color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Date getHoraConexion() {
        return this.horaConexion;
    }

    public void setHoraConexion(Date horaConexion) {
        this.horaConexion = horaConexion;
    }
    

}