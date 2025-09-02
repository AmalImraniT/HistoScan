# 🚀 Backend Admin HistoScan - Guide d'Utilisation

## 📋 **Vue d'ensemble**

Ce guide explique comment utiliser le backend admin de HistoScan, qui fournit des API REST complètes pour la gestion des utilisateurs, organes, images, statistiques et préférences système.

## 🚀 **Démarrage Rapide**

### **1. Prérequis**
- ✅ Java 17 ou supérieur
- ✅ PostgreSQL 12 ou supérieur
- ✅ Maven 3.6 ou supérieur

### **2. Configuration de la Base de Données**
```bash
# Créer la base de données
createdb tissue_db

# Ou avec psql
psql -U postgres
CREATE DATABASE tissue_db;
\q
```

### **3. Configuration de l'Application**
```bash
# Le fichier application.yml est déjà configuré avec l'initialisation automatique
# Aucune configuration supplémentaire n'est nécessaire
```

### **4. Démarrage de l'Application**
```bash
# Compiler et démarrer
mvn spring-boot:run

# Ou avec Maven wrapper
./mvnw spring-boot:run
```

## 🔐 **Authentification**

### **Connexion Admin**
```bash
# Endpoint de connexion
POST /api/auth/login

# Corps de la requête
{
  "email": "admin@histoscan.com",
  "password": "password"
}

# Réponse
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "user": {
    "id": 1,
    "username": "admin",
    "email": "admin@histoscan.com",
    "role": "ADMIN"
  }
}
```

### **Utilisation du Token**
```bash
# Ajouter le header Authorization à toutes les requêtes admin
Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...
```

## 📊 **API Endpoints**

### **👥 Gestion des Utilisateurs**

#### **Récupérer tous les utilisateurs**
```bash
GET /api/admin/users
Authorization: Bearer {token}
```

#### **Créer un nouvel utilisateur**
```bash
POST /api/admin/users
Authorization: Bearer {token}
Content-Type: application/json

{
  "username": "nouveau.etudiant",
  "email": "nouveau@example.com",
  "password": "motdepasse123",
  "role": "USER"
}
```

#### **Mettre à jour un utilisateur**
```bash
PUT /api/admin/users/{id}
Authorization: Bearer {token}
Content-Type: application/json

{
  "username": "nouveau.nom",
  "email": "nouveau@example.com",
  "role": "USER"
}
```

#### **Supprimer un utilisateur**
```bash
DELETE /api/admin/users/{id}
Authorization: Bearer {token}
```

#### **Rechercher des utilisateurs**
```bash
GET /api/admin/users/search?q=john
Authorization: Bearer {token}
```

#### **Filtrer par rôle**
```bash
GET /api/admin/users/role/USER
Authorization: Bearer {token}
```

#### **Statistiques utilisateurs**
```bash
GET /api/admin/users/statistics
Authorization: Bearer {token}
```

### **🗃️ Gestion des Organes**

#### **Récupérer tous les organes**
```bash
GET /api/admin/organs
Authorization: Bearer {token}
```

#### **Créer un nouvel organe**
```bash
POST /api/admin/organs
Authorization: Bearer {token}
Content-Type: application/json

{
  "name": "Rate",
  "description": "Organe lymphoïde du système immunitaire",
  "category": "Immunitaire",
  "priority": "MEDIUM",
  "status": "DRAFT"
}
```

#### **Mettre à jour un organe**
```bash
PUT /api/admin/organs/{id}
Authorization: Bearer {token}
Content-Type: application/json

{
  "name": "Rate Modifiée",
  "description": "Description mise à jour",
  "status": "PUBLISHED"
}
```

#### **Filtrage avancé**
```bash
GET /api/admin/organs/filters?status=PUBLISHED&category=Digestif&priority=HIGH
Authorization: Bearer {token}
```

#### **Statistiques organes**
```bash
GET /api/admin/organs/statistics
Authorization: Bearer {token}
```

### **🖼️ Gestion des Images**

#### **Récupérer toutes les images**
```bash
GET /api/admin/images
Authorization: Bearer {token}
```

#### **Uploader une nouvelle image**
```bash
POST /api/admin/images
Authorization: Bearer {token}
Content-Type: multipart/form-data

file: [fichier image]
title: "Titre de l'image"
description: "Description de l'image"
category: "Histologie"
organType: "Cœur"
tags: "cœur,normal,histologie"
uploadedById: 1
```

