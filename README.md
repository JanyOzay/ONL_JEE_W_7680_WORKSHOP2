# ONL_JEE_W_7680_WORKSHOP2
Workshop2 – User Management in Java with JDBC and BCrypt
This project is a simple demonstration of working with a MySQL database in Java using JDBC. It focuses on basic CRUD operations (Create, Read, Update, Delete) for the User entity, which represents a user in the database.

Features
Creating users with automatic password hashing using BCrypt

Reading a user by ID

Updating user details including username, email, and password

Deleting a user by ID

Listing all users in the database

Technologies Used
Java 17+

JDBC (Java Database Connectivity)

MySQL database

BCrypt (for secure password hashing)

Maven for dependency management (recommended)

Project Structure
pl.coderslab.entity.User – data class representing a user

pl.coderslab.entity.UserDao – class containing methods for database operations

pl.coderslab.MainDaoTest – simple test class demonstrating usage of UserDao

Setup Instructions
Create the database and table:

sql
CREATE DATABASE workshop2 CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

USE workshop2;

CREATE TABLE users (
id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
email VARCHAR(255) NOT NULL UNIQUE,
username VARCHAR(255) NOT NULL,
password VARCHAR(60) NOT NULL
);
Update the database connection settings in MainDaoTest (URL, username, password).

Make sure to include the jBCrypt dependency in your project (for example, in pom.xml):

xml
<dependency>
<groupId>org.mindrot</groupId>
<artifactId>jbcrypt</artifactId>
<version>0.4</version>
</dependency>
Run MainDaoTest as the main class.

How to Use
The MainDaoTest class demonstrates:

Adding a new user named “Viktor Hazard”

Reading a user by ID

Updating a user's details

Listing all users

Deleting a user by ID

Notes
Passwords are stored securely as hashes using BCrypt; plaintext passwords are never saved.

You can extend the UserDao class with additional methods, such as searching users by email.