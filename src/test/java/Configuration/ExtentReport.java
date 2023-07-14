package Configuration;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.openqa.selenium.WebDriver;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public final class ExtentReport {

    private ExtentReport(){}
    private static ExtentReports extent;
    public static ExtentTest test;
    private WebDriver driver;

    public static void initReports() throws IOException {
        ExtentSparkReporter reporter = new ExtentSparkReporter("target//Extent-Reports//index.html");
        reporter.config().setTheme(Theme.STANDARD);
        reporter.config().setReportName("Test Execution Report");
        reporter.config().setDocumentTitle("Automation Report");
        extent = new ExtentReports();
        extent.attachReporter(reporter);
    }

    public static void flushReports() throws IOException {
        extent.flush();
        Desktop.getDesktop().browse(new File("target//Extent-Reports//index.html").toURI());
    }

    public static void createTest(String testCaseName){
        test = extent.createTest(testCaseName);
    }
}