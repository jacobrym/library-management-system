# Library Management System

A web application for managing a library, built with Spring Boot and Docker Compose. This project demonstrates skills in modern Java development, containerization, and modular design. Currently, it includes a basic `/hello` endpoint, with more features to be added in future commits.

## Features
- Spring Boot application with a simple REST endpoint (`/hello`).
- Dockerized setup with MySQL database.
- Configurable via environment variables.

## Prerequisites
- [Docker](https://docs.docker.com/get-docker/)
- [Docker Compose](https://docs.docker.com/compose/install/)

## How to Run Locally

1. **Clone the repository:**
   ```bash
   git clone https://github.com/jacobrym/library-management-system.git
   cd library-management-system
   ```

2. **Copy the environment variables file:**
   ```bash
   cp .env.example .env
   ```

3. **Edit the `.env` file:**
   Open `.env` in a text editor and set your own values, e.g.:
   ```
   MYSQL_ROOT_PASSWORD=your_root_password
   MYSQL_DATABASE=library_db
   MYSQL_USER=library_user
   MYSQL_PASSWORD=your_user_password
   ```

4. **Start the application:**
   ```bash
   docker compose up --build
   ```
    - The `--build` flag ensures the application image is built before running.

5. **Test the endpoint:**
   Open your browser and navigate to: `http://localhost:8080/hello`. You should see "Hello World".

6. **Stop the application:**
   ```bash
   docker compose down
   ```