package pojos;
import java.util.*;

public class Articulo {
	private int codigo;
    private String nombre;
	private int unidades;
    private double precio;
    private String img;
	
	//SETTER
	
    public void setCodigo(int v) {
        this.codigo = v;
    }

    public void setNombre(String v) {
        this.nombre = v;
    }

    public void setUnidades(int v) {
        this.unidades = v;
    }

    public void setPrecio(int v) {
        this.precio = v;
    }

    public void setImg(String v) {
        this.img = v;
    }

    //GETTER
    
	public int getCodigo() {
        return this.codigo;
    }

    public String getNombre() {
        return this.nombre;
    }

    public int getUnidades() {
        return this.unidades;
    }

    public double getPrecio() {
        return this.precio;
    }

    public String getImg() {
        return this.img;
    }

    public void reducir(int v) {
        this.unidades = this.unidades-v;
    }

    public void aumentar(int v) {
        this.unidades = this.unidades+v;
    }

    //CONSTRUCTORES

    public Articulo(int codigo, String nombre, int unidades, double precio, String img) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.unidades = unidades;
        this.precio = precio;
        this.img = img;
    }

    public Articulo() {
        this.codigo = -1;
        this.nombre = "";
        this.unidades = -1;
        this.precio = -1;
        this.img = "";
    }

    //TO STRING

    @Override
    public String toString() {
        return "Articulo{" +
                "codigo='" + codigo + '\'' +
                ", nombre='" + nombre + '\'' +
                ", unidades='" + unidades + '\'' +
                ", precio='" + precio + '\'' +
                ", img=" + img +
                '}';
    }


    //EQUALS y HASHCODE

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Articulo articulo = (Articulo) o;
        return Objects.equals(codigo, articulo.codigo) &&
		Objects.equals(nombre, articulo.nombre) &&
        Objects.equals(unidades, articulo.unidades) &&
        Objects.equals(precio, articulo.precio) &&
		Objects.equals(img, articulo.img);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo, nombre, unidades, precio, img);
    }
}
