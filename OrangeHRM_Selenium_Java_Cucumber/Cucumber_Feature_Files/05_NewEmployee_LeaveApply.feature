# Admin user logged in and adding new user from add employee screen, 
# then adding leave entitlement days for new user.

Feature: Newuser Applying Leave from there login after leave entitlement days added.

Scenario: Admin user logged in and adding new employee
	Given admin user logged in from there credentials username "Admin" and password "admin123"
	And admin user add new employee from Add Employee screen
	Then admin user add leave entitlement days for new employee
	And admin user logged out
	
Scenario: New employee logged in verify the entitlement days and applying leave	
	Given new employee logged in from there credentials
	And new employee navigates to leave module and verify the entitlement days
	Then new employee applying leave
	And new employee logged out





