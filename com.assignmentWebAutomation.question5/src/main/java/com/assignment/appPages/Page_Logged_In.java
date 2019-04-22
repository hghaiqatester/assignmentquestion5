package com.assignment.appPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.assignment.basesetup.BaseSetUp;

public class Page_Logged_In extends BaseSetUp{
	
static boolean isEventSuccessful=false;
	
	public Page_Logged_In(WebDriver driver)
    {
    	this.driver = driver;
    	PageFactory.initElements(driver,this);
    }
	
	@FindBy(xpath = "//a[@title='Log Out']")
	public static WebElement lnk_logOut;
	
	public boolean verifyLogOutLink()
    {

        try
        {
           WebDriverWait wait= new WebDriverWait(driver,20);
          wait.until(ExpectedConditions.elementToBeClickable(lnk_logOut));
        	if(lnk_logOut.isDisplayed())
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
	

}
