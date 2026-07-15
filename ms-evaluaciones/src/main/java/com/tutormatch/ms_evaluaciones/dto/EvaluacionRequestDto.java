package com.tutormatch.ms_evaluaciones.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * DTO de ENTRADA para que un alumno registre una evaluación a un tutor.
 *
 * El alumnoId NO viene aquí — se extrae del JWT en el controlador.
 * El tutorId y sesionId sí se envían desde el frontend.
 */
@Getter
@Setter
@NoArgsConstructor
public class EvaluacionRequestDto {

    private UUID tutorId;

    private UUID sesionId;

    // Calificación del 1 al 5
    private Integer calificacion;

    // Comentario opcional
    private String comentario;
}
