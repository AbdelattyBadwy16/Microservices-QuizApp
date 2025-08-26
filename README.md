# Microservices Quiz System  

## 📌 Overview  
This project is built using **Spring Boot + Spring Cloud** and follows the **Microservices Architecture** pattern.  
It consists of multiple independent services communicating through **Eureka Server** and **API Gateway**.  

---

## 🛠️ Technologies Used
- **Java 21**  
- **Spring Boot 3.5.5**  
- **Spring Cloud Gateway**  
- **Netflix Eureka (Service Discovery)**  
- **PostgreSQL** (Database for Quiz Service)  
- **Maven**  

---

## 🏗️ Architecture  
```
                  +--------------------+
                  |   API Gateway      |
                  |  (port: 8765)      |
                  +---------+----------+
                            |
        -----------------------------------------------
        |                                             |
+---------------+                            +-----------------+
| QuestionService|                            |   QuizService   |
| (port: 8081)   |                            |  (port: 8090)   |
+----------------+                            +-----------------+
          |                                          |
          |                                          |
    +-------------+                           +--------------+
 |questiondb(PostgreSQL)|                    |quizdb(PostgreSQL)| 
    +-------------+                           +--------------+

                  +--------------------+
                  |   Eureka Server    |
                  |  (Service Registry)|
                  +--------------------+
```

---

## 📂 Services

### 1️⃣ **API Gateway** (Port 8765)  
- Entry point for all client requests.  
- Routes requests to the appropriate microservices using **Eureka Service Discovery**.  
- Auto routing enabled via **Discovery Locator**.  
- Example request:  
  ```
  http://localhost:8765/quiz-service/quiz/get/3
  ```

---

### 2️⃣ **Eureka Server**  
- Service registry for all microservices.  
- Displays the registered instances and their status via UI.

  ![Uploading Screenshot From 2025-08-26 22-30-35.png…]()


---

### 3️⃣ **Question Service** (Port 8081)  
- Manages questions.  
- Endpoints:  
  ```
  GET  /question/allQuestions
  GET  /question/category/{category}
  POST /question/add
  GET /question/generate
  POST /question/getQuestions
  POST /question/getScore
  ```

---

### 4️⃣ **Quiz Service** (Port 8090)  
- Responsible for quiz creation, retrieval, and submission.  
- Connected to **PostgreSQL** database.  
- Endpoints:  
  ```
  POST /quiz/create
  GET  /quiz/get/{id}
  POST /quiz/submit/{id}
  ```

---
## 📌 Postman Examples  

### 🔹 1. Create a new Quiz  
**POST** `http://localhost:8765/quiz-service/quiz/create`  
```json
{
  "categoryName": "java",
  "numQuestions": 5,
  "title": "Java Basics Quiz"
}
```

---

### 🔹 2. Get Quiz Questions  
**GET** `http://localhost:8765/quiz-service/quiz/get/1`  

**Response Example:**  
```json
[
  {
    "id": 10,
    "questionTitle": "What is JVM?",
    "option1": "Java Virtual Machine",
    "option2": "Java Vendor Machine",
    "option3": "Just Virtual Machine",
    "option4": "None",
    "rightAnswer": null
  },
  {
    "id": 11,
    "questionTitle": "Which keyword is used to inherit a class?",
    "option1": "implement",
    "option2": "this",
    "option3": "extends",
    "option4": "super",
    "rightAnswer": null
  }
]
```

---

### 🔹 3. Submit Quiz Answers  
**POST** `http://localhost:8765/quiz-service/quiz/submit/1`  
```json
[
  { "id": 10, "response": "Java Virtual Machine" },
  { "id": 11, "response": "extends" }
]
```

**Response Example:**  
```json
2
```

---

### 🔹 4. Get Questions by Category  
**GET** `http://localhost:8765/question-service/question/category/java`  

**Response Example:**  
```json
[
  {
    "id": 1,
    "questionTitle": "What is Polymorphism?",
    "option1": "Overloading & Overriding",
    "option2": "Encapsulation",
    "option3": "Inheritance",
    "option4": "Abstraction",
    "rightAnswer": "Overloading & Overriding",
    "difficultyLevel": "Medium",
    "category": "java"
  }
]
```

