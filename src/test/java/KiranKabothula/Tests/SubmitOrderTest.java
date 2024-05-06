package KiranKabothula.Tests;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import KiranKabothula.TestComponents.BaseTest;
import KiranKabothula.pageobjects.CartPage;
import KiranKabothula.pageobjects.CheckoutPage;
import KiranKabothula.pageobjects.ConfirmationPage;
import KiranKabothula.pageobjects.LandingPage;
import KiranKabothula.pageobjects.OrderPage;
import KiranKabothula.pageobjects.ProductCatalogue;
import io.github.bonigarcia.wdm.WebDriverManager;

public class SubmitOrderTest extends BaseTest{

	String productName = "ZARA COAT 3";
	
	@Test(dataProvider="getData",groups={"Purchase"})
	public void SubmitOrder(HashMap<String,String> input) throws IOException, InterruptedException 
	{
		ProductCatalogue productcatalogue = landingpage.loginApplication(input.get("email"),input.get("password"));
		List<WebElement> products = productcatalogue.getProductList();
		productcatalogue.addProductToCart(input.get("productName"));
		CartPage cartpage = productcatalogue.goToCartPage();
		Boolean match = cartpage.verifyProdDisplay(input.get("productName"));
		Assert.assertTrue(match);
		CheckoutPage checkoutpage = cartpage.goToCheckout();
		checkoutpage.selectCountry("india");
		ConfirmationPage confirmationpage = checkoutpage.submitOrder();
		String ConfirmMessage = confirmationpage.getConfirmationMessage();
		Assert.assertTrue(ConfirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	}
	
	//to verify the ZARA COAT 3 is displaying in orders page
	@Test(dependsOnMethods = {"SubmitOrder"})
	public void OrdersHistory()
	{
		ProductCatalogue productcatalogue = landingpage.loginApplication("saikiranpdf@gmail.com", "Saikiran@pdf1");
		OrderPage orderpage = productcatalogue.goToOrdersPage();
		Assert.assertTrue(orderpage.verifyOrderDisplay(productName));
	}
	
	
	
	
	@DataProvider
	public Object[][] getData() throws IOException
	{

		
		List<HashMap<String,String>> data = getJsonDataToMap(System.getProperty("user.dir")+"//src//test//java//KiranKabothula//Data//PurchaseOrder.json");
		return new Object[][] {{data.get(0)},{data.get(1)}};
	}

//	HashMap<String,String> map = new HashMap<String,String>();
//	map.put("email","saikiranpdf@gmail.com");
//	map.put("password", "Saikiran@pdf1");
//	map.put("productName", "ZARA COAT 3");
//	
//	HashMap<String,String> map1 = new HashMap<String,String>();
//	map1.put("email","shetty@gmail.com");
//	map1.put("password", "Iamking@000");
//	map1.put("productName", "ADIDAS ORIGINAL");
//___________________________________________________________________	
//	@DataProvider
//	public Object[][] getData()
//	{
//       return new Object[][] {{"saikiranpdf@gmail.com","Saikiran@pdf1","ZARA COAT 3"},{"shetty@gmail.com","Iamking@000","ADIDAS ORIGINAL"}};
//  }
	
}	
	