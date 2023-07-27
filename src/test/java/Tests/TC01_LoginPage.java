package Tests;

import org.testng.annotations.Test;

public class TC01_LoginPage extends baseClass {

    @Test(dataProvider = "WebsiteLoginData")
    public void LoginPage(String url, String userName, String passWord) {
        pageFactory.getLoginPage().Login(url, userName, passWord);
        pageFactory.getLogOutPage().LogOut();
    }
}
