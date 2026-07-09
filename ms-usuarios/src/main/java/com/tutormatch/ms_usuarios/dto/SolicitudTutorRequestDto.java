package com.tutormatch.ms_usuarios.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * HU-05 (Brisa): body del POST que el alumno envía desde el modal
 * para solicitar el rol de Tutor.
 */
public class SolicitudTutorRequestDto {

    @NotBlank(message = "La justificación es obligatoria")
    @Size(min = 20, max = 1000, message = "La justificación debe tener entre 20 y 1000 caracteres")
    private String justificacion;

    public SolicitudTutorRequestDto() {
    }

    public SolicitudTutorRequestDto(String justificacion) {
        this.justificacion = justificacion;
    }

    public String getJustificacion() {
        return justificacion;
    }

    public void setJustificacion(String justificacion) {
        this.justificacion = justificacion;
    }
}