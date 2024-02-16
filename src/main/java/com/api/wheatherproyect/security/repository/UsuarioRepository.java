package com.api.wheatherproyect.security.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.wheatherproyect.security.model.entity.Usuario;
import java.util.List;


public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{
	Optional<Usuario> findByNombreUsuario(String nombreUsuario);
	List<Usuario> findByNombre(String nombre);
}
