package Tests;

import Pages.PageFactory;
import ReadExcelData.ExcelDataConfig;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class baseClass {
    static WebDriver driver;
    protected PageFactory pageFactory;

    @Parameters("browserName")
    @BeforeClass
    public void setUp(String browserName) throws Exception {
        if (browserName.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            pageFactory = new PageFactory(driver);
        } else if (browserName.equalsIgnoreCase("edge")) {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
            driver.manage().window().maximize();
            pageFactory = new PageFactory(driver);
        } else if (browserName.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
            driver.manage().window().maximize();
            pageFactory = new PageFactory(driver);
        } else if (browserName.equalsIgnoreCase("headlessChrome")) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("headless");
            driver = new ChromeDriver(options);
            driver.manage().window().maximize();
            pageFactory = new PageFactory(driver);
        } else {
            throw new Exception("Invalid browser");
        }
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    public static WebDriver getDrivers() {
        return driver;
    }

    @AfterClass
    public void tearDown() {
//        driver.quit();
    }

    @DataProvider(name = "WebsiteLoginData")
    public Object[][] LoginPageData() throws IOException {
        String excelSheetPath = System.getProperty("user.dir");
        ExcelDataConfig config = new ExcelDataConfig(excelSheetPath + "//TestData//tealPDTestData.xlsx");
        int rows = config.getRowCount(0);
        int cols = config.getCellCount(0, 1);
        String[][] data = new String[rows][cols];
        for (int i = 1; i <= rows; i++) {
            for (int j = 0; j < cols; j++) {
                data[i - 1][j] = config.getData(0, i, j);
            }
        }
        return data;
    }

    @DataProvider(name = "AddContactsData")
    public Object[][] ContactsPageData() throws IOException {
        String excelSheetPath = System.getProperty("user.dir");
        ExcelDataConfig config = new ExcelDataConfig(excelSheetPath + "//TestData//tealPDTestData.xlsx");
        int rows = config.getRowCount(1);
        int cols = config.getCellCount(1, 1);
        String[][] data = new String[rows][cols];
        for (int i = 1; i <= rows; i++) {
            for (int j = 0; j < cols; j++) {
                data[i - 1][j] = config.getData(1, i, j);
            }
        }
        return data;
    }

    @DataProvider(name = "QpPageData")
    public Object[][] QpPage() throws IOException {
        String excelSheetPath = System.getProperty("user.dir");
        ExcelDataConfig config = new ExcelDataConfig(excelSheetPath + "//TestData//tealPDTestData.xlsx");
        int rows = config.getRowCount(2);
        int cols = config.getCellCount(2, 1);
        String[][] data = new String[rows][cols];
        for (int i = 1; i <= rows; i++) {
            for (int j = 0; j < cols; j++) {
                data[i - 1][j] = config.getData(2, i, j);
            }
        }
        return data;
    }

    @DataProvider(name = "skidData")
    public Object[][] skidData() throws IOException {
        String excelSheetPath = System.getProperty("user.dir");
        ExcelDataConfig config = new ExcelDataConfig(excelSheetPath + "//TestData//tealPDTestData.xlsx");
        int rows = config.getRowCount(3);
        int cols = config.getCellCount(3, 1);
        String[][] data = new String[rows][cols];
        for (int i = 1; i <= rows; i++) {
            for (int j = 0; j < cols; j++) {
                data[i - 1][j] = config.getData(3, i, j);
            }
        }
        return data;
    }
}
