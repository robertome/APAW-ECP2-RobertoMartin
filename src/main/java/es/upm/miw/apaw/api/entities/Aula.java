package es.upm.miw.apaw.api.entities;

public class Aula {

    private String id;
    private Integer bloque;
    private Integer numero;
    private String descripcion;

    public Aula() {
    }

    public static Builder builder() {
        return new Builder();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getBloque() {
        return bloque;
    }

    public void setBloque(Integer bloque) {
        this.bloque = bloque;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    static class Builder {
        private final Aula aula = new Aula();

        public Builder id(String id) {
            assert id != null && !id.isEmpty();

            aula.setId(id);
            return this;
        }

        public Builder bloque(Integer bloque) {
            assert bloque != null;

            aula.setBloque(bloque);
            return this;
        }

        public Builder numero(Integer numero) {
            assert numero != null;

            aula.setNumero(numero);
            return this;
        }

        public Builder descripcion(String descripcion) {
            aula.setDescripcion(descripcion);
            return this;
        }

        public Aula build() {
            return aula;
        }
    }

}