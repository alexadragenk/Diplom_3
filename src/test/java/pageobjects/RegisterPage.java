package pageobjects;

import common.User;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

public class RegisterPage {
    public static final String PAGE_URL = "https://stellarburgers.nomoreparties.site/register";
    private static final By registerButton = By.xpath(".//button[text()='Зарегистрироваться']");
    private static final By registerFields = By.xpath((".//input[@class='text input__textfield text_type_main-default']"));
    private static final By loginLink = By.xpath(".//a[text()='Войти']");
    public static final By wrongPass = By.xpath(".//p[text()='Некорректный пароль']");
    private final WebDriver driver;

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
    }
    public void open() {
        driver.get(PAGE_URL);
    }
    public void loginLinkClick(){
        driver.findElement(loginLink).click();
    }
    @Step("Заполнение полей формы")
    public void fillRegisterForm(User user) {
        List<WebElement> elements = driver.findElements(registerFields);
        elements.get(0).sendKeys(user.getName());
        elements.get(1).sendKeys(user.getEmail());
        elements.get(2).sendKeys(user.getPassword());
        driver.findElement(registerButton).click();
    }
}
