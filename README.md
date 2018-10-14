# APAW-ECP2-RobertoMartin
APAW. ECP2. Arquitecturas y Patrones Web

## Diseño de entidades
![alt text](https://raw.githubusercontent.com/robertome/APAW-ECP2-RobertoMartin/develop/docs/APAW.ECP2.Entidades.png)


# API
## POST /profesores
Crear profesor.

Parámetros del cuerpo:

    nombre: String (requerido)
    apellidos: String (requerido)

Respuesta

    200 OK
        id: String
    403 BAD_REQUEST

## POST /alumnos
Crear alumno

Parámetros del cuerpo

    nombre: String (requerido)
    apellidos: String (requerido)
    profesorId: String

Respuesta

    200 OK
        id: String
    403 BAD_REQUEST
    404 NOT_FOUND

## PUT /alumnos/\{id\}
Modificar alumno

Parámetros del cuerpo

    nombre: String (requerido)
    apellidos: String (requerido)
    profesorId: String

Respuesta

    200 OK
    403 BAD_REQUEST
    404 NOT_FOUND

## POST /alumnos/\{id\}/practicas
Crear práctica para alumno \{id\}

Parámetros del cuerpo

    nombre: String (requerido)
    asignatura: Asignatura (requerido)
    fecha: LocalDateTime (por defecto fecha Actual)
    entregada: Boolean (por defecto 'false')

Respuesta

    200 OK
        id: String
    403 BAD_REQUEST
    404 NOT_FOUND


## GET /alumnos/\{id\}/practicas
Listado con las prácticas del alummno \{id\}

Respuesta

    200 OK
        [ {id:String, nombre:String, asignatura:Asignatura, fecha:LocalDateTime, entregada:Boolean, nota:Integer } ]

## DELETE /alumnos/\{id\}
Borrar alumno. Borrará alumno y sus prácticas

Respuesta

    200 OK

## PATCH /alumnos/\{alumnoId\}/practicas/\{id\}/nota
Modificar el campo 'nota' de la práctica \{id\} para el alumno \{alumnoId\}

Parámetros del cuerpo

    nota: Integer (requerido)

Respuesta

    200 OK
    403 BAD_REQUEST
    404 NOT_FOUND

## GET /alumnos/search?q=average:>=5
Listado de alumnos con una media mayor o igual a 5.
En los datos del alumno se incluirá la media (media:Double)
Respuesta

    200 OK
        [ {id:String, nombre:String, apellidos:String, media:Double} ]
    403 BAD_REQUEST	

