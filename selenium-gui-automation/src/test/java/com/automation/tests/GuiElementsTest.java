package com.automation.tests;

import com.automation.pages.GuiElementsPage;
import com.automation.utils.ExtentReportManager;
import com.automation.utils.ScreenshotUtil;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileWriter;

/**
 * GuiElementsTest
 * ═══════════════════════════════════════════════════════════════════════
 * 14 TestNG tests covering EVERY GUI element on:
 *   https://testautomationpractice.blogspot.com/
 *
 * Each test:
 *   1. Creates an ExtentTest node in the HTML report.
 *   2. Performs the action via GuiElementsPage (POM).
 *   3. Captures a screenshot BEFORE the action (initial state).
 *   4. Captures a screenshot AFTER the action (result state).
 *   5. Embeds both screenshots into the report with descriptive labels.
 *   6. Asserts the expected outcome.
 *   7. Marks the test PASS / FAIL in the report.
 * ═══════════════════════════════════════════════════════════════════════
 */
public class GuiElementsTest extends BaseTest {

    private GuiElementsPage page;

    // ── Test data ────────────────────────────────────────────────────
    private static final String NAME        = "John Doe";
    private static final String EMAIL       = "johndoe@example.com";
    private static final String PHONE       = "9876543210";
    private static final String ADDRESS     = "123 MG Road, Bangalore, Karnataka 560001";
    private static final String GENDER      = "male";
    private static final String COUNTRY     = "India";
    private static final String COLOR       = "Blue";
    private static final String ANIMAL      = "Dog";
    private static final String DATE1       = "06/15/2024";   // mm/dd/yyyy
    private static final String DATE2       = "15/06/2024";   // dd/mm/yyyy
    private static final String PROMPT_TEXT = "SeleniumAutomation";
    private static final String DC_TEXT     = "CopyThisText";

    @BeforeClass
    public void initPage() {
        page = new GuiElementsPage(driver, wait);
    }

    // ─── helper: embed screenshot into the current ExtentTest node ───
    private void addScreenshot(ExtentTest test, String stepName, String description) {
        String path = ScreenshotUtil.capture(driver, stepName);
        if (!path.isEmpty()) {
            try {
                test.addScreenCaptureFromPath(path, description);
            } catch (Exception e) {
                test.log(Status.WARNING, "Could not attach screenshot: " + e.getMessage());
            }
        }
    }

    // ═══════════════════════════════════════════════════════════════
    //  TEST 01 – Name Text Field
    // ═══════════════════════════════════════════════════════════════
    @Test(priority = 1, description = "Enter text in the Name field")
    public void test01_NameField() {
        ExtentTest test = ExtentReportManager.createTest(
            "TC01 – Name Text Field",
            "Verify that the Name field accepts and retains input text");

        try {
            // BEFORE screenshot
            page.scrollTo(page.getNameField());
            addScreenshot(test, "TC01_name_before", "Name field – before input");

            // Action
            page.enterName(NAME);
            test.log(Status.INFO, "Entered name: <b>" + NAME + "</b>");

            // AFTER screenshot
            addScreenshot(test, "TC01_name_after", "Name field – after input");

            // Assert
            String actual = page.getNameField().getAttribute("value");
            Assert.assertEquals(actual, NAME, "Name field value mismatch!");

            test.pass("Name field correctly shows: " + actual);

        } catch (AssertionError | Exception e) {
            addScreenshot(test, "TC01_name_FAIL", "FAIL – Name field");
            test.fail("TC01 failed: " + e.getMessage());
            throw e;
        }
    }

    // ═══════════════════════════════════════════════════════════════
    //  TEST 02 – Email Text Field
    // ═══════════════════════════════════════════════════════════════
    @Test(priority = 2, description = "Enter text in the Email field")
    public void test02_EmailField() {
        ExtentTest test = ExtentReportManager.createTest(
            "TC02 – Email Text Field",
            "Verify that the Email field accepts valid e-mail address");

        try {
            page.scrollTo(page.getEmailField());
            addScreenshot(test, "TC02_email_before", "Email field – before input");

            page.enterEmail(EMAIL);
            test.log(Status.INFO, "Entered email: <b>" + EMAIL + "</b>");

            addScreenshot(test, "TC02_email_after", "Email field – after input");

            String actual = page.getEmailField().getAttribute("value");
            Assert.assertEquals(actual, EMAIL, "Email field value mismatch!");
            test.pass("Email field correctly shows: " + actual);

        } catch (AssertionError | Exception e) {
            addScreenshot(test, "TC02_email_FAIL", "FAIL – Email field");
            test.fail(e.getMessage());
            throw e;
        }
    }

