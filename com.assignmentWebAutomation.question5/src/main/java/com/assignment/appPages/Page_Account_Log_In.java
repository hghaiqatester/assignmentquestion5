package com.assignment.appPages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.assignment.basesetup.BaseSetUp;

public class Page_Account_Log_In extends BaseSetUp {
	
static boolean isEventSuccessful=false;
	
	public Page_Account_Log_In(WebDriver driver)
    {
    	this.driver = driver;
    	PageFactory.initElements(driver,this);
    }
	
	@FindBy(xpath = "//span[text()='Account Log In']")
	public static WebElement txt_AcctLogin;
	
	
	//@FindBy(xpath = "//input[contains(@id,'dwfrm_login_username')]")
	//public static WebElement txt_emailAdd;
	
	@FindBy(css = "input[class^='input-text email-input vd-email']")
	public static WebElement txt_emailAdd;
	
	@FindBy(xpath = "//input[@id='dwfrm_login_password']")
	public static WebElement txt_password;
	
	@FindBy(xpath = "//button[@name='dwfrm_login_login']")
	public static WebElement btn_SignIn;
	
	@FindBy(xpath = "//div[@class='error-form']")
	public static WebElement txt_ErrMsg;
	
	@FindBy(xpath = "//span[@class='error']")
	public static WebElement txt_ErrMsg_Blank;
	
	
	static String errMsg;
	static String errMSg_BlankCre;
	
	public boolean accountLoginPgDisplay()
	{
		
			try
			{
				if(txt_AcctLogin.isDisplayed())
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
	
	public void enterSignInDetails(String dataType, String inputData) {
		{
			try {
				switch (dataType) {
				case "emailAddress":
					// txt_emailAdd.clear();
					txt_emailAdd.sendKeys(inputData);

					//JavascriptExecutor js = (JavascriptExecutor)driver;
					//js.executeScript("document.getElementById('dwfrm_login_username_d0xuialvdrew').value='Virende';");
					break;
				case "password":

					txt_password.clear();
					txt_password.sendKeys(inputData);
					break;

				default:
					System.out.println("No match");
				}
			} catch (Exception e) {
				System.out.println("Exception occured" + e.getMessage());
			}
		}
	}
	
	public static String getErrorText()
	{
		try {
			errMsg=txt_ErrMsg.getText();
			
		}
		
		catch (Exception e) {
			System.out.println("Exception occured" + e.getMessage());
		}
		return errMsg;
	}
	
	public static String getErrorText_BlankCredentials()
	{
		try {
			errMSg_BlankCre=txt_ErrMsg_Blank.getText();
			
		}
		
		catch (Exception e) {
			System.out.println("Exception occured" + e.getMessage());
		}
		return errMSg_BlankCre;
	}
	
	public Page_Logged_In clickSignInButton() {
		try {
			btn_SignIn.click();
		} catch (Exception e) {
			System.out.println("Exception occured" + e.getMessage());
		}
		return new Page_Logged_In(driver);
	}
}


