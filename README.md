# HistoScan - Plateforme d'Analyse Intelligente des Tissus d'Organes

## 🚀 Vue d'ensemble

HistoScan est une plateforme moderne et intuitive qui combine intelligence artificielle et analyse médicale pour l'examen des tissus d'organes. La plateforme offre une interface utilisateur sophistiquée avec un système de navigation par sidebar adaptatif selon le rôle de l'utilisateur.

## ✨ Fonctionnalités Principales

### 🔐 Système d'Authentification
- **Connexion/Inscription** : Interface unifiée avec onglets pour la connexion et l'inscription
- **Gestion des rôles** : Distinction entre utilisateurs (USER) et administrateurs (ADMIN)
- **Redirection automatique** : Redirection intelligente selon le rôle après authentification
- **Protection des routes** : Système de protection basé sur les rôles et l'état d'authentification

### 🎨 Interface Utilisateur Moderne
- **Thème "Azul Cielo"** : Palette de couleurs cohérente et élégante
- **Sidebar responsive** : Navigation collapsible adaptée au rôle de l'utilisateur
- **Design adaptatif** : Interface responsive pour tous les appareils
- **Composants UI avancés** : Utilisation de shadcn/ui avec personnalisations Tailwind

### 👥 Espace Administrateur
- **Tableau de bord** : Vue d'overview avec statistiques et actions rapides
- **Gestion des organes** : CRUD complet pour les descriptions d'organes
- **Gestion des images** : Upload, visualisation et organisation des images de référence
- **Gestion des utilisateurs** : Invitation, modification et contrôle des comptes
- **Statistiques avancées** : Métriques de performance et analytics
- **Préférences système** : Configuration des paramètres de la plateforme

### 👤 Espace Utilisateur
- **Tableau de bord personnel** : Statistiques individuelles et actions rapides
- **Analyse d'images** : Upload et analyse IA des images de tissus
- **Historique des analyses** : Consultation et gestion des analyses précédentes
- **Profil utilisateur** : Gestion des informations personnelles et préférences

## 🏗️ Architecture Technique

### Frontend
- **Framework** : React 18 avec TypeScript
- **Build Tool** : Vite
- **Styling** : Tailwind CSS avec personnalisations avancées
- **UI Components** : shadcn/ui
- **Routing** : React Router DOM v6
- **State Management** : React Hooks (useState, useEffect)
- **Icons** : Lucide React

### Backend
- **Framework** : Spring Boot (Java 17)
- **Sécurité** : Spring Security avec JWT
- **Base de données** : PostgreSQL avec JPA
- **API** : REST API avec CORS configuré
- **Upload** : Gestion des fichiers multipart

### Services ML
- **Framework** : FastAPI (Python)
- **IA** : Keras/TensorFlow pour l'analyse d'images
- **Intégration** : Service séparé avec communication HTTP

## 📁 Structure du Projet

```
organ-atlas-scribe-05/
├── src/
│   ├── components/
│   │   ├── ui/                    # Composants UI shadcn
│   │   ├── Layout.tsx            # Layout principal avec sidebar
│   │   ├── Sidebar.tsx           # Sidebar responsive et adaptatif
│   │   ├── ProtectedRoute.tsx    # Protection des routes
│   │   ├── AutoRedirect.tsx      # Redirection automatique
│   │   └── ...
│   ├── pages/
│   │   ├── admin/                # Pages administrateur
│   │   │   ├── OrgansPage.tsx    # Gestion des organes
│   │   │   ├── ImagesPage.tsx    # Gestion des images
│   │   │   ├── UsersPage.tsx     # Gestion des utilisateurs
│   │   │   ├── StatisticsPage.tsx # Statistiques et analytics
│   │   │   ├── PreferencesPage.tsx # Préférences système
│   │   │   └── index.ts          # Export des pages admin
│   │   ├── dashboard/            # Pages utilisateur
│   │   │   ├── AnalysisPage.tsx  # Analyse d'images
│   │   │   ├── HistoryPage.tsx   # Historique des analyses
│   │   │   ├── ProfilePage.tsx   # Profil utilisateur
│   │   │   └── index.ts          # Export des pages dashboard
│   │   ├── Home.tsx              # Page d'accueil publique
│   │   ├── Login.tsx             # Connexion/inscription
│   │   ├── Dashboard.tsx         # Dashboard utilisateur
│   │   └── Admin.tsx             # Dashboard administrateur
│   ├── hooks/
│   │   └── use-toast.ts          # Hook pour les notifications
│   ├── assets/                   # Images et ressources
│   ├── App.tsx                   # Configuration des routes
│   └── main.tsx                  # Point d'entrée
├── tailwind.config.js            # Configuration Tailwind personnalisée
└── package.json
```

