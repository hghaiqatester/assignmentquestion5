package com.assignment.appPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.assignment.basesetup.BaseSetUp;

public class Page_Search extends BaseSetUp{
	
static boolean isEventSuccessful=false;
	
	public Page_Search(WebDriver driver)
    {
    	this.driver = driver;
    	PageFactory.initElements(driver,this);
    }
	
	@FindBy(xpath = "//h1[@class='product-name hidden-phone ']")
	public static WebElement hd_srchdItem;
	
	
	private static String itemText;
	
	public static String getMandatoryFieldErrMsg()
	{
		try
		{
			itemText=hd_srchdItem.getText();
		}
		
		catch (Exception e) {
			System.out.println("Exception occured" + e.getMessage());
		}
		return itemText;
		
	}

}
