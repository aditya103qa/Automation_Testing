import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.*;


public class Auto1 {
    public static void main(String[] args)
    {
        System.setProperty("webdriver.chrome.driver","C:\\Users\\adityadwivedi\\Downloads\\chromedriver_win32\\chromedriver.exe");
        WebDriver  driver= new ChromeDriver();
        driver.get("http://bing.com");
        driver.manage().window().maximize();
        WebElement bing= driver.findElement(By.id("sb_form_q"));
        bing.click();
        bing.sendKeys("bureau veritas");
        bing.submit();

    }

}