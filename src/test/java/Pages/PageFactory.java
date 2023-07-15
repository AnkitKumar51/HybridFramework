package Pages;

import org.openqa.selenium.WebDriver;

public class PageFactory {
    WebDriver driver;
    private LoginPage loginPage;
    private LogOutPage logOutPage;
    private LogisticWareHouseInventoryManagementPage logisticWareHouseInventoryManagementPage;
    private ContactsPage contactsPage;
    private AddContactsPage addContactsPage;

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

    public LogisticWareHouseInventoryManagementPage getLogisticWareHouseInventoryManagementPage(){
        if(logisticWareHouseInventoryManagementPage == null){
            logisticWareHouseInventoryManagementPage = new LogisticWareHouseInventoryManagementPage(driver);
        }
        return logisticWareHouseInventoryManagementPage;
    }

    public ContactsPage getContactsPage(){
        if(contactsPage == null){
            contactsPage = new ContactsPage(driver);
        }
        return contactsPage;
    }

    public AddContactsPage getAddContactsPage(){
        if(addContactsPage == null){
            addContactsPage = new AddContactsPage(driver);
        }
        return addContactsPage;
    }
}
