# simpleCrudProject


#server port
server.port=8081

### Requirements
For building and running the application you need:

* JDK 17
* Maven
* SpringBoot 3.0.5

### To create a new  user
  you can use postman by url:  http://localhost:8081/auth/register
  and body: 
  {
  "username":"admin" ,
  "password":"admin",
  "email":"z@isc.com",
  "firstName":"zahra",
  "lastName":"tgz",
  "role": "ADMIN"
  }
  so generate a token.
  
### To view Log file of project
 a file by name crudApp.log created.you can change level  or ...  at application.properties file.
 
### To view Swagger 2 API docs
 Run the server and browse to url: http://localhost:8081/swagger-ui.html

### To view your H2 in-memory database
 To view and query the database you can browse to http://localhost:8081/h2-console.
