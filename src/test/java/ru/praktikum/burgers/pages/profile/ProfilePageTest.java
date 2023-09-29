package ru.praktikum.burgers.pages.profile;

import io.qameta.allure.junit4.DisplayName;
import org.junit.Assert;
import org.junit.Test;
import ru.praktikum.burgers.api.page.objects.LoginPage;
import ru.praktikum.burgers.api.page.objects.MainPage;
import ru.praktikum.burgers.api.page.objects.ProfilePage;
import ru.praktikum.burgers.pages.BaseTest;

public class ProfilePageTest extends BaseTest {
    @Test
    @DisplayName("Переход по клику на «Конструктор»")
    public void navigateToConstructor() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        login();
        MainPage main = new MainPage(driver);
        main.waitForCreateOrderButton();
        main.myAccountClick();
        ProfilePage profilePage = new ProfilePage(driver);
        profilePage.constructorButtonClick();
        main.waitForCreateOrderButton();
        Assert.assertEquals(MainPage.PAGE_URL, driver.getCurrentUrl());
    }

    @Test
    @DisplayName("Переход по клику на логотип Stellar Burgers")
    public void navigateToConstructor2() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        login();
        MainPage main = new MainPage(driver);
        main.waitForCreateOrderButton();
        main.myAccountClick();
        ProfilePage profilePage = new ProfilePage(driver);
        profilePage.logoClick();
        main.waitForCreateOrderButton();
        Assert.assertEquals(MainPage.PAGE_URL, driver.getCurrentUrl());
    }

    @Test
    @DisplayName("Выход по кнопке «Выйти» в личном кабинете")
    public void logOut() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        login();
        MainPage main = new MainPage(driver);
        main.waitForCreateOrderButton();
        main.myAccountClick();
        ProfilePage profilePage = new ProfilePage(driver);
        profilePage.waitForLogoutButton();
        profilePage.logOutClick();
        main.waitForMyAccountButton();
        Assert.assertEquals(ProfilePage.PAGE_URL, driver.getCurrentUrl());
    }

    private void login() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.waitForLoginButton();
        loginPage.fillLoginForm(user);
        MainPage main = new MainPage(driver);
        main.waitForCreateOrderButton();
        Assert.assertEquals(MainPage.PAGE_URL, driver.getCurrentUrl());
    }
}
