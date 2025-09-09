# ✉️ Mail Verification

<div align="center">
  <h3>⚡ Architecture Schema </h3>
  <img src="./images/schema-1.png" alt="architecture schema">
</div>

<br>

<details>
<summary> 👤 Create Account </summary>
<img src="./images/save.png" alt="save">
</details>

<br>

<details>
<summary> ✅ Verify Account </summary>
<img src="./images/verify.png" alt="verify">
<img src="./images/verify-1.png" alt="verify-1">
</details>

<br>

<details>
<summary> 📧 Email Notification </summary>
<img src="./images/email.png" alt="email">
</details>

<br>

<details>
<summary> ❌ Unverified </summary>
<img src="./images/non-verify.png" alt="non-verify">
</details>

<br>

<details>
<summary> ⚠️ Invalid OTP </summary>
<img src="./images/invalid.png" alt="invalid">
</details>

<br>

<details>
<summary> 🔄 Re-verify </summary>
<img src="./images/reverify.png" alt="reverify">
</details>

<br>

<details>
<summary>🐳 Docker </summary>
<img src="./images/docker.png" alt="docker">
</details>

<br>

<details>
<summary> 📚 Swagger UI </summary>
<img src="./images/swagger-ui.png" alt="swagger-ui">
</details>

<br>

## 📖 About

- ✅  This project demonstrates a secure user registration system with email-based OTP (One-Time Password) verification. Users can create accounts and verify their email addresses through a time-limited verification code sent via email.

<br>

## 🛠️ Technologies Used

[![Java](https://img.shields.io/badge/Java-17-000?style=for-the-badge&logo=openjdk&logoColor=white&color=FF9A00)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.5-000?style=for-the-badge&logo=springboot&logoColor=white&color=6DB33F)](https://spring.io/projects/spring-boot)
[![MySQL](https://img.shields.io/badge/MySQL-8.0-000?style=for-the-badge&logo=mysql&logoColor=white&color=4479A1)](https://www.mysql.com/)
[![PostgreSQL](https://img.shields.io/badge/PostgreSQL-15.2-000?style=for-the-badge&logo=postgresql&logoColor=white&color=4169E1)](https://www.postgresql.org/)
[![Adminer](https://img.shields.io/badge/Adminer-4.8-000?style=for-the-badge&logo=adminer&logoColor=white&color=34567C)](https://www.adminer.org)
[![Gradle](https://img.shields.io/badge/Gradle-9.0-000?style=for-the-badge&logo=gradle&logoColor=white&color=02303A)](https://gradle.org/)
[![RabbitMQ](https://img.shields.io/badge/RabbitMQ-3.0-000?style=for-the-badge&logo=rabbitmq&logoColor=white&color=FF6600)](https://www.docker.com/)
[![Docker](https://img.shields.io/badge/Docker-28.3-000?style=for-the-badge&logo=docker&logoColor=white&color=2496ED)](https://www.docker.com/)

<br>

### 🚀 Quick Start
#### Prerequisites
- Java 17+
- MySQL 8.0
- PostgreSQL 15.2
- Docker & Docker Compose

#### Database Configuration

- **MySQL**
- **URL:** `http://localhost:9090` (Adminer)
- **Database:** `email_db`
- **Username:** `mysql`
- **Password:** `mysql`

<br>

- **PostgreSQL**
- **URL:** `http://localhost:9090` (Adminer)
- **Database:** `email_db`
- **Username:** `postgres`
- **Password:** `postgres`

### Installation

```shell
git clone https://github.com/furkankayam/spring-boot-email-verification.git
cd spring-boot-email-verification
```

<br>

### Usage

```shell
docker-compose up
```

```shell
./gradlew build
```

```shell
./gradlew :mail-service:bootRun
./gradlew :account-service:bootRun
```

#### 3. Access endpoints

- Database UI: `http://localhost:9090`
- Mail Service `http://localhost:8082/swagger-ui/index.html`
- Account Service: `http://localhost:8081/swagger-ui/index.html`

<br>

# License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details

**Created by** [Mehmet Furkan KAYA](https://www.linkedin.com/in/mehmet-furkan-kaya/)
