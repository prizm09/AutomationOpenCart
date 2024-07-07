package com.qa.opencart.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;

public class AccountsPageTest extends BaseTest {

	@BeforeClass
	public void accSetup() {
		
		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		
	}
	
	@Test
	public void accPageTitleTest() {
		String actTitle = accPage.getAccPageTitle();
		Assert.assertEquals(actTitle, AppConstants.ACCOUNTS_PAGE_TITLE);
	}
	
	@Test
	public void accPageUrlTest() {
		String actUrl = accPage.getAccPageUrl();
		Assert.assertTrue(actUrl.contains(AppConstants.ACC_PAGE_URL_FRACTION));
	}
	
	@Test
	public void isLogoutLinkExistTest() {
		Assert.assertTrue(accPage.isLogoutLinkExist());
	}
	
	@Test
	public void isMyAccLinkExistTest() {
		Assert.assertTrue(accPage.isMyAccLinkExist());
	}
	
	@Test
	public void accPageHeadersTest() {
		List<String> actHeadersList = accPage.getAccountsPageHeadersList();
		System.out.println(actHeadersList);
	}
	
	@Test
	public void searchTest() {
		accPage.doSearch("macbook");
	}
	
}
