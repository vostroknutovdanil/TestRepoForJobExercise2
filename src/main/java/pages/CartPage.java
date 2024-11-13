package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CartPage {
    private final WebDriver driver;

    public CartPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void goToCart() {
        driver.findElement(By.xpath("//a[text()='Cart']")).click();
    }

    public int getCartPrice() {
        System.out.println("Текущий URL: " + driver.getCurrentUrl());
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement priceElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[3]")));
        return Integer.parseInt(priceElement.getText().trim());
    }


    public void placeOrder() {
        driver.findElement(By.xpath("//button[text()='Place Order']")).click();
    }

    public int getOrderPrice() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement totalElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("totalm")));

        String totalText = totalElement.getText().replaceAll("\\D", "");
        if (totalText.isEmpty()) {
            throw new IllegalStateException("Значение цены пустое или элемент не найден.");
        }

        return Integer.parseInt(totalText);
    }

}
