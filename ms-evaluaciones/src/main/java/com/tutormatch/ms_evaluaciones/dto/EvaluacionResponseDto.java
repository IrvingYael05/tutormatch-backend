package com.tutormatch.ms_evaluaciones.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * DTO de SALIDA con los datos completos de una evaluación.
 * Nunca se devuelve la Entity directamente en el controlador.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EvaluacionResponseDto {

    private UUID id;
    private UUID tutorId;
    private UUID alumnoId;
    private UUID sesionId;
    private Integer calificacion;
    private String comentario;
    private LocalDateTime creadoEn;
}
