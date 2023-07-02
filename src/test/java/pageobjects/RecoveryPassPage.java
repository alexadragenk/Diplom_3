package pageobjects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RecoveryPassPage {
    public static final String PAGE_URL = "https://stellarburgers.nomoreparties.site/forgot-password";

    public static final By loginLink = By.xpath(".//a[text()='Войти']");
    private final WebDriver driver;
    public RecoveryPassPage(WebDriver driver) {
        this.driver = driver;
    }

    public void open() {
        driver.get(PAGE_URL);
    }
    @Step("Клик на ссылку Войти")
    public void loginLinkClick() {
        driver.findElement(loginLink).click();
    }
}
