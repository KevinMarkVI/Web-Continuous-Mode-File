package com.yourcompany;

import com.deque.html.axecore.results.Results;
import com.deque.html.axecore.results.Rule;
import com.deque.html.axecore.selenium.AxeBuilder;
import com.deque.html.axecore.selenium.AxeReporter;
import com.deque.html.axecore.selenium.ResultType;
import com.evinced.EvincedReporter;
import com.evinced.EvincedSDK;
import com.evinced.dto.configuration.EvincedConfiguration;
import com.evinced.dto.results.Report;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import com.evinced.EvincedWebDriver;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import static org.testng.Assert.assertEquals;
public class SampleEvincedTest {

    private EvincedWebDriver driver;
    //    private ChromeDriver driver;
    private String testName;
    public static final String demoPage = "https://demo.evinced.com/";

    @BeforeClass
    private void driverSetup() {

        driver = new EvincedWebDriver(new ChromeDriver());
        EvincedSDK.getInstance().setOfflineCredentials("SERVICE_ID","TOKEN");
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.evStart();
    }

    @AfterClass
    public void tearDown() throws Exception {
        // Stop the Evinced Engine
        Report report = driver.evStop();

        // Optional assertion for gating purposes
        //List<Issue> issues = report.getIssues();
        //Assert.assertEquals(8, issues.size());

        // Output the Accessibility results in JSON or HTML
        EvincedReporter.writeEvResultsToFile(testName, report, EvincedReporter.FileFormat.HTML);
        EvincedReporter.writeEvResultsToFile(testName, report, EvincedReporter.FileFormat.JSON);
        driver.quit();
    }

    @Test()
    public void TravelTest(Method method) throws InterruptedException {
        testName = method.getName();
        driver.get(demoPage);
        WebElement firstDropdown = driver.findElement(By.cssSelector("div.filter-container"));
        firstDropdown.click();
        driver.findElement(By.cssSelector("#gatsby-focus-wrapper > main")).click();
        WebElement secondDropdown = driver.findElement(By.cssSelector("div.filter-container > div:nth-child(2)"));
        secondDropdown.click();
        driver.findElement(By.cssSelector("#gatsby-focus-wrapper > div > ul > li:nth-child(2)")).click();
        driver.findElement(By.cssSelector(".react-date-picker")).click();
        driver.findElement(By.cssSelector("#div.filter-container > a")).click();
    }

    @Test
    public void trvlHomePage() {
        driver.get(demoPage);
        // Click "Accommodation Type" dropdown
        driver.findElement(By.cssSelector("div.filter-container > div:nth-child(1)")).click();
        // Click "City" dropdown
        driver.findElement(By.cssSelector("div.filter-container > div:nth-child(2)")).click();
        // Click "When" date picker
        driver.findElement(By.cssSelector(".react-date-picker")).click();
    }

}












//    @Test
//    public void audiEvinced() throws InterruptedException {
//        driver.get("https://www.audi.de/de/brand/de/shop.html?internal_traffic_enabled=true#f=i-Fahrzeugart.v-Neuwagen&s=variants.attributes.offerDate%20desc");
//        driver.findElement(By.id("ensCancel")).click();
//EvincedConfiguration globalConfig = new EvincedConfiguration();
//    Map<String, Boolean> toggles = new HashMap<String, Boolean>(){{
//        put("USE_AXE_BEST_PRACTICES", true);
//        put("USE_AXE_NEEDS_REVIEW", true);
//    }};
//        globalConfig.setExperimentalFlags(toggles);
//        driver.setConfiguration(globalConfig);
//        Thread.sleep(20000);
//        EvincedWebDriver evDriver = new EvincedWebDriver(driver);
//        Report report = driver.evAnalyze();
        //EvincedReporter.writeEvResultsToFile("audiEvinced", report, EvincedReporter.FileFormat.HTML);

//    @Test
//    public void audiAxe() throws InterruptedException {
//        driver.get("https://www.audi.de/de/brand/de/shop.html?internal_traffic_enabled=true#f=i-Fahrzeugart.v-Neuwagen&s=variants.attributes.offerDate%20desc");
//        driver.findElement(By.id("ensCancel")).click();
//        Thread.sleep(20000);
//        AxeBuilder builder = new AxeBuilder();
//        Results result = builder.analyze(driver);
//        List<Rule> violations = result.getViolations();
//        String resultString = AxeReporter.getAxeResultString();
//        if(AxeReporter.getReadableAxeResults(ResultType.Violations.getKey(),driver,violations) ){
//            AxeReporter.writeResultsToTextFile("/Users/kevinberg/desktop/A11yReadable", AxeReporter.getAxeResultString());
//        }
//
//    }

























//mvn test -Dtest=SampleEvincedTest



//    public void axeScan() {
//        AxeBuilder builder = new AxeBuilder();
//        Results result = builder.analyze(driver);
//        List<Rule> violations = result.getViolations();
//        if (violations.size() > 0) {
//            AxeReporter.writeResultsToJsonFile("/Users/Evinced/desktop/A11y", result);
//        }
//    }
//
//    public void createReport(Results result){
//    }