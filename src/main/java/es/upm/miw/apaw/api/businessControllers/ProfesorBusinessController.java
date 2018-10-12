package es.upm.miw.apaw.api.businessControllers;

import es.upm.miw.apaw.api.daos.DaoFactory;
import es.upm.miw.apaw.api.dtos.ProfesorDto;
import es.upm.miw.apaw.api.entities.Profesor;

public class ProfesorBusinessController {

    public String create(ProfesorDto profesorDto) {
        Profesor profesor = new Profesor(profesorDto.getNombre(), profesorDto.getApellidos());
        DaoFactory.getFactory().getProfesorDao().save(profesor);

        return profesor.getId();
    }

}
