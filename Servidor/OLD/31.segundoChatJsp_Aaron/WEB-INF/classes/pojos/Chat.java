package pojos;

import java.io.*;
import java.util.*;

public class Chat {
    // ATRIBUTOS
	private String nombreChat;
    private HashMap<String, Sala> listaSalas;

    // CONSTRUCTORES
    public Chat() {
        listaSalas = new HashMap();
    }

    // GETTER && SETTER
    public String getNombreChat() {
        return this.nombreChat;
    }

    public void setNombreChat(String nombreChat) {
        this.nombreChat = nombreChat;
    }

    public HashMap<String,Sala> getListaSalas() {
        return this.listaSalas;
    }

    public void setListaSalas(HashMap<String,Sala> listaSalas) {
        this.listaSalas = listaSalas;
    }
    
    // MÉTODOS VARIOS
    public Sala getObtenerSala(String nombreSala) {
        return listaSalas.get(nombreSala);
    }
    // Pasar solo el nombre
    public void setAddSala(Sala sala) {
        listaSalas.put(sala.getNombreSala(), sala);
    }

    public void setCargarSalas() {
        
    }

}