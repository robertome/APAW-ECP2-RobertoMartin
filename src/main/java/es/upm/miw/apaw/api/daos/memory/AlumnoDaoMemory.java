package es.upm.miw.apaw.api.daos.memory;

import es.upm.miw.apaw.api.daos.AlumnoDao;
import es.upm.miw.apaw.api.entities.Alumno;

public class AlumnoDaoMemory extends GenericDaoMemory<Alumno> implements AlumnoDao {

    @Override
    public String getId(Alumno alumno) {
        return alumno.getId();
    }

    @Override
    public void setId(Alumno alumno, String id) {
        alumno.setId(id);
    }
}
