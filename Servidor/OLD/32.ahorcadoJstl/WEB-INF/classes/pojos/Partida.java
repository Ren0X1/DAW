package pojos;
import java.util.*;

public class Partida{
	private String nombreUsu;
	private int puntos;
	private int fallos;
	private int aciertos;
	private char[] miPalabra;
	private char[] palabraAcertada;
	private ArrayList<Character> letrasUsadas;
	
	public Partida(){
		this.puntos = 0;
		this.fallos = 0;
		this.aciertos = 0;
		letrasUsadas = new ArrayList<Character>();
	}
	
	/*........Getter y Setters.......................................*/
	public String getNombreUsu(){
		return this.nombreUsu;
	}
	
	public void setNombreUsu(String nombre){
		this.nombreUsu = nombre;
	}
		
	public void setPuntos(int puntos){
		this.puntos = puntos;
	}
	
	public int getPuntos(){
		return this.puntos;
	}
	
	public int getFallos(){
		return this.fallos;
	}
	
	public void setFallos(int fallos){
		this.fallos = fallos;
	}
	
	public int getAciertos(){
		return this.aciertos;
	}
	
	public void setAciertos(int aciertos){
		this.aciertos = aciertos;
	}
	
	public char[] getMiPalabra(){
		return this.miPalabra;
	}
	
	public void setMiPalabra(char[] miPalabra){
		this.miPalabra = miPalabra;
	}
	
	public char[] getPalabraAcertada(){
		return this.palabraAcertada;
	}
	
	public void setPalabraAcertada(char[] palabraAcertada){
		this.palabraAcertada = palabraAcertada;
	}
	
	public ArrayList<Character> getLetrasUsadas(){
		return this.letrasUsadas;
	}
	
	public void setLetrasUsadas(ArrayList<Character> letrasUsadas){
		this.letrasUsadas = letrasUsadas;
	}
	
	/*...............Otros M�todos....................................*/
	public void setCambiarPalabra(HashMap<Integer,String> palabras){
		String palabraElegida = palabras.get(numAleatorio(1, palabras.size()));
		this.miPalabra = palabraElegida.toCharArray();
		this.palabraAcertada = new char[miPalabra.length];
		for(int i = 0; i < palabraAcertada.length; i++){
			palabraAcertada[i] = '_';
		}
	}
	
	public void addFallo(){
		this.fallos++;
	}
	
	public void addAcierto(){
		this.aciertos++;
	}
	
	public int getAciertosMax(){
		return miPalabra.length; 
	}
	
	public void addPuntos(int numero){
		this.puntos += numero;
	}
	
	public static int numAleatorio(int min, int max){
		return (int) (Math.random()*(max-min))+min;
	}

	public void setLimpiaLetrasUs(String prueba){
		this.letrasUsadas = new ArrayList<Character>();
	}
}