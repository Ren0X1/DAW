package pojos;
import java.util.*;

public class Cesta {
	private String nombreUsuario;
	private HashMap<Integer,Integer> carrito;
	
	
	public Cesta () {
		this.nombreUsuario = "indefinido";
		this.carrito = new HashMap<Integer,Integer>();
	}
	
	public Cesta (String nom) { // exists -> existencias
		this.nombreUsuario = nom;
		this.carrito = new HashMap<Integer,Integer>();
	}
	
	public String getNombreUsuario(){
		return this.nombreUsuario;
	}
	
	public void setNombreUsuario(String nombreUsuario){
		this.nombreUsuario = nombreUsuario;
	}
	
	public void setAddArticulo(int codigoArticulo){
		if (carrito.get(codigoArticulo) == null) { // primera vez
			carrito.put(codigoArticulo,1);
		} else { // resto de veces
			carrito.replace(codigoArticulo,carrito.get(codigoArticulo)+1);
		}
		
	}
	
	public HashMap<Integer,Integer> getCarrito(){
		return this.carrito;
	}
	
	public void setCarrito(HashMap<Integer,Integer> carrito){
		this.carrito = carrito;
	}
	
}