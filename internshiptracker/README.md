# Internship Tracker Backend

Spring Boot backend for the internship tracker app.

## Run locally

```bash
./mvnw spring-boot:run
```

By default, the app uses an in-memory H2 database for local development.

## Quick health check

Public endpoint:

```bash
GET /api/health
```

Returns:

```json
{"status":"ok"}
```

## PostgreSQL setup (Render / Railway / local Postgres)

Set these environment variables:

- `SPRING_DATASOURCE_URL` (JDBC format): `jdbc:postgresql://<host>:<port>/<database>`
- `SPRING_DATASOURCE_USERNAME`
- `SPRING_DATASOURCE_PASSWORD`
- `JWT_SECRET`
- `CORS_ALLOWED_ORIGINS` (comma-separated frontend origins)

Example:

```bash
SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/internshiptracker
SPRING_DATASOURCE_USERNAME=postgres
SPRING_DATASOURCE_PASSWORD=postgres
JWT_SECRET=replace-with-a-strong-secret
CORS_ALLOWED_ORIGINS=http://localhost:5173,https://your-frontend.vercel.app
```

Then run:

```bash
./mvnw spring-boot:run
```

## Deploy to Render

1. Push backend repo to GitHub.
2. In Render, create a new Web Service from your repo.
3. Use:
   - Root directory: `internshiptracker`
   - Build command: `./mvnw clean package -DskipTests`
   - Start command: `java -Dserver.port=$PORT -jar target/*.jar`
4. Add env vars:
   - `JWT_SECRET`
   - `SPRING_DATASOURCE_URL` (JDBC postgres URL)
   - `SPRING_DATASOURCE_USERNAME`
   - `SPRING_DATASOURCE_PASSWORD`
   - `CORS_ALLOWED_ORIGINS`
5. After deploy, confirm `https://<your-backend-domain>/api/health`.

`render.yaml` is included at repo root for blueprint-based setup.

## Deploy to Railway

1. Create a new Railway project from your backend repo.
2. Add a PostgreSQL service.
3. Set backend env vars:
   - `JWT_SECRET`
   - `SPRING_DATASOURCE_URL` (JDBC postgres URL)
   - `SPRING_DATASOURCE_USERNAME`
   - `SPRING_DATASOURCE_PASSWORD`
   - `CORS_ALLOWED_ORIGINS`
4. Confirm `https://<your-railway-backend>/api/health`.

## 403 troubleshooting

If signup/login returns `403`, it is usually CORS or wrong API URL:

1. Frontend `NEXT_PUBLIC_API_URL` must point to the deployed backend base URL.
2. Backend `CORS_ALLOWED_ORIGINS` must include your exact frontend origin(s):
   - local: `http://localhost:3000,http://127.0.0.1:3000`
   - vercel: `https://your-frontend.vercel.app,https://*.vercel.app`
3. Restart/redeploy backend after env var updates.
