package model;

public class Usuario {
	
	private String nombre;
	private String apellidos;
	private String direccion;
	private String pass;
	public Usuario(String nombre, String apellidos, String direccion, String pass) {
		super();
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.direccion = direccion;
		this.pass = pass;
	}
	
	
	public Usuario(String nombre, String pass) {
		super();
		this.nombre = nombre;
		this.pass = pass;
	}


	public Usuario() {
		super();
	}
	public String getNombre() {
		return nombre;
	}
	public String getApellidos() {
		return apellidos;
	}
	public String getDireccion() {
		return direccion;
	}
	public String getPass() {
		return pass;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	@Override
	public String toString() {
		return "Usuario [nombre=" + nombre + ", apellidos=" + apellidos + ", direccion=" + direccion + ", pass=" + pass
				+ "]";
	}

	/*public void agregarNota(String asignatura, double notas) {
        asignatura.add(modulo);
        notas.add(nota);
	}*/
	
	
	

}
