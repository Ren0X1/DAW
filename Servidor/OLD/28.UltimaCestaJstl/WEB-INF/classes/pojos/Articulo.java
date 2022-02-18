package pojos;

public class Articulo {
	private int codigoArticulo;
	private String nombreArticulo;
	private int existenciasArticulo;
	private double precioArticulo;
	private String imagenArticulo;
	
	
	public Articulo () {
		this.codigoArticulo = -1;
		this.nombreArticulo = "indefinido";
		this.existenciasArticulo = -1;
		this.precioArticulo = -1.0;
		this.imagenArticulo = "sin imagen";
	}
	
	public Articulo (int cod, String nom, int exists, double precio) {
		this.codigoArticulo = cod;
		this.nombreArticulo = nom;
		this.existenciasArticulo = exists;
		this.precioArticulo = precio;
		this.imagenArticulo = nom ;
	}
	
	public int getCodigoArticulo(){
		return this.codigoArticulo;
	}
	
	public void setCodigoArticulo(int codigoArticulo){
		this.codigoArticulo = codigoArticulo;
	}
	
	public String getNombreArticulo(){
		return this.nombreArticulo;
	}
	
	public void setNombreArticulo(String nombreArticulo){
		this.nombreArticulo = nombreArticulo;
	}
	
	public int getExistenciasArticulo(){
		return this.existenciasArticulo;
	}
	
	public void setExistenciasArticulo(int existenciasArticulo){
		this.existenciasArticulo = existenciasArticulo;
	}
	
	public double getPrecioArticulo(){
		return this.precioArticulo;
	}
	
	public void setPrecioArticulo(double precioArticulo){
		this.precioArticulo = precioArticulo;
	}
	
	public String getImagenArticulo(){
		return this.imagenArticulo;
	}
	
	public void setImagenArticulo(String imagenArticulo){
		this.imagenArticulo = imagenArticulo;
	}
	
	//METODOS
	
	public void decrementar(int v) {
		this.existenciasArticulo=this.existenciasArticulo-v;
	}

	public void aumentar(int v) {
		this.existenciasArticulo=this.existenciasArticulo+v;
	}
}