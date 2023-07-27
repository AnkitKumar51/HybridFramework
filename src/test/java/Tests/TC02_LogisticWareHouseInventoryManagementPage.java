package Tests;

import org.testng.annotations.Test;

public class TC02_LogisticWareHouseInventoryManagementPage extends baseClass {
    @Test(dataProvider = "WebsiteLoginData")
    public void ManageWareHouse(String url, String userName, String passWord){
        pageFactory.getLoginPage().Login(url, userName, passWord);
        pageFactory.getLogisticWareHouseInventoryManagementPage().goToLogisticSection();
        pageFactory.getLogisticWareHouseInventoryManagementPage().goToWhsManagementSection();
        pageFactory.getLogisticWareHouseInventoryManagementPage().newWindow();
        pageFactory.getLogisticWareHouseInventoryManagementPage().actionOnNewWindow();
        pageFactory.getLogOutPage().LogOut();
    }
}
