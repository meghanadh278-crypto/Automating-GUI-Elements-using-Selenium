package com.automation.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

/**
 * ExtentReportManager
 * ─────────────────────────────────────────────────────────────────
 * Singleton that owns the single ExtentReports instance used across
 * the entire test run.
 *
 * How it works:
 *  1. BaseTest.setUp()   → ExtentReportManager.init()
 *  2. Each @Test         → ExtentReportManager.createTest("TC01 – Name")
 *                          test.log / test.pass / test.fail / test.addScreenCaptureFromPath
 *  3. BaseTest.tearDown()→ ExtentReportManager.flush()
 *
 * Report is written to:  test-output/ExtentReport.html
 * ─────────────────────────────────────────────────────────────────
 */
public class ExtentReportManager {

    private static ExtentReports extent;

    // ThreadLocal so parallel tests each have their own ExtentTest node
    private static final ThreadLocal<ExtentTest> testNode =
            new ThreadLocal<>();

    // Output path (relative to project root)
    private static final String REPORT_PATH = "test-output/ExtentReport.html";

    /** Initialises the report – call once before the test suite starts. */
    public static void init() {
        ExtentSparkReporter spark = new ExtentSparkReporter(REPORT_PATH);
        spark.config().setTheme(Theme.DARK);
        spark.config().setDocumentTitle("GUI Automation Report");
        spark.config().setReportName("testautomationpractice.blogspot.com");
        spark.config().setTimeStampFormat("dd-MMM-yyyy HH:mm:ss");

        extent = new ExtentReports();
        extent.attachReporter(spark);
        extent.setSystemInfo("Application", "testautomationpractice.blogspot.com");
        extent.setSystemInfo("Browser",     "Google Chrome");
        extent.setSystemInfo("Environment", "QA");
        extent.setSystemInfo("Tester",      System.getProperty("user.name"));
    }

    /** Creates a new test node in the report. */
    public static ExtentTest createTest(String testName, String description) {
        ExtentTest test = extent.createTest(testName, description);
        testNode.set(test);
        return test;
    }

    /** Returns the ExtentTest node for the current thread. */
    public static ExtentTest getTest() {
        return testNode.get();
    }

    /** Writes the report to disk – call once after all tests finish. */
    public static void flush() {
        if (extent != null) {
            extent.flush();
            System.out.println("\n[Report] Extent HTML report → " + REPORT_PATH);
        }
    }
}
