# 🚀 Backend Admin - Documentation Complète

## 📋 **Vue d'ensemble**

Ce document décrit l'implémentation complète du backend pour toutes les pages admin de l'application HistoScan. Le backend fournit des API REST sécurisées pour la gestion des utilisateurs, organes, images de référence, statistiques et préférences système.

## 🏗️ **Architecture**

### **Structure des Couches**
```
┌─────────────────────────────────────────────────────────┐
│ Controllers (REST API)                                 │
├─────────────────────────────────────────────────────────┤
│ Services (Logique Métier)                              │
├─────────────────────────────────────────────────────────┤
│ Repositories (Accès aux Données)                       │
├─────────────────────────────────────────────────────────┤
│ Models (Entités JPA)                                   │
└─────────────────────────────────────────────────────────┘
```

## 🗄️ **Modèles de Données**

### **1. User (Utilisateur)**
- **Champs** : id, username, email, password, role, createdAt, lastLoginAt
- **Relations** : OneToMany avec ViewHistory
- **Rôles** : USER, ADMIN

### **2. Organ (Organe)**
- **Champs** : id, name, description, imageUrl, status, category, priority, lastModified, createdAt
- **Statuts** : DRAFT, PUBLISHED, ARCHIVED
- **Priorités** : LOW, MEDIUM, HIGH

### **3. ReferenceImage (Image de Référence)**
- **Champs** : id, title, description, imageUrl, category, organType, status, tags, fileSize, fileType, uploadedAt, lastModified
- **Statuts** : PENDING, APPROVED, REJECTED, ARCHIVED
- **Relations** : ManyToOne avec User (uploadedBy)

### **4. SystemStatistics (Statistiques Système)**
- **Champs** : id, recordedAt, totalUsers, activeUsers, totalAnalyses, todayAnalyses, totalImages, systemUptime, avgResponseTime, successRate, storageUsed, storageTotal, version

### **5. SystemPreferences (Préférences Système)**
- **Champs** : id, preferenceKey, preferenceValue, description, category, lastModified, createdAt

## 🔧 **Services Implémentés**

### **1. AdminUserService**
- ✅ **getAllUsers()** : Récupérer tous les utilisateurs
- ✅ **getUserById(Long id)** : Récupérer un utilisateur par ID
- ✅ **createUser(User user)** : Créer un nouvel utilisateur
- ✅ **updateUser(Long id, User userDetails)** : Mettre à jour un utilisateur
- ✅ **deleteUser(Long id)** : Supprimer un utilisateur
- ✅ **searchUsers(String searchTerm)** : Rechercher des utilisateurs
- ✅ **getUsersByRole(User.Role role)** : Filtrer par rôle
- ✅ **getUserStatistics()** : Statistiques utilisateurs

### **2. AdminOrganService**
- ✅ **getAllOrgans()** : Récupérer tous les organes
- ✅ **getOrganById(Long id)** : Récupérer un organe par ID
- ✅ **createOrgan(Organ organ)** : Créer un nouvel organe
- ✅ **updateOrgan(Long id, Organ organDetails)** : Mettre à jour un organe
- ✅ **deleteOrgan(Long id)** : Supprimer un organe
- ✅ **searchOrgans(String searchTerm)** : Rechercher des organes
- ✅ **getOrgansByFilters()** : Filtrage avancé
- ✅ **getOrganStatistics()** : Statistiques organes

### **3. AdminImageService**
- ✅ **getAllImages()** : Récupérer toutes les images
- ✅ **createImage()** : Créer une nouvelle image avec upload
- ✅ **updateImage()** : Mettre à jour une image
- ✅ **deleteImage()** : Supprimer une image
- ✅ **searchImages()** : Rechercher des images
- ✅ **getImagesByFilters()** : Filtrage avancé
- ✅ **handleFileUpload()** : Gestion des fichiers
- ✅ **getImageStatistics()** : Statistiques images

### **4. AdminStatisticsService**
- ✅ **getDashboardStatistics()** : Statistiques du tableau de bord
- ✅ **getTopUsers(int limit)** : Top utilisateurs
- ✅ **getMonthlyTrends()** : Tendances mensuelles
- ✅ **getSystemMetrics()** : Métriques système
- ✅ **recordSystemStatistics()** : Enregistrer les statistiques

