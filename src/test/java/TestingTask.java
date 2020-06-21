import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class TestingTask {
    public WebDriver driver;

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
        actions.doubleClick(el).build().perform();
    }

    public void countButtons() {
        List<WebElement> buttons = driver.findElements(By.className("added-manually"));
        int counter = 0;
        for (WebElement button : buttons) {
            String text = button.getText();
            if (text.equals("Delete")) {
                counter++;
            }
        }

        System.out.println("Buttons added: " + counter);
    }

    public void checkDeleteButtons() {
        Actions actions = new Actions(driver);
        List<WebElement> delButton = driver.findElements(By.className("added-manually"));
        for (WebElement button : delButton)
        {
            if(button.isDisplayed()){
                actions.click(button).build().perform();
            }
        }
        System.out.println("Buttons removed");
    }

    @Test
    public void runTest()
    {
        doubleClickOnAddButton();
        countButtons();
        checkDeleteButtons();

    }

    @AfterTest(alwaysRun = true)
    public void tearDown() throws InterruptedException {
        Thread.sleep(3000);
        driver.quit();
    }
}