    // ═══════════════════════════════════════════════════════════════
    //  TEST 03 – Phone Text Field
    // ═══════════════════════════════════════════════════════════════
    @Test(priority = 3, description = "Enter text in the Phone field")
    public void test03_PhoneField() {
        ExtentTest test = ExtentReportManager.createTest(
            "TC03 – Phone Text Field",
            "Verify that the Phone field accepts a numeric phone number");

        try {
            page.scrollTo(page.getPhoneField());
            addScreenshot(test, "TC03_phone_before", "Phone field – before input");

            page.enterPhone(PHONE);
            test.log(Status.INFO, "Entered phone: <b>" + PHONE + "</b>");

            addScreenshot(test, "TC03_phone_after", "Phone field – after input");

            String actual = page.getPhoneField().getAttribute("value");
            Assert.assertEquals(actual, PHONE, "Phone field value mismatch!");
            test.pass("Phone field correctly shows: " + actual);

        } catch (AssertionError | Exception e) {
            addScreenshot(test, "TC03_phone_FAIL", "FAIL – Phone field");
            test.fail(e.getMessage());
            throw e;
        }
    }

    // ═══════════════════════════════════════════════════════════════
    //  TEST 04 – Address Textarea
    // ═══════════════════════════════════════════════════════════════
    @Test(priority = 4, description = "Enter text in the Address textarea")
    public void test04_AddressField() {
        ExtentTest test = ExtentReportManager.createTest(
            "TC04 – Address Textarea",
            "Verify that the Address textarea accepts multi-word text");

        try {
            page.scrollTo(page.getAddressField());
            addScreenshot(test, "TC04_address_before", "Address field – before input");

            page.enterAddress(ADDRESS);
            test.log(Status.INFO, "Entered address: <b>" + ADDRESS + "</b>");

            addScreenshot(test, "TC04_address_after", "Address field – after input");

            String actual = page.getAddressField().getAttribute("value");
            Assert.assertEquals(actual, ADDRESS, "Address field value mismatch!");
            test.pass("Address field correctly shows the entered text");

        } catch (AssertionError | Exception e) {
            addScreenshot(test, "TC04_address_FAIL", "FAIL – Address field");
            test.fail(e.getMessage());
            throw e;
        }
    }

    // ═══════════════════════════════════════════════════════════════
    //  TEST 05 – Gender Radio Button
    // ═══════════════════════════════════════════════════════════════
    @Test(priority = 5, description = "Select Male radio button for Gender")
    public void test05_RadioButton() {
        ExtentTest test = ExtentReportManager.createTest(
            "TC05 – Gender Radio Button",
            "Verify that the Male radio button can be selected");

        try {
            page.scrollTo(page.getGenderRadio(GENDER));
            addScreenshot(test, "TC05_radio_before", "Gender radio – before selection");

            page.selectGender(GENDER);
            test.log(Status.INFO, "Selected gender radio: <b>" + GENDER + "</b>");

            addScreenshot(test, "TC05_radio_after", "Gender radio – after selection");

            boolean selected = page.getGenderRadio(GENDER).isSelected();
            Assert.assertTrue(selected, "Gender radio '" + GENDER + "' NOT selected!");
            test.pass("Radio button '" + GENDER + "' is selected ✔");

        } catch (AssertionError | Exception e) {
            addScreenshot(test, "TC05_radio_FAIL", "FAIL – Radio button");
            test.fail(e.getMessage());
            throw e;
        }
    }

