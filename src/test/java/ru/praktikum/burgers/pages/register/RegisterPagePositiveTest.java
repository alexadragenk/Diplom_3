package ru.praktikum.burgers.pages.register;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import ru.praktikum.burgers.api.common.Credentials;
import ru.praktikum.burgers.api.common.User;
import ru.praktikum.burgers.api.common.UserAPI;
import ru.praktikum.burgers.api.common.UserGenerator;
import ru.praktikum.burgers.api.page.objects.LoginPage;
import ru.praktikum.burgers.api.page.objects.RegisterPage;
import ru.praktikum.burgers.pages.BaseTest;

public class RegisterPagePositiveTest extends BaseTest {
    private final User user = UserGenerator.getRandom();

    @After
    public void clean() {
        ValidatableResponse response = UserAPI.login(new Credentials(user.getEmail(), user.getPassword()));
        String token = response.extract().path("accessToken");
        ValidatableResponse response2 = UserAPI.delete(token);
        Assert.assertNotNull(response2);
        boolean success = response2.extract().path("success");
        Assert.assertTrue("Тело ответа не соответствует ожидаемому", success);
    }

    @Test
    @DisplayName("Успешная регистрация")
    public void userCanBeRegistered() {
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.open();
        registerPage.fillRegisterForm(user);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.waitForLoginButton();
        Assert.assertEquals(LoginPage.PAGE_URL, driver.getCurrentUrl());
    }
}