package es.upm.miw.apaw.api.daos.memory;


import es.upm.miw.apaw.api.daos.AlumnoDao;
import es.upm.miw.apaw.api.daos.DaoFactory;
import es.upm.miw.apaw.api.daos.ProfesorDao;

public class DaoMemoryFactory extends DaoFactory {

    private ProfesorDao profesorDao;
    private AlumnoDao alumnoDao;

    @Override
    public ProfesorDao getProfesorDao() {
        if (profesorDao == null) {
            profesorDao = new ProfesorDaoMemory();
        }

        return profesorDao;
    }

    @Override
    public AlumnoDao getAlumnoDao() {
        if (alumnoDao == null) {
            alumnoDao = new AlumnoDaoMemory();
        }

        return alumnoDao;
    }
}
