package registerpage;

import common.User;
import common.UserGenerator;
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
import pageobjects.RegisterPage;

import java.time.Duration;

public class RegisterPageNegativeTest {
    private WebDriver driver;

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
    }

    @Test
    public void userCannotBeRegisteredWithShortPass() {
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.open();
        User user = UserGenerator.getRandom();
        User userWithShortPass = new User(user.getEmail(), "1234", user.getName());
        registerPage.fillRegisterForm(userWithShortPass);
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(RegisterPage.wrongPass));
        Assert.assertEquals(RegisterPage.PAGE_URL, driver.getCurrentUrl());
    }
}
