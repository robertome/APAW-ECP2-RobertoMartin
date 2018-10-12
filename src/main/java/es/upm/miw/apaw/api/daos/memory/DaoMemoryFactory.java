package es.upm.miw.apaw.api.daos.memory;


import es.upm.miw.apaw.api.daos.DaoFactory;
import es.upm.miw.apaw.api.daos.ProfesorDao;

public class DaoMemoryFactory extends DaoFactory {

    private ProfesorDao profesorDao;

    @Override
    public ProfesorDao getProfesorDao() {
        if (profesorDao == null) {
            profesorDao = new ProfesorDaoMemory();
        }

        return profesorDao;
    }
}
