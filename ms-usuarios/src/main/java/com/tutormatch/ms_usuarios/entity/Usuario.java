package com.tutormatch.ms_usuarios.entity;


import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "usuarios", schema = "schema_usuarios")
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

    public Usuario() {
    }

    public Usuario(UUID id, String email, String password, String nombre,
                   String estadoSolicitud, String justificacion, LocalDateTime creadoEn) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.nombre = nombre;
        this.estadoSolicitud = estadoSolicitud;
        this.justificacion = justificacion;
        this.creadoEn = creadoEn;
    }

    @PrePersist
    protected void onCreate() {
        if (creadoEn == null) {
            creadoEn = LocalDateTime.now();
        }
        if (estadoSolicitud == null) {
            estadoSolicitud = "NINGUNA";
        }
    }

    // ---- Getters y Setters ----

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEstadoSolicitud() {
        return estadoSolicitud;
    }

    public void setEstadoSolicitud(String estadoSolicitud) {
        this.estadoSolicitud = estadoSolicitud;
    }

    public String getJustificacion() {
        return justificacion;
    }

    public void setJustificacion(String justificacion) {
        this.justificacion = justificacion;
    }

    public LocalDateTime getCreadoEn() {
        return creadoEn;
    }

    public void setCreadoEn(LocalDateTime creadoEn) {
        this.creadoEn = creadoEn;
    }

    public Set<Rol> getRoles() {
        return roles;
    }

    public void setRoles(Set<Rol> roles) {
        this.roles = roles;
    }
}