#### **Mettre à jour une image**
```bash
PUT /api/admin/images/{id}
Authorization: Bearer {token}
Content-Type: application/json

{
  "title": "Nouveau titre",
  "description": "Nouvelle description",
  "status": "APPROVED"
}
```

#### **Filtrage des images**
```bash
GET /api/admin/images/filters?status=APPROVED&category=Histologie&organType=Cœur
Authorization: Bearer {token}
```

#### **Statistiques images**
```bash
GET /api/admin/images/statistics
Authorization: Bearer {token}
```

### **📊 Statistiques Système**

#### **Statistiques du tableau de bord**
```bash
GET /api/admin/statistics/dashboard
Authorization: Bearer {token}
```

#### **Top utilisateurs**
```bash
GET /api/admin/statistics/top-users?limit=10
Authorization: Bearer {token}
```

#### **Tendances mensuelles**
```bash
GET /api/admin/statistics/monthly-trends
Authorization: Bearer {token}
```

#### **Métriques système**
```bash
GET /api/admin/statistics/system-metrics
Authorization: Bearer {token}
```

### **⚙️ Préférences Système**

#### **Récupérer toutes les préférences**
```bash
GET /api/admin/preferences
Authorization: Bearer {token}
```

#### **Récupérer une préférence spécifique**
```bash
GET /api/admin/preferences/theme
Authorization: Bearer {token}
```

#### **Définir une préférence**
```bash
POST /api/admin/preferences/theme
Authorization: Bearer {token}
Content-Type: application/json

{
  "value": "dark",
  "description": "Thème sombre de l'interface",
  "category": "interface"
}
```

#### **Définir plusieurs préférences**
```bash
POST /api/admin/preferences/batch
Authorization: Bearer {token}
Content-Type: application/json

{
  "theme": "dark",
  "language": "en",
  "timezone": "UTC"
}
```

#### **Initialiser les préférences par défaut**
```bash
POST /api/admin/preferences/initialize
Authorization: Bearer {token}
```

## 🧪 **Tests et Validation**

### **Test avec cURL**

#### **Connexion admin**
```bash
curl -X POST http://localhost:8000/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{
    "email": "admin@histoscan.com",
    "password": "password"
  }'
```

#### **Récupérer tous les utilisateurs**
```bash
curl -X GET http://localhost:8000/api/admin/users \
  -H "Authorization: Bearer {TOKEN_RECUPERE}"
```

#### **Créer un nouvel organe**
```bash
curl -X POST http://localhost:8000/api/admin/organs \
  -H "Authorization: Bearer {TOKEN_RECUPERE}" \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Test Organ",
    "description": "Organe de test",
    "category": "Test",
    "priority": "LOW",
    "status": "DRAFT"
  }'
```

### **Test avec Postman**

1. **Collection Postman** : Créer une collection "HistoScan Admin"
2. **Variables** : Définir `baseUrl` et `token`
3. **Tests** : Ajouter des tests automatiques pour chaque endpoint

## 🔍 **Débogage et Logs**

### **Activer les logs détaillés**
```yaml
# application-dev.yml
logging:
  level:
    com.tissue.captioner: DEBUG
    org.springframework.security: DEBUG
    org.hibernate.SQL: DEBUG
```

### **Logs utiles**
- **SQL** : Voir les requêtes générées par Hibernate
- **Security** : Voir les décisions d'authentification/autorisation
- **Application** : Voir les opérations métier

## 🚨 **Gestion des Erreurs**

### **Codes de Réponse HTTP**
- **200** : Succès
- **201** : Créé avec succès
- **400** : Requête invalide
- **401** : Non authentifié
- **403** : Non autorisé (rôle insuffisant)
- **404** : Ressource non trouvée
- **500** : Erreur serveur interne

### **Format des Erreurs**
```json
{
  "timestamp": "2024-01-15T10:30:00",
  "status": 400,
  "error": "Bad Request",
  "message": "Validation failed",
  "details": ["Le nom d'utilisateur est requis"]
}
```

## 🔧 **Configuration Avancée**

### **Variables d'Environnement**
```bash
# Base de données
export DB_URL=jdbc:postgresql://localhost:5432/tissue_db
export DB_USERNAME=postgres
export DB_PASSWORD=postgres

# JWT
export JWT_SECRET=your-secret-key
export JWT_EXPIRATION=86400000

# Upload
export UPLOAD_MAX_SIZE=10485760
export UPLOAD_PATH=uploads/
```

