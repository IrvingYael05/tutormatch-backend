package com.tutormatch.ms_usuarios.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * Representa a un usuario hacia afuera (frontend / otros microservicios).
 * Regla del equipo: jamás devolvemos la Entity directamente, siempre este DTO.
 */
public class UsuarioResponseDto {

    private UUID id;
    private String email;
    private String nombre;
    private String estadoSolicitud;
    private List<String> roles;
    private LocalDateTime creadoEn;

    public UsuarioResponseDto() {
    }

    public UsuarioResponseDto(UUID id, String email, String nombre, String estadoSolicitud,
                               List<String> roles, LocalDateTime creadoEn) {
        this.id = id;
        this.email = email;
        this.nombre = nombre;
        this.estadoSolicitud = estadoSolicitud;
        this.roles = roles;
        this.creadoEn = creadoEn;
    }

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

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public LocalDateTime getCreadoEn() {
        return creadoEn;
    }

    public void setCreadoEn(LocalDateTime creadoEn) {
        this.creadoEn = creadoEn;
    }
}