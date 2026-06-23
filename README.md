# Helpdesk Ticketing System

## Overview

The Helpdesk Ticketing System is a Spring Boot REST API designed to manage support tickets within an organization. The system allows users to create tickets, assign them to support agents, update ticket status, add comments, track status history, and monitor SLA compliance.

---

## Assumptions

The following assumptions were made during implementation:

* A ticket must be created by a valid user.
* A ticket can be assigned to only one agent.
* A ticket may contain multiple comments.
* Ticket status changes must follow a predefined state machine.
* Invalid status transitions are rejected with HTTP 409 Conflict.
* SLA rules are based on ticket priority.
* Resolution time is calculated using the ticket creation and resolution timestamps.

---

## SLA Rules

| Priority | SLA     |
| -------- | ------- |
| CRITICAL | 4 Hours |
| HIGH     | 1 Day   |
| MEDIUM   | 3 Days  |
| LOW      | 7 Days  |

A ticket is considered overdue if it remains unresolved after its SLA limit.

---

## Ticket State Machine

```text
OPEN
   ↓
IN_PROGRESS
   ↓
RESOLVED
   ↓
CLOSED

CLOSED
   ↓
REOPENED
   ↓
RESOLVED
```

Invalid transitions are not allowed.

Examples:

```text
OPEN → CLOSED ❌
OPEN → IN_PROGRESS ✅
IN_PROGRESS → RESOLVED ✅
RESOLVED → CLOSED ✅
CLOSED → REOPENED ✅
```

---

## Technologies Used

* Java 21
* Spring Boot 3
* Spring Data JPA
* Oracle Database 19c
* Maven
* Postman

---

## Project Structure

```text
controller
dto
enums
exception
model
repository
service
```

---

## Database Entities

### User

Stores ticket creators.

### Agent

Stores support agents.

### Ticket

Stores ticket information, priority, status, category, and timestamps.

### Comment

Stores comments related to tickets.

### TicketStatusHistory

Stores all ticket status transitions.

---

## API Endpoints

### Create User

```http
POST /api/users
```

### Create Agent

```http
POST /api/agents
```

### Create Ticket

```http
POST /api/tickets
```

### Assign Ticket

```http
POST /api/tickets/{ticketId}/assign
```

### Update Ticket Status

```http
POST /api/tickets/{ticketId}/status
```

### Add Comment

```http
POST /api/tickets/{ticketId}/comments
```

### Get Ticket Details

```http
GET /api/tickets/{ticketId}
```

### Search Tickets

```http
GET /api/tickets?status=&priority=&category=&assignedTo=
```

### Average Resolution Time

```http
GET /api/tickets/metrics/avg-resolution-time
```

### List Overdue Tickets

```http
GET /api/tickets/overdue
```

### Ticket Status History

```http
GET /api/tickets/{ticketId}/history
```

---

## Running the Application

1. Create an Oracle database user.
2. Configure the database connection in `application.properties`.
3. Run the application:

```bash
mvn clean install
mvn spring-boot:run
```

The application will start on:

```text
http://localhost:8080
```

---

## Configuration Example

```properties
spring.datasource.url=jdbc:oracle:thin:@localhost:1522/orclpdb
spring.datasource.username=YOUR_USERNAME
spring.datasource.password=YOUR_PASSWORD

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

---

## Testing

The API was tested using Postman.

Tested scenarios include:

* Create User
* Create Agent
* Create Ticket
* Assign Ticket
* Add Comment
* Update Ticket Status
* Invalid Status Transitions
* Ticket Status History
* Search Tickets
* Average Resolution Time
* Overdue Tickets
* Resource Not Found Cases

---

## Future Improvements

* Authentication and Authorization
* Email Notifications
* Multiple Agent Assignment
* Dashboard and Reporting
* File Attachments
