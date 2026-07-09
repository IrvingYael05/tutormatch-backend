package com.tutormatch.ms_usuarios.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "usuarios", schema = "schema_usuarios")
@Getter
@Setter
@NoArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, unique = true, length = 255)
    private String email;

    @Column(nullable = false, length = 255)
    private String password;

    @Column(nullable = false, length = 100)
    private String nombre;

    /**
     * Valores posibles: NINGUNA, PENDIENTE, APROBADA, RECHAZADA
     * Ver sql/alter_usuarios_solicitud_tutor.sql
     */
    @Column(name = "estado_solicitud", nullable = false, length = 50)
    private String estadoSolicitud = "NINGUNA";

    @Column(columnDefinition = "TEXT")
    private String justificacion;

    @Column(name = "creado_en", updatable = false)
    private LocalDateTime creadoEn;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "usuario_roles",
        schema = "schema_usuarios",
        joinColumns = @JoinColumn(name = "usuario_id"),
        inverseJoinColumns = @JoinColumn(name = "rol_id")
    )
    private Set<Rol> roles = new HashSet<>();

    @PrePersist
    protected void onCreate() {
        if (creadoEn == null) {
            creadoEn = LocalDateTime.now();
        }
        if (estadoSolicitud == null) {
            estadoSolicitud = "NINGUNA";
        }
    }
}