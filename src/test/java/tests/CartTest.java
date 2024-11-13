package tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.CartPage;
import pages.LoginPage;
import pages.ProductPage;

import java.time.Duration;

public class CartTest {

    private final WebDriver driver = new ChromeDriver();
    private final LoginPage loginPage = new LoginPage(driver);
    private final ProductPage productPage = new ProductPage(driver);
    private final CartPage cartPage = new CartPage(driver);

    @BeforeEach
    public void setUp() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.demoblaze.com/");
    }

    @Test
    public void testAddProductToCartAndVerifyPrice() {
        loginPage.login("your_username", "your_password");
        productPage.addProductToCart("Samsung galaxy s6");

        int productPrice = productPage.getProductPrice();
        cartPage.goToCart();
        int cartPrice = cartPage.getCartPrice();

        Assertions.assertEquals(productPrice, cartPrice, "Цены в карточке товара и корзине не совпадают");

        cartPage.placeOrder();
        int orderPrice = cartPage.getOrderPrice();

        Assertions.assertEquals(productPrice, orderPrice, "Цены в карточке товара и заказе не совпадают");
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}
