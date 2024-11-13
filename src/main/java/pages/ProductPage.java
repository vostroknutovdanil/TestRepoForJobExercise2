package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProductPage {
    private final WebDriver driver;

    public ProductPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void addProductToCart(String productName) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        By productLink = By.xpath("//a[text()='" + productName + "']");

        try {
            // Переход на страницу товара
            WebElement productElement = wait.until(ExpectedConditions.elementToBeClickable(productLink));
            productElement.click();
            System.out.println("Товар '" + productName + "' выбран.");

            // Клик по кнопке "Add to Cart"
            By addToCartButton = By.xpath("//a[text()='Add to cart']");
            WebElement addToCartElement = wait.until(ExpectedConditions.elementToBeClickable(addToCartButton));
            addToCartElement.click();
            System.out.println("Клик по кнопке 'Add to Cart' выполнен.");

            // Подтверждение добавления в корзину
            driver.switchTo().alert().accept();
            System.out.println("Товар успешно добавлен в корзину.");
        } catch (StaleElementReferenceException e) {
            System.out.println("StaleElementReferenceException: Повторный поиск элемента.");
            WebElement productElement = wait.until(ExpectedConditions.elementToBeClickable(productLink));
            productElement.click();
        }
    }



    public int getProductPrice() {
        WebElement priceElement = driver.findElement(By.xpath("//h3[@class='price-container']"));
        String priceText = priceElement.getText().replaceAll("\\D", "");
        return Integer.parseInt(priceText);
    }
}
