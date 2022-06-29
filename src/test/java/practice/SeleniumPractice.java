package practice;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class SeleniumPractice {

    WebDriver driver;

    @BeforeMethod
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "/Users/nacer-zimu/IdeaProjects/eat-mai22-web-automation-framework/driver/mac/chromedriver102");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }
    @AfterMethod
    public void tearDown(){
        driver.close();
    }

    public void waitFor(int seconds){
        try {
            Thread.sleep(seconds*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //@Test
    public void search(){
        driver.get("https://expertautomationteam.com/practice.html");
        driver.findElement(By.cssSelector("#myInput")).sendKeys("HTTP STATUS CODE");
        waitFor(3);
    }

    //@Test
    public void dragAndDrop(){
        driver.get("https://jqueryui.com/droppable/");
        driver.switchTo().frame(0);
        System.out.println("switch to frame success");
        Actions actions = new Actions(driver);
        WebElement draggagle = driver.findElement(By.id("draggable"));
        WebElement droppable = driver.findElement(By.id("droppable"));
        actions.dragAndDrop(draggagle, droppable).build().perform();
        waitFor(3);
    }

    //@Test
    public void selectFromDropdownList(){
        driver.get("https://expertautomationteam.com/practice.html");
        WebElement dropdown = driver.findElement(By.id("cars"));
        Select select = new Select(dropdown);
        select.selectByVisibleText("Audi");
        waitFor(3);
    }

    //@Test
    public void handleRadioBtn(){
        driver.get("https://expertautomationteam.com/practice.html");
        WebElement radioBtns = driver.findElement(By.xpath("//strong[normalize-space()='Dropdown List']"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", radioBtns);
        waitFor(3);
        List<WebElement> radioButtons = driver.findElements(By.xpath("//input[@name='fruits']"));
        for (WebElement radioButton: radioButtons) {
            if (radioButton.getAttribute("value").equalsIgnoreCase("apple")){
                radioButton.click();
                System.out.println("click success");
            }
        }
        waitFor(3);
    }

    //@Test
    public void handleCheckbox(){
        driver.get("https://expertautomationteam.com/practice.html");
        WebElement radioBtns = driver.findElement(By.xpath("//strong[normalize-space()='Dropdown List']"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", radioBtns);
        waitFor(3);
        driver.findElement(By.xpath("//input[@id='fordCheckbox']")).click();
        Assert.assertTrue(driver.findElement(By.xpath("//input[@id='fordCheckbox']")).isSelected());
        waitFor(3);
    }

    //@Test
    public void enabledDisabled(){
        driver.get("https://expertautomationteam.com/practice.html");
        WebElement radioBtns = driver.findElement(By.xpath("//strong[normalize-space()='Dropdown List']"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", radioBtns);
        waitFor(3);
        WebElement typingField = driver.findElement(By.xpath("//input[@id='enableDisableField']"));
        typingField.sendKeys("Hello Selenium");
        driver.findElement(By.xpath("//button[@id='disableField']")).click();
        boolean typingFieldEbabled = typingField.isEnabled();
        Assert.assertFalse(typingFieldEbabled);
        driver.findElement(By.xpath("//button[@id='enableField']")).click();
        typingField.clear();
        waitFor(3);
    }

    //@Test
    public void hideAndShow(){
        driver.get("https://expertautomationteam.com/practice.html");
        WebElement radioBtns = driver.findElement(By.xpath("//strong[normalize-space()='Dropdown List']"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", radioBtns);
        waitFor(3);
        WebElement typingField = driver.findElement(By.xpath("//input[@id='showHideField']"));
        typingField.sendKeys("Hello Selenium");
        driver.findElement(By.xpath("//button[normalize-space()='Show/Hide']")).click();
        boolean typingFieldEbabled = typingField.isDisplayed();
        Assert.assertFalse(typingFieldEbabled);
        driver.findElement(By.xpath("//button[normalize-space()='Show/Hide']")).click();
        typingField.clear();
        waitFor(3);
    }

    //@Test
    public void hoverOver(){
        driver.get("https://expertautomationteam.com/practice.html");
        WebElement radioBtns = driver.findElement(By.xpath("//strong[normalize-space()='Dropdown List']"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", radioBtns);
        waitFor(3);
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(By.xpath("//button[@id='mouseHoverbtn']"))).build().perform();
        waitFor(3);
    }

    //@Test
    public void handleTabs(){
        driver.get("https://expertautomationteam.com/practice.html");
        WebElement enableField = driver.findElement(By.xpath("//hr[3]"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", enableField);
        waitFor(3);
        driver.findElement(By.xpath("//button[@id='openNewTab']")).click();
        waitFor(3);
        Set<String> tabs = driver.getWindowHandles();
        Iterator<String> it = tabs.iterator();
        String currentTab = it.next();
        String childTab = it.next();
        driver.switchTo().window(childTab);
        System.out.println("switch to child tab success");
        System.out.println(driver.findElement(By.xpath("//div[@class='container-fluid']//h4")).getText());
        waitFor(3);
        driver.close();
        driver.switchTo().window(currentTab);
        System.out.println("switch to parent tab success");
        waitFor(3);
    }

    @Test
    public void handleWindows(){
        driver.get("https://expertautomationteam.com/practice.html");
        WebElement enableField = driver.findElement(By.xpath("//hr[3]"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", enableField);
        waitFor(3);
        driver.findElement(By.xpath("//button[@id='openNewWindow']")).click();
        waitFor(3);
        Set<String> tabs = driver.getWindowHandles();
        Iterator<String> it = tabs.iterator();
        String currentTab = it.next();
        String childTab = it.next();
        driver.switchTo().window(childTab);
        System.out.println("switch to child tab success");
        System.out.println(driver.findElement(By.xpath("//h2[normalize-space()='Testimonials']")).getText());
        waitFor(3);
        driver.close();
        driver.switchTo().window(currentTab);
        System.out.println("switch to parent tab success");
        waitFor(3);
    }

    @Test
    public void fileUplaod() {
        driver.get("http://demo.automationtesting.in/Register.html");

        driver.findElement(By.id("imagesrc")).click();

        Robot robot = null;
        try {
            robot = new Robot();
            robot.setAutoDelay(2000);
            StringSelection stringSelection = new StringSelection("‎Macintosh HD/Users/nacer-zimu/Dropbox/file1.xls");
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null); //Equivalent to CRL+C

            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_V);

            robot.keyRelease(KeyEvent.VK_CONTROL);
            robot.keyRelease(KeyEvent.VK_V);

            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
        } catch (AWTException e) {
            e.printStackTrace();
        }

    }

}
