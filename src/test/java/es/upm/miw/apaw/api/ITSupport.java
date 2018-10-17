package es.upm.miw.apaw.api;

import es.upm.miw.apaw.api.daos.DaoFactory;
import es.upm.miw.apaw.api.daos.memory.DaoMemoryFactory;
import org.junit.jupiter.api.BeforeEach;

public abstract class ITSupport {

    @BeforeEach
    public void init() {
        DaoFactory.setFactory(new DaoMemoryFactory());
    }

}
