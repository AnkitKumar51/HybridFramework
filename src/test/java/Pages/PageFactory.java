package Pages;

import org.openqa.selenium.WebDriver;

public class PageFactory {
    WebDriver driver;
    private LoginPage loginPage;
    private LogOutPage logOutPage;

    public PageFactory(WebDriver driver) {
        this.driver = driver;

    }

    public LoginPage getLoginPage() {
        if (loginPage == null) {
            loginPage = new LoginPage(driver);
        }
        return loginPage;
    }

    public LogOutPage getLogOutPage() {
        if (logOutPage == null) {
            logOutPage = new LogOutPage(driver);
        }
        return logOutPage;
    }
}
