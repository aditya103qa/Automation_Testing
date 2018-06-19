import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import java.util.Set;

import org.openqa.selenium.*;
public class Auto2 {
    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\Java\\selenium\\drivers\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();   //maximize the window
        driver.get("http://10.0.1.86/tatoc/basic");  //open the tatoc site for basic coarse

        driver.findElement(By.cssSelector("a")).click(); //click on the first anchor tag to go to basic coarse

        //1.GRID GATE
        WebElement red= driver.findElement(By.className("redbox"));  //selecting red-box
        red.click();  //error page due to red box event
        driver.navigate().back();  //go back

        WebElement green= driver.findElement(By.className("greenbox"));  //selecting green-box
        green.click();  //next case

        //2.FRAME DUNGEON
        driver.switchTo().frame(driver.findElement(By.id("main")));  //switch to main frame for box1
        String box1= driver.findElement(By.cssSelector("#answer")).getAttribute("class");  //color of box1

        driver.switchTo().frame(driver.findElement(By.id("child")));  //switch to main frame for box2
        String box2= driver.findElement(By.cssSelector("#answer")).getAttribute("class");   //color of box2

        while (!(box1.equals(box2))) {
            driver.switchTo().defaultContent(); //to switch back to parent frame
            driver.switchTo().frame(driver.findElement(By.id("main"))); //switch back to main frame
            driver.findElement(By.cssSelector("body > center > a:nth-child(7)")).click(); //click on repaint box2
            driver.switchTo().frame(driver.findElement(By.id("child")));
            box2 = driver.findElement(By.cssSelector("#answer")).getAttribute("class");  //changing the color of box2
        }
        driver.switchTo().defaultContent(); //to switch back to parent frame
        driver.switchTo().frame(driver.findElement(By.id("main"))); //switch back to main frame
        driver.findElement(By.xpath("/html/body/center/a[2]")).click();  //click on proceed

        //3.DRAG AROUND
        WebElement drop=driver.findElement(By.id("dropbox"));  //drop-box element
        WebElement drag=driver.findElement(By.id("dragbox"));  //drag-box element
        Actions dragAndDrop = new Actions(driver);   //Action class is used to operate without using mouse
        dragAndDrop.dragAndDrop(drag, drop).build().perform(); //dragAndDrop method to perform drag-drop
        driver.findElement(By.cssSelector("body > div > div.page > a")).click(); //click to proceed

        //4.POPUP WINDOWS
        String mainwindow= driver.getWindowHandle();  //return the main window handle no.
        driver.findElement(By.xpath("/html/body/div/div[2]/a[1]")).click();  //click on the popup link will open new window

        for(String window : driver.getWindowHandles())  //storing the no of tabs in window of type string
        {      driver.switchTo().window(window);  //for the other than main-window tab switch to that tab
        }
        driver.findElement(By.xpath("//input[@id='name']")).sendKeys("pass"); //enter the text
        driver.findElement(By.xpath("//input[@id='submit']")).click(); //click on submit
        for(String window : driver.getWindowHandles())
        {
            driver.switchTo().window(mainwindow);  //switch back to the main window
        }
            driver.findElement(By.xpath("//a[contains(text(),'Proceed')]")).click(); //proceed to next

        //5.COOKIE HANDLING
        driver.findElement(By.xpath("/html/body/div/div[2]/a[1]")).click();  //click on generate token
        String t=driver.findElement(By.xpath("//*[@id=\"token\"]")).getText();  //get the token value in string t
        String[] tokenValue = t.split("\\s");  //split is string method where regex is regular expression, into a string array
        String token = tokenValue[1];
        Cookie cookie = new Cookie("Token", token);
        driver.manage().addCookie(cookie);  //create and add the cookie
        driver.findElement(By.xpath("//a[contains(text(),'Proceed')]")).click(); //proceed

        Thread.sleep(1580); //wait
        driver.close();  //close the window od tatoc
    }
}