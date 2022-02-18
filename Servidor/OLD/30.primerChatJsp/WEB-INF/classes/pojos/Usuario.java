package pojos;

public class Usuario {
	private String nickname;
	private String color;
	
	//SETTER
	
	public void setNickname(String v) {
		this.nickname = v;
	}

	public void setColor(String v) {
		this.color = v;
	}
	
	//GETTER
	
	public String getNickname() {
		return this.nickname;
	}

	public String getColor() {
		return this.color;
	}
	//METODOS
	
	//CONSTRUCTORES
	public Usuario(String nickname, String color) {
		this.nickname = nickname;
		this.color = color;
	}
}
