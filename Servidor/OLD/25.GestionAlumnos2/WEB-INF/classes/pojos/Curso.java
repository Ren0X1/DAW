package pojos;
import java.util.HashMap;

public class Curso {
    private String nombreCurso;
    private HashMap<Integer,Alumno> listaAlum;

    //SETTER

    public void setNombreCurso(String v) {
        this.nombreCurso = v;
    }

    public void setListaAlum(HashMap<Integer,Alumno> v) {
        this.listaAlum = v;
    }

    //GETTER

    public String getNombreCurso() {
        return this.nombreCurso;
    }

    public HashMap<Integer,Alumno> getListaAlum() {
        return this.listaAlum;
    }

    //METODOS

    public int getNumAlum() {
        return this.listaAlum.size();
    }

    public void addAlumno(Alumno v) {
        this.listaAlum.put(v.getNif(),v);
    }

    //CONSTRUCTORES

    public Curso(String nombreCurso, HashMap<Integer,Alumno> listaAlum) {
        this.nombreCurso = nombreCurso;
        this.listaAlum = listaAlum;
    }

    public Curso() {
        this.nombreCurso = "NULL";
        this.listaAlum = new HashMap<Integer,Alumno>();
    }
}
