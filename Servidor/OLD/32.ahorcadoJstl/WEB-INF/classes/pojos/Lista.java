package pojos;
import java.util.*;

public class Lista{
	private HashMap<Integer,String> palabras;
	private HashMap<String,Jugador> jugadores;
	private static int numPalabra = 1;
	
	private final char[] abc = {'a','b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q',
	'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
	
	public Lista(){
		palabras = new HashMap<Integer,String>();
		jugadores = new HashMap<String,Jugador>();
	}
	
	public Lista(HashMap<Integer,String> palabras, HashMap<String,Jugador> jugadores){
		this.palabras = palabras;
		this.jugadores = jugadores;
	}
	
	/*........Getter y Setters.......................................*/
	public char[] getAbc(){
		return this.abc;
	}
	
	public HashMap<Integer,String> getPalabras(){
		return this.palabras;
	}
	
	public void setPalabras(HashMap<Integer,String> palabras){
		this.palabras = palabras;
	}
	
	public HashMap<String,Jugador> getJugadores(){
		return this.jugadores;
	}
	
	public void setJugadores(HashMap<String,Jugador> jugadores){
		this.jugadores = jugadores;
	}
	
	/*...............Otros M�todos....................................*/
	public Jugador obtenerJugador(String nick){
		return jugadores.get(nick);
	}
	
	public void setAddJugador(Jugador jug){
		jugadores.put(jug.getNombreUsu(), jug);
	}
	
	public void setCargarPalabras(String palabrasXml){
		String[] palabrasString = palabrasXml.split(",");
		
		for(String pal : palabrasString){
			palabras.put(numPalabra, pal);
			numPalabra++;
		}
	}
}