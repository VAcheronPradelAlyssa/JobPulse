# JobPulse 🚀

Une application web moderne pour gérer vos candidatures en emploi de manière efficace et professionnelle.

## 📋 Description

**JobPulse** est une application full-stack conçue pour simplifier le suivi de vos candidatures professionnelles. Elle vous permet de créer, consulter, filtrer et mettre à jour le statut de vos candidatures en un seul endroit, avec une interface intuitive et fluide.

Que vous soyez en pleine recherche d'emploi ou en transition professionnelle, JobPulse vous aide à rester organisé et à ne rien oublier.

---

## ✨ Features

- ✅ **Gestion des candidatures** : Créer une nouvelle candidature en quelques clics
- 🎯 **Filtrage par statut** : Consultez vos candidatures par statut (Appliqué, Entretien, Rejeté, Accepté)
- 📝 **Mise à jour du statut** : Modifiez le statut de vos candidatures en temps réel
- 🗑️ **Suppression** : Supprimez les candidatures avec confirmation
- 📊 **Données d'exemple** : L'app se remplit automatiquement avec des données réalistes au démarrage
- 🎨 **Design moderne** : Interface minimaliste et épurée
- ⚡ **Performance** : Affichage instantané lors de la création/modification

---

## 🛠️ Tech Stack

### Backend
- **Java 21** : Langage de programmation
- **Spring Boot 4.0.5** : Framework web
- **Spring Data JPA** : ORM pour la persistance des données
- **H2 Database** : Base de données en mémoire (facilement configurable pour MariaDB/MySQL/PostgreSQL)
- **Lombok** : Réduction du boilerplate code
- **Jakarta Validation** : Validation des données
- **Maven** : Gestion des dépendances

### Frontend
- **Angular 19** : Framework frontend (Standalone Components)
- **TypeScript** : Langage typed
- **SCSS** : Stylisation
- **Reactive Forms** : Gestion des formulaires
- **HttpClient** : Communication avec l'API

---

## 🚀 Installation et Démarrage

### Prérequis
- **Java 21+**
- **Node.js 18+** (npm)
- **Maven 3.8+**

### 1️⃣ Lancer le Backend

```bash
# Naviguer dans le répertoire backend
cd jobpulse-backend

# Lancer l'application Spring Boot
mvn spring-boot:run
```

**Résultat** :
- 🟢 Application disponible sur : `http://localhost:8080`
- 📊 H2 Console accessible sur : `http://localhost:8080/h2-console`
- 📝 Données d'exemple créées automatiquement

### 2️⃣ Lancer le Frontend

Dans une autre fenêtre de terminal :

```bash
# Naviguer dans le répertoire frontend
cd jobpulse-front

# Installer les dépendances
npm install

# Lancer le serveur de développement
npm start
```

**Résultat** :
- 🟢 Application disponible sur : `http://localhost:4200`
- 🔄 Hot reload activé (les changements se rechargeront automatiquement)

---

## 📡 API Endpoints

L'API REST est accessible sur `http://localhost:8080/api/applications`

### Récupérer toutes les candidatures
```http
GET /api/applications
```

**Réponse** (200 OK) :
```json
[
  {
    "id": 1,
    "company": "Google",
    "position": "Senior Software Engineer",
    "status": "INTERVIEW",
    "appliedDate": "2026-03-15"
  }
]
```

### Créer une nouvelle candidature
```http
POST /api/applications
Content-Type: application/json

{
  "company": "Microsoft",
  "position": "Cloud Architect",
  "status": "APPLIED",
  "appliedDate": "2026-03-20"
}
```

**Réponse** (201 Created) :
```json
{
  "id": 2,
  "company": "Microsoft",
  "position": "Cloud Architect",
  "status": "APPLIED",
  "appliedDate": "2026-03-20"
}
```

### Récupérer les candidatures par statut
```http
GET /api/applications/status/{status}
```

**Valeurs possibles** : `APPLIED`, `INTERVIEW`, `REJECTED`, `ACCEPTED`

**Exemple** :
```http
GET /api/applications/status/INTERVIEW
```

### Mettre à jour le statut d'une candidature
```http
PUT /api/applications/{id}/status
Content-Type: application/json

{
  "status": "INTERVIEW"
}
```

**Réponse** (200 OK) :
```json
{
  "id": 1,
  "company": "Google",
  "position": "Senior Software Engineer",
  "status": "INTERVIEW",
  "appliedDate": "2026-03-15"
}
```

### Supprimer une candidature
```http
DELETE /api/applications/{id}
```

**Réponse** (204 No Content)

---

## 📊 Modèle de Données

