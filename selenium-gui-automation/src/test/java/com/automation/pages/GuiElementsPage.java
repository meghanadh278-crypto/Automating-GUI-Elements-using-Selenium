package com.automation.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * GuiElementsPage  –  Page Object Model
 * ═══════════════════════════════════════════════════════════════════
 * All locators verified against the LIVE page at:
 *   https://testautomationpractice.blogspot.com/
 *
 * Real element IDs / names confirmed from page source:
 *   Text fields   : name="name", name="email", name="phone", id="textarea"
 *   Radio         : name="gender"  value="male" / "female"
 *   Checkboxes    : id="sunday" "monday" "tuesday" "wednesday"
 *                   "thursday" "friday" "saturday"
 *   Country       : id="country"
 *   Colors        : id="colors"
 *   Sorted List   : name="Animals"
 *   DatePicker 1  : id="datepicker"   (mm/dd/yyyy)
 *   DatePicker 2  : id="datepicker2"  (dd/mm/yyyy)
 *   File upload   : id="singleFileUpload" / id="multipleFileUpload"
 *   Slider handle : CSS  #slider .ui-slider-handle
 *   Alert buttons : text "Simple Alert", "Confirmation Alert", "Prompt Alert"
 *   Hover button  : id="hoverButton"  sub-links text "Mobiles" / "Laptops"
 *   Double-click  : id="field1", id="field2", id="copyButton"
 *   Drag & drop   : id="draggable", id="droppable"
 * ═══════════════════════════════════════════════════════════════════
 */
public class GuiElementsPage {

    private final WebDriver     driver;
    private final WebDriverWait wait;
    private final Actions       actions;
    private final JavascriptExecutor js;

    public GuiElementsPage(WebDriver driver, WebDriverWait wait) {
        this.driver  = driver;
        this.wait    = wait;
        this.actions = new Actions(driver);
        this.js      = (JavascriptExecutor) driver;
    }

    // ─── internal helpers ────────────────────────────────────────

    /** Scrolls element into the centre of the viewport */
    public void scrollTo(WebElement el) {
        js.executeScript(
            "arguments[0].scrollIntoView({block:'center',inline:'nearest'});", el);
        sleep(300);
    }

    /** Scrolls the page by a pixel offset */
    public void scrollBy(int px) {
        js.executeScript("window.scrollBy(0," + px + ");");
        sleep(300);
    }

    public void sleep(long ms) {
        try { Thread.sleep(ms); } catch (InterruptedException ignored) {}
    }

    // ════════════════════════════════════════════════════════════
    //  1.  TEXT FIELDS
    // ════════════════════════════════════════════════════════════

    public void enterName(String value) {
        WebElement el = driver.findElement(By.name("name"));
        scrollTo(el);
        el.clear();
        el.sendKeys(value);
    }

    public void enterEmail(String value) {
        WebElement el = driver.findElement(By.name("email"));
        scrollTo(el);
        el.clear();
        el.sendKeys(value);
    }

    public void enterPhone(String value) {
        WebElement el = driver.findElement(By.name("phone"));
        scrollTo(el);
        el.clear();
        el.sendKeys(value);
    }

    public void enterAddress(String value) {
        WebElement el = driver.findElement(By.id("textarea"));
        scrollTo(el);
        el.clear();
        el.sendKeys(value);
    }

    // ════════════════════════════════════════════════════════════
    //  2.  RADIO BUTTON – Gender
    // ════════════════════════════════════════════════════════════

    /** @param gender "male" or "female" */
    public void selectGender(String gender) {
        WebElement radio = driver.findElement(
            By.xpath("//input[@type='radio' and @value='" + gender + "']"));
        scrollTo(radio);
        if (!radio.isSelected()) radio.click();
    }

    // ════════════════════════════════════════════════════════════
    //  3.  CHECKBOXES – Days of week
    // ════════════════════════════════════════════════════════════

    /** @param day full day name, e.g. "Monday" (case-insensitive) */
    public void checkDay(String day) {
        WebElement cb = driver.findElement(By.id(day.toLowerCase()));
        scrollTo(cb);
        if (!cb.isSelected()) cb.click();
    }

