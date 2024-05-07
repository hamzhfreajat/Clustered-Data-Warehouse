# Clustered Data Warehouse

## Project Summary
## Overview

Our project involves developing a data warehouse for Bloomberg to analyze FX deals. As part of our Scrum team, one of our key customer stories is to accept deal details and persist them into a database.

## Technology Stack

- **Spring Boot**: Version 2.7.13
- **Java**: JDK 8
- **Database**: PostgreSQL 16
- **Build Tool**: Maven

## Getting Started
### Configure Application

### Setup Database

1. Install PostgreSQL and create a database named `forex`.
2. Ensure PostgreSQL is running on `localhost:5432`.
3. Use the following credentials:
    - Username: `postgres`
    - Password: `123456`
    - 
Modify `src/main/resources/application.properties` to update database configurations if different from the defaults provided:


## API Usage

### Endpoints

- **POST /api/v2/deals/importDeal** - Imports a new deal transaction.

#### Request Body

```json
{
  "dealUniqueId": "36",
  "fromCurrencyISOCode": "JOD",
  "toCurrencyISOCode": "SAR",
  "dealTimestamp": "2024-05-05T12:00:00",
  "dealAmountInOrderingCurrency": 100
} 
```
#### Response

- `200 OK` - If the deal is successfully imported.
- `500 Internal Server Error` - If incorrect data is sent.
- `400 Bad Request` - If required fields are missing.


## Test Cases

### Test Case 1: Save Deal

This test case ensures that a deal can be successfully imported.

- **Test Method:** `saveDealTest`
- **Description:** Imports a deal with valid data and expects a successful response.
- **Request:**
   - **Method:** POST
   - **Endpoint:** `/api/v2/deals/importDeal`
   - **Content-Type:** `application/json`
   - **Body:**
     ```json
     {
       "dealUniqueId": "36",
       "fromCurrencyISOCode": "JOD",
       "toCurrencyISOCode": "SAR",
       "dealTimestamp": "2024-05-05T12:00:00",
       "dealAmountInOrderingCurrency": 100
     }
     ```
- **Expected Response:**
   - **Status Code:** 200 OK
   - **Body:** `Deal imported successfully`

### Test Case 2: Wrong Data Error

This test case verifies the handling of incorrect data during deal import.

- **Test Method:** `testWrongDataError`
- **Description:** Imports a deal with incorrect currency code and expects a server error response.
- **Request:**
   - **Method:** POST
   - **Endpoint:** `/api/v2/deals/importDeal`
   - **Content-Type:** `application/json`
   - **Body:**
     ```json
     {
       "dealUniqueId": "18",
       "fromCurrencyISOCode": "JODsdadads",
       "toCurrencyISOCode": "SAR",
       "dealTimestamp": "2024-05-05T12:00:00",
       "dealAmountInOrderingCurrency": 100
     }
     ```
- **Expected Response:**
   - **Status Code:** 5xx Server Error
   - **Body:** `{"error": "Transaction silently rolled back because it has been marked as rollback-only"}`

### Test Case 3: Missing Field

This test case ensures proper handling of missing fields during deal import.

- **Test Method:** `testMissingField`
- **Description:** Imports a deal with a missing required field and expects a client error response.
- **Request:**
   - **Method:** POST
   - **Endpoint:** `/api/v2/deals/importDeal`
   - **Content-Type:** `application/json`
   - **Body:**
     ```json
     {
       "dealUniqueId": "18",
       "fromCurrencyISOCode": null,
       "toCurrencyISOCode": "SAR",
       "dealTimestamp": "2024-05-05T12:00:00",
       "dealAmountInOrderingCurrency": 100
     }
     ```
- **Expected Response:**
   - **Status Code:** 4xx Client Error
   - **Body:** `{"message": "fromCurrencyISOCode field is required"}`

## Running Docker Compose

Follow these steps to run the Docker-compose based project after downloading it:

1. **Ensure Docker is Installed:**
   Make sure Docker is installed on your system. If not, download and install Docker from the official website: [Docker](https://www.docker.com/get-started)

2. **Navigate to Project Directory:**
   Open a terminal and navigate to the directory where you downloaded the project.

3. **Run Docker Compose:**
   Execute the following command to build and run the Docker containers defined in the `docker-compose.yml` file:
   ```bash
   docker-compose up -d
### Clone the Repository

```bash
git clone [repository-url]
cd [project-folder]
