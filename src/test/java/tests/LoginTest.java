package tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.LoginPage;

public class LoginTest {

    private WebDriver driver;
    private LoginPage loginPage;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        loginPage = new LoginPage(driver);
        driver.get("https://www.demoblaze.com/");
    }

    @Test
    public void testLogin() {
        loginPage.login("yourUsername", "yourPassword");
        // Допустим, мы можем проверить успешный вход по элементу, который отображается после входа
        boolean isLoggedIn = loginPage.isUserLoggedIn();
        Assertions.assertTrue(isLoggedIn, "Пользователь не вошел в систему");
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}

