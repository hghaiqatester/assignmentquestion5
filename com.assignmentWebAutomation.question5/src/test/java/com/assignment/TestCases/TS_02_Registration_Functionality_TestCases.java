package com.assignment.TestCases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.assignment.appPages.Page_Home;
import com.assignment.appPages.Page_Registration;
import com.assignment.basesetup.BaseSetUp;

public class TS_02_Registration_Functionality_TestCases extends BaseSetUp {

	Page_Home homePgObj;
	boolean flag;
	Page_Registration regPgObj;
	private String firstName = prop.getProperty("First_Name");
	private String lastName = prop.getProperty("Last_Name");
	private String email = prop.getProperty("Email_Address");
	private String password = prop.getProperty("Password");
	private String confirmPassword = prop.getProperty("Confirm_Password");
	private String InvalidEmail = prop.getProperty("Invalid_Email_Address");
	private String regUserInfo;
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
	 * Test Case 01 Test Case Name - TC_01_verifyRegistrationPage
	 * Test Objective: To verify user should be able to launch registration page
	 * email address
	 */
	@Test
	public void TC_01_verifyRegistrationPage() {
		homePgObj = new Page_Home(driver);
		regPgObj = homePgObj.click_RegistrationLink();
		flag = regPgObj.registrationPgDisplay();
		Assert.assertTrue(flag, "Registration page is displayed");
	}

	/*
	 * Test Case 02 Test Case Name - TC_02_verify_Registration_ValidData
	 * Test Objective: To verify user should be able to register in system with valid data
	 * email address
	 */
	@Test
	public void TC_02_verify_Registration_ValidData() {

		homePgObj = new Page_Home(driver);
		regPgObj = homePgObj.click_RegistrationLink();
		
		// Step 1: Verify the registration page is displayed
		
		flag = regPgObj.registrationPgDisplay();
		
		// Step 2: Enter valid registration details including first name,last name,email address, password and confirm password
		
		Assert.assertTrue(flag, "Registration page is displayed");
		regPgObj.enterRegistrationDetails("firstName", firstName);
		regPgObj.enterRegistrationDetails("lastName", lastName);
		regPgObj.enterRegistrationDetails("email", email);
		regPgObj.enterRegistrationDetails("password", password);
		regPgObj.enterRegistrationDetails("confirmPassword", confirmPassword);
		
		// Step 3: Click on Sign in button
		
		regPgObj.clickSignInButton();
		
		// Step 4: Verify user should be able to register themselves by verifying their credentials on registration page
		
		regUserInfo = Page_Registration.getRegisteredUserInfo();
		if (regUserInfo.toLowerCase().contains(firstName.toLowerCase())
				&& regUserInfo.toLowerCase().contains(lastName.toLowerCase()))
			System.out.println("User registered successfully");
		else
			System.out.println("User could not be registered");

	}

	/*
	 * Test Case 03 Test Case Name - TC_03_verify_FirstName_Mandatory_Field
	 * Test Objective: To verify system throws a valid message when 'first name' mandatory field is left blank
	 * email address
	 */

	// @Test
	public void TC_03_verify_FirstName_Mandatory_Field() {

		homePgObj = new Page_Home(driver);
		regPgObj = homePgObj.click_RegistrationLink();
		
		// Step 1: Verify the registration page is displayed
		
		flag = regPgObj.registrationPgDisplay();
		Assert.assertTrue(flag, "Registration page is displayed");
		
		// Step 2: Enter valid/mandatory registration details except first name
		
		regPgObj.enterRegistrationDetails("firstName", "");
		regPgObj.enterRegistrationDetails("lastName", lastName);
		regPgObj.enterRegistrationDetails("email", email);
		regPgObj.enterRegistrationDetails("password", password);
		regPgObj.enterRegistrationDetails("confirmPassword", confirmPassword);
		
		// Step 3: Click on Sign in button
		
		regPgObj.clickSignInButton();
		
		// Step 4: Verify user should NOT be able to proceed further with registration as proper validation message regarding first name
		// is displayed
		errMsg = Page_Registration.getMandatoryFieldErrMsg();
		Assert.assertEquals(errMsg, "Please enter a value for First Name");

	}

	/*
	 * Test Case 04 Test Case Name - TC_04_verify_lastName_Mandatory_Field
	 * Test Objective: To verify system throws a valid message when 'last name' mandatory field is left blank
	 * email address
	 */

	@Test
	public void TC_04_verify_lastName_Mandatory_Field() {

		homePgObj = new Page_Home(driver);
		regPgObj = homePgObj.click_RegistrationLink();
		
		// Step 1: Verify the registration page is displayed
		
		flag = regPgObj.registrationPgDisplay();
		Assert.assertTrue(flag, "Registration page is displayed");
		
		// Step 2: Enter valid/mandatory registration details except last name
		
		regPgObj.enterRegistrationDetails("firstName", firstName);
		regPgObj.enterRegistrationDetails("lastName", "");
		regPgObj.enterRegistrationDetails("email", email);
		regPgObj.enterRegistrationDetails("password", password);
		regPgObj.enterRegistrationDetails("confirmPassword", confirmPassword);
		
		// Step 3: Click on Sign in button
		
		regPgObj.clickSignInButton();
		
		// Step 4: Verify user should NOT be able to proceed further with registration as proper validation message regarding last name
		// is displayed
		errMsg = Page_Registration.getMandatoryFieldErrMsg();
		Assert.assertEquals(errMsg, "Please enter a value for Last Name");

	}

