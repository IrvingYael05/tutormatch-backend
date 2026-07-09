package com.tutormatch.ms_usuarios.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SolicitudTutorRequestDto {

    @NotBlank(message = "La justificación es obligatoria")
    @Size(min = 20, max = 1000, message = "La justificación debe tener entre 20 y 1000 caracteres")
    private String justificacion;
}