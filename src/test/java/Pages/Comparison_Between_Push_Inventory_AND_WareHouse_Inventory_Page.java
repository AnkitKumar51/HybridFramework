package Pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class Comparison_Between_Push_Inventory_AND_WareHouse_Inventory_Page {
    WebDriver driver;
    String url = "http://192.168.1.16:8025/secure/logistics/WarehousePush.aspx";
    String userName = "ankit";
    String passWord = "test123";
//    int sum;

    @FindBy(xpath = "//input[@id='txtUserName']")
    private WebElement username;

    @FindBy(xpath = "//input[@id='txtPassword']")
    private WebElement password;

    @FindBy(xpath = "//input[@id='cmdLogin']")
    private WebElement login;

    String Text;

    public Comparison_Between_Push_Inventory_AND_WareHouse_Inventory_Page(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void LoginIntoPushInventory() {
        driver.get(url);
        username.sendKeys(userName);
        password.sendKeys(passWord);
        login.click();
    }

//    public void checkCount() {
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
//        try {
//            Thread.sleep(10000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//        ((JavascriptExecutor) driver).executeScript("window.open('http://192.168.1.16:8025/secure/logistics/Warehouse-InventoryManager.aspx','_blank');");
//        Set<String> handles = driver.getWindowHandles();
//        List<String> handleList = new ArrayList<>(handles);
//        driver.switchTo().window(handleList.get(0));
//
//        for (int i = 0; i < 10; i++) {// Replace '10' with the desired number of selections
//            int sum = 0;// Initialize sum for each iteration
//            WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//select[@id='FluidContent_DropDownList1']")));
//            Select select = new Select(dropdown);
//            int totalOptions = select.getOptions().size();
//            int index = getRandomIndex(totalOptions);
//            // Select the option by index
//            select.selectByIndex(index);
//            WebElement selectedOption = select.getFirstSelectedOption();
//            System.out.println("Selected Text: " + selectedOption.getText());
//            Text = selectedOption.getText();
//            driver.findElement(By.xpath("//input[@id='FluidContent_btnSelectLocation']")).click();
//            // Check if the table is present
//            WebElement table = null;
//            try {
//                table = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"FluidContent_UpdatePanel1\"]/table/tbody/tr/td[2]")));
//            } catch (TimeoutException e) {
//                // Handle the case where the table is not found
//                System.out.println("Table not found. Skipping...");
//            }
//            if (table != null) {
//                List<WebElement> columnElements = table.findElements(By.xpath("//*[@id=\"FluidContent_UpdatePanel1\"]/table/tbody/tr/td[2]"));
//                // Iterate through the elements and calculate the sum
//                for (WebElement element : columnElements) {
//                    try {
//                        // Extract the text value from the element
//                        String text = element.getText().replaceAll("[^\\d.]", "");
//                        // Parse the text value to an integer (you may need to handle non-integer values)
//                        int value = Integer.parseInt(text);
//                        // Add the value to the sum
//                        sum += value;
//                    } catch (NumberFormatException e) {
//                        // Handle cases where the text cannot be parsed as an integer
//                        System.err.println("Skipping non-integer value: " + element.getText());
//                    }
//                }
//                // Print the calculated sum after processing the current option
//                System.out.println("Sum of values in the column: " + sum);
//            }
//            // Switch back to the first tab
//            driver.switchTo().window(handleList.get(1));
//            // Compare the sum of the first tab with the sum of the second tab
//            int secondTabSum = getSumFromSecondTab();
//            if (table != null) {
//                if (sum == secondTabSum) {
//                    System.out.println("Sum matches with the second tab.");
//                } else {
//                    System.out.println("Sum does not match with the second tab.");
//                }
//            }
//            // Switch back to the second tab for the next iteration
//            driver.switchTo().window(handleList.get(0));
//        }
//        // Close the second tab after the loop
//        driver.switchTo().window(handleList.get(1));
//    }
//
//    //Utility function to generate a random index
//    private int getRandomIndex(int totalOptions) {
//        Random random = new Random();
//        return random.nextInt(totalOptions);
//    }
//
//    //Function to get the sum from the second tab
//    private int getSumFromSecondTab() {
//        int secondTabSum = 0;
//        try {
//            driver.findElement(By.xpath("//div[@id=\"CurrentInventory_filter\"]/label/input[@type=\"search\"]")).click();
//            driver.findElement(By.xpath("//div[@id=\"CurrentInventory_filter\"]/label/input[@type=\"search\"]")).clear();
//            driver.findElement(By.xpath("//div[@id=\"CurrentInventory_filter\"]/label/input[@type=\"search\"]")).sendKeys(Text);
//            WebElement sumElement = driver.findElement(By.xpath("//*[@id=\"CurrentInventory\"]/tfoot/tr/th[2]/div/div/span[1]")); // Replace with the actual element
//            String sumText = sumElement.getText();
//            secondTabSum = Integer.parseInt(sumText);
//        } catch (Exception e) {
//            System.err.println("Error extracting sum from the second tab: " + e.getMessage());
//        }
//        return secondTabSum;
//    }

    public void checkCount() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        ((JavascriptExecutor) driver).executeScript("window.open('http://192.168.1.16:8025/secure/logistics/Warehouse-InventoryManager.aspx','_blank');");
        Set<String> handles = driver.getWindowHandles();
        List<String> handleList = new ArrayList<>(handles);
        driver.switchTo().window(handleList.get(0));

        for (int i = 0; i < 10; i++) {
            String Text = ""; // Initialize Text for each iteration
            int sum = 0; // Initialize sum for each iteration

            WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//select[@id='FluidContent_DropDownList1']")));
            Select select = new Select(dropdown);
            int totalOptions = select.getOptions().size();
            int index = getRandomIndex(totalOptions);

            select.selectByIndex(index);
            WebElement selectedOption = select.getFirstSelectedOption();
            System.out.println("Selected Text: " + selectedOption.getText());
            Text = selectedOption.getText();

            driver.findElement(By.xpath("//input[@id='FluidContent_btnSelectLocation']")).click();

            WebElement table = null;
            try {
                table = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"FluidContent_UpdatePanel1\"]/table/tbody/tr/td[2]")));
            } catch (TimeoutException e) {
                System.out.println("Table not found. Skipping...");
                // Handle any necessary actions for when the table is not found
            }

            if (table != null) {
                List<WebElement> columnElements = table.findElements(By.xpath("//*[@id=\"FluidContent_UpdatePanel1\"]/table/tbody/tr/td[2]"));

                for (WebElement element : columnElements) {
                    try {
                        String text = element.getText().replaceAll("[^\\d.]", "");
                        int value = Integer.parseInt(text);
                        sum += value;
                    } catch (NumberFormatException e) {
                        System.err.println("Skipping non-integer value: " + element.getText());
                    }
                }

                System.out.println("Sum of values in the column: " + sum);
            }

            driver.switchTo().window(handleList.get(1));
            int secondTabSum = getSumFromSecondTab();
            if (table != null) {
                if (sum == secondTabSum) {
                    System.out.println("Sum matches with the second tab.");
                } else {
                    System.out.println("Sum does not match with the second tab.");
                }
            }
            driver.switchTo().window(handleList.get(0));
        }
        driver.switchTo().window(handleList.get(1));
    }

    // Utility function to generate a random index
    private int getRandomIndex(int totalOptions) {
        Random random = new Random();
        return random.nextInt(totalOptions);
    }

    // Function to get the sum from the second tab
    // Function to get the sum from the second tab
    private int getSumFromSecondTab() {
        int secondTabSum = 0;
        try {
            if (Text != null && !Text.isEmpty()) {
                driver.findElement(By.xpath("//div[@id=\"CurrentInventory_filter\"]/label/input[@type=\"search\"]")).click();
                driver.findElement(By.xpath("//div[@id=\"CurrentInventory_filter\"]/label/input[@type=\"search\"]")).clear();
                driver.findElement(By.xpath("//div[@id=\"CurrentInventory_filter\"]/label/input[@type=\"search\"]")).sendKeys(Text);
                WebElement sumElement = driver.findElement(By.xpath("//*[@id=\"CurrentInventory\"]/tfoot/tr/th[2]/div/div/span[1]")); // Replace with the actual element
                String sumText = sumElement.getText();
                secondTabSum = Integer.parseInt(sumText);
            } else {
                System.err.println("Text is null or empty. Skipping...");
            }
        } catch (Exception e) {
            System.err.println("Error extracting sum from the second tab: " + e.getMessage());
        }
        return secondTabSum;
    }


}