package pojos;

import java.util.*;

public class Sala {
    // ATRIBUTOS
	private String nombreSala;
    private ArrayList<Mensaje> listaMensajes;
    private HashMap<String, Usuario> usuariosConectados;

    // CONSTRUCTORES
    public Sala() {
        this.listaMensajes = new ArrayList();
        this.usuariosConectados = new HashMap();
    }

    // GETTER && SETTER
    public String getNombreSala() {
        return this.nombreSala;
    }

    public void setNombreSala(String nombreSala) {
        this.nombreSala = nombreSala;
    }

    public ArrayList<Mensaje> getListaMensajes() {
        return this.listaMensajes;
    }

    public void setListaMensajes(ArrayList<Mensaje> listaMensajes) {
        this.listaMensajes = listaMensajes;
    }

    public HashMap<String,Usuario> getUsuariosConectados() {
        return this.usuariosConectados;
    }

    public void setUsuariosConectados(HashMap<String,Usuario> usuariosConectados) {
        this.usuariosConectados = usuariosConectados;
    }
    
    // MÉTODOS VARIOS
    public void setAddMensaje(Mensaje mensaje) {
        this.listaMensajes.add(mensaje);
    }

    public void setAddUsuario(Usuario usuario) {
        this.usuariosConectados.put(usuario.getNick(), usuario);
    }

    public void setRemoveUsuario(Usuario usuario) {
        this.usuariosConectados.remove(usuario.getNick());
    }

}