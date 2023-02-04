# Ignitis Message Application
**Note** There exists fatal flaw. The datatypes in certain places are not compatible. 
And parsing Long to Integer might throw exception in queries sections. I will fix that.

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
To log in as simple user you need to pass any of these three JSON bodies into
POST user/login/ endpoint:

``
{
"email": "person@gmail.com",
"password": "slaptazodis"
}
``

or

``
{
"email": "person2@gmail.com",
"password": "secret"
}
``

or

``
{
"email": "mock@gmail.com",
"password": "secret1"
}
``


User with ROLE_USER can write messages (POST) and receive the messages.

Database Relations graph is given below:

![DB schema](/DBSchema.PNG)
