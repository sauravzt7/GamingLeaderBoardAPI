# Gaming Leaderboard API

## Project Overview
This project is a **Gaming Leaderboard Management System** that helps maintain scores and rankings for players in games. It allows players to submit their scores, retrieve their rankings, and view leaderboard data.

The API backend is built using **Spring Boot** with the following key features:
- Score submission API
- Rank retrieval for a specific user
- Leaderboard queries to fetch top ranking players
- Database mapping with **Spring Data JPA**

---

## Table of Contents
1. [Technology Stack](#technology-stack)
2. [API Design](#api-design)
3. [Database Schema Design](#database-schema-design)
4. [How to Run the Project](#how-to-run-the-project)

---

## Technology Stack
- **Backend Framework**: Spring Boot
- **Database**: H2 (In-memory) / MySQL (Production-ready)
- **ORM**: Spring Data JPA
- **Lombok**: Simplifies boilerplate code generation for entities like getters, setters, etc.
- **Java Version**: Java 17+ (or compatible SDK version)

---

## API Design

### Base URL
`http://localhost:8080/api/leaderboard`

### Endpoints

1. **Submit a Score**
    - **URL**: `/submit`
    - **Method**: POST
    - **Request Payload**:
```json
{
       "userId": 1,
       "score": 150
     }
```
- **Response**:  
  **Status 200**: "Score successfully submitted."

- **Explanation**: Submits the score for a specific user. The service will update the user's total score and recalculate rankings.

2. **Get Ranking for a User**
    - **URL**: `/rank/{userId}`
    - **Method**: GET
    - **Path Variable**:
        - `userId` (Long): ID of the user whose rank is requested
    - **Response**:
```json
{
       "userId": 1,
       "rank": 5
     }
```
- **Explanation**: Returns the leaderboard rank of the requested user.

3. **Get Top 10 Players**
    - **URL**: `/top`
    - **Method**: GET
    - **Response**:
```json
[
       {
         "userId": 1,
         "username": "player1",
         "totalScore": 800,
         "rank": 1
       },
       ...
     ]
```
- **Explanation**: Fetches the top 10 players based on their total scores.

---

## Database Schema Design

### Table: `users`
| Column Name   | Data Type      | Constraints                     |  
|---------------|----------------|----------------------------------|  
| `id`          | BIGINT         | Primary Key, Auto Increment      |  
| `username`    | VARCHAR(255)   | Unique, Not Null                 |  
| `join_date`   | TIMESTAMP      | Not Null                         |  

### Table: `leaderboards`
| Column Name    | Data Type      | Constraints                     |  
|----------------|----------------|----------------------------------|  
| `id`           | BIGINT         | Primary Key, Auto Increment      |  
| `user_id`      | BIGINT         | Foreign Key (users.id), Not Null |  
| `total_score`  | INT            | Not Null                         |  
| `rank`         | INT            |                                  |  

### Table: `game_sessions`
| Column Name   | Data Type      | Constraints                     |  
|---------------|----------------|----------------------------------|  
| `id`          | BIGINT         | Primary Key, Auto Increment      |  
| `user_id`     | BIGINT         | Foreign Key (users.id), Not Null |  
| `score`       | INT            | Not Null                         |  
| `game_mode`   | VARCHAR(255)   | Not Null                         |  
| `timestamp`   | TIMESTAMP      | Not Null                         |  

---

## How to Run the Project

### Prerequisites
- Java 17+
- Maven
- MySQL (if using production database)

### Steps
1. Clone this repository:
```shell script
git clone <repository-url>  
   cd <repository-folder>
```

2. Build the project using Maven:
```shell script
mvn clean install
```

3. Run the Spring Boot application:
```shell script
mvn spring-boot:run
```

4. Access the APIs using Postman or any HTTP client with the base URL `http://localhost:8080/api/leaderboard`.

### Optional: Use H2 Database Console (for Dev Mode)
Visit `http://localhost:8080/h2-console`
- JDBC URL: `jdbc:h2:mem:testdb`
- Username/Password: Provided in `application.properties`

---

This documentation provides an overview of the system functionality and API usage. For further details, feel free to explore the codebase.  