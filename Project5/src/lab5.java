import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.lang.*;
import java.util.concurrent.TimeUnit;

public class lab5 {
    public static void main(String[] args) throws InterruptedException {
        // set chromedriver path
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");

        // lab5
        WebDriver driver = new ChromeDriver();
        // 1. 打開瀏覽器至交大首頁 https://www.nctu.edu.tw/
        driver.get("https://www.nctu.edu.tw/");

        // 2. 將瀏覽器放到最大
        driver.manage().window().maximize();

        // 3. 點擊最新消息
        Thread.sleep(1000);
        driver.findElement(By.className("item-516")).click();

        // 4. 點擊第二新的消息
        WebElement all_news = driver.findElement(By.id("modRandomArticle208"));
        WebElement second_new = all_news.findElements(By.tagName("a")).get(1);
        second_new.click();

        // 5. 分別print出標題和內容
        WebElement title = driver.findElement(By.id("ar-header"));
        System.out.println("標題: " + title.getText());
        System.out.println("內容: ");
        List<WebElement> content = driver.findElements(By.className("itemFullText"));
        for (int i = 0 ; i < content.size() ; i ++)
            System.out.println(content.get(i).getText());

        // 6. 到google首頁 https://www.google.com.tw/
        driver.get("https://www.google.com.tw/");

        // 7. 使用搜尋框搜尋自己的學號
        WebElement search_input = driver.findElement(By.name("q"));
        search_input.sendKeys("0856015" + Keys.ENTER);

        // 8. 印出第一個搜尋結果的標題
        WebElement first_title = new WebDriverWait(driver, 5).until(ExpectedConditions.elementToBeClickable(By.xpath("//a/h3")));
        System.out.println("google搜尋結果: " + first_title.getText());

        // 9. 關掉瀏覽器
        driver.close();
    }
}