    // ═══════════════════════════════════════════════════════════════
    //  TEST 06 – Day Checkboxes
    // ═══════════════════════════════════════════════════════════════
    @Test(priority = 6, description = "Check Monday, Wednesday, Friday checkboxes")
    public void test06_Checkboxes() {
        ExtentTest test = ExtentReportManager.createTest(
            "TC06 – Days Checkboxes",
            "Verify that multiple day checkboxes can be selected simultaneously");

        try {
            String[] days = {"Monday", "Wednesday", "Friday"};

            page.scrollTo(page.getDayCheckbox("monday"));
            addScreenshot(test, "TC06_checkbox_before", "Checkboxes – before selection");

            for (String day : days) {
                page.checkDay(day);
                test.log(Status.INFO, "Checked: <b>" + day + "</b>");
            }

            addScreenshot(test, "TC06_checkbox_after", "Checkboxes – after selecting Mon/Wed/Fri");

            for (String day : days) {
                Assert.assertTrue(page.getDayCheckbox(day).isSelected(),
                    day + " checkbox is NOT checked!");
            }
            test.pass("Monday, Wednesday and Friday are all checked ✔");

        } catch (AssertionError | Exception e) {
            addScreenshot(test, "TC06_checkbox_FAIL", "FAIL – Checkboxes");
            test.fail(e.getMessage());
            throw e;
        }
    }

    // ═══════════════════════════════════════════════════════════════
    //  TEST 07 – Dropdowns (Country, Colors, Sorted List)
    // ═══════════════════════════════════════════════════════════════
    @Test(priority = 7, description = "Select values from Country, Colors and Animals dropdowns")
    public void test07_Dropdowns() {
        ExtentTest test = ExtentReportManager.createTest(
            "TC07 – Dropdowns",
            "Verify Country, Colors and Animals (sorted list) dropdown selections");

        try {
            // Country
            page.scrollTo(page.getCountryDropdown());
            addScreenshot(test, "TC07_country_before", "Country dropdown – before");
            page.selectCountry(COUNTRY);
            test.log(Status.INFO, "Country selected: <b>" + COUNTRY + "</b>");
            addScreenshot(test, "TC07_country_after", "Country dropdown – after selecting India");

            // Colors
            page.scrollTo(page.getColorsDropdown());
            addScreenshot(test, "TC07_color_before", "Colors dropdown – before");
            page.selectColor(COLOR);
            test.log(Status.INFO, "Color selected: <b>" + COLOR + "</b>");
            addScreenshot(test, "TC07_color_after", "Colors dropdown – after selecting Blue");

            // Animals (sorted list)
            page.scrollTo(page.getAnimalsDropdown());
            addScreenshot(test, "TC07_animal_before", "Animals dropdown – before");
            page.selectAnimal(ANIMAL);
            test.log(Status.INFO, "Animal selected: <b>" + ANIMAL + "</b>");
            addScreenshot(test, "TC07_animal_after", "Animals dropdown – after selecting Dog");

            // Assertions
            Assert.assertEquals(
                new Select(page.getCountryDropdown()).getFirstSelectedOption().getText(),
                COUNTRY, "Country mismatch!");
            Assert.assertEquals(
                new Select(page.getColorsDropdown()).getFirstSelectedOption().getText(),
                COLOR, "Color mismatch!");
            Assert.assertEquals(
                new Select(page.getAnimalsDropdown()).getFirstSelectedOption().getText(),
                ANIMAL, "Animal mismatch!");

            test.pass("Country=" + COUNTRY + "  Color=" + COLOR + "  Animal=" + ANIMAL + " ✔");

        } catch (AssertionError | Exception e) {
            addScreenshot(test, "TC07_dropdown_FAIL", "FAIL – Dropdowns");
            test.fail(e.getMessage());
            throw e;
        }
    }

