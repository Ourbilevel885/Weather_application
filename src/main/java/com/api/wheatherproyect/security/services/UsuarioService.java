package com.api.wheatherproyect.security.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.api.wheatherproyect.security.model.entity.Usuario;
import com.api.wheatherproyect.security.repository.UsuarioRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsuarioService{
	
	private final UsuarioRepository usuarioRepository;
	
	public Optional<Usuario> EncontrarUsuarioPorId(int id){
		return usuarioRepository.findById(id);
	}
	
	public Integer ObtenerIdUsuarioPorUsername(String username) {
		Optional<Usuario> nombreUsuario = usuarioRepository.findByNombreUsuario(username);
		
		if (!nombreUsuario.isEmpty()) {
            return nombreUsuario.get().getIdUsuario();
        } else {
            return null;
        }
	}
	
	public Usuario ObtenerUsuarioPorNombreUsuario(String nombreUsuario) {
		return usuarioRepository.findByNombreUsuario(nombreUsuario).orElse(null);
	}
	
}
