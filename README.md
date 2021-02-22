TodoApp
This project was generated with Java EE (Tomcat).

Development server
Run dev server(Tomcat server). 
Navigate to http://localhost:8080/spilApp/api/v1/users/*.
The app can be tested via Postman.

Add a new user
POST
URL - http://localhost:8080/spilApp/api/v1/users/
Body - JSON

Get all users
GET
URL - http://localhost:8080/spilApp/api/v1/users/

Get a user by ID
GET
URL - http://localhost:8080/spilApp/api/v1/users/<User ID>

Update user
PUT - http://localhost:8080/spilApp/api/v1/users/<User ID>
Body - JSON (Without the ID)

Delete user
DELETE - http://localhost:8080/spilApp/api/v1/users/<User ID>

Database has been set to be created automatically.