    // ═══════════════════════════════════════════════════════════════
    //  TEST 08 – Date Pickers
    // ═══════════════════════════════════════════════════════════════
    @Test(priority = 8, description = "Enter dates in both jQuery UI date pickers")
    public void test08_DatePickers() {
        ExtentTest test = ExtentReportManager.createTest(
            "TC08 – Date Pickers",
            "Verify jQuery UI date pickers accept and retain entered dates");

        try {
            // Date Picker 1
            page.scrollTo(page.getDatePicker1());
            addScreenshot(test, "TC08_dp1_before", "DatePicker1 – before input");
            page.enterDatePicker1(DATE1);
            test.log(Status.INFO, "DatePicker1 (mm/dd/yyyy) entered: <b>" + DATE1 + "</b>");
            addScreenshot(test, "TC08_dp1_after", "DatePicker1 – after entering " + DATE1);

            // Date Picker 2
            page.scrollTo(page.getDatePicker2());
            addScreenshot(test, "TC08_dp2_before", "DatePicker2 – before input");
            page.enterDatePicker2(DATE2);
            test.log(Status.INFO, "DatePicker2 (dd/mm/yyyy) entered: <b>" + DATE2 + "</b>");
            addScreenshot(test, "TC08_dp2_after", "DatePicker2 – after entering " + DATE2);

            // Assertions
            Assert.assertFalse(page.getDatePicker1().getAttribute("value").isEmpty(),
                "DatePicker1 is empty after input!");
            Assert.assertFalse(page.getDatePicker2().getAttribute("value").isEmpty(),
                "DatePicker2 is empty after input!");

            test.pass("Both date pickers populated ✔  DP1="
                + page.getDatePicker1().getAttribute("value")
                + "  DP2=" + page.getDatePicker2().getAttribute("value"));

        } catch (AssertionError | Exception e) {
            addScreenshot(test, "TC08_datepicker_FAIL", "FAIL – Date Pickers");
            test.fail(e.getMessage());
            throw e;
        }
    }

    // ═══════════════════════════════════════════════════════════════
    //  TEST 09 – File Upload
    // ═══════════════════════════════════════════════════════════════
    @Test(priority = 9, description = "Upload a file via the single file upload input")
    public void test09_FileUpload() throws Exception {
        ExtentTest test = ExtentReportManager.createTest(
            "TC09 – File Upload",
            "Verify the single file upload input accepts a file path");

        try {
            // Create a real temp file for upload
            File tmp = File.createTempFile("selenium_upload_", ".txt");
            try (FileWriter fw = new FileWriter(tmp)) {
                fw.write("Selenium test file for upload verification.");
            }

            page.scrollTo(page.getSingleUpload());
            addScreenshot(test, "TC09_upload_before", "File upload – before");

            page.uploadSingleFile(tmp.getAbsolutePath());
            test.log(Status.INFO, "File path sent: <b>" + tmp.getAbsolutePath() + "</b>");

            addScreenshot(test, "TC09_upload_after", "File upload – after selecting file");

            String uploaded = page.getSingleUpload().getAttribute("value");
            Assert.assertFalse(uploaded.isEmpty(), "File upload input is empty after upload!");

            test.pass("File upload input value: " + uploaded + " ✔");
            tmp.deleteOnExit();

        } catch (AssertionError | Exception e) {
            addScreenshot(test, "TC09_upload_FAIL", "FAIL – File Upload");
            test.fail(e.getMessage());
            throw e;
        }
    }

    // ═══════════════════════════════════════════════════════════════
    //  TEST 10 – Slider
    // ═══════════════════════════════════════════════════════════════
    @Test(priority = 10, description = "Move the jQuery UI price-range slider")
    public void test10_Slider() {
        ExtentTest test = ExtentReportManager.createTest(
            "TC10 – Slider",
            "Verify the jQuery UI range slider moves when arrow keys are sent");

        try {
            page.scrollTo(page.getSliderHandle());
            String before = page.getSliderValue();
            int beforeInt = (before == null || before.isEmpty()) ? 0
                            : Integer.parseInt(before);

            addScreenshot(test, "TC10_slider_before",
                "Slider – before move (value=" + beforeInt + ")");
            test.log(Status.INFO, "Slider value BEFORE: <b>" + beforeInt + "</b>");

            page.moveSlider(15);
            test.log(Status.INFO, "Moved slider 15 steps to the right");

            String after = page.getSliderValue();
            int afterInt = Integer.parseInt(after);

            addScreenshot(test, "TC10_slider_after",
                "Slider – after move (value=" + afterInt + ")");
            test.log(Status.INFO, "Slider value AFTER: <b>" + afterInt + "</b>");

            Assert.assertTrue(afterInt > beforeInt,
                "Slider did not increase! before=" + beforeInt + " after=" + afterInt);
            test.pass("Slider moved: " + beforeInt + " → " + afterInt + " ✔");

        } catch (AssertionError | Exception e) {
            addScreenshot(test, "TC10_slider_FAIL", "FAIL – Slider");
            test.fail(e.getMessage());
            throw e;
        }
    }