### **Configuration de Production**
```yaml
# Pour la production, modifier application.yml
spring:
  jpa:
    hibernate:
      ddl-auto: validate  # Ne pas modifier le schéma
  sql:
    init:
      mode: never  # Ne pas charger data.sql

logging:
  level:
    com.tissue.captioner: INFO
    org.springframework.security: WARN
```

## 📚 **Exemples d'Intégration Frontend**

### **Service TypeScript**
```typescript
class AdminService {
  private baseUrl = '/api/admin';
  private token: string;

  constructor(token: string) {
    this.token = token;
  }

  private getHeaders() {
    return {
      'Authorization': `Bearer ${this.token}`,
      'Content-Type': 'application/json'
    };
  }

  async getUsers(): Promise<UserDto[]> {
    const response = await fetch(`${this.baseUrl}/users`, {
      headers: this.getHeaders()
    });
    
    if (!response.ok) {
      throw new Error('Erreur lors de la récupération des utilisateurs');
    }
    
    return response.json();
  }

  async createUser(user: CreateUserRequest): Promise<UserDto> {
    const response = await fetch(`${this.baseUrl}/users`, {
      method: 'POST',
      headers: this.getHeaders(),
      body: JSON.stringify(user)
    });
    
    if (!response.ok) {
      throw new Error('Erreur lors de la création de l\'utilisateur');
    }
    
    return response.json();
  }
}
```

### **Hook React**
```typescript
import { useState, useEffect } from 'react';

export const useAdminUsers = (token: string) => {
  const [users, setUsers] = useState<UserDto[]>([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState<string | null>(null);

  useEffect(() => {
    const fetchUsers = async () => {
      try {
        setLoading(true);
        const response = await fetch('/api/admin/users', {
          headers: {
            'Authorization': `Bearer ${token}`
          }
        });
        
        if (!response.ok) {
          throw new Error('Erreur lors de la récupération des utilisateurs');
        }
        
        const data = await response.json();
        setUsers(data);
      } catch (err) {
        setError(err instanceof Error ? err.message : 'Erreur inconnue');
      } finally {
        setLoading(false);
      }
    };

    if (token) {
      fetchUsers();
    }
  }, [token]);

  return { users, loading, error };
};
```

## 🔄 **Maintenance et Mise à Jour**

### **Mise à jour de la base de données**
```bash
# Sauvegarder les données existantes
pg_dump -U postgres tissue_db > backup_$(date +%Y%m%d_%H%M%S).sql

# Appliquer les nouvelles migrations
mvn flyway:migrate

# Ou recréer complètement (développement uniquement)
mvn spring-boot:run -Dspring.profiles.active=dev
```

### **Nettoyage des fichiers uploadés**
```bash
# Supprimer les anciens fichiers
find uploads/ -type f -mtime +30 -delete

# Vérifier l'espace disque
du -sh uploads/
```

## 📞 **Support et Dépannage**

### **Problèmes Courants**

#### **Erreur de connexion à la base de données**
```bash
# Vérifier que PostgreSQL est démarré
sudo systemctl status postgresql

# Vérifier la connexion
psql -U postgres -d tissue_db -h localhost
```

#### **Erreur JWT**
```bash
# Vérifier la clé secrète
echo "dGVzdC1zZWNyZXQta2V5LWZvci1kZXZlbG9wbWVudC1jaGFuZ2UtaW4tcHJvZHVjdGlvbg==" | base64 -d

# Régénérer une nouvelle clé
openssl rand -base64 64
```

#### **Erreur CORS**
```bash
# Vérifier la configuration CORS dans SecurityConfig.java
# Vérifier que l'origine frontend est autorisée
```

### **Logs de Débogage**
```bash
# Suivre les logs en temps réel
tail -f logs/application.log

# Rechercher des erreurs spécifiques
grep -i "error\|exception" logs/application.log
```

---

## 🎉 **Félicitations !**

Vous avez maintenant une compréhension complète du backend admin de HistoScan. Toutes les API sont fonctionnelles et prêtes à être utilisées par votre frontend !

### **Prochaines Étapes Recommandées**
1. **Tester toutes les API** avec Postman ou cURL
2. **Intégrer avec le frontend** React
3. **Ajouter des tests unitaires** pour les services
4. **Implémenter la validation** côté serveur
5. **Ajouter la pagination** pour les grandes listes
6. **Implémenter le cache** Redis pour les performances

**🚀 Bon développement !** 🚀
