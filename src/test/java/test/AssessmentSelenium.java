package test;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class AssessmentSelenium {
    static WebDriver driver;
    public static void main(String[] args) throws InterruptedException {

            driver = new ChromeDriver();
            driver.manage().window().maximize();
            driver.get("https://magento.softwaretestingboard.com/");
            String title = driver.getTitle();
            System.out.println(title);
            driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);



            //Access the Shop


            // shop new yoga
            WebElement accesShop = driver.findElement(By.xpath("//span[@class='action more button']"));
            accesShop.click();



        //Filter for a Certain Category and open the product page

            WebElement filter = driver.findElement(By.xpath("(//div[@class=\"filter-options-title\"])[2]"));
            filter.click();
            Thread.sleep(1000);
            WebElement electronic = driver.findElement(By.xpath("//div[@class='filter-options-item allow active']//li[1]/a[1]"));
            electronic.click();




        // Add a multiple quantities of the product to the cart

            JavascriptExecutor jsx = (JavascriptExecutor) driver;
            jsx.executeScript("window.scrollBy(0,250);");
            List<WebElement> products = driver.findElements(By.cssSelector(".product-image-photo"));
            Actions actions = new Actions(driver);


            for(WebElement productImage:products){
                actions.moveToElement(productImage).perform();
                    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Wait for 10 second

                    WebElement addToCart1 = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-product-sku='24-WG02'] > .action")));
                    actions.moveToElement(addToCart1).click().perform();


            }





        jsx.executeScript("window.scrollBy(0,150);");
        for(WebElement productImage:products){
            actions.moveToElement(productImage).perform();
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // 10 saniye bekleme süresi
           // WebElement addToCart2 = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("//ol[@class='products list items product-items']/li[2]//span[.='Add to Cart']")));
            WebElement addToCart2 = driver.findElement(By.xpath("//ol[@class='products list items product-items']/li[2]//span[.='Add to Cart']"));
            actions.moveToElement(addToCart2).click().perform();


        }


        jsx.executeScript("window.scrollBy(0,-150);");

        // perform checkout
        WebElement basket = driver.findElement(By.cssSelector(".counter-number"));
        basket.click();

        WebElement procet_to = driver.findElement(By.id("top-cart-btn-checkout"));
        procet_to.click();

        Thread.sleep(2000);
        WebElement shipping = driver.findElement(By.xpath("//span[.='Shipping']"));
        String actualResult = shipping.getText();
        String expectedResult ="Shipping";



        if(actualResult.equals(expectedResult)){
            System.out.println("Test Passed : "+actualResult);
        }
        else{
            System.out.println("Test Failed : "+actualResult);
        }
        Thread.sleep(2000);

        driver.quit();

    }



}


