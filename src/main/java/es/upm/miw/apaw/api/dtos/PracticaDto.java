package es.upm.miw.apaw.api.dtos;

import es.upm.miw.apaw.api.entities.Asignatura;

import java.time.LocalDateTime;

public class PracticaDto {

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

    public static class Builder {

        private final PracticaDto practicaDto = new PracticaDto();

        public Builder(String nombre, Asignatura asignatura) {
            assert nombre != null && !nombre.isEmpty();

            practicaDto.setNombre(nombre);
            practicaDto.setAsignatura(asignatura);
            practicaDto.setFecha(LocalDateTime.now());
            practicaDto.setEntregada(false);
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