    // ═══════════════════════════════════════════════════════════════
    //  TEST 11 – Simple Alert
    // ═══════════════════════════════════════════════════════════════
    @Test(priority = 11, description = "Click the Simple Alert button and accept the alert")
    public void test11_SimpleAlert() {
        ExtentTest test = ExtentReportManager.createTest(
            "TC11 – Simple Alert",
            "Verify a simple JavaScript alert can be triggered and accepted");

        try {
            page.scrollTo(page.getSimpleAlertBtn());
            addScreenshot(test, "TC11_alert_before", "Simple Alert button – before click");
            test.log(Status.INFO, "Clicking Simple Alert button");

            page.clickSimpleAlert();

            // Screenshot while alert is open is browser-specific – take after accept
            String alertText = page.acceptSimpleAlert();
            test.log(Status.INFO, "Alert text was: <b>" + alertText + "</b>");

            addScreenshot(test, "TC11_alert_after", "Page – after alert accepted");

            Assert.assertNotNull(alertText, "Alert text was null!");
            test.pass("Simple alert accepted. Alert text: " + alertText + " ✔");

        } catch (AssertionError | Exception e) {
            addScreenshot(test, "TC11_alert_FAIL", "FAIL – Simple Alert");
            test.fail(e.getMessage());
            throw e;
        }
    }

    // ═══════════════════════════════════════════════════════════════
    //  TEST 12 – Confirmation Alert
    // ═══════════════════════════════════════════════════════════════
    @Test(priority = 12, description = "Click Confirmation Alert button and accept it")
    public void test12_ConfirmAlert() {
        ExtentTest test = ExtentReportManager.createTest(
            "TC12 – Confirmation Alert",
            "Verify a JavaScript confirm dialog can be accepted");

        try {
            page.scrollTo(page.getConfirmAlertBtn());
            addScreenshot(test, "TC12_confirm_before", "Confirm Alert button – before click");

            page.clickConfirmAlert();
            String alertText = page.acceptConfirmAlert();
            test.log(Status.INFO, "Confirm alert text: <b>" + alertText + "</b>");

            addScreenshot(test, "TC12_confirm_after", "Page – after confirm alert accepted");

            Assert.assertNotNull(alertText, "Confirm alert text was null!");
            test.pass("Confirmation alert accepted ✔");

        } catch (AssertionError | Exception e) {
            addScreenshot(test, "TC12_confirm_FAIL", "FAIL – Confirmation Alert");
            test.fail(e.getMessage());
            throw e;
        }
    }

    // ═══════════════════════════════════════════════════════════════
    //  TEST 13 – Prompt Alert
    // ═══════════════════════════════════════════════════════════════
    @Test(priority = 13, description = "Click Prompt Alert button, type text, and accept")
    public void test13_PromptAlert() {
        ExtentTest test = ExtentReportManager.createTest(
            "TC13 – Prompt Alert",
            "Verify a JavaScript prompt dialog accepts user input");

        try {
            page.scrollTo(page.getPromptAlertBtn());
            addScreenshot(test, "TC13_prompt_before", "Prompt Alert button – before click");

            page.clickPromptAlert();
            String alertText = page.handlePromptAlert(PROMPT_TEXT);
            test.log(Status.INFO, "Prompt text: <b>" + alertText
                + "</b>  →  Typed: <b>" + PROMPT_TEXT + "</b>");

            addScreenshot(test, "TC13_prompt_after", "Page – after prompt alert submitted");

            Assert.assertNotNull(alertText, "Prompt alert text was null!");
            test.pass("Prompt alert filled with '" + PROMPT_TEXT + "' and accepted ✔");

        } catch (AssertionError | Exception e) {
            addScreenshot(test, "TC13_prompt_FAIL", "FAIL – Prompt Alert");
            test.fail(e.getMessage());
            throw e;
        }
    }

