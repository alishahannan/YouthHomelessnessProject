/*
 Welcome to the City of Orlando Youth Homelessness Project Team!
 To get started, please execute only step one. After the database has
 been created, launch the application in your preferred IDE, and come back. 
 (Bes sure to update lines 3 & 4 in the resources/application.properties file
 with the user/password of THIS MySQL connection).
 
 When the app launches, Spring Data JPA and Hibernate will create tables
 in the database that correspond to each entity in the models package.
 
 Now, uncomment steps 2 and 3 to populate some default data
 into these newly created tables. 
 
 Finally, open a browser and navigate to localhost:8080/ and log in.
 Although passwords are encrypted below, User/Password combinations are:
 student/student, employee/employee, admin/admin
 
 Happy Coding!
*/

-- 1) Create Database
DROP DATABASE IF EXISTS studentsuccess; 
CREATE DATABASE IF NOT EXISTS studentsuccess;


-- 1.5) Start app and let JPA/Hibernate create tables 
--      (or write insert statments for each table below)


USE studentsuccess;

-- -- 2) Populate users
-- -- Admin, Employee, Survey Admin, Student
-- INSERT INTO tbl_admins (id, first_name, last_name, password, user_role, username) 
-- VALUES (1, 'YHP Admin', 'admin', '$2a$12$uIQQWBfaTYlwvzdKyWi2Jukw0vJaE00BS/Rv1y0L87X/ozYss/86q', 'admin', 'admin');
-- INSERT INTO tbl_employees (id, first_name, last_name, password, user_role, username) 
-- VALUES (1, 'YHP Employee', 'employee', '$2a$12$vELJebV6s62kIbR1V..tXO6SAPHqYz5PcG4gWtNley1mUDH1oTtCm', 'employee', 'employee');
-- INSERT INTO tbl_students (id, password, user_role, username, zip) 
-- VALUES (1, '$2a$12$U4NL4WjPGDEYYFe2qaiKJO2oJL6sCw6.3hkLyYXtHyu4W9jBTAIIi', 'student', 'student', '32926');

-- -- 3) Populate Resources
-- -- Food Resources
-- INSERT INTO tbl_addresses (id, city, phone, state, street, website, zip) 
-- VALUES (1, 'Orlando', '321-321-3211', 'FL', '123 Food St.', 'https://www.stmaryskitchen.com', '32802');
-- INSERT INTO tbl_resources (id, degree, name, description, address_id, food_resource, housing_resource, dependent_resource)
-- VALUE (1, 5.0, 'St. Mary Kitchen', 'Soup Kitchen', 1, true, false, false);

-- -- Housing Resources
-- INSERT INTO tbl_addresses (id, city, phone, state, street, website, zip) 
-- VALUES (2, 'Cocoa', '321-987-9011', 'FL', '123 Housing Ave.', 'https://www.housingshelter.com', '32926');
-- INSERT INTO tbl_resources (id, degree, name, description, address_id, food_resource, housing_resource, dependent_resource)
-- VALUE (2, 5.0, 'YMCA', 'Homeless Shelter', 2, false, true, false);

-- -- Dependent Resources
-- INSERT INTO tbl_addresses (id, city, phone, state, street, website, zip) 
-- VALUES (3, 'Kissimmee', '321-321-4521', 'FL', '13 Dependent St.', 'https://www.independent.com', '32812');
-- INSERT INTO tbl_resources (id, degree, name, description, address_id, food_resource, housing_resource, dependent_resource)
-- VALUE (3, 5.0, 'Be Free, LTD.', 'Soup Kitchen', 3, false, false, true);


-- -- 4) Populate Questions
-- The SQL script for Questions and Options is located in the root directory of the repository.




-- -- View Users
-- SELECT * FROM studentsuccess.tbl_admins;
-- SELECT * FROM studentsuccess.tbl_employees;
-- SELECT * FROM studentsuccess.tbl_students;

-- -- View other entities
-- SELECT * FROM studentsuccess.tbl_resources;
-- SELECT * FROM studentsuccess.tbl_addresses;
-- SELECT * FROM studentsuccess.tbl_questions;
-- SELECT * FROM studentsuccess.tbl_options;
-- SELECT * FROM studentsuccess.tbl_surveys;
-- SELECT * FROM studentsuccess.tbl_sessions;
