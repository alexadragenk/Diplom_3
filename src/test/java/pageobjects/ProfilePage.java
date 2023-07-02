package pageobjects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProfilePage {
    private final WebDriver driver;
    public static final String PAGE_URL = "https://stellarburgers.nomoreparties.site/account/profile";
    public static final By logoutButton = By.xpath(".//button[text()='Выход']");
    public static final By constructorButton = By.xpath(".//p[text()='Конструктор']");
    public static final By logo = By.xpath(".//div[@class='AppHeader_header__logo__2D0X2']/a[1]");
    public void constructorButtonClick() {
        driver.findElement(constructorButton).click();
    }
    @Step("Клик на логотип")
    public void logoClick() {
        driver.findElement(logo).click();
    }
    @Step("Клик на кнопку Выход")
    public void logOutClick() {
        driver.findElement(logoutButton).click();
    }
    public ProfilePage(WebDriver driver) {
        this.driver = driver;
    }
}
