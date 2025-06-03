# 🌐 ClientHub CRM

> **Manage. Connect. Grow.**

Un système de gestion de la relation client (CRM) moderne et intuitif, développé avec Angular v20 et Spring Boot, entièrement containerisé avec Docker.

## 📋 Description du Projet

ClientHub est une solution CRM complète conçue pour les entreprises souhaitant centraliser et optimiser la gestion de leurs relations clients. L'application offre une interface utilisateur moderne et responsive, couplée à une API REST robuste, le tout orchestré avec Docker pour une facilité de déploiement maximale.

### 🎯 Objectifs
- Centraliser les informations clients
- Améliorer le suivi des interactions commerciales
- Fournir des statistiques et analyses pertinantes
- Faciliter la collaboration entre équipes commerciales
- Déploiement simplifié avec Docker

## 🛠️ Technologies

### Frontend
- **Angular v20** - Framework web moderne avec Signals et Standalone Components
- **Angular Material** - Composants UI professionnels
- **TypeScript** - Langage typé pour plus de robustesse
- **Tailwind CSS** - Framework CSS utilitaire
- **Nginx** - Serveur web pour la production

### Backend
- **Java 17+** - Langage de programmation
- **Spring Boot 3.x** - Framework Java pour APIs REST
- **Spring Security** - Authentification et autorisation
- **Spring Data JPA** - Persistance des données
- **JWT** - Tokens d'authentification
- **Maven** - Gestionnaire de dépendances

### Infrastructure
- **Docker** - Containerisation des services
- **Docker Compose** - Orchestration multi-conteneurs
- **PostgreSQL 15** - Base de données relationnelle
- **Nginx** - Reverse proxy et serveur statique

## ✨ Fonctionnalités

### 🔐 Authentification
- [ ] Architecture Docker multi-services
- [ ] Connexion sécurisée avec JWT
- [ ] Gestion des rôles utilisateurs
- [ ] Récupération de mot de passe
- [ ] Authentification à deux facteurs

### 👥 Gestion des Clients
- [ ] Liste des clients avec recherche et filtrage
- [ ] Ajout/modification/suppression de clients
- [ ] Fiche détaillée client
- [ ] Historique des interactions
- [ ] Gestion des documents clients

### 📊 Dashboard
- [ ] Vue d'ensemble des KPIs
- [ ] Graphiques de performance
- [ ] Activités récentes
- [ ] Statistiques commerciales

### 📱 Interface
- [ ] Design responsive (mobile/desktop)
- [ ] Interface intuitive et moderne
- [ ] Thème sombre/clair
- [ ] Notifications en temps réel

## 🚀 Installation et Démarrage

### Prérequis
```bash
# Outils requis
- Docker 24+
- Docker Compose 2.0+
- Git
```

### 🐳 Démarrage avec Docker (Recommandé)

```bash
# Cloner le repository
git clone https://github.com/[username]/clienthub-crm.git
cd clienthub-crm

# Démarrer tous les services
docker-compose up --build

# En arrière-plan
docker-compose up -d --build
```

**L'application sera accessible sur :**
- 🌐 **Frontend** : http://localhost:4200
- 🔌 **API Backend** : http://localhost:8080
- 🗄️ **Base de données** : localhost:5432

### 🔧 Développement local (sans Docker)

<details>
<summary>Pour développer sans containers (optionnel)</summary>

```bash
# Backend (Spring Boot)
cd backend
./mvnw spring-boot:run

# Frontend (Angular) - nouveau terminal
cd frontend
npm install
ng serve
```

**Configuration requise :**
- PostgreSQL installé localement
- Node.js 18+
- Java 17+

</details>

## 🏗️ Architecture

```
clienthub-crm/
├── docker-compose.yml          # Orchestration des services
├── docker-compose.prod.yml     # Configuration production
├── backend/
│   ├── Dockerfile             # Container Spring Boot
│   ├── src/main/java/
│   │   ├── config/           # Configuration Spring
│   │   ├── controller/       # Contrôleurs REST
│   │   ├── entity/           # Entités JPA
│   │   ├── repository/       # Repositories Spring Data
│   │   ├── service/          # Services métier
│   │   └── security/         # Configuration sécurité
│   └── src/main/resources/
│       └── application.yml   # Configuration application
├── frontend/
│   ├── Dockerfile           # Container Angular/Nginx
│   ├── nginx.conf           # Configuration Nginx
│   ├── src/app/
│   │   ├── core/           # Services globaux
│   │   ├── shared/         # Composants partagés
│   │   ├── features/       # Modules fonctionnels
│   │   │   ├── auth/       # Authentification
│   │   │   ├── clients/    # Gestion clients
│   │   │   └── dashboard/  # Tableau de bord
│   │   └── models/         # Interfaces TypeScript
│   └── src/assets/         # Assets statiques
├── database/
│   └── init-scripts/       # Scripts d'initialisation DB
└── docs/                   # Documentation
```

### Logo
Le logo ClientHub représente un hub de connexions, symbolisant les relations clients centralisées.

## 🚀 Déploiement

### Développement
```bash
docker-compose up --build
```

### Production
```bash
# Utiliser le fichier de configuration production
docker-compose -f docker-compose.prod.yml up -d --build

# Ou avec des variables d'environnement
ENVIRONMENT=production docker-compose up -d
```

### Variables d'environnement

Créer un fichier `.env` :
```env
# Base de données
POSTGRES_DB=clienthub
POSTGRES_USER=admin
POSTGRES_PASSWORD=your_secure_password

# Backend
JWT_SECRET=your_jwt_secret_key
API_BASE_URL=http://localhost:8080

# Frontend
ANGULAR_ENV=production
```

## 🤝 Contribution

Ce projet est développé dans un cadre d'apprentissage avec une architecture Docker professionnelle. Les contributions sont les bienvenues !

### Comment contribuer
1. Fork le projet
2. Créer une branche feature (`git checkout -b feature/nouvelle-fonctionnalite`)
3. Tester avec Docker (`docker-compose up --build`)
4. Commit les changements (`git commit -m 'Ajout nouvelle fonctionnalité'`)
5. Push vers la branche (`git push origin feature/nouvelle-fonctionnalite`)
6. Ouvrir une Pull Request

## 📄 Licence

Ce projet est sous licence MIT. Voir le fichier `LICENSE` pour plus de détails.

## 👨‍💻 Auteur

- Email: [maxence.bonnici@outlook.fr]
- LinkedIn: [@Maxence Bonnici]
- GitHub: [@ImMaxence]

---

<div align="center">
  <strong>ClientHub CRM</strong> - Développé avec ❤️ en France
</div>
