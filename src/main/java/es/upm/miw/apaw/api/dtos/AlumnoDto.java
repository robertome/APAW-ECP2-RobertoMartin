package es.upm.miw.apaw.api.dtos;

public class AlumnoDto {

    private String nombre;
    private String apellidos;
    private String profesorId;

    public AlumnoDto(String nombre, String apellidos) {
        this(nombre, apellidos, null);
    }

    public AlumnoDto(String nombre, String apellidos, String profesorId) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.profesorId = profesorId;
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

    @Override
    public String toString() {
        return "AlumnoDto{" +
                "nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", profesorId='" + profesorId + '\'' +
                '}';
    }
}