	/*
	 * Test Case 05 Test Case Name - TC_05_verify_email_Mandatory_Field
	 * Test Objective: To verify system throws a valid message when 'email name' mandatory field is left blank
	 * email address
	 */
	@Test
	public void TC_05_verify_email_Mandatory_Field() {

		homePgObj = new Page_Home(driver);
		regPgObj = homePgObj.click_RegistrationLink();
		
		// Step 1: Verify the registration page is displayed
		
		flag = regPgObj.registrationPgDisplay();
		Assert.assertTrue(flag, "Registration page is displayed");
		
		// Step 2: Enter valid/mandatory registration details except email address
		regPgObj.enterRegistrationDetails("firstName", firstName);
		regPgObj.enterRegistrationDetails("lastName", lastName);
		regPgObj.enterRegistrationDetails("email", "");
		regPgObj.enterRegistrationDetails("password", password);
		regPgObj.enterRegistrationDetails("confirmPassword", confirmPassword);
		
		// Step 3: Click on Sign in button
		regPgObj.clickSignInButton();
		// Step 4: Verify user should NOT be able to proceed further with registration as proper validation message regarding email address
		// is displayed
		errMsg = Page_Registration.getMandatoryFieldErrMsg();
		Assert.assertEquals(errMsg, "Please enter a value for Email");

	}

	/*
	 * Test Case 06 Test Case Name - TC_06_verify_password_Mandatory_Field
	 * Test Objective: To verify system throws a valid message when 'password' mandatory field is left blank
	 * email address
	 */
	@Test
	public void TC_06_verify_password_Mandatory_Field() {

		homePgObj = new Page_Home(driver);
		regPgObj = homePgObj.click_RegistrationLink();
		
		// Step 1: Verify the registration page is displayed
		
		flag = regPgObj.registrationPgDisplay();
		Assert.assertTrue(flag, "Registration page is displayed");
		
		// Step 2: Enter valid/mandatory registration details except password
		regPgObj.enterRegistrationDetails("firstName", firstName);
		regPgObj.enterRegistrationDetails("lastName", lastName);
		regPgObj.enterRegistrationDetails("email", email);
		regPgObj.enterRegistrationDetails("password", "");
		regPgObj.enterRegistrationDetails("confirmPassword", confirmPassword);
		
		// Step 3: Click on Sign in button
		regPgObj.clickSignInButton();
		// Step 4: Verify user should NOT be able to proceed further with registration as proper validation message regarding password
		// is displayed
		errMsg = Page_Registration.getMandatoryFieldErrMsg();
		Assert.assertEquals(errMsg, "Please enter a value for Password");

	}
	
	/*
	 * Test Case 06 Test Case Name - TC_07_verify_Confirmpassword_Mandatory_Field
	 * Test Objective: To verify system throws a valid message when 'confirm password' mandatory field is left blank
	 * email address
	 */
	@Test
	public void TC_07_verify_Confirmpassword_Mandatory_Field() {

		homePgObj = new Page_Home(driver);
		regPgObj = homePgObj.click_RegistrationLink();
		
		// Step 1: Verify the registration page is displayed
		
		flag = regPgObj.registrationPgDisplay();
		
		// Step 2: Enter valid/mandatory registration details except confirm password
		
		Assert.assertTrue(flag, "Registration page is displayed");
		regPgObj.enterRegistrationDetails("firstName", firstName);
		regPgObj.enterRegistrationDetails("lastName", lastName);
		regPgObj.enterRegistrationDetails("email", email);
		regPgObj.enterRegistrationDetails("password", password);
		regPgObj.enterRegistrationDetails("confirmPassword", "");
		
		// Step 3: Click on Sign in button
		regPgObj.clickSignInButton();
		
		// Step 4: Verify user should NOT be able to proceed further with registration as proper validation message regarding confirm password
		// is displayed
		
		errMsg = Page_Registration.getMandatoryFieldErrMsg();
		Assert.assertEquals(errMsg, "Please enter a value for Confirm Password");

	}
		

	/*
	 * Test Case 07 Test Case Name - TC_07_verify_Invalid_Email_Address_format
	 * Test Objective: To verify system throws a valid message for invalid email address say '@' gets omitted in email address
	 * email address
	 */

