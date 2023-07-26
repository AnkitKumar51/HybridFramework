package Pages;

import Configuration.ExtentLogger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LogOutPage {
    WebDriver driver;

    @FindBy(xpath = "//a[@id='dropdownMenu1']")
    private WebElement logoutDropDownMenu;

    @FindBy(xpath = "//a[@id='linkLogout']")
    private WebElement logout;

    public LogOutPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void LogOut() {
        logoutDropDownMenu.click();
        logout.click();
    }
}
