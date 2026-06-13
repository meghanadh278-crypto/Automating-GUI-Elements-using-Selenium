This is a Selenium GUI Automation Framework capstone where the goal is to automate and validate the interactive elements on testautomationpractice.blogspot.com using Java and Selenium WebDriver. It is built as a structured automation framework rather than a one-off script, so it is easier to maintain, extend, and report test results professionally.

The application under test is a practice website that contains many common GUI components such as text fields, radio buttons, checkboxes, dropdowns, date pickers, file upload, alerts, drag and drop, sliders, mouse hover actions, a dynamic button, and a Wikipedia search widget. Your framework was designed to interact with these elements and verify that each behaves correctly. In the capstone, 18 test cases were created to cover these features, and all 18 passed successfully.

The main purpose of the project is to demonstrate end-to-end Selenium automation skills in a real framework setup. Instead of manually testing each GUI control, the framework performs browser launch, element interaction, assertion checking, screenshot capture, and report generation automatically. This makes the project a strong example of test automation engineering.

Main framework structure
The project follows the Page Object Model, or POM, which is the core design pattern used here. In POM, page-specific locators and actions are separated from the test logic, so the tests stay clean and readable. Each web page or screen gets its own Java class, and the test class calls methods from that page class instead of directly locating elements everywhere.

The main Java files in the project are:

BaseTest.java, which handles browser setup and teardown.

GuiElementsPage.java, which stores locators and page actions.

GuiElementsTest.java, which contains the test methods and assertions.

ExtentReportManager.java, which manages the HTML test report.

ScreenshotUtil.java, which captures screenshots during execution.

Framework layers
The framework is organized into layers to keep the code modular:

Test layer: Contains the actual test cases.

Page layer: Contains the UI element locators and browser actions.

Base layer: Contains browser initialization and cleanup.

Utility layer: Contains reusable support code like reporting and screenshots.

This layered structure is useful because if a locator changes, only the page class usually needs to be updated, not every test. That is one of the biggest advantages of using POM.

Technologies and frameworks used
The project uses several tools and libraries together:

Java 11 as the programming language.

Selenium WebDriver 4.18.1 for browser automation.

TestNG 7.9.0 as the test framework.

Maven 3.x for dependency management and build control.

WebDriverManager 5.7.0 for automatic browser driver handling.

ExtentReports 5.1.1 for rich HTML reporting.

Log4j2 2.23.1 for logging test execution details.

Eclipse as the IDE.

GitHub for source control and project publishing.

Selenium concepts used
The automation covers several important Selenium concepts:

WebDriver to open and control the Chrome browser.

Locators such as id, xpath, cssSelector, and tagName to find elements.

Select class for dropdown handling.

Actions class for hover, drag and drop, double click, and slider interaction.

JavascriptExecutor for scrolling and handling special cases like read-only fields.

Alert handling for accepting, dismissing, or entering text in alerts.

Implicit and explicit waits to manage synchronization with dynamic elements.

TakesScreenshot to capture evidence during test execution.

These Selenium features are what allow the framework to handle the variety of GUI controls on the demo site.

TestNG usage
TestNG is used to manage the execution flow of the automation suite. Lifecycle annotations such as @BeforeSuite, @BeforeClass, and @AfterSuite control browser start-up, page initialization, and cleanup. Test methods are marked with @Test, and assertions like assertEquals and assertTrue are used to validate expected outcomes. TestNG also helps organize the test order and makes the execution more structured.

Reporting and evidence
A major part of the project is test reporting. The framework generates an ExtentReports HTML file after execution, which shows pass/fail status, system information, log messages, and embedded screenshots. Screenshots are captured before actions, after actions, and on failure, which gives visual proof of what happened during each test. This is especially valuable for debugging and for presenting the work as a professional capstone project.

Main outcome
The final result is a scalable Selenium automation framework that successfully validates all 18 GUI scenarios on the practice site. It demonstrates not just test automation, but also framework design, reporting, screenshot evidence, bug tracking, and maintainable coding practices. The project shows that you can build a real-world style automation solution using Java, Selenium, TestNG, Maven, and supporting utilities.

If needed, the same explanation can also be rewritten in simpler spoken-style language for viva presentation or interview use.