	@Test
	public void TC_07_verify_Invalid_Email_Address_format() {

		homePgObj = new Page_Home(driver);
		regPgObj = homePgObj.click_RegistrationLink();
		
		// Step 1: Verify the registration page is displayed
		
		flag = regPgObj.registrationPgDisplay();
		
		// Step 2: Enter valid/mandatory registration details with invalid email address format
		Assert.assertTrue(flag, "Registration page is displayed");
		regPgObj.enterRegistrationDetails("firstName", firstName);
		regPgObj.enterRegistrationDetails("lastName", lastName);
		regPgObj.enterRegistrationDetails("email", InvalidEmail);
		regPgObj.enterRegistrationDetails("password", password);
		regPgObj.enterRegistrationDetails("confirmPassword", confirmPassword);
		
		// Step 3: Click on Sign in button
		
		regPgObj.clickSignInButton();
		
		// Step 4: Verify user should NOT be able to proceed further with registration as proper validation message regarding invalid email address
		//format is displayed
		
		errMsg = Page_Registration.getMandatoryFieldErrMsg();
		Assert.assertEquals(errMsg, "The email address is invalid.");

	}
	
	/*
	 * Test Case 08 Test Case Name - TC_08_verify_Existing_Email_Address
	 * Test Objective: To verify system throws a valid message when user tries to registger with already registered email address
	 * email address
	 */

	@Test
	public void TC_08_verify_Existing_Email_Address() {

		homePgObj = new Page_Home(driver);
		regPgObj = homePgObj.click_RegistrationLink();
		
		// Step 1: Verify the registration page is displayed
		
		flag = regPgObj.registrationPgDisplay();
		Assert.assertTrue(flag, "Registration page is displayed");
		
		// Step 2: Enter valid/mandatory registration details with valid email address already taken
		
		regPgObj.enterRegistrationDetails("firstName", firstName);
		regPgObj.enterRegistrationDetails("lastName", lastName);
		regPgObj.enterRegistrationDetails("email", email);
		regPgObj.enterRegistrationDetails("password", password);
		regPgObj.enterRegistrationDetails("confirmPassword", confirmPassword);
		
		// Step 3: Click on Sign in button
		
		regPgObj.clickSignInButton();
		
		// Step 4: Verify user should NOT be able to proceed further with registration as proper validation message regarding already registered
		//email address is displayed
		errMsg = Page_Registration.getMandatoryFieldErrMsg();
		Assert.assertEquals(errMsg, "Username already taken.");

	}

	
	/*
	 * Test Case 09 Test Case Name - TC_09_verify_Passowrd_Does_Not_Match_with_confirm_password
	 * Test Objective: To verify system throws a valid message when both password and confirm password do'nt match
	 */
	@Test
	public void TC_09_verify_Passowrd_Does_Not_Match_with_confirm_password() {
		homePgObj = new Page_Home(driver);
		regPgObj = homePgObj.click_RegistrationLink();
		// Step 1: Verify the registration page is displayed
		
		flag = regPgObj.registrationPgDisplay();
		Assert.assertTrue(flag, "Registration page is displayed");
		
		// Step 2: Enter valid/mandatory registration details with both password and confirm password do'nt match
		
		regPgObj.enterRegistrationDetails("firstName", firstName);
		regPgObj.enterRegistrationDetails("lastName", lastName);
		regPgObj.enterRegistrationDetails("email", email);
		regPgObj.enterRegistrationDetails("password", password);
		regPgObj.enterRegistrationDetails("confirmPassword", "test12345");
		
		// Step 3: Click on Sign in button
		
		regPgObj.clickSignInButton();
		
		// Step 4: Verify user should NOT be able to proceed further with registration as proper validation message regarding password and confirm
		// password do'nt match is displayed
		
		errMsg = Page_Registration.getpswdErrMsg();
		Assert.assertEquals(errMsg, "The password you entered does not match");

	}

	/*
	 * Test Case 10 Test Case Name - TC_10_verify_Passowrd_Requirements
	 * Test Objective: To verify system throws a valid message when password does not meet requirements say password length is less than
	 * required length or password does not alphanumeric
	 */
	@Test
	public void TC_10_verify_Passowrd_Requirements() {
		homePgObj = new Page_Home(driver);
		regPgObj = homePgObj.click_RegistrationLink();
		
		// Step 1: Verify the registration page is displayed
		
		flag = regPgObj.registrationPgDisplay();
		
		// Step 2: Enter valid/mandatory registration details with password does not meet system requirements
		
		Assert.assertTrue(flag, "Registration page is displayed");
		regPgObj.enterRegistrationDetails("firstName", firstName);
		regPgObj.enterRegistrationDetails("lastName", lastName);
		regPgObj.enterRegistrationDetails("email", email);
		regPgObj.enterRegistrationDetails("password", "test");
		regPgObj.enterRegistrationDetails("confirmPassword", "test");
		
		// Step 3: Click on Sign in button
		
		regPgObj.clickSignInButton();
		errMsg = Page_Registration.getpswdRqmtErrMsg();
		
		// Step 4: Verify user should NOT be able to proceed further with registration as proper validation message regarding password 
		// system requirements
		
		Assert.assertEquals(errMsg, "Password does not meet the minimum requirements. It must be at least 7 characters in length, contain one number and one letter.");

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
