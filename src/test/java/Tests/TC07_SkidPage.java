package Tests;

import org.testng.annotations.Test;

public class TC07_SkidPage extends baseClass {
    @Test
    public void SkidsTest() {
        pageFactory.getSkidPage().skidTask();
    }
    @Test(priority = 1)
    public void CheckValues(){
        pageFactory.getSkidPage().listTask();
    }
}
