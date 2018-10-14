package es.upm.miw.apaw.api.businessControllers;

import es.upm.miw.apaw.api.daos.DaoFactory;
import es.upm.miw.apaw.api.dtos.AlumnoDto;
import es.upm.miw.apaw.api.entities.Alumno;
import es.upm.miw.apaw.api.entities.Profesor;
import es.upm.miw.apaw.api.exceptions.NotFoundException;

public class AlumnoBusinessController {

    public String create(AlumnoDto alumnoDto) {
        Alumno alumno = Alumno.builder().nombre(alumnoDto.getNombre()).apellidos(alumnoDto.getApellidos()).build();
        DaoFactory.getFactory().getAlumnoDao().save(alumno);

        return alumno.getId();
    }

    public void update(String id, AlumnoDto alumnoDto) {
        Alumno alumno = DaoFactory.getFactory().getAlumnoDao().read(id)
                .orElseThrow(() -> new NotFoundException("Alumno id: " + id));
        Profesor profesor = alumnoDto.getProfesorId() == null ? null : DaoFactory.getFactory().getProfesorDao().read(alumnoDto.getProfesorId())
                .orElseThrow(() -> new NotFoundException("Pofesor id: " + alumnoDto.getProfesorId()));

        alumno.setNombre(alumnoDto.getNombre());
        alumno.setApellidos(alumnoDto.getApellidos());
        alumno.setProfesor(profesor);

        DaoFactory.getFactory().getAlumnoDao().save(alumno);
    }

}
