# Appointment Booking Service (Kotlin + Spring Boot)

This is a backend microservice built using **Kotlin**, **Spring Boot**, and **MySQL** to manage appointment bookings between clients and professionals.

It supports features such as:
- Creating bookings with overlap prevention
- Viewing bookings (with filters)
- Checking a professional’s availability
- Deleting bookings

---

## 📌 Endpoints Summary

### ✅ `POST /bookings`
- Creates a new booking
- Rejects overlaps (returns `409 Conflict`)
- ✅ Returns `201 Created` on success

### ✅ `GET /bookings`
- Lists all bookings
- Filters supported: `clientId`, `professionalId`, `date`

### ✅ `DELETE /bookings/{id}`
- Deletes a booking by ID
- ✅ Returns `204 No Content` on success
- ❌ Returns `404 Not Found` if ID doesn’t exist

### ✅ `GET /bookings/availability`
- Shows available time slots for a professional on a specific date

---

## ⚙️ Tech Stack

- Kotlin
- Spring Boot
- Spring Data JPA
- MySQL
- Caching with Spring Cache (for availability endpoint)
- Gradle

---

## 🛠️ How to Run

### 📦 Prerequisites
- JDK 17+
- MySQL Server running on port 3306
- IntelliJ IDEA (recommended)
- Postman (for testing)

### 🚀 Steps

1. Clone the repo
   ```bash
   git clone https://github.com/your-username/appointment-booking-service.git
   cd appointment-booking-service

2. Create MySQL database - CREATE DATABASE bookingservice;

3. Update `application.properties` if needed:
   File: `src/main/resources/application.properties`
   
   ```properties
    spring.datasource.url=jdbc:mysql://localhost:3306/bookingservice
    spring.datasource.username=root
    spring.datasource.password=your_password
    server.port=1010
   ```

   
5. Run the project:
   From IntelliJ: Run `BookingserviceApplication.kt`

6. test the endpoints using Postman on `http://localhost:1010`
   

## 📤 Sample JSON Request (for POST /bookings)

   ```json
   {
     "clientId": 2,
     "professionalId": 101,
     "startTime": "2025-06-07T10:00:00",
     "endTime": "2025-06-07T11:00:00"
   }
   ```

⚠️ Note: Some parts of this backend assignment were accelerated using ChatGPT for implementation assistance and verification. All APIs have been tested successfully using Postman.
