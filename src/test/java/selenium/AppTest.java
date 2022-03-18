package selenium;

import static org.junit.Assert.assertEquals;
import org.junit.After;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.junit.Test;
import org.junit.Before;

public class AppTest {

    private WebDriver driver;

    @Before
    public void setUp() {
        System.out.println("Iniciando configuración...");
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver");
        driver = new ChromeDriver();
        // driver.get("https://www.amazon.com");
        // driver.manage().window().maximize();
        // System.out.println(driver.getCurrentUrl());
        // System.out.println(driver.getTitle());
        driver.get("https://www.google.com");
    }

    @Test
    public void shouldGoogleSearchDevOpsHandbookBook() {
        System.out.println("Iniciando Pruebas...");

        // busqueda libro
        WebElement searchbox = driver.findElement(By.name("q"));
        searchbox.sendKeys("Devops HandBook");
        searchbox.submit();

        // revisión de una imágen
        driver.findElement(By.linkText("Imágenes")).click();
        WebElement first_img = driver.findElement(By.cssSelector("div a div img"));
        first_img.click();

        // movimiento a inicio de pagina
        driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL, Keys.HOME);
        new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(By.linkText("Shopping")));
        // revisión opción de compra en google
        driver.findElement(By.linkText("Shopping")).click();

        assertEquals("Devops HandBook - Google Shopping", driver.getTitle());
    }

    @Test
    public void shouldFindBookThePhoenix() {
        driver.manage().window().maximize();
        driver.navigate().to("https://www.amazon.com");
        WebElement searchbox = driver.findElement(By.name("field-keywords"));
        searchbox.sendKeys("The Phoenix Project");
        searchbox.submit();
        new WebDriverWait(driver, 10).until(ExpectedConditions
                .elementToBeClickable(By.cssSelector("span.a-size-medium.a-color-base.a-text-normal:first-child")));
        WebElement bookResult = driver
                .findElement(By.cssSelector("span.a-size-medium.a-color-base.a-text-normal:first-child"));
        assertEquals(
                "The Phoenix Project: A Novel about IT, DevOps, and Helping Your Business Win 5th Anniversary Edition",
                bookResult.getText());
    }

    @After
    public void close() {
        driver.close();
    }

}
