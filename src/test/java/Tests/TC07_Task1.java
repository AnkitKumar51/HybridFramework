package Tests;

import org.testng.annotations.Test;

public class TC07_Task1 extends baseClass {
    @Test
    public void skidTest() {
        pageFactory.getTask1().skidTask();
        pageFactory.getTask1().tableOperations();
    }
}
