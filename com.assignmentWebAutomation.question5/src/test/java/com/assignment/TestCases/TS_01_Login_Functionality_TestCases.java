package com.assignment.TestCases;

import org.junit.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.assignment.appPages.Page_Account_Log_In;
import com.assignment.appPages.Page_Home;
import com.assignment.appPages.Page_Logged_In;
import com.assignment.basesetup.BaseSetUp;

public class TS_01_Login_Functionality_TestCases extends BaseSetUp {
	Page_Home homePgObj;
	boolean flag;
	Page_Account_Log_In acctLogInPgObj;
	Page_Logged_In loggedInPgObj;
	private String validEmailAddress;
	private String validPassword;
	private String InvalidEmailAddress;
	private String InvalidPassword;
	private String errMsg;

	
	/* 
	 * Method Name - beforeMethod
	 * Test Objective: To initialize configuration file and setting up browser from base setup class before every test method
	 */
	@BeforeMethod
	public void beforeMethod() {
		initializeTestBaseSetup();
	}

	/*
	 * Test Case 01 Test Case Name - TC_01_verifyLandingPage 
	 * Test Objective: To verify whether user is landed on home page
	 */
	@Test
	public void TC_01_verifyLandingPage() {
		homePgObj = new Page_Home(driver);
		flag = homePgObj.verifyHomePage();
		if (flag)
			System.out.println("User landed at correct home page");
		else
			System.out.println("User is incorrectly landed at home page");
	}

	/*
	 * Test Case 02 Test Case Name - TC_02_logingWithValidCredentials
	 * Test Objective: To verify user should be able to login/or sign in with valid credentials
	 */
	@Test
	public void TC_02_logingWithValidCredentials() {

		// Step 1: Fetch the valid valid email address and password

		validEmailAddress = prop.getProperty("Valid_User_Id");
		validPassword = prop.getProperty("Valid_User_Password");
		homePgObj = new Page_Home(driver);

		// Step 2: Click on 'Sign In' link on home page.

		acctLogInPgObj = homePgObj.clickSignInLink();

		// Step 3: Verify login/or Sign in page is displayed

		flag = acctLogInPgObj.accountLoginPgDisplay();
		Assert.assertTrue("Account login in page is displayed", flag);

		// Step 4: Enter valid email address and password and click on 'Sign in' button

		acctLogInPgObj.enterSignInDetails("emailAddress", validEmailAddress);
		acctLogInPgObj.enterSignInDetails("password", validPassword);
		loggedInPgObj = acctLogInPgObj.clickSignInButton();

		// Step 5: Verify user is able to sign in by verifying the log out link

		flag = loggedInPgObj.verifyLogOutLink();
		Assert.assertTrue("User logged in successfully", flag);

	}

	/*
	 * Test Case 03 Test Case Name - TC_03_logingWithInValid_EmailAddress Test
	 * Objective: To verify user should NOT be able to login/or sign in with invalid
	 * email address but valid password
	 */

	@Test
	public void TC_03_logingWithInValid_EmailAddress() {

		// Step 1: Fetch the invalid email address and valid password

		InvalidEmailAddress = prop.getProperty("InValid_User_Id");
		validPassword = prop.getProperty("Valid_User_Password");
		homePgObj = new Page_Home(driver);

		// Step 2: Click on 'Sign In' link on home page.

		acctLogInPgObj = homePgObj.clickSignInLink();

		// Step 3: Verify login/or Sign in page is displayed

		flag = acctLogInPgObj.accountLoginPgDisplay();
		Assert.assertTrue("Account login in page is displayed", flag);

		// Step 4: Enter invalid email address and valid password and click on 'Sign in'
		// button

		acctLogInPgObj.enterSignInDetails("emailAddress", InvalidEmailAddress);
		acctLogInPgObj.enterSignInDetails("password", validPassword);
		loggedInPgObj = acctLogInPgObj.clickSignInButton();

		// Step 5: Verify user is NOT able to sign in as error message should be
		// displayed

		errMsg = Page_Account_Log_In.getErrorText();
		Assert.assertTrue(
				errMsg.contains("Your login attempt has been unsuccessful. Please check your details and try again."));

	}

