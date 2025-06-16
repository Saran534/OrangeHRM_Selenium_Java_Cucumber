# The Admin user is trying to log in to the OrangeHRM site.
# After a successful login, admin user adding new user on Add Employee screen

Feature: Add New Employee in OrangeHRM

Scenario: Admin adds a new employee and verifies the record
	Given the admin user logs in with username "Admin" and password "admin123"
	And the admin user navigates to the Add Employee screen
	And the admin user adding new employee with firstname "Andy" and lastname "Jassy"
	And the admin user creates login credentials with username "andy_jassy" and password "andy_j123"
	Then the new employee should be saved successfully
	When the admin user navigates to Employee List screen
	And searches for the employee with first name "Andy" and the generated employee ID
	Then the employee record should be displayed
	And the admin user logout the application
