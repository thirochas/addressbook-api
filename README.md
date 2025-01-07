# Address Book Application

This is a Spring Boot application that manages an address book. It allows you to import data from a file, count the number of males, find the oldest person, and calculate the days difference between two people's birth dates.

## Features

- Import data from a file
- Count the number of males
- Find the oldest person
- Calculate the days difference between two people's birth dates

## Technologies Used

- Java
- Spring Boot
- Gradle

## Getting Started

### Prerequisites

- Java 11 or higher
- Gradle

### Installation

1. Clone the repository:
   ```sh
   git clone https://github.com/thirochas/address-book.git
   cd address-book
   ```

2. Build the project:
   ```sh
   ./gradlew build
   ```

3. Run the application:
   ```sh
   ./gradlew bootRun
   ```

## Usage

### Import Data

To import data from a file, send a POST request to `/api/addressbook/import` with the file as a form-data parameter named `file`.

Example:
```sh
curl -X POST -F "file=@path/to/your/file.txt" http://localhost:8080/api/addressbook/import
```

### File Format

The file should contain data in the following format:
```
Bill McKnight, Male, 16/03/77
Paul Robinson, Male, 15/01/85
Gemma Lane, Female, 20/11/91
Sarah Stone, Female, 20/09/80
Wes Jackson, Male, 14/08/74
```

### Endpoints

- `POST /api/addressbook/import`: Imports data from a file and returns answers to predefined questions.

## Running Tests

To run the tests, use the following command:
```sh
./gradlew test
```

The tests cover the following scenarios:
- Counting the number of males in the list.
- Finding the oldest person in the list.
- Calculating the days difference between two people's birth dates.
- Handling the case where one of the names does not exist in the list.

## License

This project is licensed under the MIT License. See the LICENSE file for details.