import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class TestingTask {
    public WebDriver driver;
    private int clickCount = 2;
    private String deleteButtonClassName = "added-manually";

    @BeforeTest
    public void setUp()
    {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.MILLISECONDS);
        driver.navigate().to("http://the-internet.herokuapp.com/add_remove_elements/");
    }

    public void doubleClickOnAddButton(){
        Actions actions = new Actions(driver);
        WebElement el = driver.findElement(By.xpath("//button[contains(text(),'Add Element')]"));
        for (int i = 0; i < clickCount; i++){
            actions.click(el).build().perform();
        }
    }

    public void countDeleteButtons(int expectedCount) {
        List<WebElement> buttons = driver.findElements(By.className(deleteButtonClassName));
        int counter = 0;
        for (WebElement button : buttons) {
            String text = button.getText();
            if (text.equals("Delete")) {
                counter++;
            }
        }

        Assert.assertEquals(expectedCount, counter, "Invalid buttons count" );
        System.out.println("Buttons added: " + counter);
    }

    public void clickDeleteButtons() {
        Actions actions = new Actions(driver);
        List<WebElement> delButton = driver.findElements(By.className(deleteButtonClassName));
        for (WebElement button : delButton)
        {
            if(button.isDisplayed()){
                actions.click(button).build().perform();
            }
        }
    }

    @Test
    public void runTest()
    {
        doubleClickOnAddButton();
        countDeleteButtons(clickCount);
        clickDeleteButtons();
        countDeleteButtons(0);
    }

    @AfterTest(alwaysRun = true)
    public void tearDown() throws InterruptedException {
        Thread.sleep(3000);
        driver.quit();
    }
}