	/*
	 * Test Case 04 Test Case Name - TC_04_logingWithInValid_Password
	 * Test Objective: To verify user should NOT be able to login/or sign in with valid
	 * email address but invalid password
	 */
	@Test
	public void TC_04_logingWithInValid_Password() {

		// Step 1: Fetch the valid email address and invalid password

		validEmailAddress = prop.getProperty("Valid_User_Id");
		InvalidPassword = prop.getProperty("InValid_User_Password");
		homePgObj = new Page_Home(driver);

		// Step 2: Click on 'Sign In' link on home page.

		acctLogInPgObj = homePgObj.clickSignInLink();

		// Step 3: Verify login/or Sign in page is displayed

		flag = acctLogInPgObj.accountLoginPgDisplay();
		Assert.assertTrue("Account login in page is displayed", flag);

		// Step 4: Enter valid email address and invalid password and click on 'Sign in'
		// button

		acctLogInPgObj.enterSignInDetails("emailAddress", validEmailAddress);
		acctLogInPgObj.enterSignInDetails("password", InvalidPassword);
		loggedInPgObj = acctLogInPgObj.clickSignInButton();

		// Step 5: Verify user is NOT able to sign in as error message should be
		// displayed

		errMsg = Page_Account_Log_In.getErrorText();
		Assert.assertTrue(
				errMsg.contains("Your login attempt has been unsuccessful. Please check your details and try again."));

	}

	/*
	 * Test Case 05 Test Case Name - TC_05_loginWith_BlankEmailAddress
	 * Test Objective: To verify user should NOT be able to login/or sign in with blank
	 * email address
	 */

	@Test
	public void TC_05_loginWith_BlankEmailAddress() {
		// Step 1: Fetch the valid password

		validPassword = prop.getProperty("Valid_User_Password");
		homePgObj = new Page_Home(driver);

		// Step 2: Click on 'Sign In' link on home page.

		acctLogInPgObj = homePgObj.clickSignInLink();

		// Step 3: Verify login/or Sign in page is displayed

		flag = acctLogInPgObj.accountLoginPgDisplay();
		Assert.assertTrue("Account login in page is displayed", flag);

		// Step 4: Enter blank email address and valid password and click on 'Sign in'button

		acctLogInPgObj.enterSignInDetails("emailAddress", "");
		acctLogInPgObj.enterSignInDetails("password", validPassword);
		loggedInPgObj = acctLogInPgObj.clickSignInButton();

		// Step 5: Verify user is NOT able to sign in as error message should be displayed

		errMsg = Page_Account_Log_In.getErrorText_BlankCredentials();
		Assert.assertTrue(errMsg.contains("Please enter a value for Email Address"));
	}

	/*
	 * Test Case 06 Test Case Name - TC_06_loginWith_BlankPassword
	 * Test Objective: To verify user should NOT be able to login/or sign in with blank
	 * email address
	 */
	@Test
	public void TC_06_loginWith_BlankPassword() {
		// Step 1: Fetch the valid password
		
		validEmailAddress = prop.getProperty("Valid_User_Id");
		homePgObj = new Page_Home(driver);
		
		// Step 2: Click on 'Sign In' link on home page.
		
		acctLogInPgObj = homePgObj.clickSignInLink();
		
		// Step 3: Verify login/or Sign in page is displayed
		
		flag = acctLogInPgObj.accountLoginPgDisplay();
		Assert.assertTrue("Account login in page is displayed", flag);
		
		// Step 4: Enter valid email address and blank password and click on 'Sign in'button
		
		acctLogInPgObj.enterSignInDetails("emailAddress", validEmailAddress);
		acctLogInPgObj.enterSignInDetails("password", "");
		
		loggedInPgObj = acctLogInPgObj.clickSignInButton();
		
		// Step 5: Verify user is NOT able to sign in as error message should be displayed
		
		errMsg = Page_Account_Log_In.getErrorText_BlankCredentials();
		Assert.assertTrue(errMsg.contains("Please enter a value for Password"));
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
