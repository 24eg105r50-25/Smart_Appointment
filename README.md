# Smart Hospital Appointment System

A Spring Boot web app where patients book appointments with doctors, doctors manage them, and admins oversee everything.

```
Smart-Hospital-Appointment-System/
├── backend/      Spring Boot 3.3 (Java 17) + Thymeleaf + JPA + Spring Security + MySQL
│   ├── src/
│   ├── pom.xml
│   └── Dockerfile
├── frontend/     Static login page that talks to the JSON API (POST /api/auth/login)
└── README.md
```

## Features
- Register / log in with roles: **PATIENT**, **DOCTOR**, **ADMIN** (BCrypt passwords, role-based access)
- Patients: pick a doctor, book a date and time, view and cancel their appointments
- Doctors: see only their own appointments and mark them completed
- Admin: overview of doctors, patient count and all appointments
- Double-booking protection: a doctor's time slot can't be booked twice

## Run locally
Requirements: Java 17, Maven, MySQL 8.

```bash
cd backend
mvn spring-boot:run
```
Open http://localhost:8080 . MySQL defaults: `root` / `password` on `localhost:3306` (database is created automatically). Override with the environment variables `DB_URL`, `DB_USERNAME`, `DB_PASSWORD`.

A default admin is created on first start: **admin / admin123**. Change it with `ADMIN_USERNAME` / `ADMIN_PASSWORD`.

## Run with Docker
```bash
docker network create hospital-net

docker run -d --name hospital-db --network hospital-net \
  -e MYSQL_ROOT_PASSWORD=password -e MYSQL_DATABASE=smart_hospital mysql:8

cd backend
docker build -t smart-hospital .
docker run -d --name hospital-app --network hospital-net -p 8080:8080 \
  -e DB_URL="jdbc:mysql://hospital-db:3306/smart_hospital?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC" \
  -e DB_USERNAME=root -e DB_PASSWORD=password \
  smart-hospital
```
(MySQL needs ~30 seconds to start; if the app exits early, run `docker start hospital-app` again.)

## Frontend
Open `frontend/index.html` in a browser (or serve the folder with any static server) while the backend is running. It signs in via `POST http://localhost:8080/api/auth/login`. The full booking features live in the backend's server-rendered pages at http://localhost:8080 .

## Quick walk-through
1. Register a **Doctor** account at `/register`.
2. Register a **Patient** account, log in, and book an appointment.
3. Log in as the doctor and click **Complete**.
4. Log in as `admin` to see everything.

## Notes
- Anyone can self-register as a Doctor. For real use, add an admin approval step.
- The JSON login endpoint only checks credentials; it does not issue a token or session.
