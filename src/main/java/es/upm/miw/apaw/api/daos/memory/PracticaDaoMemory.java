package es.upm.miw.apaw.api.daos.memory;

import es.upm.miw.apaw.api.daos.PracticaDao;
import es.upm.miw.apaw.api.entities.Practica;

public class PracticaDaoMemory extends GenericDaoMemory<Practica> implements PracticaDao {

    @Override
    public String getId(Practica practica) {
        return practica.getId();
    }

    @Override
    public void setId(Practica practica, String id) {
        practica.setId(id);
    }
}
