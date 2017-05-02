
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class zz {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    baseUrl = "https://pokefind.co/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testK() throws Exception {
    driver.get(baseUrl + "/ivcalc.php");
    new Select(driver.findElement(By.id("p_id"))).selectByVisibleText("Bulbasaur");
    new Select(driver.findElement(By.id("p_id"))).selectByVisibleText("Bulbasaur");
    driver.findElement(By.id("cp")).clear();
    driver.findElement(By.id("cp")).sendKeys("613");
    driver.findElement(By.id("cp")).clear();
    driver.findElement(By.id("cp")).sendKeys("613");
    driver.findElement(By.id("hp")).clear();
    driver.findElement(By.id("hp")).sendKeys("64");
    driver.findElement(By.id("hp")).clear();
    driver.findElement(By.id("hp")).sendKeys("64");
    new Select(driver.findElement(By.id("dust"))).selectByVisibleText("4000");
    new Select(driver.findElement(By.id("dust"))).selectByVisibleText("4000");
    new Select(driver.findElement(By.id("up"))).selectByVisibleText("No");
    new Select(driver.findElement(By.id("up"))).selectByVisibleText("No");
    driver.findElement(By.id("estimate-btn")).click();
    assertEquals("26% - 55%", driver.findElement(By.id("min-max")).getAttribute("value"));
  }

  @After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}
