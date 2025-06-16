# Admin logged in and adding new user, then adding leave entitlement for new user.
# New user logged in and verify the entitlement days, then applying leave.
# Admin user approve, the new user applied leave. 

Feature: New user applying leave and Admin user approve that leave

Scenario: Admin user adding new user and adding leave entitlement days
	And admin user logged in username "Admin" and password "admin123"
	And admin user adding new user from Add Employee screen
	Then admin user leave entitlement days for new user
	And admin user logout from a orangehrm application
	
Scenario: New employee logged in and verify the entitlement days and applying leave
	Given new employee logged in on orangehrm application
	And new employee navigates to leave module and verify the entitlement days added
	Then new employee applyed leave
	And new employee verify applied leave record found on leave list
	Then new employee logout from the ornagehrm application
	
Scenario: Admin approved the new user applied leave
	Given admin user logged in
	And admin navigates to leave module and approve new user applied leave
	Then admin logout the orangehrm application

Scenario: New employee verifying the applied leave approved by admin
	Given new employee logged in orangehrm application
	And new employee verify the leave has been approved by admin
	Then new employee logout application	
		






