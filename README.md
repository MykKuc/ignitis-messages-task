# Ignitis Message Application
This is an application intended for managing messages, creating, sending, receiving the messages.

It was created using Spring Boot, Liquibase, Spring Data JPA, Spring Security, JWT, Gradle
and H2 (DBMS).

### Admin user login
To log in as admin user you need to pass this JSON body into
POST user/login/ endpoint: 

``
{
    "email": "admin@gmail.com",
    "password": "admin"
}
``

User with ROLE_ADMIN can create other users with ROLE_USER and it can also
delete the user and get the statistics about all the messages.

Database Relations graph is given below:
![DB schema](/DBSchema.PNG)
