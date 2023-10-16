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

    private ComparisonBetweenInventoryPages comparisonBetweenInventoryPages;

    private Comparison_Between_Push_Inventory_AND_WareHouse_Inventory_Page comparison_between_push_inventory_and_wareHouse_inventory_page;

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

    public Comparison_Between_Push_Inventory_AND_WareHouse_Inventory_Page getComparison_between_push_inventory_and_wareHouse_inventory_page(){
        if(comparison_between_push_inventory_and_wareHouse_inventory_page == null){
            comparison_between_push_inventory_and_wareHouse_inventory_page = new Comparison_Between_Push_Inventory_AND_WareHouse_Inventory_Page(driver);
        }
        return comparison_between_push_inventory_and_wareHouse_inventory_page;
    }

    /*

    public ComparisonBetweenInventoryPages getComparisonBetweenInventoryPages(){
        if(comparisonBetweenInventoryPages == null){
            comparisonBetweenInventoryPages = new ComparisonBetweenInventoryPages(driver);
        }
        return comparisonBetweenInventoryPages;
    }
     */
}
