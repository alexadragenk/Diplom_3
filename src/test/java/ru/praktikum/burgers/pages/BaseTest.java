package ru.praktikum.burgers.pages;

import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import ru.praktikum.burgers.api.common.Credentials;
import ru.praktikum.burgers.api.common.User;
import ru.praktikum.burgers.api.common.UserAPI;
import ru.praktikum.burgers.api.common.UserGenerator;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public abstract class BaseTest {
    protected final User user = UserGenerator.getRandom();
    protected WebDriver driver;

    @Before
    public void setUp() {
        ValidatableResponse response = UserAPI.register(user);
        boolean success = response.extract().path("success");
        Assert.assertTrue("Тело ответа не соответствует ожидаемому", success);

        ChromeOptions option = new ChromeOptions();
        option.addArguments("--remote-allow-origins=*");

        boolean useYandexBrowser = false;
        String webDriverPath = null;
        try (InputStream input = new FileInputStream("src/test/java/ru/praktikum/burgers/pages/config.properties")) {
            Properties prop = new Properties();
            prop.load(input);
            String val = prop.getProperty("use_yandex_browser");
            webDriverPath = prop.getProperty("web_driver_path");

            useYandexBrowser = Boolean.parseBoolean(val);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        if (useYandexBrowser) {
            System.setProperty(ChromeDriverService.CHROME_DRIVER_EXE_PROPERTY, webDriverPath);
            option.setBinary("/usr/bin/yandex-browser");
        } else {
            System.clearProperty(ChromeDriverService.CHROME_DRIVER_EXE_PROPERTY);
        }

        driver = new ChromeDriver(option);
    }

    @After
    public void cleanUp() {
        driver.quit();
        ValidatableResponse response = UserAPI.login(new Credentials(user.getEmail(), user.getPassword()));
        String token = response.extract().path("accessToken");
        ValidatableResponse response2 = UserAPI.delete(token);
        Assert.assertNotNull(response2);
        boolean success = response2.extract().path("success");
        Assert.assertTrue("Тело ответа не соответствует ожидаемому", success);
    }
}