    // ════════════════════════════════════════════════════════════
    //  4.  DROPDOWNS
    // ════════════════════════════════════════════════════════════

    public void selectCountry(String country) {
        WebElement el = driver.findElement(By.id("country"));
        scrollTo(el);
        new Select(el).selectByVisibleText(country);
    }

    public void selectColor(String color) {
        WebElement el = driver.findElement(By.id("colors"));
        scrollTo(el);
        new Select(el).selectByVisibleText(color);
    }

    /** Sorted List – Animals: Cat,Cheetah,Deer,Dog,Elephant,Fox,Giraffe,Lion,Rabbit,Zebra */
    public void selectAnimal(String animal) {
        WebElement el = driver.findElement(By.name("Animals"));
        scrollTo(el);
        new Select(el).selectByVisibleText(animal);
    }

    // ════════════════════════════════════════════════════════════
    //  5.  DATE PICKERS  (jQuery UI)
    // ════════════════════════════════════════════════════════════

    /** Date Picker 1  id="datepicker"  format mm/dd/yyyy */
    public void enterDatePicker1(String date) {
        WebElement el = driver.findElement(By.id("datepicker"));
        scrollTo(el);
        el.click();
        el.clear();
        el.sendKeys(date);
        el.sendKeys(Keys.ESCAPE);
    }

    /** Date Picker 2  id="datepicker2"  format dd/mm/yyyy */
    public void enterDatePicker2(String date) {
        WebElement el = driver.findElement(By.id("datepicker2"));
        scrollTo(el);
        el.click();
        el.clear();
        el.sendKeys(date);
        el.sendKeys(Keys.ESCAPE);
    }

    // ════════════════════════════════════════════════════════════
    //  6.  FILE UPLOAD
    // ════════════════════════════════════════════════════════════

    public void uploadSingleFile(String absolutePath) {
        WebElement el = driver.findElement(By.id("singleFileUpload"));
        scrollTo(el);
        el.sendKeys(absolutePath);
    }

    public void uploadMultipleFiles(String path1, String path2) {
        WebElement el = driver.findElement(By.id("multipleFileUpload"));
        scrollTo(el);
        // sendKeys with newline-separated paths triggers multi-file selection
        el.sendKeys(path1 + "\n" + path2);
    }

    // ════════════════════════════════════════════════════════════
    //  7.  SLIDER  (jQuery UI)
    // ════════════════════════════════════════════════════════════

    public void moveSlider(int steps) {
        WebElement handle = driver.findElement(
            By.cssSelector("#slider .ui-slider-handle"));
        scrollTo(handle);
        handle.click();
        for (int i = 0; i < steps; i++) handle.sendKeys(Keys.ARROW_RIGHT);
    }

    public String getSliderValue() {
        return driver.findElement(
            By.cssSelector("#slider .ui-slider-handle"))
            .getAttribute("aria-valuenow");
    }

    // ════════════════════════════════════════════════════════════
    //  8.  ALERTS
    // ════════════════════════════════════════════════════════════

    public void clickSimpleAlert() {
        WebElement btn = driver.findElement(
            By.xpath("//button[normalize-space()='Simple Alert']"));
        scrollTo(btn);
        btn.click();
    }

    public String acceptSimpleAlert() {
        wait.until(ExpectedConditions.alertIsPresent());
        Alert a = driver.switchTo().alert();
        String text = a.getText();
        a.accept();
        return text;
    }

    public void clickConfirmAlert() {
        WebElement btn = driver.findElement(
            By.xpath("//button[normalize-space()='Confirmation Alert']"));
        scrollTo(btn);
        btn.click();
    }

    public String acceptConfirmAlert() {
        wait.until(ExpectedConditions.alertIsPresent());
        Alert a = driver.switchTo().alert();
        String text = a.getText();
        a.accept();
        return text;
    }

    public void clickPromptAlert() {
        WebElement btn = driver.findElement(
            By.xpath("//button[normalize-space()='Prompt Alert']"));
        scrollTo(btn);
        btn.click();
    }

    public String handlePromptAlert(String inputText) {
        wait.until(ExpectedConditions.alertIsPresent());
        Alert a = driver.switchTo().alert();
        String text = a.getText();
        a.sendKeys(inputText);
        a.accept();
        return text;
    }

