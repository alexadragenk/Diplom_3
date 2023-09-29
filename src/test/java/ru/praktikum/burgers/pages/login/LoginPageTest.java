package ru.praktikum.burgers.pages.login;

import io.qameta.allure.junit4.DisplayName;
import org.junit.Assert;
import org.junit.Test;
import ru.praktikum.burgers.api.page.objects.LoginPage;
import ru.praktikum.burgers.api.page.objects.MainPage;
import ru.praktikum.burgers.api.page.objects.RecoveryPassPage;
import ru.praktikum.burgers.api.page.objects.RegisterPage;
import ru.praktikum.burgers.pages.BaseTest;

public class LoginPageTest extends BaseTest {
    @Test
    @DisplayName("Вход по кнопке «Войти в аккаунт» на главной")
    public void loginFromMainPage() {
        MainPage main = new MainPage(driver);
        main.open();
        main.loginButtonClick();
        login();
    }

    @Test
    @DisplayName("Вход через кнопку «Личный кабинет»")
    public void loginFromMyAccount() {
        MainPage main = new MainPage(driver);
        main.open();
        main.myAccountClick();
        login();
    }

    @Test
    @DisplayName("Вход через кнопку в форме регистрации")
    public void loginFromRegisterPage() {
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.open();
        registerPage.loginLinkClick();
        login();
    }

    @Test
    @DisplayName("Вход через кнопку в форме восстановления пароля")
    public void loginFromRecoveryPassPage() {
        RecoveryPassPage recoveryPassPage = new RecoveryPassPage(driver);
        recoveryPassPage.open();
        recoveryPassPage.loginLinkClick();
        login();
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