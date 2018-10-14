package es.upm.miw.apaw.api.entities;

import java.time.LocalDateTime;

public class Practica {

    private String id;
    private String nombre;
    private LocalDateTime fecha = LocalDateTime.now();
    private Boolean entregada = false;
    private Integer nota;
    private Asignatura asignatura;

    public Practica() {
    }

    public static Builder builder(String nombre, Asignatura asignatura) {
        return new Builder(nombre, asignatura);
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

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public Boolean isEntregada() {
        return entregada;
    }

    public void setEntregada(Boolean entregada) {
        this.entregada = entregada;
    }

    public Integer getNota() {
        return nota;
    }

    public void setNota(Integer nota) {
        this.nota = nota;
    }

    public Asignatura getAsignatura() {
        return asignatura;
    }

    public void setAsignatura(Asignatura asignatura) {
        this.asignatura = asignatura;
    }

    @Override
    public String toString() {
        return "Practica{" +
                "id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", fecha=" + fecha +
                ", entregada=" + entregada +
                ", nota=" + nota +
                ", asignatura=" + asignatura +
                '}';
    }

    public static class Builder {

        private final Practica practica = new Practica();

        public Builder(String nombre, Asignatura asignatura) {
            assert nombre != null && !nombre.isEmpty();
            assert asignatura != null;

            practica.setNombre(nombre);
            practica.setAsignatura(asignatura);
            practica.setFecha(LocalDateTime.now());
            practica.setEntregada(false);
        }

        public Builder fecha(LocalDateTime fecha) {
            practica.setFecha(fecha);
            return this;
        }

        public Builder entregada(Boolean entregada) {
            practica.setEntregada(entregada);
            return this;
        }

        public Builder asignatura(Asignatura asignatura) {
            practica.setAsignatura(asignatura);
            return this;
        }

        public Builder nota(Integer nota) {
            practica.setNota(nota);
            return this;
        }

        public Practica build() {
            return practica;
        }
    }
}