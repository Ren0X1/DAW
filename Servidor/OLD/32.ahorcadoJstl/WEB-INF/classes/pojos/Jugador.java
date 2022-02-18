package pojos;
import java.util.*;

public class Jugador{
	private String nombreUsu;
	private String passwd;
	private int puntuacion;
	private ArrayList<String> palabrasAcertadas;
	
	public Jugador(){
		puntuacion = 0;
		palabrasAcertadas = new ArrayList<String>();
	}
	
	public Jugador(String nombreUsu, String passwd, int puntuacion){
		this.nombreUsu = nombreUsu;
		this.passwd = passwd;
		this.puntuacion = puntuacion;
		palabrasAcertadas = new ArrayList<String>();
	}
	
	/*........Getter y Setters.......................................*/
	public String getNombreUsu(){
		return this.nombreUsu;
	}
	
	public void setNombreUsu(String nombreUsu){
		this.nombreUsu = nombreUsu;
	}
	
	public String getPasswd(){
		return this.passwd;
	}
	
	public void setPasswd(String passwd){
		this.passwd = passwd;
	}
	
	public int getPuntuacion(){
		return this.puntuacion;
	}
	
	public void setPuntuacion(int puntuacion){
		this.puntuacion = puntuacion;
	}

	public ArrayList<String> getPalabrasAcertadas() {
		return this.palabrasAcertadas;
	}

	public void setPalabrasAcertadas(ArrayList<String> v) {
		this.palabrasAcertadas = v;
	}
	
	
	/*...............Otros M�todos....................................*/
	public void addPuntos(int puntosNuevos){
		this.puntuacion += puntosNuevos;
	}
}