package Pages;

import Configuration.ExtentLogger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task1 {

    WebDriver driver;
    String url = "http://192.168.1.16";
    String userName = "ankit";
    String passWord = "test123";

    @FindBy(xpath = "//input[@id='txtUserName']")
    private WebElement username;

    @FindBy(xpath = "//input[@id='txtPassword']")
    private WebElement password;

    @FindBy(xpath = "//input[@id='cmdLogin']")
    private WebElement login;

    String skidTablePath = "//*[@id=\"FluidContent_up3\"]/table/tbody/tr";

    public Task1(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void skidTask() {
        driver.get(url);
        username.sendKeys(userName);
        password.sendKeys(passWord);
        login.click();
    }

    public void listTask() {
        String filePath = "D:\\SeleniumProjects\\HybridFrameWork\\TestData\\skid'sProjectIds.txt";
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String id;
            while ((id = br.readLine()) != null) {
                // Navigate to the URL with the current ID
                String baseUrl = "http://192.168.1.16/secure/items/skidsmanage.aspx?skid=";
                driver.navigate().to(baseUrl + id);
                driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
                System.out.println("Now actions are Performing on this " + driver.getCurrentUrl());
//                ExtentLogger.pass(driver.getCurrentUrl());
                tableOperations();
                // You can add more actions here based on your specific requirements
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void tableOperations() {

        List<WebElement> rows = driver.findElements(By.xpath(skidTablePath));
        System.out.println("The Page have total rows " + rows.size());

        // Create a data structure to store the table data.
        List<List<String>> tableData = new ArrayList<>();

        // Loop through the rows to extract the data and store it in the data structure.
        for (WebElement row : rows) {
            List<WebElement> columns = row.findElements(By.xpath("td"));
            List<String> rowData = new ArrayList<>();
            for (WebElement column : columns) {
                rowData.add(column.getText());
            }
            tableData.add(rowData);
        }
        // Assuming the tableData now contains the values for each row and column.

        // Now, let's perform the multiplication and verification.
        for (List<String> rowData : tableData) {
            String itemLinkText = rowData.get(1);
            String qtyValue = rowData.get(2); // Assuming Qty is at index 2 (0-indexed)
            String unitCostValue = rowData.get(3); // Assuming Unit Cost is at index 3 (0-indexed)
            String totalCostValue = rowData.get(4); // Assuming Total Cost is at index 4 (0-indexed)

//            double qtyDouble = Double.parseDouble(qtyValue); // Convert to double
            double qtyDouble = extractNumericValue(qtyValue);
            double unitCostDouble = Double.parseDouble(unitCostValue); // Convert to double
            double expectedTotalCost = qtyDouble * unitCostDouble;
            System.out.println(qtyDouble + " * " + unitCostDouble + " = " + expectedTotalCost);

            double totalCostDouble = Double.parseDouble(totalCostValue); // Convert to double

            // Compare the expected total cost with the value in "Total Cost".
            if (Math.abs(expectedTotalCost - totalCostDouble) < 0.0001) {
                if (itemLinkText.contains("Assembly") && !itemLinkText.contains("Assemble")) {
                    System.out.println("No operation needed for row: " + itemLinkText);
//                    ExtentLogger.pass("No operation needed for row: " + itemLinkText);
                } else {
                    // If "itemLinkText" does not contain "ASSEMBLY" text, click on the link.
                    WebElement itemLink = driver.findElement(By.linkText(itemLinkText));
                    itemLink.click();
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    // Handle the child window.
                    switchToChildWindow();
                    String pageTitle = driver.getTitle();
                    // Check if the page title contains "ASSEMBLY."
                    if (pageTitle.contains("ASSEMBLY")) {
                        System.out.println("No operation needed for action because it contains ASSEMBLY Item: " + pageTitle);
//                        ExtentLogger.pass("No operation needed for action because it contains ASSEMBLY Item: " + pageTitle);
                    } else {
                        // Verify the List Price in the child window.
                        verifyListPrice(unitCostDouble);
                    }
                    // Close the child window.
                    driver.close();
                }
                // Switch back to the parent window.
                switchToParentWindow();
            }
        }
    }

    private static double extractNumericValue(String input) {
        Pattern pattern = Pattern.compile("(\\d+\\.?\\d*)");
        Matcher matcher = pattern.matcher(input);
        if (matcher.find()) {
            return Double.parseDouble(matcher.group(1));
        }
        return 0.0; // Return a default value or handle error cases based on your requirement
    }

    // Method to switch to the child window.
    private void switchToChildWindow() {
        String parentWindowHandle = driver.getWindowHandle();

        // Perform actions to open the child window, e.g., click on a link or a button.
        // After performing the action, the child window will open.

        // Switch to the child window.
        Set<String> handles = driver.getWindowHandles();
        Iterator<String> it = handles.iterator();
        String parentWindow = it.next();
        String childWindow = it.next();
        driver.switchTo().window(childWindow);
    }

    // Method to switch back to the parent window.
    private void switchToParentWindow() {
        Set<String> handles = driver.getWindowHandles();
        Iterator<String> it = handles.iterator();
        String parentWindow = it.next();
        driver.switchTo().window(parentWindow);
    }

    // Method to verify the List Price in the child window.
    private void verifyListPrice(double expectedListPrice) {
        int columnIndex = 1; // Replace 1 with the correct column index you want to target.

        // Get all rows of the table.
        List<WebElement> rows = driver.findElements(By.xpath("//*[@id='FluidContent_lblVendorData']/table/tbody/tr"));

        // Now, iterate through the rows and perform the assertion for the desired data in the correct column.
        for (int rowIndex = 1; rowIndex < rows.size(); rowIndex++) { // Start from 1 to skip the header row.
            WebElement dataRow = rows.get(rowIndex);
            List<WebElement> dataColumns = dataRow.findElements(By.tagName("td"));

            if (dataColumns.size() > columnIndex) {
                WebElement element = dataColumns.get(columnIndex);
                String text = element.getText();
                String numericText = text.replaceAll("[^\\d.]", "");

                // Convert the numeric text to a double value.
                double listPrice = Double.parseDouble(numericText);
                try {
                    Assert.assertEquals(listPrice, expectedListPrice);
//                    ExtentLogger.pass("Assertion Passed at row: " + rowIndex);
                    System.out.println("Unit Cost Value " + expectedListPrice + "  List Price Value " + listPrice);
//                    ExtentLogger.pass("Unit Cost Value " + expectedListPrice + "  List Price Value " + listPrice);
                } catch (AssertionError e) {
                    // Handle the assertion failure. You can print a message or log it.
                    System.out.println("Assertion failed for row: " + rowIndex);
//                    ExtentLogger.pass("Assertion failed for row: " + rowIndex);
                }
            }
        }
    }
}
