package com.assignment.appPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.assignment.basesetup.BaseSetUp;

public class Page_Home extends BaseSetUp {

	
	static boolean isEventSuccessful=false;
	
	public Page_Home(WebDriver driver)
    {
    	this.driver = driver;
    	PageFactory.initElements(driver,this);
    }

	
	@FindBy(xpath = "//a[@href='/home']")
	public static WebElement lnk_WareHouseHomePage;
	
	@FindBy(xpath = "//a[@title='Sign in']")
	public static WebElement lnk_SignIn;
	
	@FindBy(xpath = "//a[@title='Register']")
	public static WebElement lnk_Register;
	
	@FindBy(xpath = "//h1[@class='product-name hidden-phone ']")
	public static WebElement hd_srchdItem;
	
	@FindBy(xpath = "//span[@class='misspelled-words']")
	public static WebElement hd_NonsrchdItem;
	
	@FindBy(xpath = "//button[@data-twg-id='header-search-button']")
	public static WebElement btn_search;
	
	@FindBy(id = "q-search")
	public static WebElement input_searchBox;
	
		
	private static String itemText;

	public boolean verifyHomePage()
    {

        try
        {
           
        	if(lnk_WareHouseHomePage.isDisplayed())
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
	
	
	public boolean verifySignInLinkDisplay()
	{
		try
		{
			if(lnk_SignIn.isDisplayed())
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
	
	
	public Page_Account_Log_In clickSignInLink() {

		try {
			lnk_SignIn.click();
		}

		catch (Exception e) {
			System.out.println("Exception occured" + e.getMessage());
		}
		return new Page_Account_Log_In(driver);

	}

	public Page_Registration click_RegistrationLink() {
		try {
			lnk_Register.click();
		}

		catch (Exception e) {
			System.out.println("Exception occured" + e.getMessage());
		}
		return new Page_Registration(driver);

	}
	
	public void enterSearchItem(String itemName)
	{
		try
		{
			input_searchBox.clear();
			input_searchBox.sendKeys(itemName);
		}
		
		catch (Exception e) {
			System.out.println("Exception occured" + e.getMessage());
		}
	}
	
	public String getItemText(String itemType)
	{
		try
		{
			if(itemType.equalsIgnoreCase("Searchable Item"))
				itemText=hd_srchdItem.getText();
			else if(itemType.equalsIgnoreCase("Non Searchable Item"))
				itemText=hd_NonsrchdItem.getText();
		}
		
		catch (Exception e) {
			System.out.println("Exception occured" + e.getMessage());
		}
		return itemText;
		
	}
	
	public void clickSearchBtn()
	{
		try
		{
			btn_search.click();
		}
		catch (Exception e) {
			System.out.println("Exception occured" + e.getMessage());
		}
	}
}
