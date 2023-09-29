package ru.praktikum.burgers.pages.register;

import io.qameta.allure.junit4.DisplayName;
import org.junit.Assert;
import org.junit.Test;
import ru.praktikum.burgers.api.common.User;
import ru.praktikum.burgers.api.common.UserGenerator;
import ru.praktikum.burgers.api.page.objects.RegisterPage;
import ru.praktikum.burgers.pages.BaseTest;

public class RegisterPageNegativeTest extends BaseTest {
    @Test
    @DisplayName("Ошибка при регистрации с некорректным паролем")
    public void userCannotBeRegisteredWithShortPass() {
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.open();
        User user = UserGenerator.getRandom();
        User userWithShortPass = new User(user.getEmail(), "1234", user.getName());
        registerPage.fillRegisterForm(userWithShortPass);
        registerPage.waitForWrongPass();
        Assert.assertEquals(RegisterPage.PAGE_URL, driver.getCurrentUrl());
    }
}