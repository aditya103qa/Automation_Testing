package com.qait.Automation.HrisLogin;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class HrisLoginTestNG {
	WebDriver driver;

	@BeforeTest
	public void init() {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\USER\\Downloads\\chromedriver_win32 (1)\\chromedriver.exe");
		driver = new ChromeDriver();
	}

	@Test //test-1 to check the launching of site
	public void Test1ToLaunchHris() {
		driver.get("https://hris.qainfotech.com/login.php");
	}

	@Test //test-2 to enter incorrect password then invalid login shows up
	public void Test2ToLoginWithIncorrectPasswordWillShowInvalidLogin()
	{
		driver.findElement(By.id("txtUserName")).sendKeys("adityadwivedi");
		driver.findElement(By.id("txtPassword")).sendKeys("Incorrect_Password");
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		driver.findElement(By.cssSelector("#login > form > div.loginTxtBtn.extraText > div > input")).submit();

		// Expected error Message (Assertion)

		Assert.assertTrue(
				driver.findElement(By.cssSelector("#login > form > div.loginTxt.txtHideDiv.alert.alert-error > div"))
				.getText().equals("Invalid Login"));
	}


	@Test //test-3 to enter blank password then text-field border turns red

	public void Test3ToLoginWithBlankPasswordWillHighlightRedBox()
	{
		driver.findElement(By.id("txtUserName")).clear();
		driver.findElement(By.id("txtUserName")).sendKeys("adityadwivedi");
		driver.findElement(By.id("txtPassword")).clear();
		driver.findElement(By.id("txtPassword")).sendKeys("");
		driver.findElement(By.cssSelector("#login > form > div.loginTxtBtn.extraText > div > input")).submit();
		//Expected Error
		String border=driver.findElement(By.cssSelector("#txtPassword")).getAttribute("style");
		Assert.assertTrue(border.contains("red"));
		

	}

	@Test
	public void Test4ToLoginWithCorrectPassword()
	{driver.findElement(By.id("txtUserName")).clear();
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		driver.findElement(By.id("txtUserName")).sendKeys("adityadwivedi");
		driver.findElement(By.id("txtPassword")).clear();
		driver.findElement(By.id("txtPassword")).sendKeys("Aditya@321#");
		driver.findElement(By.cssSelector("#login > form > div.loginTxtBtn.extraText > div > input")).submit();
		
	}

	@AfterClass
	public void CloseTab()
	{
		driver.quit();

	}
}
