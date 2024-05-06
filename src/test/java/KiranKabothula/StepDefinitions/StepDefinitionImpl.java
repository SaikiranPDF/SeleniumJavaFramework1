package KiranKabothula.StepDefinitions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import KiranKabothula.TestComponents.BaseTest;
import KiranKabothula.pageobjects.CartPage;
import KiranKabothula.pageobjects.CheckoutPage;
import KiranKabothula.pageobjects.ConfirmationPage;
import KiranKabothula.pageobjects.LandingPage;
import KiranKabothula.pageobjects.ProductCatalogue;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitionImpl extends BaseTest {

	public  LandingPage landingPage;
	public ProductCatalogue productcatalogue;
	public ConfirmationPage confirmationpage;
	
	@Given("I landed on Ecommerce Page")
	public void I_landed_on_Ecommerce_Page() throws IOException
	{
		landingPage = launchApplication();
	}
	
	@Given("^Logged in with username (.+) and password (.+)$")
	public void logged_in_username_and_password(String username, String password)
	{
		productcatalogue = landingpage.loginApplication(username,password);	
	}
	
	@When("^I add the product (.+) to cart$")
	public void i_add_product_to_cart(String productName) throws InterruptedException 
	{
		List<WebElement> products = productcatalogue.getProductList();
		productcatalogue.addProductToCart(productName);
	}
	
	@When("^checkout ('+) and submit the order$")
	public void checkout_submit_order(String productName)
	{
		CartPage cartpage = productcatalogue.goToCartPage();
		Boolean match = cartpage.verifyProdDisplay(productName);
		Assert.assertTrue(match);
		CheckoutPage checkoutpage = cartpage.goToCheckout();
		checkoutpage.selectCountry("india");
		confirmationpage = checkoutpage.submitOrder();
	}
	
	@Then("{string} message is displayed on ConfirmationPage")
	public void message_displayed_confirmationPage(String string) 
	{
		String ConfirmMessage = confirmationpage.getConfirmationMessage();
		Assert.assertTrue(ConfirmMessage.equalsIgnoreCase(string));
	}
	
}
