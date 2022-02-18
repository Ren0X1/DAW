package pojos;

public class Mensaje {
	private String nickname;
	private String color;
	private String texto;
	
	//SETTER
	
	public void setNickname(String v) {
		this.nickname = v;
	}

	public void setColor(String v) {
		this.color = v;
	}
	
	public void setTexto(String v) {
		this.texto = v;
	}
	
	//GETTER
	
	public String getNickname() {
		return this.nickname;
	}

	public String getColor() {
		return this.color;
	}
	
	public String setTexto() {
		return this.texto;
	}
	//METODOS
	
	//CONSTRUCTORES
	public Mensaje(String nickname, String color, String texto) {
		this.nickname = nickname;
		this.color = color;
		this.texto = texto;
	}
}
