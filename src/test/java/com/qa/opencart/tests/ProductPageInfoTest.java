package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ExcelUtil;

public class ProductPageInfoTest extends BaseTest{

	@BeforeClass
	public void infoPageSetup() {
		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@DataProvider
	public Object[][] getProductSearchData() {
		return new Object[][] {
			{"macbook", "MacBook Pro"},
			{"imac", "iMac"},
			{"samsung", "Samsung SyncMaster 941BW"},
			{"samsung", "Samsung Galaxy Tab 10.1"}
		};
		
	}
	
	
	@Test(dataProvider = "getProductSearchData")
	public void productHeaderTest(String searchKey, String productName) {
		searchResultPage = accPage.doSearch(searchKey);
		productInfoPage = searchResultPage.selectProduct(productName);
		Assert.assertEquals(productInfoPage.getProductHeader(), productName);
	}
	
	@DataProvider
	public Object[][] getProductImagesData() {
		return new Object[][] {
			{"macbook", "MacBook Pro", 4},
			{"imac", "iMac", 3},
			{"samsung", "Samsung SyncMaster 941BW", 1},
			{"samsung", "Samsung Galaxy Tab 10.1", 7}
		};
		
	}
	
	@DataProvider
	public Object[][] getProductImagesDataFromExcel(){
		return ExcelUtil.getTestData(AppConstants.PRODUCT_SHEET_NAME);
	}
	
	@Test(dataProvider = "getProductImagesDataFromExcel")
	public void productImagesCountTest(String searchKey, String productName, String imagesCount) {
		searchResultPage = accPage.doSearch(searchKey);
		productInfoPage = searchResultPage.selectProduct(productName);
		Assert.assertEquals(productInfoPage.getProductImagesCount(), Integer.parseInt(imagesCount));
	}
	
	@Test
	public void productInfoTest() {
		searchResultPage = accPage.doSearch("macbook");
		productInfoPage = searchResultPage.selectProduct("MacBook Pro");
		Map<String, String> productActDetailsMap = productInfoPage.getProductDetailsMap();
		softAssert.assertEquals(productActDetailsMap.get("Brand"), "Apple");
		softAssert.assertEquals(productActDetailsMap.get("Product Code"), "Product 18");
		softAssert.assertEquals(productActDetailsMap.get("Availibility"), "In Stock");
		softAssert.assertEquals(productActDetailsMap.get("productprice"), "$2,000.00");
		softAssert.assertEquals(productActDetailsMap.get("extaxprice"), "$2,000.00");
		softAssert.assertAll();
	}
	
}
