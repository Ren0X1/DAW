package pojos;
import java.util.*;

public class Sala {
	private String nombreSala;
	private Arraylist<Mensaje> listaMensajes;
	
	//SETTER
	
	public void setNombreSala(String v) {
		this.nickname = v;
	}

	public void setListaMensajes(Arraylist<Mensaje> v) {
		this.color = v;
	}
	
	//GETTER
	
	public String getNombreSala() {
		return this.nombreSala;
	}

	public Arraylist<Mensaje> getListaMensajes() {
		return this.listaMensajes;
	}
	
	//METODOS
	
	//CONSTRUCTORES
	public Sala(String nombreSala, Arraylist<Mensaje> listaMensajes) {
		this.nombreSala = nombreSala;
		this.listaMensajes = listaMensajes;
	}
	
	public Sala(String nombreSala) {
		this.nombreSala = nombreSala;
		this.listaMensajes = new Arraylist<Mensaje>();
	}
}
