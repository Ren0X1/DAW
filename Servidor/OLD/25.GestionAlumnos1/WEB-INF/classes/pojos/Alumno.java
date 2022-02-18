package pojos;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;

public class Alumno {
    private int nif;
    private String nombre;
    private String apellido1;
    private String apellido2;
    private int edad;
    private String curso;
    private String poblacion;
    private int nAsig;
    private HashMap<Integer, Double> asignaturas;
    private String cp;
    private int numHermanos;

    //SETTER

    public void setNif(int v) {
        this.nif = v;
    }

    public void setNombre(String v) {
        this.nombre = v;
    }

    public void setApellido1(String v) {
        this.apellido1 = v;
    }

    public void setApellido2(String v) {
        this.apellido2 = v;
    }

    public void setEdad(int v) {
        this.edad = v;
    }

    public void setCurso(String v) {
        this.curso = v;
    }

    public void setPoblacion(String v) {
        this.poblacion = v;
    }

    public void setNAsig(int v) {
        this.nAsig = v;
    }

    public void setAsignaturas(HashMap<Integer, Double> v) {
        this.asignaturas = v;
    }

    public void setCp(String v) {
        this.cp = v;
    }

    public void setNumHermanos(int v) {
        this.numHermanos = v;
    }

    //GETTER

    public int getNif() {
        return this.nif;
    }

    public String getNombre() {
        return this.nombre;
    }

    public String getApellido1() {
        return this.apellido1;
    }

    public String getApellido2() {
        return this.apellido2;
    }

    public int getEdad() {
        return this.edad;
    }

    public String getCurso() {
        return this.curso;
    }

    public String getPoblacion() {
        return this.poblacion;
    }

    public int getNAsig() {
        return this.nAsig;
    }

    public HashMap<Integer, Double> getAsignaturas() {
        return this.asignaturas;
    }

    public String getCp() {
        return this.cp;
    }

    public int getNumHermanos() {
        return this.numHermanos;
    }

    //METODOS

    public void ponerNota(int k, double v) {
        this.asignaturas.put(k, v);
    }

    public double verNota(int k) {
        return this.asignaturas.get(k);
    }

    public double mediaCurso() {
        double media=0;
        Iterator it = this.asignaturas.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<Integer, Double> pair = (Map.Entry<Integer, Double>)it.next();
            Object obj = pair.getKey();
            double nota = this.asignaturas.get(obj);
            media+=nota;
        }
        return media/this.asignaturas.size();
    }

    //CONSTRUCTORES

    public Alumno(int nif, String nombre, String apellido1, String apellido2, int edad, String curso, String poblacion, int nAsig, HashMap<Integer, Double> asignaturas, String cp, int numHermanos) {
        this.nif = nif;
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.edad = edad;
        this.curso = curso;
        this.poblacion = poblacion;
        this.nAsig = nAsig;
        this.asignaturas = asignaturas;
        this.cp = cp;
        this.numHermanos = numHermanos;
    }

    public Alumno() {
        this.nif = -1;
        this.nombre = "";
        this.apellido1 = "";
        this.apellido2 = "";
        this.edad = -1;
        this.curso = "";
        this.poblacion = "";
        this.nAsig = -1;
        this.asignaturas = new HashMap<Integer, Double>();
        this.cp = "";
        this.numHermanos = -1;
    }

    //TO STRING

    @java.lang.Override
    public java.lang.String toString() {
        return "Alumno{" +
                "nif=" + nif +
                ", nombre='" + nombre + '\'' +
                ", apellido1='" + apellido1 + '\'' +
                ", apellido2='" + apellido2 + '\'' +
                ", edad=" + edad +
                ", curso='" + curso + '\'' +
                ", poblacion='" + poblacion + '\'' +
                ", nAsig=" + nAsig +
                ", asignaturas=" + asignaturas +
                ", cp='" + cp + '\'' +
                ", numHermanos=" + numHermanos +
                '}';
    }


    // EQUALS & HASHCODE

    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;
        Alumno alumno = (Alumno) object;
        return nif == alumno.nif && 
        edad == alumno.edad && 
        nAsig == alumno.nAsig && 
        numHermanos == alumno.numHermanos && 
        nombre.equals(alumno.nombre) && 
        apellido1.equals(alumno.apellido1) && 
        apellido2.equals(alumno.apellido2) && 
        curso.equals(alumno.curso) && 
        poblacion.equals(alumno.poblacion) && 
        asignaturas.equals(alumno.asignaturas) && 
        cp.equals(alumno.cp);
    }

    public int hashCode() {
        return Objects.hash(super.hashCode(), nif, nombre, apellido1, apellido2, edad, curso, poblacion, nAsig, asignaturas, cp, numHermanos);
    }
}
