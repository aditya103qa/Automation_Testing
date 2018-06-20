package com.qait.AutomationTraining.TestNGAuto;

import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;


public class FirstTestNG {
	
	WebDriver driver;

	@BeforeTest
	public void init() {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\adityadwivedi\\Downloads\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();
	}
	
	@Test //test-1 to check the launching of site
	public void TestToLaunchHris() {
	
		driver.get("https://hris.qainfotech.com/login.php");
	}

	@Test //test-2 to enter incorrect password then invalid login shows up
	public void TestToLoginWithIncorrectPasswordWillShowInvalidLogin()
	{
		driver.findElement(By.cssSelector("#txtUserName")).sendKeys("adityadwivedi");
		driver.findElement(By.id("txtPassword")).sendKeys("Incorrect_Password");
		driver.findElement(By.cssSelector("#login > form > div.loginTxtBtn.extraText > div > input")).submit();

		// Expected error Message (Assertion)

		Assert.assertTrue(
				driver.findElement(By.cssSelector("#login > form > div.loginTxt.txtHideDiv.alert.alert-error > div"))
						.getText().contains("INVALID LOGIN"));
     }
	
	
   @Test //test-3 to enter blank password then text-feild border turns red
	
	public void TestToLoginWithBlankPasswordWillHighlightRedBox()
	{
		driver.findElement(By.cssSelector("#txtUserName")).sendKeys("adityadwivedi");
		driver.findElement(By.id("txtPassword")).sendKeys("");
		driver.findElement(By.cssSelector("#login > form > div.loginTxtBtn.extraText > div > input")).submit();
		
		//Expected Error
		String box= driver.findElement(By.cssSelector("#txtPassword")).getAttribute("style");
		Assert.assertTrue(
				driver.findElement(By.cssSelector("#login > form > div.loginTxt.txtHideDiv.alert.alert-error > div"))
						.getText().contains("INVALID LOGIN"));
	}
	
	@AfterTest
   public void Step_04_ClearForm()
	{
		driver.findElement(By.cssSelector("input[id=txtUserName]")).clear();
		driver.findElement(By.id("txtPassword")).clear();	
		
	}
}
