package es.upm.miw.apaw.api.dtos;

import es.upm.miw.apaw.api.entities.Asignatura;
import es.upm.miw.apaw.api.entities.Practica;

import java.time.LocalDateTime;

public class PracticaDto {

    private String id;
    private String nombre;
    private LocalDateTime fecha = LocalDateTime.now();
    private Boolean entregada = false;
    private Integer nota;
    private Asignatura asignatura;

    public PracticaDto() {
    }

    public static Builder builder(String nombre, Asignatura asignatura) {
        return new Builder(nombre, asignatura);
    }

    public static Builder builder(Practica practica) {
        return new Builder(practica);
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "PracticaDto{" +
                "id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", fecha=" + fecha +
                ", entregada=" + entregada +
                ", nota=" + nota +
                ", asignatura=" + asignatura +
                '}';
    }

    public static class Builder {

        private final PracticaDto practicaDto = new PracticaDto();

        public Builder(Practica practica) {
            this(practica.getNombre(), practica.getAsignatura());
            this.fecha(practica.getFecha()).entregada(practica.isEntregada()).nota(practica.getNota());
        }

        public Builder(String nombre, Asignatura asignatura) {
            assert nombre != null && !nombre.isEmpty();
            assert asignatura != null;

            this.nombre(nombre).asignatura(asignatura).fecha(LocalDateTime.now()).entregada(false);
        }

        public Builder id(String id) {
            practicaDto.setId(id);
            return this;
        }

        public Builder nombre(String nombre) {
            practicaDto.setNombre(nombre);
            return this;
        }

        public Builder fecha(LocalDateTime fecha) {
            practicaDto.setFecha(fecha);
            return this;
        }

        public Builder entregada(Boolean entregada) {
            practicaDto.setEntregada(entregada);
            return this;
        }

        public Builder asignatura(Asignatura asignatura) {
            practicaDto.setAsignatura(asignatura);
            return this;
        }

        public Builder nota(Integer nota) {
            practicaDto.setNota(nota);
            return this;
        }

        public PracticaDto build() {
            return practicaDto;
        }
    }
}
