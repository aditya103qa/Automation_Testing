import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;

import org.openqa.selenium.*;
public class Auto2 {
    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\adityadwivedi\\Downloads\\chromedriver_win32\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();   //maximize the window
        driver.get("http://10.0.1.86/tatoc/basic");  //open the tatoc site for basic coarse
        
        driver.findElement(By.cssSelector("a")).click(); //click on the first anchor tag to go to basic coarse
        
        WebElement red= driver.findElement(By.className("redbox"));
        red.click();  //error page due to red box event
        driver.navigate().back();  //go back
        
        WebElement green= driver.findElement(By.className("greenbox"));  
        green.click();  //next case
        
        driver.switchTo().frame(driver.findElement(By.id("main")));  //switch to main frame for box1 
        String box1= driver.findElement(By.cssSelector("#answer")).getAttribute("class");  //color of box1
        
        driver.switchTo().frame(driver.findElement(By.id("child")));  //switch to main frame for box2
        String box2= driver.findElement(By.cssSelector("#answer")).getAttribute("class");   //color of box2
        
        while (!(box1.equals(box2))) {  
        driver.switchTo().defaultContent(); //to switch back to parent frame
        driver.switchTo().frame(driver.findElement(By.id("main"))); //switch back to main frame
        driver.findElement(By.cssSelector("body > center > a:nth-child(7)")).click(); //click on repaint box2
        driver.switchTo().frame(driver.findElement(By.id("child")));  
        box2 = driver.findElement(By.cssSelector("#answer")).getAttribute("class");
        }
        driver.switchTo().defaultContent(); //to switch back to parent frame
        driver.switchTo().frame(driver.findElement(By.id("main"))); //switch back to main frame
        driver.findElement(By.xpath("/html/body/center/a[2]")).click();  //click on proceed
        
        driver.findElement(By.id("dropbox"));
        driver.findElement(By.id("dragbox")); 
        
        
   
}
}