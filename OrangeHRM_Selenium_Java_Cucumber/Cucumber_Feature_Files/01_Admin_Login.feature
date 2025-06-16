# The Admin user is trying to log in to the OrangeHRM site.
# After a successful login, the Admin user should be able to view the dashboard screen.

Feature: Admin User Login on OrangeHRM site

 Scenario: Successful login by Admin user
	Given the admin user enters valid credentials with username "Admin" and password "admin123"
	When the admin user clicks the login button
	Then the admin user should see the dashboard screen
	And the admin user name should be visible on the login info panel
	And the admin user logs out
