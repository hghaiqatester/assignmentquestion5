package com.assignment.TestCases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.assignment.appPages.Page_Home;
import com.assignment.basesetup.BaseSetUp;

public class TS_03_Search_Functionality extends BaseSetUp{
	Page_Home homePgObj;
	private String itemTxt = prop.getProperty("Item_Name");
	
	/* 
	 * Method Name - beforeMethod
	 * Test Objective: To initialize configuration file and setting up browser from base setup class before every test method
	 */
	
	@BeforeMethod
	public void beforeMethod() {
		initializeTestBaseSetup();
	}
	
	/*
	 * Test Case Name - verifySearchItem 
	 * Test Objective: To verify whether system is able to search an existing item
	 */
	
	@Test
	public void verifySearchItem() throws InterruptedException
	{
		homePgObj = new Page_Home(driver);
		homePgObj.enterSearchItem(itemTxt);
		homePgObj.clickSearchBtn();
		itemTxt=homePgObj.getItemText("Searchable Item");
		Assert.assertEquals(itemTxt, "Garage Women's PU Leather Jacket");
	}
	
	/*
	 * Test Case Name - verifySearch_NonExistentItem 
	 * Test Objective: To verify whether system should not able to search non-existing item
	 */
	
	@Test
	public void verifySearch_NonExistentItem() throws InterruptedException
	{
		homePgObj = new Page_Home(driver);
		homePgObj.enterSearchItem("test123");
		homePgObj.clickSearchBtn();
		itemTxt=homePgObj.getItemText("Non Searchable Item");
		Assert.assertTrue(itemTxt.contains("We're sorry, no products were found for your search:"));
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
