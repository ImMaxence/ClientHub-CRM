# 🌐 ClientHub CRM

> **Manage. Connect. Grow.**

Un système de gestion de la relation client (CRM) moderne et intuitif, développé avec Angular v20 et Spring Boot.

## 📋 Description du Projet

ClientHub est une solution CRM complète conçue pour les entreprises souhaitant centraliser et optimiser la gestion de leurs relations clients. L'application offre une interface utilisateur moderne et responsive, couplée à une API REST robuste.

### 🎯 Objectifs
- Centraliser les informations clients
- Améliorer le suivi des interactions commerciales
- Fournir des statistiques et analyses pertinentes
- Faciliter la collaboration entre équipes commerciales

## 🛠️ Technologies

### Frontend
- **Angular v20** - Framework web moderne avec Signals et Standalone Components
- **Angular Material** - Composants UI professionnels
- **TypeScript** - Langage typé pour plus de robustesse
- **Tailwind CSS** - Framework CSS utilitaire

### Backend
- **Java 17+** - Langage de programmation
- **Spring Boot 3.x** - Framework Java pour APIs REST
- **Spring Security** - Authentification et autorisation
- **Spring Data JPA** - Persistance des données
- **PostgreSQL** - Base de données relationnelle
- **JWT** - Tokens d'authentification

## ✨ Fonctionnalités

### 🔐 Authentification
- [x] Connexion sécurisée avec JWT
- [x] Gestion des rôles utilisateurs
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
- Node.js 18+
- Java 17+
- PostgreSQL 13+
- Git
```

### Backend (Spring Boot)
```bash
# Cloner le repository
git clone https://github.com/[username]/clienthub-crm.git
cd clienthub-crm

# Démarrer le backend
cd backend
./mvnw spring-boot:run
```

### Frontend (Angular)
```bash
# Installer les dépendances
cd frontend
npm install

# Démarrer le serveur de développement
ng serve
```

L'application sera accessible sur `http://localhost:4200`

## 🏗️ Architecture

```
clienthub-crm/
├── backend/                 # API Spring Boot
│   ├── src/main/java/
│   │   ├── config/         # Configuration Spring
│   │   ├── controller/     # Contrôleurs REST
│   │   ├── entity/         # Entités JPA
│   │   ├── repository/     # Repositories Spring Data
│   │   ├── service/        # Services métier
│   │   └── security/       # Configuration sécurité
│   └── src/main/resources/
│       └── application.yml # Configuration application
├── frontend/               # Application Angular
│   ├── src/app/
│   │   ├── core/          # Services globaux
│   │   ├── shared/        # Composants partagés
│   │   ├── features/      # Modules fonctionnels
│   │   │   ├── auth/      # Authentification
│   │   │   ├── clients/   # Gestion clients
│   │   │   └── dashboard/ # Tableau de bord
│   │   └── models/        # Interfaces TypeScript
│   └── src/assets/        # Assets statiques
└── docs/                  # Documentation
```

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

### Phase 1 - MVP (Q2 2025)
- [x] Setup projet et architecture
- [x] Identité visuelle et charte graphique
- [ ] Backend : Authentification JWT
- [ ] Backend : CRUD Clients
- [ ] Frontend : Pages de connexion
- [ ] Frontend : Liste et gestion clients

### Phase 2 - Fonctionnalités Avancées (Q3 2025)
- [ ] Dashboard avec statistiques
- [ ] Système de notifications
- [ ] Export de données
- [ ] API de recherche avancée

### Phase 3 - Optimisations (Q4 2025)
- [ ] Performance et cache
- [ ] Tests automatisés
- [ ] Documentation API
- [ ] Déploiement CI/CD

## 🤝 Contribution

Ce projet est développé dans un cadre d'apprentissage. Les contributions sont les bienvenues !

### Comment contribuer
1. Fork le projet
2. Créer une branche feature (`git checkout -b feature/nouvelle-fonctionnalite`)
3. Commit les changements (`git commit -m 'Ajout nouvelle fonctionnalité'`)
4. Push vers la branche (`git push origin feature/nouvelle-fonctionnalite`)
5. Ouvrir une Pull Request

## 📄 Licence

Ce projet est sous licence MIT. Voir le fichier `LICENSE` pour plus de détails.

## 👨‍💻 Auteur

**[Ton Nom]**
- Email: [ton.email@exemple.com]
- LinkedIn: [ton-profil-linkedin]
- GitHub: [@ton-username]

## 🔗 Liens Utiles

- [Documentation Angular v20](https://angular.dev)
- [Guide Spring Boot](https://spring.io/guides)
- [Angular Material](https://material.angular.io)
- [Tailwind CSS](https://tailwindcss.com)

---

<div align="center">
  <strong>ClientHub CRM</strong> - Développé avec ❤️ en France
</div>