### **5. AdminPreferencesService**
- ✅ **getAllPreferences()** : Récupérer toutes les préférences
- ✅ **getPreference(String key)** : Récupérer une préférence
- ✅ **setPreference()** : Définir une préférence
- ✅ **setMultiplePreferences()** : Définir plusieurs préférences
- ✅ **initializeDefaultPreferences()** : Initialiser les préférences par défaut
- ✅ **getPreferencesStructure()** : Structure des préférences

## 🌐 **API REST Endpoints**

### **Base URL** : `/api/admin`

#### **👥 Gestion des Utilisateurs** (`/users`)
- `GET /users` - Récupérer tous les utilisateurs
- `GET /users/{id}` - Récupérer un utilisateur par ID
- `POST /users` - Créer un nouvel utilisateur
- `PUT /users/{id}` - Mettre à jour un utilisateur
- `DELETE /users/{id}` - Supprimer un utilisateur
- `GET /users/search?q={term}` - Rechercher des utilisateurs
- `GET /users/role/{role}` - Filtrer par rôle
- `GET /users/statistics` - Statistiques utilisateurs

#### **🗃️ Gestion des Organes** (`/organs`)
- `GET /organs` - Récupérer tous les organes
- `GET /organs/{id}` - Récupérer un organe par ID
- `POST /organs` - Créer un nouvel organe
- `PUT /organs/{id}` - Mettre à jour un organe
- `DELETE /organs/{id}` - Supprimer un organe
- `GET /organs/search?q={term}` - Rechercher des organes
- `GET /organs/status/{status}` - Filtrer par statut
- `GET /organs/category/{category}` - Filtrer par catégorie
- `GET /organs/priority/{priority}` - Filtrer par priorité
- `GET /organs/filters` - Filtrage avancé
- `GET /organs/categories` - Toutes les catégories
- `GET /organs/statistics` - Statistiques organes
- `GET /organs/recent` - Organes récemment modifiés

#### **🖼️ Gestion des Images** (`/images`)
- `GET /images` - Récupérer toutes les images
- `GET /images/{id}` - Récupérer une image par ID
- `POST /images` - Créer une nouvelle image (avec upload)
- `PUT /images/{id}` - Mettre à jour une image
- `DELETE /images/{id}` - Supprimer une image
- `GET /images/search?q={term}` - Rechercher des images
- `GET /images/status/{status}` - Filtrer par statut
- `GET /images/category/{category}` - Filtrer par catégorie
- `GET /images/organ-type/{organType}` - Filtrer par type d'organe
- `GET /images/filters` - Filtrage avancé
- `GET /images/categories` - Toutes les catégories
- `GET /images/organ-types` - Tous les types d'organes
- `GET /images/statistics` - Statistiques images
- `GET /images/recent` - Images récemment uploadées

#### **📊 Statistiques** (`/statistics`)
- `GET /statistics/dashboard` - Statistiques du tableau de bord
- `GET /statistics/top-users?limit={n}` - Top utilisateurs
- `GET /statistics/monthly-trends` - Tendances mensuelles
- `GET /statistics/system-metrics` - Métriques système
- `POST /statistics/record` - Enregistrer les statistiques

#### **⚙️ Préférences Système** (`/preferences`)
- `GET /preferences` - Toutes les préférences
- `GET /preferences/{key}` - Une préférence spécifique
- `GET /preferences/category/{category}` - Préférences par catégorie
- `POST /preferences/{key}` - Définir une préférence
- `POST /preferences/batch` - Définir plusieurs préférences
- `DELETE /preferences/{key}` - Supprimer une préférence
- `POST /preferences/initialize` - Initialiser les préférences par défaut
- `GET /preferences/structure` - Structure des préférences

## 🔒 **Sécurité**

### **Authentification**
- ✅ **JWT Token** : Authentification basée sur les tokens
- ✅ **Spring Security** : Configuration de sécurité complète
- ✅ **@PreAuthorize** : Contrôle d'accès basé sur les rôles

