<<<<<<< HEAD
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
=======
# 🏥 Smart Hospital Appointment System

## 📌 Project Overview

The **Smart Hospital Appointment System** is a web-based application designed to make hospital appointment management easier and faster. It allows patients to access the system, manage their accounts, and book appointments with doctors. It also helps manage doctor and patient information.

The main goal of this project is to reduce manual appointment booking and provide a simple, user-friendly interface for hospital appointment management.

## 🎯 Objectives

* Make hospital appointment booking easier.
* Provide a simple and user-friendly interface.
* Manage patient and doctor information.
* Support secure user login and registration.
* Reduce manual work in appointment management.

## ✨ Features

* **User Registration:** Allows users to create an account.
* **User Login:** Provides login functionality.
* **Patient Dashboard:** Provides access to patient-related features.
* **Doctor Dashboard:** Provides access to doctor-related features.
* **Appointment Management:** Supports hospital appointment-related operations.
* **Database Integration:** Uses a database to store application data.

## 🛠️ Technologies Used

### Backend

* Java
* Spring Boot
* Spring Security
* Spring Data JPA
* Maven

### Frontend

* HTML
* CSS
* JavaScript
* Thymeleaf

### Database

* MySQL

### Tools

* Visual Studio Code
* Git
* GitHub
* Render

## 📂 Project Structure

```text
Smart-Hospital-Appointment-System/
│
├── backend/
│   ├── src/
│   │   └── main/
│   │       ├── java/
│   │       └── resources/
│   │           ├── static/
│   │           │   ├── css/
│   │           │   └── js/
│   │           ├── templates/
│   │           └── application.properties
│   └── pom.xml
│
├── frontend/
│   ├── index.html
│   ├── dashboard.html
│   ├── style.css
│   └── script.js
│
└── README.md
```

## ⚙️ Requirements

Before running the project, install:

* Java JDK 21 or a compatible version supported by the project.
* Maven, or use the Maven Wrapper if included.
* MySQL Server.
* Visual Studio Code or another Java-compatible IDE.

## 🚀 How to Run the Project Locally

### Step 1: Clone the Repository

```bash
git clone https://github.com/24eg105r50-25/Smart-Hospital-Appointment-System.git
```

### Step 2: Open the Project

```bash
cd Smart-Hospital-Appointment-System
```

### Step 3: Configure the Database

Create a MySQL database for the application.

```sql
CREATE DATABASE hospital_db;
```

Configure the database connection in `backend/src/main/resources/application.properties`.

Example configuration:

```properties
spring.datasource.url=${DB_URL:jdbc:mysql://localhost:3306/hospital_db}
spring.datasource.username=${DB_USERNAME:root}
spring.datasource.password=${DB_PASSWORD}
spring.jpa.hibernate.ddl-auto=update
```

Set `DB_PASSWORD` to your local MySQL password. Update the database name and other settings to match your actual configuration.

**Security note:** Do not upload real database passwords or other secrets to GitHub.

### Step 4: Run the Backend

Open a terminal in the backend directory:

```bash
cd backend
```

On Windows PowerShell:

```powershell
.\mvnw.cmd spring-boot:run
```

If the Maven Wrapper is unavailable, install Maven and run:

```bash
mvn spring-boot:run
```

### Step 5: Open the Application

Once the Spring Boot application starts successfully, open:

```text
http://localhost:8080
```

The exact pages and routes depend on the application's controller configuration.

## ☁️ Deployment

The backend can be deployed using a hosting platform such as Render.

Deployment requires:

1. A correctly configured Java build environment or Dockerfile.
2. The correct backend root directory.
3. A configured production database.
4. Environment variables for database credentials.
5. A successful application build and startup.

The application will be accessible through the public URL provided by the hosting platform after deployment succeeds.

## 🔒 Security

* Use secure password handling.
* Keep database credentials private.
* Configure authentication and authorization appropriately.
* Use environment variables for production secrets.
* Enable HTTPS for public deployments.

## 🔮 Future Enhancements

* Online appointment booking and cancellation.
* Doctor availability and scheduling.
* Appointment status tracking.
* Email or SMS appointment notifications.
* Patient medical history management.
* Online payment integration.
* Improved administrative dashboard.

## 👩‍💻 Project Information

**Project Name:** Smart Hospital Appointment System
**Project Type:** Web Application
**Purpose:** Simplify hospital appointment and information management.

## 📄 License

This project is intended for educational and demonstration purposes. Add a license if you plan to distribute or reuse the project publicly.
>>>>>>> bcd2ce809b79020c4779aaa106071b5c2858f4c4
