package com.automation.utils;

import org.openqa.selenium.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * ScreenshotUtil
 * ─────────────────────────────────────────────────────────────────
 * Central utility for capturing browser screenshots.
 *
 * Usage from any test / page:
 *   String path = ScreenshotUtil.capture(driver, "TC01_NameField");
 *
 * Screenshots are saved to:
 *   <project-root>/screenshots/<testName>_<timestamp>.png
 *
 * The returned absolute path is embedded into the ExtentReport so
 * every step has a visible screenshot in the HTML report.
 * ─────────────────────────────────────────────────────────────────
 */
public class ScreenshotUtil {

    // All screenshots land in this folder (relative to project root)
    private static final String SCREENSHOT_DIR = "screenshots";

    // Timestamp pattern used in file names  e.g. 20240615_143022_450
    private static final SimpleDateFormat SDF =
            new SimpleDateFormat("yyyyMMdd_HHmmss_SSS");

    /**
     * Takes a full-page screenshot and saves it as a PNG.
     *
     * @param driver   active WebDriver instance
     * @param stepName short label – becomes part of the filename
     *                 (spaces replaced with underscores automatically)
     * @return absolute path of the saved PNG file,
     *         or empty string if capture fails (so callers never crash)
     */
    public static String capture(WebDriver driver, String stepName) {

        // Sanitize the step name so it can safely be used in a filename
        String safeName = stepName.replaceAll("[^a-zA-Z0-9_\\-]", "_");
        String timestamp = SDF.format(new Date());
        String fileName  = safeName + "_" + timestamp + ".png";

        // Ensure the screenshots directory exists
        File dir = new File(SCREENSHOT_DIR);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        File destFile = new File(dir, fileName);

        try {
            // Selenium TakesScreenshot interface – works for full-window capture
            TakesScreenshot ts = (TakesScreenshot) driver;
            File srcFile = ts.getScreenshotAs(OutputType.FILE);

            // Copy from Selenium temp location → our screenshots folder
            Files.copy(srcFile.toPath(), destFile.toPath(),
                       StandardCopyOption.REPLACE_EXISTING);

            System.out.println("  📸 Screenshot saved → " + destFile.getAbsolutePath());
            return destFile.getAbsolutePath();

        } catch (IOException e) {
            System.err.println("  ⚠ Screenshot capture failed for step '"
                               + stepName + "': " + e.getMessage());
            return "";
        }
    }

    /**
     * Convenience overload – scrolls an element into view, then captures.
     * Useful when you want to guarantee the element is visible in the shot.
     */
    public static String captureElement(WebDriver driver,
                                        WebElement element,
                                        String stepName) {
        try {
            ((JavascriptExecutor) driver)
                .executeScript(
                    "arguments[0].scrollIntoView({block:'center'});", element);
            Thread.sleep(300);   // brief settle after scroll
        } catch (Exception ignored) {}
        return capture(driver, stepName);
    }
}