### JobApplication
```java
{
  "id": Long (Primary Key, Auto-generated),
  "company": String (Required, Not Blank),
  "position": String (Required, Not Blank),
  "status": ApplicationStatus (ENUM: APPLIED, INTERVIEW, REJECTED, ACCEPTED),
  "appliedDate": LocalDate (Required, Not Null)
}
```

### ApplicationStatus (Enum)
```
APPLIED    - Candidature envoyée
INTERVIEW  - Entretien planifié/en cours
REJECTED   - Rejet
ACCEPTED   - Offre acceptée
```

---

## 📁 Structure du Projet

```
JobPulse/
├── jobpulse-backend/
│   ├── src/main/java/com/alyssa/jobpulse/
│   │   ├── controller/          # Controllers REST
│   │   ├── service/             # Business logic
│   │   ├── repository/          # Data access
│   │   ├── model/               # Entities
│   │   ├── dto/                 # Data Transfer Objects
│   │   ├── exception/           # Exception handling
│   │   ├── initializer/         # Data initialization
│   │   └── config/              # Configuration
│   ├── src/main/resources/
│   │   ├── application.yaml     # Configuration
│   │   └── application.properties
│   └── pom.xml                  # Maven config
│
├── jobpulse-front/
│   ├── src/app/
│   │   ├── components/          # Components Angular
│   │   │   ├── job-form/        # Formulaire de création
│   │   │   ├── job-list/        # Liste des candidatures
│   │   │   └── job-filter/      # Filtrage
│   │   ├── service/             # Services HTTP
│   │   ├── model/               # Interfaces TypeScript
│   │   └── app.ts               # Root component
│   ├── package.json             # Dependencies
│   └── angular.json             # Angular config
│
└── README.md                     # Ce fichier
```

---

## 🔍 Fonctionnalités Détaillées

### 1. Création de Candidature
- Formulaire de création avec validation en temps réel
- Statut par défaut : "APPLIED"
- Date de candidature : aujourd'hui par défaut
- Affichage instantané dans la liste après création

### 2. Filtrage
- Boutons de filtre : ALL, APPLIED, INTERVIEW, REJECTED, ACCEPTED
- Filtrage côté client (performant, pas d'appel API)
- Filtre conservé lors des modifications

### 3. Mise à Jour
- Dropdown pour changer le statut
- Mise à jour instantanée en front
- Synchronisation avec le backend
- Message de succès

### 4. Suppression
- Bouton avec confirmation
- Suppression instantanée de la liste
- Message de succès avec auto-hide

---

## ⚙️ Configuration

### Backend (application.yaml)

```yaml
spring:
  datasource:
    url: jdbc:h2:mem:jobpulse
    driverClassName: org.h2.Driver
  jpa:
    database-platform: org.hibernate.dialect.H2Dialect
  h2:
    console:
      enabled: true
```

### Frontend (environment.ts)

```typescript
export const environment = {
  apiUrl: 'http://localhost:8080/api/applications'
};
```

---

## 🐛 Gestion des Erreurs

L'API retourne des réponses d'erreur appropriées :

### Validation Error (400)
```json
{
  "timestamp": "2026-04-02T10:35:00",
  "status": 400,
  "message": "Validation failed",
  "error": "Invalid input",
  "path": "/api/applications",
  "fieldErrors": [
    {
      "field": "company",
      "message": "Company name is required"
    }
  ]
}
```

### Not Found (404)
```json
{
  "timestamp": "2026-04-02T10:35:00",
  "status": 404,
  "message": "Job application not found with id: 999",
  "error": "Resource not found",
  "path": "/api/applications/999"
}
```

---

## 🚀 Production

### Compiler le Frontend
```bash
cd jobpulse-front
npm run build
# Sortie : dist/jobpulse-front/
```

### Compiler le Backend
```bash
cd jobpulse-backend
mvn clean package
# JAR généré : target/jobpulse-0.0.1-SNAPSHOT.jar
```

---

## 📝 Notes

- **H2 Database** : En mémoire par défaut. Les données sont effacées au redémarrage.
- **CORS** : Configuré pour accepter les requêtes depuis `localhost:4200`
- **Hot Reload** : Frontend avec Angular CLI, Backend avec Spring Boot DevTools

---

## 📄 Licence

Ce projet est fourni à titre d'exemple éducatif.

---

## 👨‍💻 Auteur

Créé par **Alyssa** en avril 2026.

---

## 💡 Suggestions d'Améliorations Futures

- [ ] Authentification utilisateur
- [ ] Historique des candidatures
- [ ] Notifications/Rappels
- [ ] Export en CSV/PDF
- [ ] Intégration LinkedIn
- [ ] Dark mode
- [ ] Mobile app (React Native)
- [ ] Statistiques et graphiques

---

**Happy Job Hunting! 🎯**
