<--- CREATE TABLE STATEMENTS (optional ---->
USE studentsuccess;
CREATE TABLE hibernate_sequence (NEXT_VAL BIGINT);
INSERT INTO hibernate_sequence VALUES ( 1 );
CREATE TABLE tbl_addresses (id BIGINT NOT NULL, city VARCHAR(255), phone VARCHAR(255), state VARCHAR(255), street VARCHAR(255), website VARCHAR(255), zip VARCHAR(5), PRIMARY KEY (id));
CREATE TABLE tbl_admins (id BIGINT NOT NULL, first_name VARCHAR(255), last_name VARCHAR(255), password VARCHAR(255), user_role VARCHAR(255), username VARCHAR(255), PRIMARY KEY(id));
CREATE TABLE tbl_employees (id BIGINT NOT NULL AUTO_INCREMENT, first_name VARCHAR(255), last_name VARCHAR(255), password VARCHAR(255), user_role VARCHAR(255), username VARCHAR(255), PRIMARY KEY(id));
CREATE TABLE tbl_options (id BIGINT NOT NULL AUTO_INCREMENT, option_title VARCHAR(255) NOT NULL, value DOUBLE PRECISION NOT NULL, question_id BIGINT NOT NULL, PRIMARY KEY(id));
CREATE TABLE tbl_questions (id BIGINT NOT NULL AUTO_INCREMENT, dependent_resource BIT, food_resource BIT, housing_resource BIT, score DOUBLE PRECISION NOT NULL, question_title VARCHAR(255) NOT NULL, survey_id BIGINT, PRIMARY KEY(id));
CREATE TABLE tbl_resources (id BIGINT NOT NULL AUTO_INCREMENT, degree DOUBLE PRECISION, dependent_resource BIT, description VARCHAR(255), food_resource BIT, housing_resource BIT, name VARCHAR(255), address_id BIGINT, PRIMARY KEY (id));
CREATE TABLE tbl_sessions (id BIGINT NOT NULL, dependent_score DOUBLE PRECISION, end_time DATETIME(6), food_score DOUBLE PRECISION, housing_score DOUBLE PRECISION, start_time DATETIME(6) NOT NULL, current_student_id BIGINT, student_name VARCHAR(255), student_id BIGINT, session_id BIGINT, PRIMARY KEY (id));
CREATE TABLE tbl_students (id BIGINT NOT NUll AUTO_INCREMENT, password VARCHAR(200), user_role VARCHAR(255), username VARCHAR(255), zip VARCHAR(5), PRIMARY KEY(id));
CREATE TABLE tbl_surveys (question_id BIGINT NOT NULL AUTO_INCREMENT, PRIMARY KEY(question_id));
ALTER TABLE tbl_admins ADD CONSTRAINT UK_a_username UNIQUE (username);
ALTER TABLE tbl_employees ADD CONSTRAINT UK_e_username UNIQUE (username);
ALTER TABLE tbl_students ADD CONSTRAINT UK_s_username UNIQUE (username);
ALTER TABLE tbl_options ADD CONSTRAINT FK_questionid FOREIGN KEY(question_id) REFERENCES tbl_questions (id) ON DELETE CASCADE;
ALTER TABLE tbl_questions ADD CONSTRAINT FK_surveryid FOREIGN KEY(survey_id) REFERENCES tbl_surveys (question_id);
ALTER TABLE tbl_resources ADD CONSTRAINT FK_addressid FOREIGN KEY (address_id) REFERENCES tbl_addresses (id);
ALTER TABLE tbl_sessions ADD CONSTRAINT FK_studentid FOREIGN KEY (student_id) REFERENCES tbl_students (id);
ALTER TABLE tbl_sessions add constraint FK_sessionid FOREIGN KEY(session_id) REFERENCES tbl_surveys (question_id);

<--- END OF CREATE STATEMENTS --->

<---- Use these queries first, this will create two stored procedures allowing you to call createQuestion and createOption ---->

DELIMITER $$
USE `studentsuccess`$$
CREATE PROCEDURE `createQuestion` (IN dependent_resource INT, IN food_resource INT, IN housing_resource INT, IN score INT, IN question_title VARCHAR(255))
BEGIN
INSERT INTO tbl_questions (dependent_resource, food_resource, housing_resource, score, survey_id, question_title) VALUES (dependent_resource, food_resource, housing_resource, score, null, question_title);
END$$

DELIMITER ;

USE `studentsuccess`;
DROP procedure IF EXISTS `createOption`;

DELIMITER $$
USE `studentsuccess`$$
CREATE PROCEDURE `createOption` (IN option_title VARCHAR(255), IN question_id INT, IN value DOUBLE)
BEGIN
INSERT INTO tbl_options (option_title, question_id, value) VALUES(option_title, question_id, value);

END$$

DELIMITER ;

<--- END OF STORED PROCEDURES ---->


<---- Queries to create intial questions in the database ---->

CALL createQuestion(0, 1, 0, 0, "The food that I bought just didn’t last and I didn’t have money to get more (in the last 30 days).");
CALL createQuestion(0, 1, 0, 0, "I couldn’t afford to eat balanced meals (in the last 30 days)");
CALL createQuestion(0, 1, 0, 0, "In the last 30 days, did you ever cut the size of your meals or skip meals because there wasn’t enough money for food?");
CALL createQuestion(0, 1, 0, 0, "How many days did this happen? (Skipped or cut size of meals due to money)*");
CALL createQuestion(0, 1, 0, 0, "In the last 30 days, did you ever eat less than you felt you should because there  Wasn’t enough money for food?");
CALL createQuestion(0, 0, 1, 0, "I had difficulty paying for my rent (past 12 months).");
CALL createQuestion(0, 0, 1, 0, "I didn’t pay the full amount of my rent (past 12 months).");
CALL createQuestion(0, 0, 1, 0, "I had difficulty paying the full amount of a gas, oil, or electricity bill (past 12 months).");
CALL createQuestion(0, 0, 1, 0, "I moved 2 or more times (past 12 months).");
CALL createQuestion(0, 0, 1, 0, "I lived with others beyond the expected capacity of my house or apartment (past 12 months).");
CALL createQuestion(0, 0, 1, 0, "I moved in with other people due to financial problems (past 12 months)");
CALL createQuestion(0, 0, 1, 0, "Since starting college, have you ever been Homeless?");
CALL createQuestion(0, 0, 1, 0, "I was thrown out of my home (in past 12 months)");
CALL createQuestion(0, 0, 1, 0, "I was evicted from my home (in past 12 months).");
CALL createQuestion(0, 0, 1, 0, "I stayed in a shelter (in past 12 months).");
CALL createQuestion(0, 0, 1, 0, "I stayed in an abandoned building (in past 12 months).");
CALL createQuestion(0, 0, 1, 0, "I didn’t know where I would sleep at night(in past 12 months)");
CALL createQuestion(0, 0, 1, 0, "I didn’t have a home (in past 12 months)");
CALL createQuestion(0, 0, 1, 0, "I temporarily stayed with a relative, friend, or couch surfed while I looked for housing (in past 12 months).");
CALL createQuestion(0, 0, 1, 0, "I slept in an outdoor location such as a street, sidewalk, or alley, bus or train stop (in past 12 months). ");
CALL createQuestion(0, 0, 1, 0, "I slept in a closed area/space not meant for human habitation such as a car or truck, van, RV, or camper, encampment or tent, or unconverted garage, attic, or basement (in past 12 months).");
CALL createQuestion(1, 0, 0, 0, "Imagine that the interest rate on your savings account is 1% per year and inflation is 2% per year. After 1 year, would you be able to buy more than today, exactly the same as today, or less than today with the money in this account?");
CALL createQuestion(1, 0, 0, 0, "Suppose you have $100 in a savings account and the interest rate was 2% peryear. After 5 years, how much would you have in the account if you left the money to grow?");
CALL createQuestion(1, 0, 0, 0, "Suppose you borrowed $5,000 to help cover college expenses for the coming year. You can choose to repay this loan over 10 years, 20 years, or 30 years. Which of these options will cost you the least amount of money over the length of the repayment period?");
CALL createQuestion(0, 1, 0, 0, "Food Assistance - In the past 12 months, have you used public assistance in the following areas?");
CALL createQuestion(1, 0, 0, 0, "Unemployment Assistance - In the past 12 months, have you used public assistance in the following areas?");
CALL createQuestion(0, 0, 1, 0, "Housing Assistance - In the past 12 months, have you used public assistance in the following areas?");
CALL createQuestion(0, 0, 1, 0, "Utility Assistance - In the past 12 months, have you used public assistance in the following areas?");
CALL createQuestion(1, 0, 0, 0, "Medical Assistance - In the past 12 months, have you used public assistance in the following areas?");
CALL createQuestion(1, 0, 0, 0, "Child Care Assistance - In the past 12 months, have you used public assistance in the following areas? ");
CALL createQuestion(1, 0, 0, 0, "In the past 12 months, how many times did you borrow money from your family and/or friends");
CALL createQuestion(1, 0, 0, 0, "I worry about being able to pay my current monthly expenses");
CALL createQuestion(1, 0, 0, 0, "In the past 12 months, how many times did you borrow a pay day loan?*");
CALL createQuestion(1, 0, 0, 0, "Desktop Computer - Do you use any of the following devices for college coursework?");
CALL createQuestion(1, 0, 0, 0, "Laptop Computer - Do you use any of the following devices for college coursework?");
CALL createQuestion(1, 0, 0, 0, "Are you a citizen of the United States of America?");
CALL createQuestion(1, 0, 0, 0, "At any time since you turned 13, were you in foster care or were you a dependent of the court?");
CALL createQuestion(1, 0, 0, 0, "Did you indicate on the FAFSA (Free Application for Federal Student Aid) that you were previously in foster care or a ward of the state?*");
CALL createQuestion(1, 0, 0, 0, "Did you receive increased funding/support as a result of identifying yourself as a former foster youth on the FAFSA?*");
CALL createQuestion(1, 0, 0, 0, "During the school year, about how many hours do you spend in a typical 7-day week working for pay?");
CALL createQuestion(1, 0, 0, 0, "Are you a dependent or independent Student?");
CALL createQuestion(1, 0, 0, 0, "About how many hours do you spend in a typical 7-day week providing care for dependents (children, parents, etc)?*");

<---- END OF QUESTIONS ---->

<---- Queries to create intial options (answers) in the database ---->
CALL createOption("Often", 1, 5.0);
CALL createOption("Sometimes", 1, 2.5);
CALL createOption("Never", 1, 0.0);
CALL createOption("", 1, 0.0);
CALL createOption("", 1, 0.0);
CALL createOption("Often", 2, 5.0);
CALL createOption("Sometimes", 2, 2.5);
CALL createOption("Never", 2, 0.0);
CALL createOption("", 2, 0.0);
CALL createOption("", 2, 0.0);
CALL createOption("Yes", 3, 5.0);
CALL createOption("No", 3, 0.0);
CALL createOption("", 3, 0.0);
CALL createOption("", 3, 0.0);
CALL createOption("", 3, 0.0);
CALL createOption("Three or more days.", 4, 5.0);
CALL createOption("Fewer Than 3 times a week.", 4, 2.5);
CALL createOption("Never", 4, 0.0);
CALL createOption("", 4, 0.0);
CALL createOption("", 4, 0.0);
CALL createOption("Yes", 5, 5.0);
CALL createOption("No", 5, 0.0);
CALL createOption("", 5, 0.0);
CALL createOption("", 5, 0.0);
CALL createOption("", 5, 0.0);
CALL createOption("True", 6, 5.0);
CALL createOption("False", 6, 0.0);
CALL createOption("I don't know", 6, 0.0);
CALL createOption("", 6, 0.0);
CALL createOption("", 6, 0.0);
CALL createOption("True", 7, 5.0);
CALL createOption("False", 7, 0.0);
CALL createOption("I don't know", 7, 0.0);
CALL createOption("", 7, 0.0);
CALL createOption("", 7, 0.0);
CALL createOption("True", 8, 5.0);
CALL createOption("False", 8, 0.0);
CALL createOption("I don't know", 8, 0.0);
CALL createOption("", 8, 0.0);
CALL createOption("", 8, 0.0);
CALL createOption("True", 9, 5.0);
CALL createOption("False", 9, 0.0);
CALL createOption("I don't know", 9, 0.0);
CALL createOption("", 9, 0.0);
CALL createOption("", 9, 0.0);
CALL createOption("True", 10, 5.0);
CALL createOption("False", 10, 0.0);
CALL createOption("I don't know", 10, 0.0);
CALL createOption("", 10, 0.0);
CALL createOption("", 10, 0.0);
CALL createOption("True", 11, 5.0);
CALL createOption("False", 11, 0.0);
CALL createOption("I don't know", 11, 0.0);
CALL createOption("", 11, 0.0);
CALL createOption("", 11, 0.0);
CALL createOption("Yes", 12, 5.0);
CALL createOption("No", 12, 0.0);
CALL createOption("I don't know.", 13, 0.0);
CALL createOption("", 12, 0.0);
CALL createOption("", 12, 0.0);
CALL createOption("True", 13, 5.0);
CALL createOption("False", 13, 0.0);
CALL createOption("I don't know", 13, 0.0);
CALL createOption("", 13, 0.0);
CALL createOption("", 13, 0.0);
CALL createOption("True", 14, 5.0);
CALL createOption("False", 14, 0.0);
CALL createOption("I don't know", 14, 0.0);
CALL createOption("", 14, 0.0);
CALL createOption("", 14, 0.0);
CALL createOption("True", 15, 5.0);
CALL createOption("False", 15, 0.0);
CALL createOption("I don't know", 16, 0.0);
CALL createOption("", 15, 0.0);
CALL createOption("", 15, 0.0);
CALL createOption("True", 16, 5.0);
CALL createOption("False", 16, 0.0);
CALL createOption("I don't know", 16, 0.0);
CALL createOption("", 16, 0.0);
CALL createOption("", 16, 0.0);
CALL createOption("True", 17, 5.0);
CALL createOption("False", 17, 0.0);
CALL createOption("I don't know", 17, 0.0);
CALL createOption("", 17, 0.0);
CALL createOption("", 17, 0.0);
CALL createOption("True", 18, 5.0);
CALL createOption("False", 18, 0.0);
CALL createOption("I don't know", 18, 0.0);
CALL createOption("", 18, 0.0);
CALL createOption("", 18, 0.0);
CALL createOption("True", 19, 5.0);
CALL createOption("False", 19, 0.0);
CALL createOption("I don't know", 20, 0.0);
CALL createOption("", 19, 0.0);
CALL createOption("", 19, 0.0);
CALL createOption("True", 20, 5.0);
CALL createOption("False", 20, 0.0);
CALL createOption("I don't know", 20, 0.0);
CALL createOption("", 20, 0.0);
CALL createOption("", 20, 0.0);
CALL createOption("True", 21, 5.0);
CALL createOption("False", 21, 0.0);
CALL createOption("I don't know", 21, 0.0);
CALL createOption("", 21, 0.0);
CALL createOption("", 21, 0.0);
CALL createOption("More Than Today", 22, 0.0);
CALL createOption("Exactly the Same as Today", 22, 2.5);
CALL createOption("Less Than Today", 22, 5.0);
CALL createOption("I don't know", 22, 0.0);
CALL createOption("", 22, 0.0);
CALL createOption("More Than $102", 23, 0.0);
CALL createOption("Exactly $102", 23, 2.5);
CALL createOption("Less Than $102", 23, 5.0);
CALL createOption("I don't know", 23, 0.0);
CALL createOption("", 23, 0.0);
CALL createOption("10-year", 24, 0.0);
CALL createOption("20-year", 24, 2.5);
CALL createOption("30-year", 24, 5.0);
CALL createOption("I don't know", 25, 0.0);
CALL createOption("", 25, 0.0);
CALL createOption("Yes", 25, 5.0);
CALL createOption("No", 25, 0.0);
CALL createOption("I don't know.", 25, 0.0);
CALL createOption("", 25, 0.0);
CALL createOption("", 25, 0.0);
CALL createOption("Yes", 26, 5.0);
CALL createOption("No", 26, 0.0);
CALL createOption("I don't know.", 26, 0.0);
CALL createOption("", 26, 0.0);
CALL createOption("", 26, 0.0);
CALL createOption("Yes", 27, 5.0);
CALL createOption("No", 27, 0.0);
CALL createOption("I don't know.", 27, 0.0);
CALL createOption("", 27, 0.0);
CALL createOption("", 27, 0.0);
CALL createOption("Yes", 28, 5.0);
CALL createOption("No", 28, 0.0);
CALL createOption("I don't know.", 28, 0.0);
CALL createOption("", 28, 0.0);
CALL createOption("", 28, 0.0);
CALL createOption("Yes", 29, 5.0);
CALL createOption("No", 29, 0.0);
CALL createOption("I don't know.", 29, 0.0);
CALL createOption("", 29, 0.0);
CALL createOption("", 29, 0.0);
CALL createOption("Yes", 30, 5.0);
CALL createOption("No", 30, 0.0);
CALL createOption("I don't know.", 30, 0.0);
CALL createOption("", 30, 0.0);
CALL createOption("", 30, 0.0);
CALL createOption("Never", 31, 0.0);
CALL createOption("One Time", 31, 1.0);
CALL createOption("Two Times", 31, 2.0);
CALL createOption("Three Times", 31, 4.0);
CALL createOption("Four or More Times", 31, 5.0);
CALL createOption("Agree", 32, 5.0);
CALL createOption("Neutral", 32, 2.5);
CALL createOption("Disagree.", 32, 1.0);
CALL createOption("Strongly Disagree", 32, 0.0);
CALL createOption("", 32, 0.0);
CALL createOption("One Time", 33, 1.0);
CALL createOption("Two Times", 33, 2.0);
CALL createOption("Three Times", 33, 3.0);
CALL createOption("Four Times", 33, 4.0);
CALL createOption("Five Times or more", 34, 5.0);
CALL createOption("All of the time.", 34, 0.0);
CALL createOption("Some of the time.", 34, 2.5);
CALL createOption("Not at all.", 34, 5.0);
CALL createOption("I don't know.", 34, 0.0);
CALL createOption("", 34, 0.0);
CALL createOption("All of the time.", 35, 0.0);
CALL createOption("Some of the time.", 35, 2.5);
CALL createOption("Not at all.", 35, 5.0);
CALL createOption("I don't know.", 35, 0.0);
CALL createOption("", 35, 0.0);
CALL createOption("Yes", 36, 5.0);
CALL createOption("No", 36, 0.0);
CALL createOption("", 36, 0.0);
CALL createOption("", 36, 0.0);
CALL createOption("", 36, 0.0);
CALL createOption("Yes", 37, 5.0);
CALL createOption("No", 37, 0.0);
CALL createOption("I don't know.", 37, 0.0);
CALL createOption("", 37, 0.0);
CALL createOption("", 37, 0.0);
CALL createOption("Yes", 38, 5.0);
CALL createOption("No", 38, 0.0);
CALL createOption("I don't know.", 38, 0.0);
CALL createOption("", 38, 0.0);
CALL createOption("", 38, 0.0);
CALL createOption("Yes", 39, 5.0);
CALL createOption("No", 39, 0.0);
CALL createOption("I don't know.", 39, 0.0);
CALL createOption("", 39, 0.0);
CALL createOption("", 39, 0.0);
CALL createOption("Less than 20 hours", 40, 1.0);
CALL createOption("30-39 hours", 40, 2.5);
CALL createOption("More than 40 hours.", 40, 5.0);
CALL createOption("", 40, 0.0);
CALL createOption("", 40, 0.0);
CALL createOption("Independent", 41, 5.0);
CALL createOption("Dependent", 41, 2.5);
CALL createOption("", 41, 0.0);
CALL createOption("", 41, 0.0);
CALL createOption("", 41, 0.0);
CALL createOption("Less than 20 hours.", 42, 1.0);
CALL createOption("21-39 hours", 42, 2.5);
CALL createOption("40 or more hours.", 42, 5.0);
CALL createOption("", 42, 0.0);
CALL createOption("", 42, 0.0);

<---- END OF QUERIES ---->