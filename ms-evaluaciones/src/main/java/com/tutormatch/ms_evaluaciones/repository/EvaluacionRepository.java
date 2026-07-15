package com.tutormatch.ms_evaluaciones.repository;

import com.tutormatch.ms_evaluaciones.entity.Evaluacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

/**
 * Repositorio JPA para la entidad Evaluacion.
 * Proporciona métodos de consulta sobre la tabla 'evaluaciones'.
 */
@Repository
public interface EvaluacionRepository extends JpaRepository<Evaluacion, UUID> {

    /**
     * Busca todas las evaluaciones recibidas por un tutor específico.
     * Útil para mostrar el historial de evaluaciones del tutor.
     *
     * @param tutorId UUID del tutor
     * @return Lista de evaluaciones para ese tutor
     */
    List<Evaluacion> findByTutorId(UUID tutorId);

    /**
     * Calcula el promedio (AVG) de calificaciones recibidas por un tutor.
     * Devuelve null si el tutor aún no tiene evaluaciones.
     *
     * @param tutorId UUID del tutor a consultar
     * @return Promedio de calificaciones como Double, o null si no hay evaluaciones
     */
    @Query("SELECT AVG(e.calificacion) FROM Evaluacion e WHERE e.tutorId = :tutorId")
    Double calcularPromedioPorTutor(@Param("tutorId") UUID tutorId);
}
