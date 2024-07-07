package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;

public class SearchResultsTest extends BaseTest{

	@BeforeClass
	public void searchResultSetup() {
		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	
	@DataProvider
	public Object[][] getProductCountData() {
		return new Object[][] {
			{"macbook", 3},
			{"imac", 1},
			{"samsung", 2}
		};
		
	}
	
	@Test(dataProvider = "getProductCountData")
	public void searchResultsCountTest(String searchKey, int productCount) {
		searchResultPage = accPage.doSearch(searchKey);
		Assert.assertEquals(searchResultPage.getSearchProductCount(), productCount);
	}
	
	@Test
	public void searchResultsTest() {
		searchResultPage = accPage.doSearch("macbook");
		Assert.assertEquals(searchResultPage.getSearchProductCount(),3);
	}
}
