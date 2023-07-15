package Tests;

import org.testng.annotations.Test;

public class TC04_AddContactsPage extends baseClass {
    @Test(priority = 0, dataProvider = "WebsiteLoginData")
    public void addContactsPage(String url, String userName, String passWord) {
        pageFactory.getLoginPage().Login(url, userName, passWord);
        pageFactory.getContactsPage().goToContactsPage();
        pageFactory.getAddContactsPage().addContact();
    }

    @Test(priority = 1, dataProvider = "AddContactsData")
    public void addContactData(String name, String title, String phoneMain, String email) {
        pageFactory.getAddContactsPage().addDetails(name, title, phoneMain, email);
        pageFactory.getAddContactsPage().contactEntry();
//        pageFactory.getAddContactsPage().verifyContactIsCreatedOrNot(name);

    }
}
