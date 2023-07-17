package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ContactsPage {

    WebDriver driver;

    @FindBy(xpath = "(//a[contains(text(),'Contacts')])[1]")
    private WebElement goToContactsSection;

    @FindBy(xpath = "(//a[contains(text(),'List Contacts')])[1]")
    private WebElement goToListContacts;

    @FindBy(xpath = "//*[@id='searchResults']/tbody[1]/tr/td[2]")
    private WebElement tableActions;

    String tableElements = "//*[@id='searchResults']/tbody[1]/tr/td[2]";

    public ContactsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void goToContactsPage() {
        Actions actions = new Actions(driver);
        actions.moveToElement(goToContactsSection).perform();
    }

    public void performTableActions() {
//        goToListContacts.click();
//        List<WebElement> elements = driver.findElements(By.xpath(tableElements));
//        System.out.println(elements.size());
////        int clickLimit = Math.min(8, elements.size());
//        int totalElements = elements.size();
//
//        Random random = new Random();
//        int randomIndex = random.nextInt(totalElements);
//
//        for (int i = 0; i < randomIndex; i++) {
//            // Find the elements again to avoid stale element reference
//            elements = driver.findElements(By.xpath(tableElements));
//
//            if (i >= elements.size()) {
//                // No more elements to click, exit the loop
//                System.out.println("No more elements to click.");
//                break;
//            }
//
//            WebElement element = elements.get(i);
//            String expectedText = element.getText();
//            System.out.println(expectedText);
//            JavascriptExecutor executor = (JavascriptExecutor) driver;
//            executor.executeScript("arguments[0].click();", element.findElement(By.tagName("a")));
//            System.out.println(driver.getTitle());
//            Assert.assertEquals(driver.getTitle(), expectedText, "Incorrect page title after login.");
//            driver.navigate().back();
        goToListContacts.click();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        List<WebElement> elements = driver.findElements(By.xpath(tableElements));

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
            elements = driver.findElements(By.xpath(tableElements));

            if (index >= elements.size()) {
                // No more elements to click, exit the loop
                System.out.println("No more elements to click.");
                break;
            }

            WebElement element = elements.get(index);
            String expectedText = element.getText();
            System.out.println(expectedText);
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript("arguments[0].click();", element.findElement(By.tagName("a")));

            // Perform additional actions after clicking (e.g., validate page title)
            Assert.assertEquals(driver.getTitle(), expectedText, "Incorrect page title after login.");
            driver.navigate().back();
        }
    }
}

