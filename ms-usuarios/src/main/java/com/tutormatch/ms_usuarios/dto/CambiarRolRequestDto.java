package com.tutormatch.ms_usuarios.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CambiarRolRequestDto {

    @NotBlank(message = "La decisión es obligatoria")
    @Pattern(regexp = "APROBADA|RECHAZADA", message = "La decisión debe ser APROBADA o RECHAZADA")
    private String decision;
}