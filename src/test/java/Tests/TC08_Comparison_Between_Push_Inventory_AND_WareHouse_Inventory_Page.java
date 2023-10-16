package Tests;

import Pages.PageFactory;
import org.testng.annotations.Test;

public class TC08_Comparison_Between_Push_Inventory_AND_WareHouse_Inventory_Page extends baseClass {
    @Test(priority = 0)
    public void doLoginIntoPushInventory() {
        pageFactory.getComparison_between_push_inventory_and_wareHouse_inventory_page().LoginIntoPushInventory();
        pageFactory.getComparison_between_push_inventory_and_wareHouse_inventory_page().checkCount();
    }
}
