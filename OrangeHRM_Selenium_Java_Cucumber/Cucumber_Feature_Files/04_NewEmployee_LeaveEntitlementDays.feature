# Admin user adding a new user, from add employee screen and adding leave entitlement days

Feature: Adding Leave EntitlementDays for Newuser

Scenario: Admin adding newuser
	Given admin logs in using username "Admin" and password "admin123" and see there dashboard
	And admin adding newuser with login credentials
	And admin verifying newuser record availabel on employee lists
	Then admin logout from application
	
Scenario: New employee login on application and verifying the leave entitlement days
	Given new employee login with their usrename "tim_cook" and password "tim_cook123"
	And new employee able to see dashboard
	And new employee verify the leave entitlement days
	Then new employee logout from the application
	
Scenario: Admin adding new leave entitlement days for newuser
	Given admin logs in using there username "Admin" and password "admin123" and see there dashboard
	And admin navigates to leave module and adding leave entitlement days for newuser
	Then admin logut from application
	
Scenario: New employee verifying new entitlement days
	Given new employee login with their username "tim_cook" and password "tim_cook123"
	And new employee verify the leave entitlement days added
	Then new employee logout from application
	