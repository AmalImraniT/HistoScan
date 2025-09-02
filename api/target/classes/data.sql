-- Insertion d'un utilisateur admin par défaut
-- Mot de passe: admin123 (encodé avec BCrypt)
INSERT INTO users (username, email, password, role, created_at, last_modified) 
VALUES ('admin', 'admin@tissue.com', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVEFDi', 'ADMIN', NOW(), NOW())
ON CONFLICT (username) DO NOTHING;

-- Insertion d'un utilisateur normal pour les tests
INSERT INTO users (username, email, password, role, created_at, last_modified) 
VALUES ('user', 'user@tissue.com', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVEFDi', 'USER', NOW(), NOW())
ON CONFLICT (username) DO NOTHING;
