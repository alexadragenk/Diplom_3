package pageobjects;

import common.User;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class LoginPage {
    public static final String PAGE_URL = "https://stellarburgers.nomoreparties.site/login";
    private static final By loginFields = By.xpath((".//input[@class='text input__textfield text_type_main-default']"));
    public static final By loginButton = By.xpath(".//button[text()='Войти']");
    private final WebDriver driver;
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }
    @Step("Открытие страницы логина")
    public void open() {
        driver.get(PAGE_URL);
    }
    @Step("Заполнение полей формы")
    public void fillLoginForm(User user) {
        List<WebElement> elements = driver.findElements(loginFields);
        elements.get(0).sendKeys(user.getEmail());
        elements.get(1).sendKeys(user.getPassword());
        driver.findElement(loginButton).click();
    }

}
