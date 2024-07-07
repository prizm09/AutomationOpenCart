package com.qa.opencart.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.pages.AccountsPage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencart.pages.RegistrationPage;
import com.qa.opencart.pages.SearchResultsPage;

import io.qameta.allure.Step;

public class BaseTest {
	
	WebDriver driver;
	protected Properties prop;
	DriverFactory df;
	
	//The reason to make this protected is that, the inherit class can access the login page reference and access their methods.
	protected LoginPage loginPage; 
	protected AccountsPage accPage;
	protected SearchResultsPage searchResultPage;
	protected RegistrationPage registrationPage;
	protected ProductInfoPage productInfoPage;
	protected SoftAssert softAssert;
	
	@Step("Setup: launching {0} browser & init the properties")
	@Parameters({"browser"})
	@BeforeTest
	public void setup(String browserName) {
		df = new DriverFactory();
		prop = df.initProp();
		driver = df.initDriver(prop);	// call by reference
		loginPage = new LoginPage(driver);
		softAssert = new SoftAssert();
	}
	
	@Step("Closing the browser")
	@AfterTest
	public void tearDown() {
		driver.quit();
	}
	

}
