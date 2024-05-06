@tag
Feature: Purchase the order from Ecommerce Website
	I 
	Background:
	Given I landed on Ecommerce Page
	
	@tag
	Scenario Outline: positive test of submitting the order
		Given Logged in with username <name> and password <password>
		When I add the product <productName> to cart
		And checkout <productName> and submit the order
		Then "THANKYOU FOR THE ORDER." message is displayed on ConfirmationPage
		
		Examples:
		| name  			  		  | password      | productName |
		| saikirankabothula@gmail.com | Saikiran@pdf1 | ZARA COAT 3 |
	