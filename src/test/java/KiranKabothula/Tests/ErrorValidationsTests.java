package KiranKabothula.Tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.sun.net.httpserver.Authenticator.Retry;

import KiranKabothula.TestComponents.BaseTest;
import KiranKabothula.pageobjects.CartPage;
import KiranKabothula.pageobjects.CheckoutPage;
import KiranKabothula.pageobjects.ConfirmationPage;
import KiranKabothula.pageobjects.ProductCatalogue;

public class ErrorValidationsTests extends BaseTest{

	@Test(groups = {"ErrorHandling"})
	public void LoginErrorValidation() throws IOException, InterruptedException 
	{

		String productName =  "ZARA COAT 3";
		ProductCatalogue productcatalogue = landingpage.loginApplication("saikiranpdf@gmail.com", "SAikiran@pdf1");
		Assert.assertEquals("Incorrect email or password.",landingpage.getErrorMessage());
	}
	
	@Test
	public void ProductErrorValidation() throws IOException, InterruptedException 
	{

		String productName =  "ZARA COAT 3";
		
		ProductCatalogue productcatalogue = landingpage.loginApplication("saikiranpdf@gmail.com", "Saikiran@pdf1");
		List<WebElement> products = productcatalogue.getProductList();
		productcatalogue.addProductToCart(productName);
		CartPage cartpage = productcatalogue.goToCartPage();
		Boolean match = cartpage.verifyProdDisplay("ZARA COAT 4");
		Assert.assertFalse	(match);
			
		

	}
	
	
}	
	