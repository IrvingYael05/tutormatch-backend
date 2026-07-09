package com.tutormatch.ms_usuarios.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tutormatch.ms_usuarios.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {

    // Usado por el Auth Server para login (buscar por email)
    Optional<Usuario> findByEmail(String email);

    boolean existsByEmail(String email);

    // HU-06 (Luis): listar solicitudes pendientes en el panel de admin
    List<Usuario> findByEstadoSolicitud(String estadoSolicitud);
}