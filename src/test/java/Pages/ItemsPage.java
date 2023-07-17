package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.*;
import java.util.regex.Pattern;

public class ItemsPage {

    WebDriver driver;
    String parentWindow;
    String childWindow;

    @FindBy(xpath = "(//a[contains(text(),'Items')])[1]")
    private WebElement goToItemsSearch;

    @FindBy(xpath = "(//a[contains(text(),'Item Search')])[1]")
    private WebElement itemSearch;

    String itemNameLink = "//*[@id='searchresults']/tbody/tr/td[2]";


    public ItemsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void goToItemSection() {
        Actions actions = new Actions(driver);
        actions.moveToElement(goToItemsSearch).perform();
        itemSearch.click();
    }

    public void tableOperations() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        List<WebElement> elements = driver.findElements(By.xpath(itemNameLink));

        System.out.println(elements.size());

        // Generate 10 unique random indices
        int totalElements = elements.size();
        Random random = new Random();
        List<Integer> clickedIndices = new ArrayList<>();
        while (clickedIndices.size() < 10) {
            int randomIndex = random.nextInt(totalElements);
            if (!clickedIndices.contains(randomIndex)) {
                clickedIndices.add(randomIndex);
            }
        }

        // Click on the 10 elements at the randomly selected indices
        for (int index : clickedIndices) {
            // Find the elements again to avoid stale element reference
            elements = driver.findElements(By.xpath(itemNameLink));

            if (index >= elements.size()) {
                // No more elements to click, exit the loop
                System.out.println("No more elements to click.");
                break;
            }

            WebElement element = elements.get(index);
            String expectedText = element.getText();
            System.out.println("Clicked on element :" + (index + 1) + ":" + expectedText);
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript("arguments[0].click();", element.findElement(By.tagName("a")));

            // Perform additional actions after clicking (e.g., validate page title)
            Set<String> handle = driver.getWindowHandles();
            Iterator<String> it = handle.iterator();
            parentWindow = it.next();
            childWindow = it.next();
            driver.switchTo().window(childWindow);
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            String actualTitle = driver.getTitle();
            System.out.println("Actual title: " + actualTitle);

//            actualTitle = actualTitle.replace("Item Details:", "");
//            Assert.assertEquals(actualTitle, expectedText,
//                    "The actual title does not match the expected title without 'Item Details: '.");
//            Assert.assertTrue(driver.getTitle().contains(expectedText));

            String regexPattern = ".*" + Pattern.quote("Item Details:") + ".*";

            // Check if the actual title matches the regex pattern
            boolean isPartialMatch = actualTitle.matches(regexPattern);
            Assert.assertTrue(isPartialMatch, "The actual title does not match the expected partial title.");
            driver.close();
            driver.switchTo().window(parentWindow);
        }
    }
}