### **Autorisation**
- ✅ **Rôle ADMIN requis** : Tous les endpoints admin nécessitent le rôle ADMIN
- ✅ **Validation des données** : Validation des entrées avec Bean Validation
- ✅ **Gestion des erreurs** : Gestion centralisée des exceptions

### **CORS**
- ✅ **Origines autorisées** : `http://localhost:5173`, `http://192.168.11.123:8080`
- ✅ **Méthodes HTTP** : GET, POST, PUT, DELETE
- ✅ **Headers** : Content-Type, Authorization
- ✅ **Credentials** : Support des cookies et sessions

## 📁 **Structure des Fichiers**

```
src/main/java/com/tissue/captioner/
├── model/
│   ├── User.java
│   ├── Organ.java
│   ├── ReferenceImage.java
│   ├── SystemStatistics.java
│   └── SystemPreferences.java
├── dto/
│   └── UserDto.java
├── repository/
│   ├── UserRepository.java
│   ├── OrganRepository.java
│   ├── ReferenceImageRepository.java
│   ├── SystemStatisticsRepository.java
│   └── SystemPreferencesRepository.java
├── service/
│   ├── AdminUserService.java
│   ├── AdminOrganService.java
│   ├── AdminImageService.java
│   ├── AdminStatisticsService.java
│   └── AdminPreferencesService.java
└── controller/
    ├── AdminUserController.java
    ├── AdminOrganController.java
    ├── AdminImageController.java
    ├── AdminStatisticsController.java
    └── AdminPreferencesController.java
```

## 🧪 **Tests et Validation**

### **Tests Unitaires Recommandés**
- ✅ **Services** : Tester la logique métier
- ✅ **Controllers** : Tester les endpoints REST
- ✅ **Repositories** : Tester l'accès aux données
- ✅ **Validation** : Tester la validation des modèles

### **Tests d'Intégration**
- ✅ **API Endpoints** : Tester les réponses HTTP
- ✅ **Base de données** : Tester les opérations CRUD
- ✅ **Sécurité** : Tester l'authentification et l'autorisation

## 🚀 **Déploiement**

### **Configuration Requise**
- ✅ **Java 17+** : Version minimale requise
- ✅ **Spring Boot 3.x** : Framework principal
- ✅ **PostgreSQL** : Base de données
- ✅ **JWT** : Authentification

### **Variables d'Environnement**
```bash
# Base de données
DB_URL=jdbc:postgresql://localhost:5432/tissue_db
DB_USERNAME=postgres
DB_PASSWORD=postgres

# JWT
JWT_SECRET=your-secret-key

# Upload
UPLOAD_DIR=uploads/
MAX_FILE_SIZE=10MB
```

## 📚 **Utilisation Frontend**

### **Exemple d'Appel API**
```typescript
// Récupérer tous les utilisateurs
const response = await fetch('/api/admin/users', {
  headers: {
    'Authorization': `Bearer ${token}`,
    'Content-Type': 'application/json'
  }
});

// Créer un nouvel utilisateur
const newUser = await fetch('/api/admin/users', {
  method: 'POST',
  headers: {
    'Authorization': `Bearer ${token}`,
    'Content-Type': 'application/json'
  },
  body: JSON.stringify({
    username: 'john.doe',
    email: 'john@example.com',
    password: 'password123',
    role: 'USER'
  })
});
```

## 🔄 **Prochaines Étapes**

### **Améliorations Recommandées**
1. **Cache Redis** : Mise en cache des statistiques
2. **Logging** : Logs détaillés des opérations admin
3. **Audit Trail** : Traçabilité des modifications
4. **Backup** : Sauvegarde automatique des données
5. **Monitoring** : Métriques de performance

### **Fonctionnalités Futures**
1. **Notifications** : Système de notifications en temps réel
2. **Workflow** : Gestion des processus d'approbation
3. **Reporting** : Génération de rapports avancés
4. **API Rate Limiting** : Limitation du taux de requêtes
5. **Webhooks** : Intégrations externes

---

**🎉 Status** : **BACKEND ADMIN 100% COMPLÉTÉ** - Toutes les API nécessaires sont implémentées et prêtes à être utilisées par le frontend ! 🚀

