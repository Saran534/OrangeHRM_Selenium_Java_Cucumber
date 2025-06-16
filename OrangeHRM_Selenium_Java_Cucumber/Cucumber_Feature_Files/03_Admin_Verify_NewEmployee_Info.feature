# Admin user logged in on orangehrm site, and adding new user. And verify the new user personal information data.
# Then new user looged in application, and adding thier personal informations and saved it.
# After new user saved thier personal info, admin user is verify the new user data from there login.


Feature: Verifying New Employee Info Data from Admin PIM Module

Scenario: Admin user logs in and adds a new user
	Given admin user logs in with username "Admin" and password "admin123"
	And admin user navigates to the Add Employee screen
	And admin user adds a new employee with firstname "Kalyan" and lastname "Krishnamurthy"
	And admin user creates login credentials with username "kalyan_krishna" and password "kalyan_krish123"
	Then new employee should be saved successfully
	When admin user navigates to the Employee List screen
	And searches for the employee with firstname "Kalyan" and the generated employee ID
	Then employee record should be displayed
	And admin user logs out of the application

Scenario: New employee logs in and adds personal info in the My Info module
	Given new employee login with username "kalyan_krishna" and password "kalyan_krish123"
	And new employee successfully views the dashboard screen
	And new employee navigates to the My Info module
	And new employee adds their details on the Personal Information screen
	Then new employee saves the personal information
	And new employee logs out of the application

Scenario: Admin user verifies the new user personal info
	Given admin user logs in with username "Admin" and password "admin123"
	And admin user navigates to the Employee List screen
	And admin user searches for the employee with firstname "Kalyan" and generated employee ID
	Then admin user verifies the new user personal information
	And admin user logs out of the application
