package com.tutormatch.ms_usuarios.dto;


import java.util.UUID;

/**
 * HU-06 (Luis): fila que se muestra en el panel del Admin
 * con la lista de solicitudes pendientes de revisión.
 */
public class SolicitudTutorResponseDto {

    private UUID usuarioId;
    private String nombre;
    private String email;
    private String justificacion;
    private String estadoSolicitud;

    public SolicitudTutorResponseDto() {
    }

    public SolicitudTutorResponseDto(UUID usuarioId, String nombre, String email,
                                      String justificacion, String estadoSolicitud) {
        this.usuarioId = usuarioId;
        this.nombre = nombre;
        this.email = email;
        this.justificacion = justificacion;
        this.estadoSolicitud = estadoSolicitud;
    }

    public UUID getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(UUID usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getJustificacion() {
        return justificacion;
    }

    public void setJustificacion(String justificacion) {
        this.justificacion = justificacion;
    }

    public String getEstadoSolicitud() {
        return estadoSolicitud;
    }

    public void setEstadoSolicitud(String estadoSolicitud) {
        this.estadoSolicitud = estadoSolicitud;
    }
}