    // ════════════════════════════════════════════════════════════
    //  9.  MOUSE HOVER
    // ════════════════════════════════════════════════════════════

    /**
     * Hovers over "Point Me" button (id="hoverButton") and clicks
     * the given sub-link ("Mobiles" or "Laptops").
     */
    public void mouseHoverAndClick(String linkText) {
        WebElement hoverBtn = driver.findElement(By.id("hoverButton"));
        scrollTo(hoverBtn);
        actions.moveToElement(hoverBtn).perform();
        sleep(600);
        WebElement link = wait.until(
            ExpectedConditions.elementToBeClickable(
                By.xpath("//div[@class='dropdown-content']//a[text()='"
                         + linkText + "']")));
        link.click();
    }

    // ════════════════════════════════════════════════════════════
    //  10.  DOUBLE CLICK
    // ════════════════════════════════════════════════════════════

    public void setField1(String text) {
        WebElement field1 = driver.findElement(By.id("field1"));
        scrollTo(field1);
        field1.clear();
        field1.sendKeys(text);
    }

    public void doubleClickCopyButton() {
        WebElement copyBtn = driver.findElement(By.id("copyButton"));
        scrollTo(copyBtn);
        actions.doubleClick(copyBtn).perform();
        sleep(500);
    }

    public String getField2Value() {
        return driver.findElement(By.id("field2")).getAttribute("value");
    }

    // ════════════════════════════════════════════════════════════
    //  11.  DRAG AND DROP
    // ════════════════════════════════════════════════════════════

    public void dragAndDrop() {
        WebElement source = driver.findElement(By.id("draggable"));
        WebElement target = driver.findElement(By.id("droppable"));
        scrollTo(source);
        actions.dragAndDrop(source, target).perform();
        sleep(600);
    }

    public String getDropTargetText() {
        return driver.findElement(By.id("droppable")).getText().trim();
    }

    // ════════════════════════════════════════════════════════════
    //  GETTERS – expose elements so tests can capture them
    // ════════════════════════════════════════════════════════════

    public WebElement getNameField()       { return driver.findElement(By.name("name")); }
    public WebElement getEmailField()      { return driver.findElement(By.name("email")); }
    public WebElement getPhoneField()      { return driver.findElement(By.name("phone")); }
    public WebElement getAddressField()    { return driver.findElement(By.id("textarea")); }
    public WebElement getGenderRadio(String g) {
        return driver.findElement(
            By.xpath("//input[@type='radio' and @value='" + g + "']"));
    }
    public WebElement getDayCheckbox(String day) {
        return driver.findElement(By.id(day.toLowerCase()));
    }
    public WebElement getCountryDropdown() { return driver.findElement(By.id("country")); }
    public WebElement getColorsDropdown()  { return driver.findElement(By.id("colors")); }
    public WebElement getAnimalsDropdown() { return driver.findElement(By.name("Animals")); }
    public WebElement getDatePicker1()     { return driver.findElement(By.id("datepicker")); }
    public WebElement getDatePicker2()     { return driver.findElement(By.id("datepicker2")); }
    public WebElement getSingleUpload()    { return driver.findElement(By.id("singleFileUpload")); }
    public WebElement getSliderHandle()    { return driver.findElement(By.cssSelector("#slider .ui-slider-handle")); }
    public WebElement getSimpleAlertBtn()  { return driver.findElement(By.xpath("//button[normalize-space()='Simple Alert']")); }
    public WebElement getConfirmAlertBtn() { return driver.findElement(By.xpath("//button[normalize-space()='Confirmation Alert']")); }
    public WebElement getPromptAlertBtn()  { return driver.findElement(By.xpath("//button[normalize-space()='Prompt Alert']")); }
    public WebElement getHoverButton()     { return driver.findElement(By.id("hoverButton")); }
    public WebElement getField1()          { return driver.findElement(By.id("field1")); }
    public WebElement getCopyButton()      { return driver.findElement(By.id("copyButton")); }
    public WebElement getDraggable()       { return driver.findElement(By.id("draggable")); }
    public WebElement getDroppable()       { return driver.findElement(By.id("droppable")); }
}
