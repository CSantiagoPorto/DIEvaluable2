package model;

import java.util.List;

public class Profesor extends Usuario{
   
    private List<String> asignatura;
    private String dni_profesor;
	public Profesor(String dni_profesor,String nombre, String apellidos, String direccion, String pass) {
		super(nombre, apellidos, direccion, pass);
		this.dni_profesor = dni_profesor;
	}
	public Profesor(String nombre, String apellidos, String direccion, String pass) {
		super(nombre, apellidos, direccion, pass);
	}
	public List<String> getAsignatura() {
		return asignatura;
	}
	public String getDni_profesor() {
		return dni_profesor;
	}
	public void setAsignatura(List<String> asignatura) {
		this.asignatura = asignatura;
	}
	public void setDni_profesor(String dni_profesor) {
		this.dni_profesor = dni_profesor;
	}
	@Override
	public String toString() {
		return "Profesor [asignatura=" + asignatura + ", dni_profesor=" + dni_profesor + "]";
	}
    
    

 
}

