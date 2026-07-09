package com.tutormatch.ms_usuarios.dto;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SolicitudTutorResponseDto {
    private UUID usuarioId;
    private String nombre;
    private String email;
    private String justificacion;
    private String estadoSolicitud;
}