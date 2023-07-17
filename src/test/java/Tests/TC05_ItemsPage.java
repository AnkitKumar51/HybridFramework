package Tests;

import org.testng.annotations.Test;

public class TC05_ItemsPage extends baseClass {
    @Test(dataProvider = "WebsiteLoginData")
    public void ItemsPage(String url, String userName, String passWord) {
        pageFactory.getLoginPage().Login(url, userName, passWord);
        pageFactory.getItemsPage().goToItemSection();
        pageFactory.getItemsPage().tableOperations();
    }
}
