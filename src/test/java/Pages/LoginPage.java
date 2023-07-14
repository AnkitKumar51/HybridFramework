package Pages;

import Configuration.ExtentLogger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class LoginPage {
    WebDriver driver;

    @FindBy(xpath = "//input[@id='txtUserName']")
    private WebElement username;

    @FindBy(xpath = "//input[@id='txtPassword']")
    private WebElement password;

    @FindBy(xpath = "//input[@id='cmdLogin']")
    private WebElement login;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void Login(String url, String userName, String passWord) {
        driver.get(url);
        ExtentLogger.pass("Website url :" + url);
        username.sendKeys(userName);
        ExtentLogger.pass("Website UserName :" + userName);
        password.sendKeys(passWord);
        login.click();
        String pageTitle = driver.getTitle();
        String expectedTitle = "TealPD Login Page";
        Assert.assertEquals(pageTitle, expectedTitle, "Incorrect page title after login.");
    }
}
