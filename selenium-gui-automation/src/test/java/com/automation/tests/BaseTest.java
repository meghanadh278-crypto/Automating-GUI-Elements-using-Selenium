package com.automation.tests;

import com.automation.utils.ExtentReportManager;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.time.Duration;

/**
 * BaseTest
 * ─────────────────────────────────────────────────────────────────
 * • @BeforeSuite  – launches Chrome, opens the target URL, inits report
 * • @AfterSuite   – closes browser, flushes Extent HTML report
 *
 * driver and wait are public so GuiElementsTest can use them directly.
 * ─────────────────────────────────────────────────────────────────
 */
public class BaseTest {

    public static WebDriver     driver;
    public static WebDriverWait wait;

    public static final String BASE_URL =
            "https://testautomationpractice.blogspot.com/";

    @BeforeSuite
    public void setUp() {

        // ── 1. Initialise Extent HTML report ──────────────────────
        ExtentReportManager.init();

        // ── 2. Download / configure the correct ChromeDriver ──────
        WebDriverManager.chromedriver().setup();

        // ── 3. Chrome options ─────────────────────────────────────
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-infobars");
        options.addArguments("--remote-allow-origins=*");
        // Uncomment to run without visible browser:
        // options.addArguments("--headless=new");

        // ── 4. Launch Chrome ───────────────────────────────────────
        driver = new ChromeDriver(options);
        driver.manage().timeouts()
              .implicitlyWait(Duration.ofSeconds(10));
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        // ── 5. Open the page ───────────────────────────────────────
        driver.get(BASE_URL);
        System.out.println("[BaseTest] Browser launched → " + BASE_URL);
    }

    @AfterSuite
    public void tearDown() {
        // ── Close browser ─────────────────────────────────────────
        if (driver != null) {
            driver.quit();
            System.out.println("[BaseTest] Browser closed.");
        }
        // ── Flush Extent report to disk ───────────────────────────
        ExtentReportManager.flush();
    }
}
