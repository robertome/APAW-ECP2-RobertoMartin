package es.upm.miw.apaw.api.daos.memory;

import es.upm.miw.apaw.api.daos.ProfesorDao;
import es.upm.miw.apaw.api.entities.Profesor;

public class ProfesorDaoMemory extends GenericDaoMemory<Profesor> implements ProfesorDao {

    @Override
    public String getId(Profesor profesor) {
        return profesor.getId();
    }

    @Override
    public void setId(Profesor profesor, String id) {
        profesor.setId(id);
    }
}
