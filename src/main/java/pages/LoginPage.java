package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    private final WebDriver driver;

    // Локаторы для полей и кнопок входа
    private final By loginLink = By.id("login2");
    private final By usernameField = By.id("loginusername");
    private final By passwordField = By.id("loginpassword");
    private final By loginButton = By.xpath("//button[text()='Log in']");
    private final By logoutLink = By.id("logout2");

    private final By closeBannerButton = By.cssSelector(".close-banner");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    public void login(String username, String password) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            // Проверка и закрытие баннера, если он существует
            if (!driver.findElements(closeBannerButton).isEmpty()) {
                driver.findElement(closeBannerButton).click();
                System.out.println("Баннер закрыт.");
            }

            // Используем JavaScript для клика по кнопке "Log in"
            WebElement loginLinkElement = wait.until(ExpectedConditions.visibilityOfElementLocated(loginLink));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", loginLinkElement);
            System.out.println("Клик по кнопке 'Log in' выполнен.");

            // Ждём появления полей ввода логина и пароля
            wait.until(ExpectedConditions.visibilityOfElementLocated(usernameField)).sendKeys(username);
            driver.findElement(passwordField).sendKeys(password);

            // Нажимаем кнопку логина
            driver.findElement(loginButton).click();
            System.out.println("Вход выполнен.");
        } catch (ElementClickInterceptedException e) {
            System.out.println("Ошибка клика по кнопке 'Log in': элемент перекрыт.");
        } catch (NoSuchElementException e) {
            System.out.println("Не удалось найти элемент.");
        }
    }

    public boolean isUserLoggedIn() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(logoutLink));
            return driver.findElement(logoutLink).isDisplayed();
        } catch (TimeoutException e) {
            System.out.println("Элемент 'Logout' не появился.");
            return false;
        }
    }
}
