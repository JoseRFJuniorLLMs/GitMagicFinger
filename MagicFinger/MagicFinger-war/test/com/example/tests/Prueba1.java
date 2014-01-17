package com.example.tests;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class Prueba1 {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    baseUrl = "http://localhost:8080/";
    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
  }

  @Test
  public void testPrueba1() throws Exception {
    driver.get(baseUrl + "/MagicFinger-war/");
    driver.findElement(By.id("j_idt10:j_idt12")).clear();
    driver.findElement(By.id("j_idt10:j_idt12")).sendKeys("admin");
    driver.findElement(By.id("j_idt10:j_idt14")).sendKeys("admin");
    driver.findElement(By.id("j_idt10:j_idt15")).click();
    driver.findElement(By.cssSelector("a > img")).click();
    driver.findElement(By.linkText("Agregar universidad")).click();
    driver.findElement(By.id("form:nombre")).click();
    driver.findElement(By.id("form:nombre")).sendKeys("TestUniversi");
    driver.findElement(By.id("form:rut")).sendKeys("1-9");
    driver.findElement(By.id("form:direccion")).sendKeys("asdfasdf");
    driver.findElement(By.id("form:telefono")).sendKeys("12312312");
    driver.findElement(By.linkText("Guardar universidad")).click();
    try {
      assertTrue(isElementPresent(By.xpath("//tbody[@id='j_idt22:j_idt46_data']/tr/td")));
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    driver.findElement(By.id("j_idt16")).click();
    driver.findElement(By.id("j_idt18")).click();
  }
caca man
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
