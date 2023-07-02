package registerpage;

import common.Credentials;
import common.User;
import common.UserAPI;
import common.UserGenerator;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageobjects.LoginPage;
import pageobjects.RegisterPage;

import java.time.Duration;

public class RegisterPagePositiveTest {
    private WebDriver driver;
    private final User user = UserGenerator.getRandom();
    @Before
    public void setUp() {
        ChromeOptions option = new ChromeOptions();
        option.addArguments("--remote-allow-origins=*");
        System.setProperty(ChromeDriverService.CHROME_DRIVER_EXE_PROPERTY, "/home/alexandra/WebDriver/bin/yandexdriver");
        option.setBinary("/usr/bin/yandex-browser");
        driver = new ChromeDriver(option);
    }

    @After
    public void cleanUp() {
        driver.quit();
        ValidatableResponse response= UserAPI.login(new Credentials(user.getEmail(), user.getPassword()));
        String token = response.extract().path("accessToken");
        ValidatableResponse response2 = UserAPI.delete(token);
        Assert.assertNotNull(response2);
        boolean success = response2.extract().path("success");
        Assert.assertTrue("Тело ответа не соответствует ожидаемому", success);
    }

    @Test
    public void userCanBeRegistered() {
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.open();
        registerPage.fillRegisterForm(user);
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(LoginPage.loginButton));
        Assert.assertEquals(LoginPage.PAGE_URL, driver.getCurrentUrl());
    }
}
