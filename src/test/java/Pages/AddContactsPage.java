package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.concurrent.TimeUnit;

public class AddContactsPage {

    WebDriver driver;

    @FindBy(xpath = "(//a[contains(text(),'Add A Contact')])[1]")
    private WebElement goToAddContactSection;

    @FindBy(xpath = "//select[@id='contentPlaceHolder_contactadd_ddlSuperCategory']")
    private WebElement selectCategory;

    @FindBy(xpath = "//select[@id='contentPlaceHolder_contactadd_ddlCompany']")
    private WebElement selectOrganisation;

    @FindBy(xpath = "//input[@id='contentPlaceHolder_contactadd_FirstName']")
    private WebElement addFirstName;

    @FindBy(xpath = "//input[@id='contentPlaceHolder_contactadd_JobTitle']")
    private WebElement addTitle;

    @FindBy(xpath = "//input[@id='contentPlaceHolder_contactadd_ContactPhone']")
    private WebElement addPhoneMain;

    @FindBy(xpath = "//input[@id='contentPlaceHolder_contactadd_ContactEmail']")
    private WebElement addEmail;

    @FindBy(xpath = "//input[@id='contentPlaceHolder_contactadd_BtnFullForm']")
    private WebElement submit;

    @FindBy(xpath = "(//input[@name='ctl00$contentPlaceHolder$RadioButtonList1'])[1]")
    private WebElement specificStatus;

    @FindBy(xpath = "(//input[@id='contentPlaceHolder_chkProjects_0'])[1]")
    private WebElement associatedWith;

    @FindBy(xpath = "(//input[@id='contentPlaceHolder_BtnTags'])[1]")
    private WebElement submitContact;

    @FindBy(xpath = "(//a[contains(text(),'List Contacts')])[1]")
    private WebElement goToListContacts;

    @FindBy(xpath = "(//input[@aria-controls='searchResults'])[1]")
    private WebElement contactSearchBox;

    @FindBy(xpath = "(//a[contains(text(),'Delete this contact')])[1]")
    private WebElement deleteContact;

    @FindBy(xpath = "//input[@id='ContentPlaceHolder1_Delete_yes']")
    private WebElement deletionConfirmation;

    public AddContactsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void addContact() {
        goToAddContactSection.click();
        Select se = new Select(selectCategory);
        se.selectByVisibleText("Design, Dev. & Const.");

        Select select = new Select(selectOrganisation);
        select.selectByVisibleText("Crowne Partners");
    }

    public void addDetails(String name, String title, String phoneMain, String email) {
        addFirstName.sendKeys(name);
        addTitle.sendKeys(title);
        addPhoneMain.sendKeys(phoneMain);
        addEmail.sendKeys(email);
        submit.click();
    }

    public void contactEntry() {
        specificStatus.click();
        associatedWith.click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        submitContact.click();
        System.out.println(driver.getTitle());
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        deleteContact.click();
        deletionConfirmation.click();
    }

//    public void verifyContactIsCreatedOrNot(String name) {
//        goToListContacts.click();
//        contactSearchBox.sendKeys(name);
//
//        while (true) {
//            // Find and click the link
//            WebElement linkElement = driver.findElement(By.linkText(name));
//            if (linkElement != null) {
//                linkElement.click();
//                break;
//            }
//        }
//    }
}