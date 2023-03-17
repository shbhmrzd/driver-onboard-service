# API Documentation for Driver Onboard Service

This API documentation covers all the available endpoints of the Driver Onboard Service.

## Base URL

The base URL for all the endpoints is `http://localhost:8080`.

## Endpoints

### Driver Controller :  /driver
#### Sign up as a new driver
HTTP Method: POST
Endpoint: /signup

Request Body:
- driverRequest: JSON object containing driver's information


Response Body:
- driverResponse: JSON object containing the newly created driver's information


#### Get a driver by username

HTTP Method: GET
Endpoint: /driver/{username}

Path Variables:
- username: Unique username of the driver to retrieve (String)

Response Body:
- driverResponse: JSON object containing the driver's information
    - firstName: string
    - lastName: string
    - documentId (optional): ID of the driver's license (Long)
    - backgroundCheckStatus (optional): Status of the driver's background check (string)

Status Codes:
- 200 OK: Driver found and retrieved successfully
- 404 Not Found: Driver with the specified ID not found

#### Update a driver by username

HTTP Method: PUT
Endpoint: /driver/{username}

Path Variables:
- username: Unique username of the driver to update (String)

Status Codes:
- 200 OK: Driver updated successfully
- 400 Bad Request: Usernmae in request body does not match username in path variable
- 404 Not Found: Driver with the specified username not found


### Document Controller :  /document

#### Upload a document

HTTP Method: POST
Endpoint: /upload

Request Parameters:
- file: The document to be uploaded (MultipartFile)
- username: Unique username of the driver associated with the document (String)

Request Body: None

Response Body:
- documentUploadResponse: JSON object containing the document upload response information
    - id: ID of the uploaded document (Long)
    - status: Status of the upload process (string)

Status Codes:
- 201 Created: Document uploaded successfully
- 400 Bad Request: Missing or invalid request parameters
- 500 Internal Server Error: Error uploading the document


#### Get a document by ID

HTTP Method: GET
Endpoint: /{id}

Path Variables:
- id: ID of the document to retrieve (Long)

Response Body:
- binary file: Binary file of the document (PDF)

Response Headers:
- Content-Type: application/octet-stream
- Content-Disposition: attachment; filename="file{id}.pdf"

Status Codes:
- 200 OK: Document retrieved successfully
- 404 Not Found: Document with the specified ID not found


### Background Check Controller :  /background_check
This API is used to initiate a background verification process for a driver.

#### API Endpoint:
POST /initiate

Request Parameters:
- driverId (Long) (required): The ID of the driver to initiate the background check for.

Response:
- HTTP Status: 201 Created
- Body: BackgroundCheckDTO object