    // ═══════════════════════════════════════════════════════════════
    //  TEST 14 – Mouse Hover
    // ═══════════════════════════════════════════════════════════════
    @Test(priority = 14, description = "Hover over 'Point Me' and click 'Mobiles' sub-link")
    public void test14_MouseHover() {
        ExtentTest test = ExtentReportManager.createTest(
            "TC14 – Mouse Hover",
            "Verify dropdown sub-menu appears on hover and sub-link is clickable");

        try {
            page.scrollTo(page.getHoverButton());
            addScreenshot(test, "TC14_hover_before", "Hover button – before hover");

            page.mouseHoverAndClick("Mobiles");
            test.log(Status.INFO, "Hovered over 'Point Me' button and clicked 'Mobiles'");

            addScreenshot(test, "TC14_hover_after", "Page – after clicking Mobiles sub-link");
            test.pass("Mouse hover → sub-menu appeared → 'Mobiles' clicked ✔");

        } catch (AssertionError | Exception e) {
            addScreenshot(test, "TC14_hover_FAIL", "FAIL – Mouse Hover");
            test.fail(e.getMessage());
            throw e;
        }
    }

    // ═══════════════════════════════════════════════════════════════
    //  TEST 15 – Double Click
    // ═══════════════════════════════════════════════════════════════
    @Test(priority = 15, description = "Double-click Copy button to copy Field1 text into Field2")
    public void test15_DoubleClick() {
        ExtentTest test = ExtentReportManager.createTest(
            "TC15 – Double Click",
            "Verify double-clicking the Copy button copies Field1 text to Field2");

        try {
            page.scrollTo(page.getField1());
            addScreenshot(test, "TC15_dblclick_before", "Double-click area – before");

            page.setField1(DC_TEXT);
            test.log(Status.INFO, "Field1 set to: <b>" + DC_TEXT + "</b>");

            addScreenshot(test, "TC15_dblclick_field1_filled", "Field1 filled – before double-click");

            page.doubleClickCopyButton();
            test.log(Status.INFO, "Double-clicked the Copy button");

            addScreenshot(test, "TC15_dblclick_after", "After double-click – Field2 should match");

            String field2 = page.getField2Value();
            Assert.assertEquals(field2, DC_TEXT,
                "Field2 does not contain the copied text!");
            test.pass("Field2 value = '" + field2 + "' (matches Field1) ✔");

        } catch (AssertionError | Exception e) {
            addScreenshot(test, "TC15_dblclick_FAIL", "FAIL – Double Click");
            test.fail(e.getMessage());
            throw e;
        }
    }

    // ═══════════════════════════════════════════════════════════════
    //  TEST 16 – Drag and Drop
    // ═══════════════════════════════════════════════════════════════
    @Test(priority = 16, description = "Drag #draggable element and drop onto #droppable target")
    public void test16_DragAndDrop() {
        ExtentTest test = ExtentReportManager.createTest(
            "TC16 – Drag and Drop",
            "Verify draggable element can be dragged and dropped onto the target");

        try {
            page.scrollTo(page.getDraggable());
            addScreenshot(test, "TC16_dragdrop_before", "Drag & Drop – before drag");
            test.log(Status.INFO, "Dragging #draggable onto #droppable");

            page.dragAndDrop();

            addScreenshot(test, "TC16_dragdrop_after", "Drag & Drop – after drop");

            String dropText = page.getDropTargetText();
            test.log(Status.INFO, "Drop target text after drop: <b>" + dropText + "</b>");

            Assert.assertFalse(dropText.isEmpty(),
                "Drop target text is empty – drag-and-drop may have failed!");
            test.pass("Drag and drop successful. Target text: '" + dropText + "' ✔");

        } catch (AssertionError | Exception e) {
            addScreenshot(test, "TC16_dragdrop_FAIL", "FAIL – Drag and Drop");
            test.fail(e.getMessage());
            throw e;
        }
    }
}
