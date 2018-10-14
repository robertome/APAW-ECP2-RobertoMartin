package es.upm.miw.apaw.api.businessControllers;

import es.upm.miw.apaw.api.daos.DaoFactory;
import es.upm.miw.apaw.api.dtos.AlumnoDto;
import es.upm.miw.apaw.api.dtos.PracticaDto;
import es.upm.miw.apaw.api.entities.Alumno;
import es.upm.miw.apaw.api.entities.Practica;
import es.upm.miw.apaw.api.entities.Profesor;
import es.upm.miw.apaw.api.exceptions.NotFoundException;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

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
                .orElseThrow(() -> new NotFoundException("Profesor id: " + alumnoDto.getProfesorId()));

        alumno.setNombre(alumnoDto.getNombre());
        alumno.setApellidos(alumnoDto.getApellidos());
        alumno.setProfesor(profesor);

        DaoFactory.getFactory().getAlumnoDao().save(alumno);
    }

    public String createPractica(String alumnoId, PracticaDto practicaDto) {
        Alumno alumno = DaoFactory.getFactory().getAlumnoDao().read(alumnoId)
                .orElseThrow(() -> new NotFoundException("Alumno id: " + alumnoId));

        Practica practica = Practica.builder(practicaDto.getNombre(), practicaDto.getAsignatura()).fecha(practicaDto.getFecha()).entregada(practicaDto.isEntregada()).build();
        DaoFactory.getFactory().getPractica().save(practica);

        alumno.addPractica(practica);
        DaoFactory.getFactory().getAlumnoDao().save(alumno);

        return practica.getId();
    }

    public Set<PracticaDto> readAllPracticas(String alumnoId) {
        Alumno alumno = DaoFactory.getFactory().getAlumnoDao().read(alumnoId).orElse(null);
        return alumno == null ? new HashSet<PracticaDto>() : alumno.getPracticas().stream().map(practica -> PracticaDto.builder(practica).build()).collect(Collectors.toSet());
    }
}
