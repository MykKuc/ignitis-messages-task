# Ignitis Message Application
This is an application intended for managing messages, creating, sending, receiving the messages.

It was created using Spring Boot, Liquibase, JOOQ , Spring Security, JWT, Gradle
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

### Simple User login
To log in as simple user you need to pass this JSON body into
POST user/login/ endpoint:

``
{
"email": "person@gmail.com",
"password": "slaptazodis"
}
``
User with ROLE_USER can write messages (POST) and receive the messages.

**Note** - Password encoding. Passwords of mock users are not encoded, but passwords of newly created
users will get encoded.

Database Relations graph is given below:
![DB schema](/DBSchema.PNG)
