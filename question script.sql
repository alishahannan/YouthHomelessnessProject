INSERT INTO tbl_questions (dependent_resource, food_resource, housing_resource, score, survey_id, question_title) VALUES (1, 0, 0, 0, null, "This is question 2");
INSERT INTO tbl_options (option_title, question_id, value) VALUES("Yes", 2, 1.0);
INSERT INTO tbl_options (option_title, question_id, value) VALUES("No", 2, 2.0);
INSERT INTO tbl_options (option_title, question_id, value) VALUES("Maybe", 2, 3.0);
INSERT INTO tbl_options (option_title, question_id, value) VALUES("Sometimes", 2, 4.0);
INSERT INTO tbl_options (option_title, question_id, value) VALUES("Hardly", 2, 5.0);


DELIMITER $$
USE `studentsuccess`$$
CREATE PROCEDURE `createQuestion` (IN dependent_resource INT, IN food_resource INT, IN housing_resource INT, IN score INT, IN question_title VARCHAR(100))
BEGIN
INSERT INTO tbl_questions (dependent_resource, food_resource, housing_resource, score, survey_id, question_title) VALUES (dependent_resource, food_resource, housing_resource, score, null, question_title);
END$$

DELIMITER ;

USE `studentsuccess`;
DROP procedure IF EXISTS `CreateOption`;

DELIMITER $$
USE `studentsuccess`$$
CREATE PROCEDURE `CreateOption` (IN option_title VARCHAR(100), IN question_id INT, IN value DOUBLE)
BEGIN
INSERT INTO tbl_options (option_title, question_id, value) VALUES(option_title, question_id, value);

END$$

DELIMITER ;

