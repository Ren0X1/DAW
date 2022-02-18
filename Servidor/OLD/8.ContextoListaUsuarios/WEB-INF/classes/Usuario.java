import java.util.*;

public class Usuario {
	private int puesto;
    private String nombreUsuario;
	private GregorianCalendar horaConex;
	
    //GETTER
    
	public int getPuesto() {
        return puesto;
    }
    
	public String getNombreUsuario() {
        return nombreUsuario;
    }
	
	public GregorianCalendar getHoraConex() {
        return horaConex;
    }
	
	//SETTER
	
    public void setPuesto(int puesto) {
        this.puesto = puesto;
    }
    
	public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }
	
    public void setHoraConex(GregorianCalendar horaConex) {
        this.horaConex = horaConex;
    }

    //CONSTRUCTORES
	
    public Usuario(int puesto, String nombreUsuario, GregorianCalendar horaConex) {
		this.puesto = puesto;
		this.nombreUsuario = nombreUsuario;
		this.horaConex = horaConex;
    }
	
    //TO STRING

    @Override
    public String toString() {
        return "Usuario{" +
                "puesto='" + puesto + '\'' +
                ", nombreUsuario=" + nombreUsuario +
                ", horaConex='" + horaConex + '\'' +
                '}';
    }


    //EQUALS y HASHCODE

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(puesto, usuario.puesto) &&
		Objects.equals(nombreUsuario, usuario.nombreUsuario) &&
		Objects.equals(horaConex, usuario.horaConex);
    }

    @Override
    public int hashCode() {
        return Objects.hash(puesto, nombreUsuario, horaConex);
    }
}