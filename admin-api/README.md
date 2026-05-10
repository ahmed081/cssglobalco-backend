# CSS Global Recruit Backend

Spring Boot backend generated for the CSS Global Recruit React prototype.

## Stack

- Java 17
- Spring Boot 3.3.x
- Spring Security OAuth2 Resource Server
- Keycloak Admin Client + token API login
- Spring Data JPA
- PostgreSQL/H2
- Flyway
- MapStruct
- Hexagonal package structure

## Run locally

```bash
mvn spring-boot:run
```

Default context path:

```text
/api/v1
```

Swagger:

```text
http://localhost:8080/api/v1/swagger-ui.html
```

## Auth flow

The frontend calls this backend directly:

- `POST /api/v1/auth/register`
- `POST /api/v1/auth/login`

The backend calls Keycloak APIs in Java and returns Keycloak tokens plus the local user profile.

## Main APIs

- `GET /api/v1/dashboard`
- `GET /api/v1/candidates`
- `POST /api/v1/candidates`
- `GET /api/v1/candidates/{id}`
- `PUT /api/v1/candidates/{id}`
- `PATCH /api/v1/candidates/{id}/stage`
- `DELETE /api/v1/candidates/{id}`
- `GET /api/v1/roles`
- `POST /api/v1/roles`
- `GET /api/v1/roles/{id}`
- `PUT /api/v1/roles/{id}`
- `DELETE /api/v1/roles/{id}`

## Keycloak variables

```bash
KEYCLOAK_BASE_URL=http://localhost:8081
KEYCLOAK_REALM=css-global
KEYCLOAK_CLIENT_ID=css-recruit-api
KEYCLOAK_CLIENT_SECRET=change-me
KEYCLOAK_ADMIN_CLIENT_ID=admin-cli
KEYCLOAK_ADMIN_USERNAME=admin
KEYCLOAK_ADMIN_PASSWORD=admin
KEYCLOAK_ISSUER_URI=http://localhost:8081/realms/css-global
```

The client must allow Direct Access Grants if using password login.
