package com.assignment.appPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.assignment.basesetup.BaseSetUp;

public class Page_Registration extends BaseSetUp {
	
static boolean isEventSuccessful=false;
	
	public Page_Registration(WebDriver driver)
    {
    	this.driver = driver;
    	PageFactory.initElements(driver,this);
    }
	
	@FindBy(xpath = "//span[text()='Account Settings ']")
	public static WebElement lnk_AcctSetting;
	
	@FindBy(id = "dwfrm_profile_customer_firstname")
	public static WebElement txt_FirstName;
	
	@FindBy(id = "dwfrm_profile_customer_lastname")
	public static WebElement txt_LastName;
	
	@FindBy(id = "dwfrm_profile_customer_email")
	public static WebElement txt_Email;
	
	@FindBy(id = "dwfrm_profile_login_password")
	public static WebElement txt_Password;
	
	@FindBy(id = "dwfrm_profile_login_passwordconfirm")
	public static WebElement txt_ConfirmPassword;
	
	@FindBy(name = "dwfrm_profile_confirm")
	public static WebElement btn_SignIn;
	
	@FindBy(xpath = "//h1[@class='account-ttl h1']")
	public static WebElement hd_userName;

	@FindBy(xpath = "//div[@class='controls']//span")
	public static WebElement txt_ManfieldsErrMsg;
	
	@FindBy(xpath = "//span[text()='The password you entered does not match']")
	public static WebElement txt_confirmPswdErrMsg;
	
	@FindBy(xpath = "//span[text()='Password does not meet the minimum requirements. It must be at least 7 characters in length, contain one number and one letter.'][1]")
	public static WebElement txt_pswdReqErrMsg;
	
	
	private static String userInfo;
	private static String errMsg;
	
	public boolean registrationPgDisplay()
	{
		
			try
			{
				if(lnk_AcctSetting.isDisplayed())
	        		isEventSuccessful=true;
	        	else
	        		isEventSuccessful=false;
			}
			
			catch(Exception e)
	        {
	         System.out.println("Exception occured" + e.getMessage());
	        }
			return isEventSuccessful;	
	}
	
	public void enterRegistrationDetails(String dataType, String inputData) {
		{
			try {
				switch (dataType) {
				case "firstName":
					// txt_emailAdd.clear();
					txt_FirstName.sendKeys(inputData);

					break;
				case "lastName":

					//txt_password.clear();
					txt_LastName.sendKeys(inputData);
					break;
				case "email":

					//txt_password.clear();
					txt_Email.sendKeys(inputData);
					break;
				case "password":

					//txt_password.clear();
					txt_Password.sendKeys(inputData);
					break;
				case "confirmPassword":

					//txt_password.clear();
					txt_ConfirmPassword.sendKeys(inputData);
					break;

				default:
					System.out.println("No match");
				}
			} catch (Exception e) {
				System.out.println("Exception occured" + e.getMessage());
			}
		}
	}
	
	public void clickSignInButton()
	{
		try
		{
		if(btn_SignIn.isDisplayed())
			btn_SignIn.click();
		else
			System.out.println("Sign in button is not displayed");
		}
		
		catch (Exception e) {
			System.out.println("Exception occured" + e.getMessage());
		}
	}
	
	public static String getRegisteredUserInfo()
	{
		try
		{
			userInfo=hd_userName.getText();
		}
		
		catch (Exception e) {
			System.out.println("Exception occured" + e.getMessage());
		}
		return userInfo;
		
	}
	
	public static String getMandatoryFieldErrMsg()
	{
		try
		{
			errMsg=txt_ManfieldsErrMsg.getText();
		}
		
		catch (Exception e) {
			System.out.println("Exception occured" + e.getMessage());
		}
		return errMsg;
		
	}
	
	public static String getpswdErrMsg()
	{
		try
		{
			errMsg=txt_confirmPswdErrMsg.getText();
		}
		
		catch (Exception e) {
			System.out.println("Exception occured" + e.getMessage());
		}
		return errMsg;
		
	}
	
	public static String getpswdRqmtErrMsg()
	{
		try
		{
			errMsg=txt_pswdReqErrMsg.getText();
		}
		
		catch (Exception e) {
			System.out.println("Exception occured" + e.getMessage());
		}
		return errMsg;
		
	}

}
