# SmartPark System

A client-server car parking lot management system. Built with **JavaFX** on the client, an **OCSF**-based custom server, and **Hibernate/MySQL** for persistence. Client and server communicate over a shared `EventBus` (mediator pattern) for decoupled, event-driven updates.

## Features

- **Parking operations** — enter/exit with or without a reservation, standard and full memberships, spot reservations and cancellations
- **Roles** — customer, parking lot employee, customer service employee, manager, and general manager, each with a dedicated dashboard
- **Membership & billing** — sign-up, membership purchase/renewal, balance tracking, and payment flows
- **Requests & approvals** — price-change requests routed from managers to the general manager for approval
- **Complaints** — customers submit complaints; staff track and respond to them
- **Reporting** — orders, complaints, and disabled-spot reports, generated on request and reviewed by management
- **Spot management** — disable/enable individual spots and track their history

## Project structure

| Module | Description |
|---|---|
| [`client/`](client) | JavaFX desktop client. Uses `EventBus` to pass events between the network layer (`SimpleClient`) and UI controllers. |
| [`server/`](server) | OCSF-based server handling client requests and business logic. |
| [`entities/`](entities) | Shared module: JPA entities and message DTOs used by both client and server. |

## Prerequisites

- JDK 15+
- Maven
- MySQL server

## Setup

1. Copy the Hibernate config template and fill in your local database credentials:
   ```
   cp server/src/main/resources/hibernate.properties.example server/src/main/resources/hibernate.properties
   ```
   Then edit `hibernate.properties` with your MySQL URL, username, and password. This file is git-ignored — never commit real credentials.

2. Create the database referenced in `hibernate.connection.url` (default: `cps-db`). Hibernate will create the tables automatically (`hibernate.hbm2ddl.auto = create`).

3. Build all modules from the project root:
   ```
   mvn install
   ```

## Running

1. Start the server:
   ```
   cd server
   mvn exec:java
   ```
2. Start the client:
   ```
   cd client
   mvn javafx:run
   ```
