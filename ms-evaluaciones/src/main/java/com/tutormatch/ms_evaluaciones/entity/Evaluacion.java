package com.tutormatch.ms_evaluaciones.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Entidad JPA que representa una evaluación que un alumno hace a un tutor
 * después de una sesión de tutoría.
 *
 * Mapeada a la tabla 'evaluaciones' en el esquema 'schema_evaluaciones'.
 * Los IDs de tutor, alumno y sesión son UUID sin FK físicas (desacoplamiento entre microservicios).
 */
@Entity
@Table(name = "evaluaciones", schema = "schema_evaluaciones")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Evaluacion {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    // ID del tutor evaluado (extraído del sistema, no FK física)
    @Column(name = "tutor_id", nullable = false)
    private UUID tutorId;

    // ID del alumno que realiza la evaluación (extraído del JWT)
    @Column(name = "alumno_id", nullable = false)
    private UUID alumnoId;

    // ID de la sesión que se está evaluando (sin FK física por desacoplamiento)
    @Column(name = "sesion_id", nullable = false)
    private UUID sesionId;

    // Calificación del 1 al 5
    @Column(name = "calificacion", nullable = false)
    private Integer calificacion;

    // Comentario opcional del alumno
    @Column(name = "comentario", columnDefinition = "TEXT")
    private String comentario;

    @Column(name = "creado_en", updatable = false)
    private LocalDateTime creadoEn;

    @PrePersist
    protected void onCreate() {
        this.creadoEn = LocalDateTime.now();
    }
}
