package com.tissue.captioner.service;

import com.tissue.captioner.dto.AuthDtos;
import com.tissue.captioner.model.User;
import com.tissue.captioner.repository.UserRepository;
import com.tissue.captioner.security.JwtService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final JwtService jwtService;

	public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.jwtService = jwtService;
	}

	@Transactional
	public AuthDtos.AuthResponse register(AuthDtos.RegisterRequest request) {
		if (userRepository.existsByEmail(request.email) || userRepository.existsByUsername(request.username)) {
			throw new IllegalArgumentException("Email ou username déjà utilisé");
		}
		User.Role role = parseRoleOrDefault(request.role);
		User user = new User(request.username, request.email, passwordEncoder.encode(request.password), role);
		userRepository.save(user);
		return buildAuthResponse(user);
	}

	@Transactional(readOnly = true)
	public AuthDtos.AuthResponse login(AuthDtos.LoginRequest request) {
		User user = userRepository.findByEmail(request.email)
			.orElseThrow(() -> new IllegalArgumentException("Utilisateur non trouvé"));
		if (!passwordEncoder.matches(request.password, user.getPassword())) {
			throw new IllegalArgumentException("Mot de passe invalide");
		}
		return buildAuthResponse(user);
	}

	private User.Role parseRoleOrDefault(String role) {
		if (role == null) return User.Role.USER;
		try {
			User.Role parsed = User.Role.valueOf(role.toUpperCase());
			return parsed;
		} catch (Exception e) {
			return User.Role.USER;
		}
	}

	private AuthDtos.AuthResponse buildAuthResponse(User user) {
		Map<String, Object> claims = new HashMap<>();
		claims.put("role", user.getRole().name());
		claims.put("username", user.getUsername());
		String token = jwtService.generateToken(user.getEmail(), claims);
		AuthDtos.AuthResponse resp = new AuthDtos.AuthResponse();
		resp.token = token;
		resp.role = user.getRole().name();
		resp.username = user.getUsername();
		resp.email = user.getEmail();
		return resp;
	}
}
