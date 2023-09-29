package ru.praktikum.burgers.api.page.objects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProfilePage {
    public static final String PAGE_URL = "https://stellarburgers.nomoreparties.site/account/profile";
    private static final By logoutButton = By.xpath(".//button[text()='Выход']");
    private static final By constructorButton = By.xpath(".//p[text()='Конструктор']");
    private static final By logo = By.xpath(".//div[@class='AppHeader_header__logo__2D0X2']/a[1]");
    private final WebDriver driver;

    public ProfilePage(WebDriver driver) {
        this.driver = driver;
    }

    public void constructorButtonClick() {
        driver.findElement(constructorButton).click();
    }

    @Step("Клик на логотип")
    public void logoClick() {
        driver.findElement(logo).click();
    }

    @Step("Клик на кнопку «Выход»")
    public void logOutClick() {
        driver.findElement(logoutButton).click();
    }

    @Step("Ожидание кнопки «Выход»")
    public void waitForLogoutButton() {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(ProfilePage.logoutButton));
    }
}
