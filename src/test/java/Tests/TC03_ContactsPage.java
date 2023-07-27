package Tests;

import org.testng.annotations.Test;

public class TC03_ContactsPage extends baseClass{
    @Test(dataProvider = "WebsiteLoginData")
    public void ContactsPage(String url, String userName, String passWord){
        pageFactory.getLoginPage().Login(url, userName, passWord);
        pageFactory.getContactsPage().goToContactsPage();
        pageFactory.getContactsPage().performTableActions();
        pageFactory.getLogOutPage().LogOut();
    }
}
