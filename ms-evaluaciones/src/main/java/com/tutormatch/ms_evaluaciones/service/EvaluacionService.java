package com.tutormatch.ms_evaluaciones.service;

import com.tutormatch.ms_evaluaciones.dto.EvaluacionRequestDto;
import com.tutormatch.ms_evaluaciones.dto.EvaluacionResponseDto;
import com.tutormatch.ms_evaluaciones.entity.Evaluacion;
import com.tutormatch.ms_evaluaciones.repository.EvaluacionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

/**
 * Capa de negocio para el módulo de Evaluaciones.
 *
 * Responsabilidades:
 * - Registrar una nueva evaluación de un alumno a un tutor.
 * - Consultar el promedio de calificaciones de un tutor.
 */
@Service
public class EvaluacionService {

    private final EvaluacionRepository evaluacionRepository;

    public EvaluacionService(EvaluacionRepository evaluacionRepository) {
        this.evaluacionRepository = evaluacionRepository;
    }

    /**
     * HU-Evaluaciones: Registra la evaluación de un alumno a un tutor.
     *
     * Validaciones de negocio:
     * 1. La calificación debe estar en el rango [1, 5].
     *
     * @param dto       Datos de la evaluación enviados por el alumno
     * @param alumnoId  UUID del alumno extraído del token JWT por el controlador
     * @return          DTO con los datos de la evaluación registrada
     */
    @Transactional
    public EvaluacionResponseDto evaluarTutor(EvaluacionRequestDto dto, UUID alumnoId) {

        // --- VALIDACIÓN: Calificación en rango [1, 5] ---
        if (dto.getCalificacion() == null || dto.getCalificacion() < 1 || dto.getCalificacion() > 5) {
            throw new IllegalArgumentException(
                "La calificación debe ser un número entre 1 y 5."
            );
        }

        // --- GUARDAR EN BASE DE DATOS ---
        Evaluacion nuevaEvaluacion = new Evaluacion();
        nuevaEvaluacion.setTutorId(dto.getTutorId());
        nuevaEvaluacion.setAlumnoId(alumnoId);
        nuevaEvaluacion.setSesionId(dto.getSesionId());
        nuevaEvaluacion.setCalificacion(dto.getCalificacion());
        nuevaEvaluacion.setComentario(dto.getComentario());

        Evaluacion guardada = evaluacionRepository.save(nuevaEvaluacion);

        // --- TRANSFORMAR Entity → DTO de Respuesta ---
        return mapToResponseDto(guardada);
    }

    /**
     * Calcula el promedio de calificaciones recibidas por un tutor.
     * Si el tutor no tiene evaluaciones, devuelve 0.0.
     *
     * @param tutorId UUID del tutor a consultar
     * @return        Promedio de calificaciones
     */
    @Transactional(readOnly = true)
    public Double obtenerPromedioPorTutor(UUID tutorId) {
        Double promedio = evaluacionRepository.calcularPromedioPorTutor(tutorId);
        return promedio != null ? promedio : 0.0;
    }

    /**
     * Convierte una Entity Evaluacion a su DTO de respuesta.
     * Método reutilizable para evitar duplicación.
     */
    private EvaluacionResponseDto mapToResponseDto(Evaluacion evaluacion) {
        return new EvaluacionResponseDto(
            evaluacion.getId(),
            evaluacion.getTutorId(),
            evaluacion.getAlumnoId(),
            evaluacion.getSesionId(),
            evaluacion.getCalificacion(),
            evaluacion.getComentario(),
            evaluacion.getCreadoEn()
        );
    }
}
