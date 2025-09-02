package com.tissue.captioner.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class AuthDtos {

	public static class RegisterRequest {
		@NotBlank
		@Size(min = 3, max = 50)
		public String username;

		@NotBlank
		@Email
		public String email;

		@NotBlank
		@Size(min = 6, max = 120)
		public String password;

		/**
		 * Role attendu: "ADMIN" ou "USER" (par défaut USER si nul/invalid)
		 */
		public String role;
	}

	public static class LoginRequest {
		@NotBlank
		@Email
		public String email;

		@NotBlank
		public String password;
	}

	public static class AuthResponse {
		public String token;
		public String role;
		public String username;
		public String email;
	}
}
