package es.upm.miw.apaw.api.businessControllers;

import es.upm.miw.apaw.api.daos.DaoFactory;
import es.upm.miw.apaw.api.dtos.AlumnoDto;
import es.upm.miw.apaw.api.entities.Alumno;

public class AlumnoBusinessController {

    public String create(AlumnoDto alumnoDto) {
        Alumno alumno = Alumno.builder().nombre(alumnoDto.getNombre()).apellidos(alumnoDto.getApellidos()).build();
        DaoFactory.getFactory().getAlumnoDao().save(alumno);

        return alumno.getId();
    }

}
