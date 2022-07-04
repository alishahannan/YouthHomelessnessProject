-- 1) Create Database
-- DROP DATABASE IF EXISTS studentsuccess; 
-- CREATE DATABASE IF NOT EXISTS studentsuccess;

-- 1.5) Start app to create tables (or write insert statments for each table below)

-- 2) Populate users
-- USE studentsuccess;
-- Admin, Employee, Survey Admin, Student
-- INSERT INTO tbl_admins (id, first_name, last_name, password, user_role, username) 
-- VALUES (1, 'derek', 'admin', 'admin', 'admin', 'admin');
-- INSERT INTO tbl_employees (id, first_name, last_name, password, user_role, username) 
-- VALUES (1, 'derek', 'employee', 'employee', 'employee', 'employee');
-- INSERT INTO tbl_students (id, password, user_role, username, zip) 
-- VALUES (1, 'student', 'student', 'student', '32926');

-- -- 3) Populate Resources
-- -- Food Resources
-- INSERT INTO tbl_addresses (id, city, phone, state, street, website, zip) 
-- VALUES (1, 'Orlando', '3213213211', 'FL', '123 Food St.', 'www.stmaryskitchen.com', '32802');
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




-- View Users
-- SELECT * FROM studentsuccess.tbl_admins;
-- SELECT * FROM studentsuccess.tbl_employees;
-- SELECT * FROM studentsuccess.tbl_students;

-- View other entities
-- SELECT * FROM studentsuccess.tbl_resources;
-- SELECT * FROM studentsuccess.tbl_addresses;
-- SELECT * FROM studentsuccess.tbl_questions;
-- SELECT * FROM studentsuccess.tbl_options;
-- SELECT * FROM studentsuccess.tbl_surveys;
-- SELECT * FROM studentsuccess.tbl_sessions;
