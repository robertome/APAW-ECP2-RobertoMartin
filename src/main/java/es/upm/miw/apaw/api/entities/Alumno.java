package es.upm.miw.apaw.api.entities;

import java.util.HashSet;
import java.util.Set;

public class Alumno {

    private final Set<Practica> practicas = new HashSet<>();
    private String id;
    private String nombre;
    private String apellidos;
    private Profesor profesor;

    public Alumno() {
    }

    public static Builder builder() {
        return new Builder();
    }

    public Set<Practica> getPracticas() {
        return practicas;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }

    public void addPractica(Practica practica) {
        practicas.add(practica);
    }

    public Double average() {
        return practicas.stream().mapToDouble(p -> p.getNota() != null ? p.getNota() : 0).average().orElse(Double.NaN);
    }

    @Override
    public String toString() {
        return "Alumno{" +
                "id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", profesor=" + profesor +
                ", practicas=" + practicas +
                '}';
    }

    public static class Builder {
        private final Alumno alumno = new Alumno();

        public Builder nombre(String nombre) {
            assert nombre != null && !nombre.isEmpty();

            alumno.setNombre(nombre);
            return this;
        }

        public Builder apellidos(String apellidos) {
            assert apellidos != null && !apellidos.isEmpty();

            alumno.setApellidos(apellidos);
            return this;
        }

        public Builder profesor(Profesor profesor) {
            alumno.setProfesor(profesor);
            return this;
        }

        public Alumno build() {
            return alumno;
        }
    }
}