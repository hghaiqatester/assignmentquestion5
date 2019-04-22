package com.assignment.TestCases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.assignment.appPages.Page_Home;
import com.assignment.basesetup.BaseSetUp;

public class TS_04_Add_To_Cart extends BaseSetUp {

	Page_Home homePgObj;
	private String itemTxt = prop.getProperty("Item_Name");
	private String itemText;
	private String guestCheckouttex;
	private String headerTxt;
	private String placeOrderTxt;

	
	/* 
	 * Method Name - beforeMethod
	 * Test Objective: To initialize configuration file and setting up browser from base setup class before every test method
	 */
	@BeforeMethod
	public void beforeMethod() {
		initializeTestBaseSetup();
	}
	
	/* Test Case 01
	 * Test Case Name - TC_01_addToCartTest 
	 * Test Objective: To verify whether user is succeeded into searching and adding the product into shopping cart
	 */

	@Test
	public void TC_01_addToCartTest() throws InterruptedException {
		homePgObj = new Page_Home(driver);
		homePgObj.enterSearchItem(itemTxt);
		homePgObj.clickSearchBtn();

		// Step 1 : Fetch the name of the searched item.

		itemTxt = homePgObj.getItemText("Searchable Item");

		// Step 2: Verify that searched item is searched and display successfully

		Assert.assertEquals(itemTxt, "Garage Women's PU Leather Jacket");

		// Step 3: Click on Add to Cart button.

		WebElement addTocartBtn = driver.findElement(By.id("add-to-cart"));
		addTocartBtn.click();

		// Step 4: Hover over add to cart icon and then click on it.

		WebElement cartIcon = driver.findElement(By.xpath("//span[@class='mini-cart-total-label']"));
		Actions action = new Actions(driver);
		action.moveToElement(cartIcon).click().build().perform();
		Thread.sleep(2000);

		// Step 5 : Verify that user is routed to 'Add to Cart' page for the selected
		// product

		WebElement addToCartItemText = driver.findElement(By.xpath("(//a[contains(@title,'PU Leather Jacket')])[2]"));
		itemText = addToCartItemText.getText();
		System.out.println("Add to cart item text is " + itemText);

		Assert.assertTrue(itemText.contains(itemTxt));
		// or
		if (itemText.contains(itemTxt))
			System.out.println("User is on right add to cart page");
		else
			System.out.println("User is NOT on right add to cart page");

		// Step 6: click on 'Proceed to Checkout' button

		WebElement checkOutBtn = driver.findElement(By.name("dwfrm_cart_checkoutCart"));
		checkOutBtn.click();

		// Step 7: Verify that user is routed to 'Account login/Guest Checkout Page'

		WebElement we = driver.findElement(By.xpath("//span[text()='Guest Checkout']"));
		guestCheckouttex = we.getText();
		if (guestCheckouttex.contains("Guest Checkout"))
			System.out.println("User is on right add to cart page");
		else
			System.out.println("User is NOT on right add to cart page");

		// Step 8: Enter Guest's email address

		WebElement guestEmailAddress = driver.findElement(By.xpath("//input[@data-automation-id='guestEmail']"));
		guestEmailAddress.sendKeys("hghai@gmail.com");

		// Step 9 : Click on 'Checkout As Guest' button

		WebElement guestChkoutBtn = driver.findElement(By.name("dwfrm_login_unregistered"));
		guestChkoutBtn.click();

		// Step 10: Verify whether user is routed to 'Delivery Address' page

		WebElement hdtxt = driver
				.findElement(By.xpath("//h3[@class='h1 reskinned-header reskinned-header-top header-long']"));
		headerTxt = hdtxt.getText();

		if (headerTxt.contains("DELIVERY ADDRESS"))
			System.out.println("User is on righ delivery address");
		else
			System.out.println("User is NOT on righ delivery address");

		// Step 11: Enter Customer First name

		WebElement firstName = driver
				.findElement(By.id("dwfrm_singleshipping_shippingAddress_addressFields_firstName"));
		firstName.sendKeys("Test User First Name");

		// Step 12: Enter Customer last name

		WebElement lastName = driver.findElement(By.id("dwfrm_singleshipping_shippingAddress_addressFields_lastName"));
		lastName.sendKeys("Test User Last Name");

		// Step 13: Enter Customer's address

		WebElement address = driver.findElement(By.id("dwfrm_singleshipping_shippingAddress_addressFields_address1"));
		address.sendKeys("5/241,Onewa Road");

		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.elementToBeClickable(
				driver.findElement(By.id("dwfrm_singleshipping_shippingAddress_addressFields_address2"))));

		WebElement suburb = driver.findElement(By.id("dwfrm_singleshipping_shippingAddress_addressFields_address2"));
		suburb.sendKeys("Birkenhead");
		WebElement city = driver.findElement(By.id("dwfrm_singleshipping_shippingAddress_addressFields_city"));
		city.sendKeys("Auckland");
		WebElement zipCode = driver.findElement(By.id("dwfrm_singleshipping_shippingAddress_addressFields_zip"));
		zipCode.sendKeys("0626");

		// Step 14: Select Customer's area code

		WebElement areaCodeDropDown = driver
				.findElement(By.xpath("//select[@name='phone-shipping-container_areacode']"));

		Select select = new Select(areaCodeDropDown);
		select.selectByValue("022");

		// Step 15: Enter phone number:

		WebElement phoneNum = driver.findElement(By.name("phone-shipping-container_phonenumber"));
		phoneNum.sendKeys("5210027");

		// Step 16: Click on Continue button

		WebElement continueBtn = driver.findElement(By.name("dwfrm_singleshipping_shippingAddress_save"));
		continueBtn.click();

		// Step 17:Verify that user is on Payment / Place Order page by checking the
		// 'Payment/Place Order header' and 'Proceed to Payment' button

		wait.until(ExpectedConditions
				.elementToBeClickable(driver.findElement(By.xpath("//button[@name='dwfrm_billing_save']"))));

		WebElement hd_placeOrder = driver.findElement(By.xpath("//div[@class='step-2 active']"));
		WebElement btn_Proceed_Payment = driver.findElement(By.name("dwfrm_billing_save"));
		placeOrderTxt = hd_placeOrder.getText();
		if (placeOrderTxt.toLowerCase().contains("place order") && btn_Proceed_Payment.isDisplayed())
			System.out.println("User is correctly on Proceed to Payment page");
		else
			System.out.println("User is NOT on Proceed to Payment page");
	}

	/* 
	 * Method Name - afterMethod
	 * Test Objective: To quite the browser after every test method
	 */
	@AfterMethod
	public void afterMethod() {
		driver.quit();
	}

}
