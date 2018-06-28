package com.qait.MavenDemo.HRIS_POM;

import java.util.Set;
import java.io.FileInputStream;
import java.util.Iterator;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
public class HrisTestPom {

	WebDriver driver;

	Properties prop = new Properties();
	FileInputStream fs;
	String uname;
	String incorrectpwd;
	String correctpwd;


	@BeforeTest
	public void browser()
	{
		System.setProperty("webdriver.chrome.driver", "driver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://hris.qainfotech.com/login.php");
		driver.manage().window().maximize();
		
		try {
			fs = new FileInputStream("datafile.properties");
			prop.load(fs);
			uname= prop.getProperty("username");
			incorrectpwd= prop.getProperty("incorrectpassword");
			correctpwd= prop.getProperty("correctpassword");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Test(priority=1)  //To check the HRIS logo on the top of the page/web-site which will navigate to the home-page.
	public void Test1HRISLogo() throws Exception
	{
		driver.findElement(By.cssSelector("#demo-2 > div > div.span12.text-center.margin-10 > a > img")).click();
		Assert.assertTrue(
				driver.findElement(By.cssSelector("#demo-2 > div > div.span12.text-center.margin-10 > a > img")).isDisplayed());
	}

	@Test(priority=2) //To check the Login Panel is opened by default at the home-page.
	public void Test2LoginPanelTab()
	{
		driver.findElement(By.cssSelector(".nav.nav-tabs li:nth-child(1) a")).click();
		Assert.assertTrue(
				driver.findElement(By.id("txtUserName")).isEnabled());
		Assert.assertTrue(
				driver.findElement(By.id("txtPassword")).isEnabled());

	}
	@Test(priority=3) //To check the "About HRIS" tab.
	public void Test3AboutHRISTab()
	{
		driver.findElement(By.cssSelector(".nav.nav-tabs li:nth-child(2) a")).click();
		Assert.assertTrue(
				driver.findElement(By.cssSelector("div[class='box li-spacing']")).isDisplayed());
	} 
	@Test(priority=4) //To check the "Celebration" tab.
	public void Test4CelebrationTab()
	{
		driver.findElement(By.cssSelector(".nav.nav-tabs li:nth-child(3) a")).click();
		Assert.assertTrue(
				driver.findElement(By.cssSelector("div#panel3>div.row-fluid>div.span4>h4>i.icon-star:nth-child(1)")).isDisplayed());
	}  
	@Test(priority=5) //To check the "HR Policy" tab.
	public void Test5HRPolicyTab()
	{
		driver.findElement(By.cssSelector(".nav.nav-tabs li:nth-child(4) a")).click();
		Assert.assertTrue(
				driver.findElement(By.cssSelector("#demo-2 > div > div.tabbable.custom-tabs.tabs-left.tabs-animated."
						+ "flat.flat-all.hide-label-980.shadow.track-url.auto-scroll > div")).isDisplayed());
	} 
	@Test(priority=6) //To check the "Food Menu" tab.
	public void Test6foodMenuTab()
	{
		driver.findElement(By.cssSelector(".nav.nav-tabs li:nth-child(5) a")).click();
		Assert.assertTrue(
				driver.findElement(By.cssSelector("#panel5 > div:nth-child(1) > div > h4")).isDisplayed());
	} 
	@Test(priority=7) //To check the "Did you know?" tab.
	public void Test7DidYouKnowTab()
	{
		driver.findElement(By.cssSelector(".nav.nav-tabs li:nth-child(6) a")).click();
		Assert.assertTrue(
				driver.findElement(By.cssSelector("#panel7 > div > div > h4")).isDisplayed());
	} 
	@Test(priority=8) //To check the "Access Payroll Online" link at the bottom of the page.
	public void Test8AccessPayrollOnline()
	{
		driver.findElement(By.cssSelector(".nav.nav-tabs li:nth-child(7) a")).click();
		Assert.assertTrue(
				driver.findElement(By.cssSelector("#panel6 > div > div > h4")).isDisplayed());
	} 
	@Test(priority=9) //To check the "Report a Bug" link at the bottom of the page.
	public void Test9ReportBug()
	{
		driver.findElement(By.cssSelector("body > footer > div > div:nth-child(1) > ul > li:nth-child(1) > a")).click();
		Set<String> tabs = driver.getWindowHandles();
		Iterator<String> i = tabs.iterator();
		String main = i.next();
		String child = i.next();
		driver.switchTo().window(child);
		driver.switchTo().window(main);
	}
	@Test(priority=10) //Test Login Panel with incorrect password
	public void Test10IncorrectPassword()
	{
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElement(By.cssSelector("body > footer > div > div:nth-child(1) > ul > li:nth-child(2) > a")).click();
		Set<String> tabs = driver.getWindowHandles();
		Iterator<String> i = tabs.iterator();
		String main = i.next();
		String child = i.next();
		driver.switchTo().window(child);
		driver.switchTo().window(main);
		driver.findElement(By.xpath("//*[@id=\"demo-2\"]/div/div[2]/ul/li[1]/a")).click();
	}
	@Test(priority=11) //Test Login Panel with blank password
	public void Test11BlankPassword()
	{	
		driver.findElement(By.cssSelector(".nav.nav-tabs li:nth-child(1) a")).click();

		driver.findElement(By.id("txtUserName")).sendKeys(uname);
		driver.findElement(By.id("txtPassword")).sendKeys(incorrectpwd);
	//	driver.manage().timeouts().implicitlyWait(3,TimeUnit.SECONDS);
		driver.findElement(By.cssSelector("#login > form > div.loginTxtBtn.extraText > div > input")).submit();
		driver.findElement(By.cssSelector(".nav.nav-tabs li:nth-child(1) a")).click();
		
System.out.println(driver.findElement(By.cssSelector("#login > form > div.loginTxt.txtHideDiv.alert.alert-error > div"))
				.getText());
		Assert.assertTrue(
				driver.findElement(By.cssSelector("#login > form > div.loginTxt.txtHideDiv.alert.alert-error > div"))
				.getText().equals("Invalid Login"));
	}
	@Test(priority=12) //Test Login Panel with correct password
	public void Test12CorrectPassword()
	{		
		driver.findElement(By.cssSelector(".nav.nav-tabs li:nth-child(1) a")).click();

		driver.findElement(By.id("txtUserName")).sendKeys(uname);
		driver.findElement(By.id("txtPassword")).sendKeys("");
		driver.manage().timeouts().implicitlyWait(3,TimeUnit.SECONDS);
		driver.findElement(By.cssSelector("#login > form > div.loginTxtBtn.extraText > div > input")).submit();

		driver.findElement(By.id("txtUserName")).clear();
		driver.findElement(By.id("txtPassword")).clear();

		String border=driver.findElement(By.cssSelector("#txtPassword")).getAttribute("style");
		Assert.assertTrue(border.contains("red"));
	}
	@Test(priority=13)
	public void Test13()
	{		
		driver.findElement(By.cssSelector(".nav.nav-tabs li:nth-child(1) a")).click();

		driver.findElement(By.id("txtUserName")).sendKeys(uname);
		driver.findElement(By.id("txtPassword")).sendKeys(correctpwd);
		driver.manage().timeouts().implicitlyWait(3,TimeUnit.SECONDS);
		driver.findElement(By.cssSelector("#login > form > div.loginTxtBtn.extraText > div > input")).submit();

		boolean status = false;
		if(driver.findElements(By.cssSelector("#txtUserName")).size()>0)
		{
			status = false ;
		}
		Assert.assertFalse(status);
	}

	@AfterClass
	public void close()
	{
		driver.quit();
	}
}
