package MavenTesting.TatocBasic;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class Tatoc extends TatocMain{
	WebDriver driver;
	public static void main(String[] args) throws InterruptedException {
		TatocMain tm = new TatocMain();
				tm.browser();
	tm.driver.manage().window().maximize();   
		tm.driver.get("http://10.0.1.86/tatoc");  
		tm.driver.findElement(By.cssSelector("a")).click(); 
		//1.GRID GATE
		WebElement red= tm.driver.findElement(By.className("redbox"));  
		red.click();  //error page due to red box event
		tm.driver.navigate().back();  //go back

		WebElement green= tm.driver.findElement(By.className("greenbox"));
		green.click();  //next case

		//2.FRAME DUNGEON
		tm.driver.switchTo().frame(tm.driver.findElement(By.id("main")));   
		String box1= tm.driver.findElement(By.cssSelector("#answer")).getAttribute("class"); 

		tm.driver.switchTo().frame(tm.driver.findElement(By.id("child"))); 
		String box2= tm.driver.findElement(By.cssSelector("#answer")).getAttribute("class");   

		while (!(box1.equals(box2))) {  
			tm.driver.switchTo().defaultContent(); 
			tm.driver.switchTo().frame(tm.driver.findElement(By.id("main"))); 
			tm.driver.findElement(By.cssSelector("body > center > a:nth-child(7)")).click(); 
			tm.driver.switchTo().frame(tm.driver.findElement(By.id("child")));  
			box2 = tm.driver.findElement(By.cssSelector("#answer")).getAttribute("class");
		}
		tm.driver.switchTo().defaultContent(); 
		tm.driver.switchTo().frame(tm.driver.findElement(By.id("main"))); 
		tm.driver.findElement(By.xpath("/html/body/center/a[2]")).click(); 

		//3.DRAG AROUND
		WebElement drop=tm.driver.findElement(By.id("dropbox")); 
		WebElement drag=tm.driver.findElement(By.id("dragbox")); 
		Actions dragAndDrop = new Actions(tm.driver);   
		dragAndDrop.dragAndDrop(drag, drop).build().perform();
		tm.driver.findElement(By.cssSelector("body > div > div.page > a")).click();

		//4.POPUP WINDOWS
		String mainwindow= tm.driver.getWindowHandle(); 
		tm.driver.findElement(By.xpath("/html/body/div/div[2]/a[1]")).click();

		for(String win : tm.driver.getWindowHandles()) 
		{      tm.driver.switchTo().window(win);  
		}
		tm.driver.findElement(By.xpath("//input[@id='name']")).sendKeys("pass"); 
		tm.driver.findElement(By.xpath("//input[@id='submit']")).click();
		for(String window : tm.driver.getWindowHandles())
		{
			tm.driver.switchTo().window(mainwindow);
		}
		tm.driver.findElement(By.xpath("//a[contains(text(),'Proceed')]")).click();
		//5.COOKIE HANDLING
		tm.driver.findElement(By.xpath("/html/body/div/div[2]/a[1]")).click(); 
		String t=tm.driver.findElement(By.xpath("//*[@id=\"token\"]")).getText();  
		String[] tokenValue = t.split("\\s");
		String token = tokenValue[1];  
		Cookie cookie = new Cookie("Token", token);  
		tm.driver.manage().addCookie(cookie);  
		tm.driver.findElement(By.xpath("//a[contains(text(),'Proceed')]")).click(); 

		Thread.sleep(1580);  
		tm.driver.close(); 

	}
}
