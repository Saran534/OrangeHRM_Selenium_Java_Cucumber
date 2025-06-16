# Admin user adding new user with login credentials.
# New user logged in first time successfully, then newuser logged out. 
# New user forgot login password.
# Admin change password for new user.
# New user login with new password.

Feature: Admin user change password for New user from Admin module

Scenario: Admin logged in and adding new employee
	Given admin enters there username "Admin" and password "admin123"
	And admin adding a new employee with login credentials
	Then admin logout the application
	
Scenario: New employee first time login
	Given new employee login with there credentials
	And after successfull login new employee can see the dashboard
	Then new employee logout the application
	
Scenario: New employee forgot login password
	Given new employee login with there credentials correct username and wrong password
	Then new employee unable to login and seeing the error message of invalid login	
	
Scenario: Admin logged in and change new password for new employee
	Given admin enters a username "Admin" and password "admin123"
	And admin navigates to Admin Module and change password for new employee
	Then admin logout orangehrm application
	
Scenario: New employee login with new credentials
	Given new employee login with there new credentials username and password
	And after successfull login new employee see the dashboard
	Then new employee logout a application	
		