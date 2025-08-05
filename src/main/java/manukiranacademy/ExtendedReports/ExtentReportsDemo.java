package manukiranacademy.ExtendedReports;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportsDemo {

    WebDriver driver;
    ExtentReports extent;
    ExtentTest test;

    @BeforeTest
    public void config() {
        String path = System.getProperty("user.dir") + "\\reports\\index.html";
        ExtentSparkReporter reporter = new ExtentSparkReporter(path);
        reporter.config().setReportName("Web automation by Manu");
        reporter.config().setDocumentTitle("Test Results");

        extent = new ExtentReports();
        extent.attachReporter(reporter);
        extent.setSystemInfo("tester", "Manukiran");

        driver = new ChromeDriver(); // initialize once
    }

    @Test
    public void initialDemo() {
        test = extent.createTest("Initial Demo test");
        try {
            driver.get("https://rahulshettyacademy.com");
            String title = driver.getTitle();
            test.pass("Navigated to homepage. Title: " + title);
        } catch (Exception e) {
            test.fail("Test failed: " + e.getMessage());
        }
    }

    @Test
    public void test1() {
        test = extent.createTest("test1");
        try {
            driver.findElement(By.className("theme-btn")).click();
            driver.findElement(By.cssSelector("input[autocomplete='name']")).sendKeys("manukiran");
            test.pass("Filled name input field");
        } catch (Exception e) {
            test.fail("Test failed: " + e.getMessage());
        }
    }

    @Test
    public void test2() {
        test = extent.createTest("test2");
        try {
            driver.findElement(By.xpath("//input[@autocomplete='email']")).sendKeys("manukiran.b@gmail.com");
            driver.findElement(By.id("allowMarketingEmails")).click();
            String msg = driver.findElement(By.xpath("//div[contains(text(), 'instructional emails')]")).getText();
            System.out.println(msg);
            test.pass("Message displayed: " + msg);
        } catch (Exception e) {
            test.fail("Test failed: " + e.getMessage());
        }
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
        extent.flush(); // flush once after all tests
    }
}
