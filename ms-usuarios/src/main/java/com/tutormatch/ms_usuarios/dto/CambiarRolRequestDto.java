package com.tutormatch.ms_usuarios.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
/**
 * HU-07 (Luis): body del PUT/PATCH que envía el Admin
 * al aprobar o rechazar una solicitud de tutor.
 */
public class CambiarRolRequestDto {

    @NotBlank(message = "La decisión es obligatoria")
    @Pattern(regexp = "APROBADA|RECHAZADA", message = "La decisión debe ser APROBADA o RECHAZADA")
    private String decision;

    public CambiarRolRequestDto() {
    }

    public CambiarRolRequestDto(String decision) {
        this.decision = decision;
    }

    public String getDecision() {
        return decision;
    }

    public void setDecision(String decision) {
        this.decision = decision;
    }
}