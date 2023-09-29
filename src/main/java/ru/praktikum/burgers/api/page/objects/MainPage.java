package ru.praktikum.burgers.api.page.objects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage {
    public static final String PAGE_URL = "https://stellarburgers.nomoreparties.site/";
    private static final By loginButton = By.xpath(".//button[text()='Войти в аккаунт']");
    private static final By createOrderButton = By.xpath(".//button[text()='Оформить заказ']");
    private static final By myAccountButton = By.xpath(".//p[text()='Личный Кабинет']");
    private static final By scrollButtons = By.xpath(".//div[@class='tab_tab__1SPyG  pt-4 pr-10 pb-4 pl-10 noselect']/../*");
    private static final By bunsButtonActive = By.xpath(".//div[@class='tab_tab__1SPyG  pt-4 pr-10 pb-4 pl-10 noselect']/../div[1][@class='tab_tab__1SPyG tab_tab_type_current__2BEPc pt-4 pr-10 pb-4 pl-10 noselect']");
    private static final By saucesButtonActive = By.xpath(".//div[@class='tab_tab__1SPyG  pt-4 pr-10 pb-4 pl-10 noselect']/../div[2][@class='tab_tab__1SPyG tab_tab_type_current__2BEPc pt-4 pr-10 pb-4 pl-10 noselect']");
    private static final By fillingsButtonActive = By.xpath(".//div[@class='tab_tab__1SPyG  pt-4 pr-10 pb-4 pl-10 noselect']/../div[3][@class='tab_tab__1SPyG tab_tab_type_current__2BEPc pt-4 pr-10 pb-4 pl-10 noselect']");

    private final WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Открытие главной страницы")
    public void open() {
        driver.get(PAGE_URL);
    }

    @Step("Клик на кнопку Войти в аккаунт")
    public void loginButtonClick() {
        driver.findElement(loginButton).click();
    }

    @Step("Клик на кнопку Личный кабинет")

    public void myAccountClick() {
        driver.findElement(myAccountButton).click();
    }

    @Step("Клик на кнопку раздела Булочки")
    public void bunsClick() {
        driver.findElements(scrollButtons).get(0).click();
    }

    @Step("Клик на кнопку раздела Соусы")
    public void saucesClick() {
        driver.findElements(scrollButtons).get(1).click();
    }

    @Step("Клик на кнопку раздела Начинки")
    public void fillingsClick() {
        driver.findElements(scrollButtons).get(2).click();
    }

    @Step("Ожидание кнопки «Оформить заказ»")
    public void waitForCreateOrderButton() {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(MainPage.createOrderButton));
    }

    @Step("Ожидание кнопки «Личный кабинет»")
    public void waitForMyAccountButton() {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(MainPage.myAccountButton));
    }

    @Step("Ожидание кнопки «Булки»")
    public void waitForBunsButtonActive() {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(MainPage.bunsButtonActive));
    }

    @Step("Ожидание кнопки «Соусы»")
    public void waitForSaucesButtonActive() {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(MainPage.saucesButtonActive));
    }

    @Step("Ожидание кнопки «Начинки»")
    public void waitForFillingsButtonActive() {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(MainPage.fillingsButtonActive));
    }

    @Step("Проверка видимости кнопки «Булки»")
    public boolean isBunsButtonActive() {
        return driver.findElement(MainPage.bunsButtonActive).isDisplayed();
    }

    @Step("Проверка видимости «Соусы»")
    public boolean isSaucesButtonActive() {
        return driver.findElement(MainPage.saucesButtonActive).isDisplayed();
    }

    @Step("Проверка видимости «Начинки»")
    public boolean isFillingsButtonActive() {
        return driver.findElement(MainPage.fillingsButtonActive).isDisplayed();
    }

}
