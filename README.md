# JobPulse

JobPulse est une API REST Spring Boot pour suivre les candidatures à des offres d'emploi.

## Fonctionnalités
- Créer une candidature
- Lister toutes les candidatures
- Mettre à jour le statut
- Supprimer une candidature
- Filtrer par statut

## Tech stack
- Java 17
- Spring Boot 3
- Spring Web, Spring Data JPA
- H2 Database (in-memory)
- Lombok

## Endpoints
- `GET /api/applications` : liste toutes les candidatures
- `POST /api/applications` : crée une candidature
- `PUT /api/applications/{id}/status?status=ACCEPTED` : met à jour le statut
- `DELETE /api/applications/{id}` : supprime une candidature
- `GET /api/applications/status/{status}` : filtre par statut

## Lancement
1. Cloner le repo et ouvrir dans VS Code
2. S'assurer d'avoir Java 17+ et Maven
3. Lancer :
   ```sh
   ./mvnw spring-boot:run
   ```
4. Accéder à la console H2 : http://localhost:8080/h2-console

## Notes
- Les statuts possibles : APPLIED, INTERVIEW, REJECTED, ACCEPTED
- Les DTOs sont utilisés pour l'entrée/sortie
- Validation basique sur les champs
- CORS activé sur `/api/**`
- Données d'exemple insérées au démarrage

## Exemple de payload POST
```json
{
  "company": "OpenAI",
  "position": "AI Engineer",
  "status": "APPLIED",
  "appliedDate": "2026-04-01"
}
```
