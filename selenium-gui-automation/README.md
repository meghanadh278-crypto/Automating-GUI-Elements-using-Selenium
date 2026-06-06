# Selenium GUI Automation
### Target: https://testautomationpractice.blogspot.com/

---

## Project Structure

```
selenium-gui-automation/
│
├── pom.xml                                          ← Maven build + all dependencies
│
├── screenshots/                                     ← PNG screenshots (auto-created at runtime)
│   └── TC01_name_before_20240615_143022.png  ...
│
├── test-output/
│   └── ExtentReport.html                           ← Rich HTML report with embedded screenshots
│
└── src/
    └── test/
        ├── resources/
        │   ├── testng.xml                           ← TestNG suite definition
        │   └── log4j2.xml                           ← Logging config
        └── java/com/automation/
            ├── tests/
            │   ├── BaseTest.java                    ← @BeforeSuite / @AfterSuite (WebDriver + report)
            │   └── GuiElementsTest.java             ← 16 @Test methods with screenshots
            ├── pages/
            │   └── GuiElementsPage.java             ← Page Object Model (all locators & actions)
            └── utils/
                ├── ScreenshotUtil.java              ← Captures & saves PNG screenshots
                └── ExtentReportManager.java         ← Singleton HTML report manager
```

---

## Prerequisites

| Tool      | Version |
|-----------|---------|
| Java      | 11 +    |
| Maven     | 3.6 +   |
| Chrome    | Latest  |

> **No manual ChromeDriver download needed.**  
> `WebDriverManager` auto-downloads the matching driver at runtime.

---

## How to Run

```bash
# Clone / unzip the project, then:
cd selenium-gui-automation

# Run all 16 tests
mvn test
```

To run headless (no visible browser), uncomment this line in `BaseTest.java`:
```java
// options.addArguments("--headless=new");
```

---

## What Happens During a Test Run

For **every single test step**, the framework:

1. **Captures a BEFORE screenshot** – shows the page state before the action.
2. **Performs the action** – enters text, clicks, selects, drags, etc.
3. **Captures an AFTER screenshot** – shows the result state.
4. **Asserts** the expected outcome with `Assert.assertEquals` / `Assert.assertTrue`.
5. **Logs PASS / FAIL** with both screenshots embedded in the HTML report.

---

## Tests Covered (16 total)

| # | Test ID | Element | Action |
|---|---------|---------|--------|
| 1  | TC01 | Name field        | `sendKeys` |
| 2  | TC02 | Email field       | `sendKeys` |
| 3  | TC03 | Phone field       | `sendKeys` |
| 4  | TC04 | Address textarea  | `sendKeys` |
| 5  | TC05 | Gender radio      | `click` if not selected |
| 6  | TC06 | Days checkboxes   | Check Mon / Wed / Fri |
| 7  | TC07 | Dropdowns         | Country → India, Color → Blue, Animal → Dog |
| 8  | TC08 | Date pickers      | jQuery UI DP1 + DP2 |
| 9  | TC09 | File upload       | `sendKeys` absolute path |
| 10 | TC10 | Slider            | Arrow keys × 15 |
| 11 | TC11 | Simple alert      | `alert.accept()` |
| 12 | TC12 | Confirm alert     | `alert.accept()` |
| 13 | TC13 | Prompt alert      | `alert.sendKeys()` + accept |
| 14 | TC14 | Mouse hover       | Hover → click 'Mobiles' |
| 15 | TC15 | Double click      | Copy Field1 → Field2 |
| 16 | TC16 | Drag and drop     | `dragAndDrop(source, target)` |

---

## Output After Run

| Output | Location |
|--------|----------|
| PNG screenshots | `screenshots/` folder (before + after per step) |
| HTML report     | `test-output/ExtentReport.html` |
| Console log     | Terminal (TestNG verbose=2) |

Open `test-output/ExtentReport.html` in any browser to see the full report with all embedded screenshots.
