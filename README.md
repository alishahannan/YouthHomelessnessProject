# Youth Homelessness Project
## Youth Homelessness Project for Valencia BAS CTSD Senior Capstone - Summer 2022

This is a Java Spring MVC project which collects data via survey and presents helpful resources based on responses. The anonymous data collected can then be used by city officials to generate reports which will help them visualize the problem and develop targeted solutions.

Survey questions were inspired by this [Student Financial Wellness Survey](https://valenciacollege.edu/academics/analytics-and-planning/institutional-evaluation/research-and-evaluation/documents/valencia_fall2020-sfws-school-report.pdf).

## Installation
Note: These settings are for local development and testing purposes only.  

- Clone this repository
- Install MySQL and MySQL Workbench
- Open MySQL Workbench and set up a new connection with the following properties:
  - Connection name: Any (or blank)
  - Hostname: 127.0.0.1
  - Port: 3306
  - Username: root
  - Password: password
- Double-click the new connection and login with credentials above
- Select File > Open SQL Script... and select studentsuccess.SQL from the top project directory
- Uncomment and run the following command:
  - CREATE DATABASE studentsuccess;
- Import the project into your favorite IDE
- Launch the spring boot application and copy security password from console
  - Hibernate will automatically create a table in the database for each entity
- Go back to MySQL workbench and refresh to verify tables have been created.
- Uncomment and execute the INSERT statements to populate some users (note the login credentials), questions and resources
- Open a browser and navigate to [localhost](http://localhost:8080/login/)
  - User: user
  - Password: paste from IDE console

The application currently has three types of user: admin, employee, student.
- Admins CRUD users
- Employees CRD resources and survey questions (future functions will include updates, reviewing 'sanitized' survey data, and generating reports)
- Students take surveys, view returned resources, review past surveys, view all resources

