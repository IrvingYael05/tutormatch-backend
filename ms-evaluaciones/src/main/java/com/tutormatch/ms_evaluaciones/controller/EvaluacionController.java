package com.tutormatch.ms_evaluaciones.controller;

import com.tutormatch.ms_evaluaciones.dto.EvaluacionRequestDto;
import com.tutormatch.ms_evaluaciones.dto.EvaluacionResponseDto;
import com.tutormatch.ms_evaluaciones.service.EvaluacionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * Controlador REST para el módulo de Evaluaciones.
 *
 * Base path: /api/evaluaciones
 *
 * Endpoints:
 *  - POST /api/evaluaciones          → Registrar evaluación (solo ROLE_ALUMNO)
 *  - GET  /api/evaluaciones/promedio/{tutorId} → Obtener promedio de un tutor (cualquier autenticado)
 */
@RestController
@RequestMapping("/api/evaluaciones")
public class EvaluacionController {

    private final EvaluacionService evaluacionService;

    public EvaluacionController(EvaluacionService evaluacionService) {
        this.evaluacionService = evaluacionService;
    }

    /**
     * POST /api/evaluaciones
     *
     * HU-Evaluaciones: Permite que un Alumno registre una evaluación a un tutor
     * después de una sesión de tutoría.
     *
     * Seguridad: Solo usuarios con rol ROLE_ALUMNO pueden llamar este endpoint.
     * El alumnoId se extrae automáticamente del token JWT (claim "sub"),
     * NO se recibe desde el body para evitar suplantación de identidad.
     *
     * @param dto  Body JSON con tutorId, sesionId, calificacion y comentario
     * @param jwt  Token JWT inyectado automáticamente por Spring Security
     * @return     201 Created con los datos de la evaluación registrada
     */
    @PostMapping
    @PreAuthorize("hasRole('ROLE_ALUMNO')")
    public ResponseEntity<EvaluacionResponseDto> evaluarTutor(
            @RequestBody EvaluacionRequestDto dto,
            @AuthenticationPrincipal Jwt jwt) {

        // Extraemos el ID del alumno del JWT (claim "sub" contiene el UUID del usuario)
        UUID alumnoId = UUID.fromString(jwt.getSubject());

        EvaluacionResponseDto evaluacionCreada = evaluacionService.evaluarTutor(dto, alumnoId);

        return ResponseEntity.status(HttpStatus.CREATED).body(evaluacionCreada);
    }

    /**
     * GET /api/evaluaciones/promedio/{tutorId}
     *
     * Devuelve el promedio de calificaciones de un tutor.
     * Accesible para cualquier usuario autenticado (alumnos, tutores, etc.).
     *
     * @param tutorId UUID del tutor a consultar
     * @return        200 OK con el promedio como Double
     */
    @GetMapping("/promedio/{tutorId}")
    public ResponseEntity<Double> obtenerPromedio(@PathVariable UUID tutorId) {
        Double promedio = evaluacionService.obtenerPromedioPorTutor(tutorId);
        return ResponseEntity.ok(promedio);
    }

    /**
     * Manejo de errores de validación de negocio.
     * Cuando el Service lanza IllegalArgumentException, devolvemos 400 Bad Request.
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleValidationError(IllegalArgumentException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
}
