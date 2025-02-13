package model;

import java.util.ArrayList;

public class Alumno extends Usuario {
    private String dni_alumno;
    private ArrayList<String> asignatura = new ArrayList<>();
    private ArrayList<String> notas = new ArrayList<>(); 

    public Alumno(String nombre, String apellidos, String direccion, String pass) {
        super(nombre, apellidos, direccion, pass);
    }

    public Alumno(String dni_alumno, String nombre, String apellidos, String direccion, String pass) {
        super(nombre, apellidos, direccion, pass);
        this.dni_alumno = dni_alumno;
    }


    public String getDni_alumno() {
        return dni_alumno;
    }

    public ArrayList<String> getAsignatura() {
        return asignatura;
    }

    public ArrayList<String> getNotas() {
        return notas;
    }

    public void setDni_alumno(String dni_alumno) {
        this.dni_alumno = dni_alumno;
    }

    public void setAsignatura(ArrayList<String> asignatura) {
        this.asignatura = asignatura;
    }

    public void setNotas(ArrayList<String> notas) {
        this.notas = notas; 
    }

    @Override
    public String toString() {
        return "Alumno [dni_alumno=" + dni_alumno + ", asignatura=" + asignatura + ", notas=" + notas + "]";
    }
}
