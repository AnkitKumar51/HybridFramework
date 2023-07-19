package Pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class QpPage {
    WebDriver driver;

    @FindBy(xpath = "(//li/a[contains(text(),'Tasks')]/span)[1]")
    private WebElement taskSection;

    @FindBy(xpath = "(//li/a[contains(text(),'QP')]/span)[1]")
    private WebElement qpSection;

    @FindBy(xpath = "(//li/a[contains(text(),'Build A New QP')])[1]")
    private WebElement buildNewQP;

    @FindBy(xpath = "//input[@id='ContentPlaceHolder1_TextBoxJobName']")
    private WebElement QpName;

    @FindBy(xpath = "//textarea[@id='ContentPlaceHolder1_TextBoxJobInstructions']")
    private WebElement QpInstructions;

    @FindBy(xpath = "//select[@id='ContentPlaceHolder1_ddlProjects']")
    private WebElement QpSite;

    @FindBy(xpath = "//select[@id='ContentPlaceHolder1_ddlCategory']")
    private WebElement selectCategory;

    @FindBy(xpath = "//select[@id='ContentPlaceHolder1_DropDownListQPItem']")
    private WebElement selectItem;

    @FindBy(xpath = "(//table[@id='ContentPlaceHolder1_chkJobComponents']/tbody/tr/td/input[@type='checkbox'])[4]")
    private WebElement selectComponent;

    @FindBy(xpath = "(//table[@id='ContentPlaceHolder1_chkJobClassification']/tbody/tr/td/input[@type='checkbox'])[4]")
    private WebElement selectClasses;

    @FindBy(xpath = "(//table[@id='ContentPlaceHolder1_chkJobActions']/tbody/tr/td/input[@type='checkbox'])[4]")
    private WebElement includeActions;

    @FindBy(xpath = "//input[@id='ContentPlaceHolder1_btnSubmitQP']")
    private WebElement addQpInformation;

    @FindBy(xpath = "(//li/a[contains(text(),'List QPs')])[1]")
    private WebElement listQPs;

    @FindBy(xpath = "(//input[@type='submit'])[1]")
    private WebElement updateQP;

    String updatedQP = "Updated QP Text";


    public QpPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void goToQpSection() {
        Actions actions = new Actions(driver);
        actions.moveToElement(taskSection).perform();

        Actions ac = new Actions(driver);
        ac.moveToElement(qpSection).perform();
    }


    public void addQPData(String qpName, String qpInstructions) {
        buildNewQP.click();
        QpName.sendKeys(qpName);

        QpInstructions.sendKeys(qpInstructions);

        Select se = new Select(QpSite);
        se.selectByVisibleText(" Whistler (aka 859 Spring)");

        Select select = new Select(selectCategory);
        select.selectByVisibleText("Component");

        Select sel = new Select(selectItem);
        sel.selectByVisibleText(" 1/2\" Red & White Balancing Valve Lead Free PN 9517AB");
    }

    public void submitQP() {
        selectComponent.click();
        selectClasses.click();
        includeActions.click();
        addQpInformation.click();
        try {
            // Your test steps that may trigger an unexpected alert
            // ...
            String expectedText = "Data has been successfully submitted";
            // Check if an alert is present
            Alert alert = driver.switchTo().alert();

            // If an alert is present, get the alert text and accept it
            String alertText = alert.getText();
            System.out.println("Alert text : " + alertText);
            Assert.assertEquals(alertText, expectedText, "Not Matched");
            alert.accept();
        } catch (NoAlertPresentException e) {
            // No alert is present, continue with your test flow
            System.out.println("No alert present.");
        }
    }

    public void checkQpAddedOrNot(String qpName) {
        String pageTitle = driver.getTitle();
        System.out.println(pageTitle);
        Assert.assertEquals(pageTitle, qpName, "not matched");
        goToQpSection();
        listQPs.click();

    }

//    public void tableOperation(String qpName) {
//
//        WebElement link = driver.findElement(By.xpath("//a[contains(text(), '" + qpName + "')]"));
//        link.click();
//        QpName.clear();
//        QpName.sendKeys(qpName);
//        QpName.click();
//        if (isMessageDisplayed(driver)) {
//            // If the message is displayed, add a new name
//            QpName.clear();
//            QpName.sendKeys(updatedQP);
//            Select select = new Select(selectCategory);
//            select.selectByVisibleText("Communication");
//            updateQP.click();
//
//            try {
//                // Your test steps that may trigger an unexpected alert
//                // ...
//                String expectedText = "Data has been successfully submitted";
//                // Check if an alert is present
//                Alert alert = driver.switchTo().alert();
//
//                // If an alert is present, get the alert text and accept it
//                String alertText = alert.getText();
//                System.out.println("Alert text : " + alertText);
//                alert.accept();
//            } catch (NoAlertPresentException e) {
//                // No alert is present, continue with your test flow
//                System.out.println("No alert present.");
//            }
//        }
//    }


    public void deleteQP(String qpName) {

//        goToQpSection();
//        listQPs.click();
//        try {
//            Thread.sleep(3000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
        WebElement link = driver.findElement(By.xpath("//a[contains(text(), '" + qpName + "')]"));

        // Check if the link is found
        if (link != null) {
            // Find the delete button in the same row as the link using relative XPath
            WebElement deleteButton = link.findElement(By.xpath("./ancestor::tr//a[contains(@id, 'linkDelete')]"));
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            // Click the delete button
            deleteButton.click();

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            try {
                // Your test steps that may trigger an unexpected alert
                // ...
                String expectedText = "Data has been successfully submitted";
                // Check if an alert is present
                Alert alert = driver.switchTo().alert();

                // If an alert is present, get the alert text and accept it
                String alertText = alert.getText();
                alert.accept();
            } catch (NoAlertPresentException e) {
                // No alert is present, continue with your test flow
                System.out.println("No alert present.");
            }
            System.out.println("element deleted");

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

//    private boolean isMessageDisplayed(WebDriver driver) {
//        try {
//            // Find the message element
//            WebElement messageElement = driver.findElement(By.xpath("//*[@id=\"spnMsg\"]"));
//
//            // Check if the message is displayed
//            return messageElement.isDisplayed();
//        } catch (org.openqa.selenium.NoSuchElementException e) {
//            return false; // Message element is not found, so the message is not displayed
//        }
//    }
}
