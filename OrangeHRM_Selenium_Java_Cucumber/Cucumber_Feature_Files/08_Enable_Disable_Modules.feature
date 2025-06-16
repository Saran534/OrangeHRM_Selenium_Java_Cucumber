#Admin user disabled the all enbaled modules from sidebar menu panel:
#Then printing the default module names from sidebar menu panel:

Feature: Enable and Disable the modules

Scenario: Admin user disable the all enabled modules
	Given admin login with there credentials username "Admin" and password "admin123"
	And admin verifying default enabled modules
	And admin navigates to admin module
	And admin disable the all enabled modules
	And admin verifying enabled modules are disabled
	Then admin logout from orangehrm application