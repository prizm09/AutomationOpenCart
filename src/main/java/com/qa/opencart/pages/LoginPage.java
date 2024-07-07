package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.logger.Log;
import com.qa.opencart.utils.ElementUtil;
import com.qa.opencart.utils.TimeUtil;

import io.qameta.allure.Step;

public class LoginPage {

	// Page Class/Page Library/Page Object
	private WebDriver driver;
	private ElementUtil eleUtil;

	// 1. Private By Locators

	private By emailId = By.id("input-email");
	private By password = By.id("input-password");
	private By loginButton = By.xpath("//input[@value='Login']");
	private By forgotPWdLink = By.linkText("Forgotten Password");
	private By registerLink = By.linkText("Register");
	// 2. Public Page Class Constructor

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	// 3. Public Page Actions/Methods
	// Accessing the Private member of the class by public method is the concept of
	// Encapsulation

	@Step("getting login page title...")
	public String getLoginPageTitle() {

		String title = eleUtil.waitForTitleIs(AppConstants.LOGIN_PAGE_TITLE, TimeUtil.DEFAULT_MEDIUM_TIME);
		//System.out.println("Login page title is: " + title);
		Log.info("Login page title is: " + title);
		return title;
	}

	@Step("getting login page title...")
	public String getLoginPageUrl() {
		String url = eleUtil.waitForURLContains(AppConstants.LOGIN_PAGE_URL_FRACTION, TimeUtil.DEFAULT_MEDIUM_TIME);
		System.out.println("Login page url is: " + url);
		return url;
	}

	@Step("getting the state of forget password link...")
	public boolean isForgotPwdLinkExist() {
		return eleUtil.isElementDisplayed(forgotPWdLink);
	}

	@Step("login with username: {0} and password: {1}")
	public AccountsPage doLogin(String username, String pwd) {
		System.out.println("user creds: " + username + " : " + pwd);
		eleUtil.waitForElementVisible(emailId, TimeUtil.DEFAULT_LONG_TIME).sendKeys(username);
		eleUtil.doSendKeys(password, pwd);
		eleUtil.doClick(loginButton);
		return new AccountsPage(driver);
	}
	
	@Step("navigating to registeration page...")
	public RegistrationPage navigateToRegisterPage() {
		eleUtil.waitForElementVisible(registerLink, TimeUtil.DEFAULT_LONG_TIME).click(); 
		return new RegistrationPage(driver);
	} 

}
