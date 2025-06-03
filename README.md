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
- **Déploiement simplifié** avec Docker

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
- [x] Architecture Docker multi-services
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

### 🛠️ Commandes utiles

```bash
# Voir les logs
docker-compose logs -f

# Redémarrer un service spécifique
docker-compose restart backend

# Accéder à la base de données
docker-compose exec database psql -U admin -d clienthub

# Stopper tous les services
docker-compose down

# Supprimer les volumes (⚠️ perte de données)
docker-compose down -v
```

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

## 🏗️ Architecture Docker

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

## 🐳 Services Docker

### 📦 **database** (PostgreSQL 15)
- **Port** : 5432
- **Base** : clienthub
- **Utilisateur** : admin
- **Volumes** : Persistance des données

### 🚀 **backend** (Spring Boot + OpenJDK 17)
- **Port** : 8080
- **API REST** : /api/v1/
- **Swagger** : http://localhost:8080/swagger-ui.html
- **Health Check** : http://localhost:8080/actuator/health

### 🌐 **frontend** (Angular + Nginx)
- **Port** : 4200
- **SPA** : Application monopage
- **Build optimisé** pour la production
- **Proxy API** : /api/* → backend:8080

## 🎨 Charte Graphique

### Couleurs Principales
- **Bleu Principal**: `#2563eb`
- **Bleu Foncé**: `#1d4ed8`
- **Gris Anthracite**: `#1e293b`
- **Gris Moyen**: `#64748b`
- **Gris Clair**: `#f8fafc`

### Logo
Le logo ClientHub représente un hub de connexions, symbolisant les relations clients centralisées.

## 📈 Roadmap

### Phase 1 - Infrastructure & MVP (Q2 2025)
- [x] Setup Docker multi-services
- [x] Identité visuelle et charte graphique
- [ ] Backend : Configuration Spring Boot + PostgreSQL
- [ ] Backend : Authentification JWT + Docker
- [ ] Backend : CRUD Clients avec API REST
- [ ] Frontend : Build Angular optimisé pour Docker
- [ ] Frontend : Pages de connexion + routing
- [ ] Frontend : Liste et gestion clients

### Phase 2 - Fonctionnalités Avancées (Q3 2025)
- [ ] Dashboard avec statistiques temps réel
- [ ] Système de notifications WebSocket
- [ ] Export de données (PDF, Excel)
- [ ] API de recherche avancée avec pagination
- [ ] Cache Redis pour les performances

### Phase 3 - Production & Déploiement (Q4 2025)
- [ ] Configuration Docker production
- [ ] CI/CD avec GitHub Actions
- [ ] Monitoring avec Prometheus/Grafana
- [ ] Tests automatisés (backend + frontend)
- [ ] Documentation API complète
- [ ] Sécurité renforcée (rate limiting, CORS)

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

**[Ton Nom]**
- Email: [maxence.bonnici@outlook.fr]
- LinkedIn: [@Maxence Bonnici]
- GitHub: [@ImMaxence]

## 🔗 Liens Utiles

- [Documentation Angular v20](https://angular.dev)
- [Guide Spring Boot](https://spring.io/guides)
- [Docker Compose Documentation](https://docs.docker.com/compose/)
- [PostgreSQL Docker Image](https://hub.docker.com/_/postgres)
- [Angular Material](https://material.angular.io)
- [Tailwind CSS](https://tailwindcss.com)

---

<div align="center">
  <strong>ClientHub CRM</strong> - Développé avec ❤️ en France
</div>
