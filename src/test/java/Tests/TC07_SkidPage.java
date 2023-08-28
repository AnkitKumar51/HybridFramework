package Tests;

import org.testng.annotations.Test;

public class TC07_SkidPage extends baseClass {
    @Test
    public void SkidsTest() {
        pageFactory.getTask1().skidTask();
        pageFactory.getTask1().listTask();
    }
}
