package com.salesforce.test.pages.login;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.salesforce.test.pages.base.BasePage;

public class LoginPage extends BasePage {
	@FindBy(xpath=".//input[@id='username']") WebElement username;
	@FindBy(xpath=".//input[@id='password']") WebElement password;
	@FindBy(xpath = "//input[@id='Login']") WebElement loginButton;
	@FindBy(xpath="//input[@id='rememberUn']") WebElement rememberme;
	@FindBy(css="#idcard-identity") WebElement ID;
	@FindBy(xpath="//a[@id='forgot_password_link']") WebElement forgotpwd;
	@FindBy(xpath="//div[@id='error']") WebElement loginerrormsg;
	
	public LoginPage(WebDriver driver) {
		
		super(driver);
	}
	
	public void enterUserName(String usrname) {
		waitUntilVisible(username,"user name field");
		enterText(username, usrname, "username field");
	}
	public void enterPassword(String passWord) {
		
		enterText(password, passWord, "password field");
		
	}
	public void clickforgotpwd() {
		clickElement(forgotpwd,"forgotpwd");
	}
	
	public void clickLoginButton() {
		clickElement(loginButton,"login button");
	}
	
	public void login(String usrname,String password) {
		enterUserName(usrname);
		enterPassword(password);
		clickLoginButton();
	}
	
	public void loginwithnopwd(String usrname,String password) {
		enterUserName(usrname);
		clearpassword(password);
		clickLoginButton();


	}
	public void clickrememberme() {
		clickElement(rememberme,"rememberme");
	}
	private void clearpassword(String password2) {
		waitUntilVisible(password,"password field");
		clearElement(password, password2);
	}
	public void loginwithremeberme(String usrname,String password) {
		enterUserName(usrname);
		enterPassword(password);
		clickrememberme();
		clickLoginButton();
	}
	public String gettextFromloginpage() {
		String text=readText(loginerrormsg, "text form field");
		String path=captureWebElementScreenshot(loginerrormsg, "loginerrormsg");
		try {
			report.attachScreeshot(path);
		} catch (IOException e) {
			report.logTestFailedWithException(e);
		}
		return text;
	}
	public String gettextFromID() {
		String text=readText(ID, "text form field");
		String path=captureWebElementScreenshot(ID, "ID");
		try {
			report.attachScreeshot(path);
		} catch (IOException e) {
			report.logTestFailedWithException(e);
		}
		return text;
	}

}

