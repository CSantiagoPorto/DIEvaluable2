package model;

public class Asignatura {
 private String id_asignatura;
 private String denominacion;
 private String horas;
 private String descripcion;
//Añadir objeto profesor en constructor
 
 //TODO--> Constructor con todo pendiente de hacer
public Asignatura() {
	super();
}
public String getId_asignatura() {
	return id_asignatura;
}
public String getDenominacion() {
	return denominacion;
}
public String getHoras() {
	return horas;
}
public String getDescripcion() {
	return descripcion;
}
public void setId_asignatura(String id_asignatura) {
	this.id_asignatura = id_asignatura;
}
public void setDenominacion(String denominacion) {
	this.denominacion = denominacion;
}
public void setHoras(String horas) {
	this.horas = horas;
}
public void setDescripcion(String descripcion) {
	this.descripcion = descripcion;
}
 
 
}
