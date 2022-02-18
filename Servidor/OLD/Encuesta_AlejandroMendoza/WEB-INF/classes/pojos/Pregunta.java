package pojos;
import java.util.*;

public class Pregunta {
	private int codigoPregunta;
    private String tituloPregunta;
	private String[] respuestas;
    private int[] valoresRespuestas;
    private ArrayList<Integer> puntuaciones = new ArrayList<Integer>();
	
	//SETTER
	
    public void setCodigoPregunta(int v) {
        this.codigoPregunta = v;
    }

    public void setTituloPregunta(String v) {
        this.tituloPregunta = v;
    }

    public void setRespuestas(String[] v) {
        this.respuestas = v;
    }

    public void setValoresRespuestas(int[] v) {
        this.valoresRespuestas = v;
    }

    public void setPuntuaciones(ArrayList<Integer> v) {
        this.puntuaciones = v;
    }

    //GETTER
    
	public int getCodigoPregunta() {
        return this.codigoPregunta;
    }

    public String getTituloPregunta() {
        return this.tituloPregunta;
    }

    public String[] getRespuestas() {
        return this.respuestas;
    }

    public int[] getValoresRespuestas() {
        return this.valoresRespuestas;
    }

    public ArrayList<Integer> getPuntuaciones() {
        return this.puntuaciones;
    }

    //METODOS

    public void addPuntuacion(int v) {
        this.puntuaciones.add(v);
    }

    //CONSTRUCTORES
    public Pregunta(int codigoPregunta, String tituloPregunta, String[] respuestas, int[] valoresRespuestas, ArrayList<Integer> puntuaciones) {
        this.codigoPregunta = codigoPregunta;
        this.tituloPregunta = tituloPregunta;
        this.respuestas = respuestas;
        this.valoresRespuestas = valoresRespuestas;
        this.puntuaciones = puntuaciones;
    }

    public Pregunta(int codigoPregunta, String tituloPregunta, String[] respuestas, int[] valoresRespuestas) {
        this.codigoPregunta = codigoPregunta;
        this.tituloPregunta = tituloPregunta;
        this.respuestas = respuestas;
        this.valoresRespuestas = valoresRespuestas;
    }

    public Pregunta() {
        this.codigoPregunta = -1;
        this.tituloPregunta = "NULA";
        this.respuestas = new String[]{"NULA","NULA"};
        this.valoresRespuestas = new int[]{0,0};
    }

    //TO STRING
    @Override
    public String toString() {
        return "Articulo{" +
                "codigoPregunta='" + codigoPregunta + '\'' +
                ", tituloPregunta='" + tituloPregunta +
                '}';
    }


    //EQUALS y HASHCODE

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pregunta pregunta = (Pregunta) o;
        return Objects.equals(codigoPregunta, pregunta.codigoPregunta) &&
		Objects.equals(tituloPregunta, pregunta.tituloPregunta) &&
        Objects.equals(respuestas, pregunta.respuestas) &&
        Objects.equals(valoresRespuestas, pregunta.valoresRespuestas) &&
        Objects.equals(puntuaciones, pregunta.puntuaciones);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigoPregunta, tituloPregunta, respuestas, valoresRespuestas, puntuaciones);
    }
}
