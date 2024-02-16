package com.api.wheatherproyect.security.auth;

import java.util.Optional;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.api.wheatherproyect.security.jwt.JwtService;
import com.api.wheatherproyect.security.model.entity.Role;
import com.api.wheatherproyect.security.model.entity.Usuario;
import com.api.wheatherproyect.security.repository.UsuarioRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {
	
	private final UsuarioRepository usuarioRepository;
	private final JwtService jwtService;
	private final PasswordEncoder passwordEncoder;
	private final AuthenticationManager authenticationManager;

	public AuthResponse login(LoginRequest request) {
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getNombreUsuario(), request.getPassword()));
		UserDetails usuario = usuarioRepository.findByNombreUsuario(request.getNombreUsuario()).orElseThrow();
		String token = jwtService.getToken(usuario);
		
		return AuthResponse.builder()
				.token(token)
				.build();
	}

	public AuthResponse register(RegisterRequest request) {
		Usuario usuario = Usuario.builder()
									.nombre(request.getNombre())
									.apellido(request.getApellido())
									.country(request.getCountry())
									.nombreUsuario(request.getNombreUsuario())
									.password(passwordEncoder.encode(request.getPassword()))
									.role(Role.USER)
									.build();
		
		usuarioRepository.save(usuario);
		
		return AuthResponse.builder()
							.token(jwtService.getToken(usuario))
							.build();
		
	}
	
	public Optional<Usuario> EncontrarUsuarioPorId(int id){
		return usuarioRepository.findById(id);
	}
	
		
}
