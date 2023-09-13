package Pages;

import org.openqa.selenium.WebDriver;

public class PageFactory {
    WebDriver driver;
    private LoginPage loginPage;
    private LogOutPage logOutPage;
    private LogisticWareHouseInventoryManagementPage logisticWareHouseInventoryManagementPage;
    private ContactsPage contactsPage;
    private AddContactsPage addContactsPage;
    private ItemsPage itemsPage;
    private QpPage qpPage;
    private SkidPage skidPage;

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

    public LogisticWareHouseInventoryManagementPage getLogisticWareHouseInventoryManagementPage() {
        if (logisticWareHouseInventoryManagementPage == null) {
            logisticWareHouseInventoryManagementPage = new LogisticWareHouseInventoryManagementPage(driver);
        }
        return logisticWareHouseInventoryManagementPage;
    }

    public ContactsPage getContactsPage() {
        if (contactsPage == null) {
            contactsPage = new ContactsPage(driver);
        }
        return contactsPage;
    }

    public AddContactsPage getAddContactsPage() {
        if (addContactsPage == null) {
            addContactsPage = new AddContactsPage(driver);
        }
        return addContactsPage;
    }

    public ItemsPage getItemsPage() {
        if (itemsPage == null) {
            itemsPage = new ItemsPage(driver);
        }
        return itemsPage;
    }

    public QpPage getQpPage(){
        if (qpPage == null){
            qpPage = new QpPage(driver);
        }
        return qpPage;
    }

    public SkidPage getSkidPage(){
        if(skidPage == null){
            skidPage = new SkidPage(driver);
        }
        return skidPage;
    }
}
