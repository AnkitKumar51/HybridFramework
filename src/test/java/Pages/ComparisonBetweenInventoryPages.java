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

public class ComparisonBetweenInventoryPages {
    private static final String PUSH_INVENTORY_URL = "http://192.168.1.16:8025/secure/logistics/WarehousePush.aspx";
    private static final String WAREHOUSE_INVENTORY_URL = "http://192.168.1.16:8025/secure/logistics/Warehouse-InventoryManager.aspx";
    private static final String USERNAME = "ankit";
    private static final String PASSWORD = "test123";
    WebDriver driver;

    @FindBy(xpath = "//input[@id='txtUserName']")
    private WebElement username;

    @FindBy(xpath = "//input[@id='txtPassword']")
    private WebElement password;

    @FindBy(xpath = "//input[@id='cmdLogin']")
    private WebElement login;

    String Text;

    public ComparisonBetweenInventoryPages(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void loginIntoPushInventory() {
        driver.get(PUSH_INVENTORY_URL);
        username.sendKeys(USERNAME);
        password.sendKeys(PASSWORD);
        login.click();
    }

    public void compareInventoryPages() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        ((JavascriptExecutor) driver).executeScript("window.open('" + WAREHOUSE_INVENTORY_URL + "','_blank');");
        Set<String> handles = driver.getWindowHandles();
        List<String> handleList = new ArrayList<>(handles);
        driver.switchTo().window(handleList.get(0));

        for (int i = 0; i < 20; i++) {
            int sum = 0;
            WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//select[@id='FluidContent_DropDownList1']")));
            Select select = new Select(dropdown);
            int totalOptions = select.getOptions().size();
            int index = getRandomIndex(totalOptions);
            select.selectByIndex(index);
            WebElement selectedOption = select.getFirstSelectedOption();
            System.out.println("Selected Text: " + selectedOption.getText());
            String text = selectedOption.getText();
            driver.findElement(By.xpath("//input[@id='FluidContent_btnSelectLocation']")).click();

            WebElement table = findElementWithRetry(wait, By.xpath("//*[@id='FluidContent_UpdatePanel1']/table/tbody/tr/td[2]"));

            if (table != null) {
                List<WebElement> columnElements = table.findElements(By.xpath("//*[@id='FluidContent_UpdatePanel1']/table/tbody/tr/td[2]"));
                for (WebElement element : columnElements) {
                    try {
                        String elementText = element.getText().replaceAll("[^\\d.]", "");
                        int value = Integer.parseInt(elementText);
                        sum += value;
                    } catch (NumberFormatException e) {
                        System.err.println("Skipping non-integer value: " + element.getText());
                    }
                }
                System.out.println("Sum of values in the column: " + sum);
            }

            driver.switchTo().window(handleList.get(1));
            int secondTabSum = getSumFromSecondTab(wait, text);
            if (table != null) {
                if (sum == secondTabSum) {
                    System.out.println("Sum matches with the second tab.");
                } else {
                    System.out.println("Sum does not match with the second tab.");
                }
            }
            driver.switchTo().window(handleList.get(0));
        }
        driver.switchTo().window(handleList.get(1)).close();
    }

    private int getRandomIndex(int totalOptions) {
        Random random = new Random();
        return random.nextInt(totalOptions);
    }

    private WebElement findElementWithRetry(WebDriverWait wait, By locator) {
        try {
            return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        } catch (TimeoutException e) {
            System.out.println("Element not found: " + locator.toString());
            return null;
        }
    }

    private int getSumFromSecondTab(WebDriverWait wait, String text) {
        int secondTabSum = 0;
        try {
            WebElement searchInput = findElementWithRetry(wait, By.xpath("//div[@id='CurrentInventory_filter']/label/input[@type='search']"));
            if (searchInput != null) {
                searchInput.click();
                searchInput.clear();
                searchInput.sendKeys(text);
                WebElement sumElement = findElementWithRetry(wait, By.xpath("//*[@id='CurrentInventory']/tfoot/tr/th[2]/div/div/span[1]"));
                if (sumElement != null) {
                    String sumText = sumElement.getText();
                    secondTabSum = Integer.parseInt(sumText);
                } else {
                    System.err.println("Sum element not found on the second tab.");
                }
            } else {
                System.err.println("Search input not found on the second tab.");
            }
        } catch (Exception e) {
            System.err.println("Error extracting sum from the second tab: " + e.getMessage());
        }
        return secondTabSum;
    }
}
