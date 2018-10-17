package es.upm.miw.apaw.api.dtos;

import es.upm.miw.apaw.api.entities.Alumno;

public class AlumnoWithMediaDto {

    private String nombre;
    private String apellidos;
    private String profesorId;
    private Double media;

    public AlumnoWithMediaDto(Alumno alumno, Double media) {
        this.nombre = alumno.getNombre();
        this.apellidos = alumno.getApellidos();
        this.profesorId = alumno.getProfesor() != null ? alumno.getProfesor().getId() : null;
        this.media = media;
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

    public String getProfesorId() {
        return profesorId;
    }

    public void setProfesorId(String profesorId) {
        this.profesorId = profesorId;
    }

    public Double getMedia() {
        return media;
    }

    public void setMedia(Double media) {
        this.media = media;
    }

    @Override
    public String toString() {
        return "AlumnoWithMediaDto{" +
                "nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", profesorId='" + profesorId + '\'' +
                ", media=" + media +
                '}';
    }
}