## 🎯 Système de Navigation

### Sidebar Administrateur
- **Tableau de bord** (`/admin`) : Vue d'overview générale
- **Organes & Descriptions** (`/admin/organs`) : CRUD des descriptions d'organes
- **Images de Référence** (`/admin/images`) : Gestion de la bibliothèque d'images
- **Utilisateurs** (`/admin/users`) : Gestion des comptes utilisateurs
- **Statistiques** (`/admin/statistics`) : Analytics et métriques
- **Préférences** (`/admin/preferences`) : Configuration système

### Sidebar Utilisateur
- **Tableau de bord** (`/dashboard`) : Vue personnelle et statistiques
- **Analyse d'images** (`/dashboard/analysis`) : Upload et analyse IA
- **Historique** (`/dashboard/history`) : Consultation des analyses précédentes
- **Profil** (`/dashboard/profile`) : Gestion du compte personnel

## 🚀 Installation et Démarrage

### Prérequis
- Node.js 18+ et npm
- Java 17+ et Maven
- PostgreSQL 13+
- Python 3.8+ et pip

### Frontend
```bash
cd organ-atlas-scribe-05
npm install
npm run dev
```

### Backend Spring Boot
```bash
cd api
mvn spring-boot:run
```

### Service ML
```bash
cd ml-service
pip install -r requirements.txt
uvicorn main:app --reload --port 8001
```

## 🎨 Personnalisations Tailwind

Le projet utilise une configuration Tailwind étendue avec :
- **Couleurs personnalisées** : Palette "azul cielo" cohérente
- **Gradients** : Dégradés sophistiqués pour les composants
- **Animations** : Transitions et animations fluides
- **Ombres** : Système d'ombres élégant et cohérent
- **Responsive** : Breakpoints optimisés pour tous les appareils

## 🔒 Sécurité et Authentification

- **JWT Tokens** : Authentification sécurisée
- **CORS configuré** : Communication frontend-backend sécurisée
- **Protection des routes** : Accès basé sur les rôles
- **Validation des données** : Vérification côté client et serveur
- **Gestion des sessions** : Expiration automatique des tokens

## 📱 Responsive Design

- **Mobile First** : Approche responsive pour tous les écrans
- **Sidebar adaptatif** : Collapsible sur mobile, étendu sur desktop
- **Grilles flexibles** : Layouts qui s'adaptent à la taille d'écran
- **Navigation tactile** : Optimisé pour les appareils tactiles

## 🧪 Tests et Développement

### Tests Frontend
```bash
npm run test
npm run test:coverage
```

### Tests Backend
```bash
mvn test
mvn test:coverage
```

## 🚀 Déploiement

### Frontend
```bash
npm run build
# Déployer le dossier dist/
```

### Backend
```bash
mvn clean package
java -jar target/tissue-captioner-0.0.1-SNAPSHOT.jar
```

## 🤝 Contribution

1. Fork le projet
2. Créer une branche feature (`git checkout -b feature/AmazingFeature`)
3. Commit les changements (`git commit -m 'Add some AmazingFeature'`)
4. Push vers la branche (`git push origin feature/AmazingFeature`)
5. Ouvrir une Pull Request

## 📄 Licence

Ce projet est sous licence MIT. Voir le fichier `LICENSE` pour plus de détails.

## 📞 Support

Pour toute question ou support :
- Créer une issue sur GitHub
- Contacter l'équipe de développement
- Consulter la documentation technique

---

**HistoScan** - Révolutionner l'analyse des tissus avec l'intelligence artificielle 🧠✨
