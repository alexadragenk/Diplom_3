package ru.praktikum.burgers.pages.main;

import io.qameta.allure.junit4.DisplayName;
import org.junit.Assert;
import org.junit.Test;
import ru.praktikum.burgers.api.page.objects.LoginPage;
import ru.praktikum.burgers.api.page.objects.MainPage;
import ru.praktikum.burgers.api.page.objects.ProfilePage;
import ru.praktikum.burgers.pages.BaseTest;

public class MainPageTest extends BaseTest {
    @Test
    @DisplayName("Переход по клику на «Личный кабинет»")
    public void navigateToMyAccount() {
        MainPage main = new MainPage(driver);
        main.open();
        main.myAccountClick();
        login();
        main.myAccountClick();
        ProfilePage profilePage = new ProfilePage(driver);
        profilePage.waitForLogoutButton();
        Assert.assertEquals(ProfilePage.PAGE_URL, driver.getCurrentUrl());
        profilePage.logoClick();
        main.waitForCreateOrderButton();
        Assert.assertEquals(MainPage.PAGE_URL, driver.getCurrentUrl());
    }

    private void login() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.waitForLoginButton();
        loginPage.fillLoginForm(user);
        MainPage main = new MainPage(driver);
        main.waitForCreateOrderButton();
        Assert.assertEquals(MainPage.PAGE_URL, driver.getCurrentUrl());
    }

    @Test
    @DisplayName("Переходы к разделам: «Булки», «Соусы», «Начинки»")
    public void scrollTest() {
        MainPage main = new MainPage(driver);
        main.open();
        main.waitForBunsButtonActive();
        main.fillingsClick();
        main.waitForFillingsButtonActive();
        Assert.assertTrue(main.isFillingsButtonActive());

        main.saucesClick();
        main.waitForSaucesButtonActive();
        Assert.assertTrue(main.isSaucesButtonActive());

        main.bunsClick();
        main.waitForBunsButtonActive();
        Assert.assertTrue(main.isBunsButtonActive());
    }
}