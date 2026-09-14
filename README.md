# Restful Booker API Tests

Automated API tests for [Restful Booker](https://restful-booker.herokuapp.com/) using REST Assured, Cucumber, JUnit, and request helper classes.

## Project Information

| Field | Details |
|-------|---------|
| **Author** | Susan Laime Lucero |
| **Course** | Certification II - Universidad Privada Boliviana |
| **Instructor** | Mauricio Viscarra Rivera |
| **Repository** | https://github.com/SusanLaime/RestfulBookerTest-AutomatedFramework.git|

## Requirements Covered

- REST API testing with REST Assured
- JUnit test execution
- Cucumber feature files and step definitions
- GET, POST, PUT, and DELETE booking operations
- Authentication with the Restful Booker token
- Cucumber `Background`
- Cucumber `DataTable` request data
- Response status-code and field-value validation
- Extent HTML, Spark HTML, and PDF reports

## Tech Stack

- **Java 26** - Programming language
- **Maven** - Build tool
- **JUnit 5** - Test support
- **REST Assured 6** - API testing library
- **Cucumber 7** - BDD test framework
- **Jackson** - JSON serialization
- **ExtentReports** - Test reporting

## Getting Started

### Prerequisites

- Java 26 or newer
- Maven 3.6 or newer
- Internet connection

### Run Tests

From the project root, execute:

```bash
mvn test
```

Alternatively, run `TestRunner.java` from IntelliJ IDEA. Individual `.feature` files can also be executed directly.

The tests communicate with the Restful Booker API and validate booking creation, retrieval, update, and deletion.

## Test Coverage

### GET Booking Feature

- Retrieves a booking by ID.
- Verifies the booking response structure and expected values.

### POST Booking Feature

- Creates a new booking.
- Verifies the returned booking details for `firstname` and `lastname`.

### PUT Booking Feature

- Authenticates with the API.
- Updates an existing booking by ID.
- Verifies the HTTP status code and updated booking values.

### DELETE Booking Feature

- Authenticates with the API.
- Deletes an existing booking.
- Verifies the `200` response.
- Verifies the `404` response for an invalid booking ID.

## Project Structure

```text
src/
├── main/java/
│   └── org/example/
│       └── Main.java
└── test/
    ├── java/
    │   ├── TestRunner.java
    │   ├── BookingsCRUD.java
    │   ├── constants/
    │   │   └── RestfulBookerEndpoints.java
    │   ├── entities/
    │   │   ├── Booking.java
    │   │   └── BookingDates.java
    │   ├── stepDefinitions/
    │   │   └── BookingSteps.java
    │   └── util/
    │       └── Request.java
    └── resources/
        ├── GetBookings.feature
        ├── PostBookings.feature
        ├── PutBookings.feature
        └── DeleteBokking.feature
```

## Reports

After test execution, reports are generated in:

```text
test-output/
├── HtmlReport/Extent.html
├── SparkReport/Spark.html
└── PdfReport/ExtentPdf.pdf
```

Open the HTML reports in a browser and the PDF report with any PDF reader.
