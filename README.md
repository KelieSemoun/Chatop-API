# Estate

This project was generated with [Angular CLI](https://github.com/angular/angular-cli) version 14.1.0.

## Start Frontend

Git clone:

> git clone : https://github.com/KelieSemoun/Chatop-API

Go inside folder:

> cd Chatop-API

Install dependencies:

> npm install

Launch Front-end:

> npm run start;

## Setup Backend

Create a MySQL Database using MySQL CLI with the following commands :
```
CREATE DATABASE myDatabase;
USE myDatabase;
```
SQL script for creating the schema is available `ressources/sql/script.sql`

Go to backend/src/main/resources and create a file named "env.properties" where you put the following lines :
```
DB_USER=[YOUR DATABASE USERNAME]
DB_PASSWORD=[YOUR DATABASE PASSWORD]
DB_DATABASE_NAME=[YOUR DATABASE NAME]
```
Start the backend application by executing the following command line in the backend/ folder :

> mvn spring-boot:run;

And visit http://localhost:4200 to view the site.

## Swagger

Swager documentation is available at http://localhost:8080/swagger-ui/index.html once you started the backend application.
