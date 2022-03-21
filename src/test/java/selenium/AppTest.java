package selenium;

import static org.junit.Assert.assertEquals;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.junit.Test;
import org.junit.After;
import org.junit.Before;

public class AppTest {

    private WebDriver driver;

    @Before
    public void setUp() {
        System.out.println("Iniciando configuración...");
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver");
        driver = new ChromeDriver();
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

    @Test
    public void shouldGenerarUnaCompra() {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        driver.manage().window().maximize();
        driver.navigate().to("http://automationpractice.com/index.php");
        wait.until(ExpectedConditions.elementToBeClickable(By.id("search_query_top")));
        WebElement searchbox = driver.findElement(By.id("search_query_top"));
        searchbox.sendKeys("blouse");
        searchbox.submit();
        By element = By.cssSelector("a.product_img_link");
        wait.until(ExpectedConditions.presenceOfElementLocated(element));
        WebElement itemElement = driver.findElement(element);
        ((JavascriptExecutor) driver).executeScript("javascript:window.scrollBy(250,350)");
        wait.until(ExpectedConditions.elementToBeClickable(element));
        // mueve mouse sobre prenda
        Actions actionProvider = new Actions(driver);
        actionProvider.moveToElement(itemElement).build().perform();
        // entra a pagina prenda
        driver.findElement(By.linkText("More")).click();

        // agregamos a la carta
        By button = By.cssSelector("#add_to_cart button");
        wait.until(ExpectedConditions.elementToBeClickable(button));
        WebElement buttonElement = driver.findElement(button);
        buttonElement.click();

        By proceed = By.linkText("Proceed to checkout");
        wait.until(ExpectedConditions.elementToBeClickable(proceed));
        WebElement proceedElement = driver.findElement(proceed);
        proceedElement.click();

        assertEquals("$27.00", driver.findElement(By.id("total_product")).getText());
        assertEquals("$2.00", driver.findElement(By.id("total_shipping")).getText());
        assertEquals("$29.00", driver.findElement(By.id("total_price")).getText());

        driver.findElement(By.cssSelector("body")).sendKeys(Keys.PAGE_DOWN);
        wait.until(ExpectedConditions.elementToBeClickable(proceed));
        WebElement procToCheckout = driver.findElement(proceed);
        procToCheckout.click();
        String randomEmail = UUID.randomUUID().toString();
        String emailToCreate = randomEmail + "+YcdUC4rGbuarhGsy@examle.com";
        By inputEmail = By.id("email_create");
        By buttonCreateAccount = By.id("SubmitCreate");
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        wait.until(ExpectedConditions.elementToBeClickable(inputEmail));
        driver.findElement(inputEmail).sendKeys(emailToCreate);
        driver.findElement(buttonCreateAccount).click();

        By emailFormField = By.id("email");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("account-creation_form")));
        assertEquals(emailToCreate, driver.findElement(emailFormField).getAttribute("value"));

        driver.findElement(By.id("id_gender1")).click();
        driver.findElement(By.id("customer_firstname")).sendKeys("juanito");
        driver.findElement(By.id("customer_lastname")).sendKeys("perez");
        driver.findElement(By.id("passwd")).sendKeys("juanitopassword");
        driver.findElement(By.id("days")).sendKeys(Keys.DOWN, Keys.DOWN);
        driver.findElement(By.id("months")).sendKeys(Keys.DOWN, Keys.DOWN);
        driver.findElement(By.id("years")).sendKeys(Keys.DOWN, Keys.DOWN);
        driver.findElement(By.id("address1")).sendKeys("avenida falsa 1234");
        driver.findElement(By.id("city")).sendKeys("valpo");
        driver.findElement(By.cssSelector("body")).sendKeys(Keys.DOWN, Keys.DOWN);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.id("id_state")).sendKeys(Keys.DOWN, Keys.DOWN);
        driver.findElement(By.id("postcode")).sendKeys("34500");
        driver.findElement(By.id("phone_mobile")).sendKeys("56912345678");
        driver.findElement(By.id("submitAccount")).click();

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.findElement(By.cssSelector("body")).sendKeys(Keys.DOWN, Keys.DOWN, Keys.DOWN, Keys.DOWN, Keys.DOWN);
        assertEquals("juanito perez",
                driver.findElement(By.cssSelector(".address_firstname.address_lastname")).getText());
        assertEquals("avenida falsa 1234",
                driver.findElement(By.cssSelector(".address_address1.address_address2")).getText());
        assertEquals("valpo, Alaska 34500",
                driver.findElement(By.cssSelector(".address_city.address_state_name.address_postcode")).getText());
        assertEquals("United States", driver.findElement(By.cssSelector(".address_country_name")).getText());
        assertEquals("56912345678", driver.findElement(By.cssSelector(".address_phone_mobile")).getText());

        driver.findElement(By.cssSelector("body")).sendKeys(Keys.DOWN, Keys.DOWN, Keys.DOWN, Keys.DOWN, Keys.DOWN);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("button[name='processAddress']")));
        driver.findElement(By.cssSelector("button[name='processAddress']")).click();

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("cgv")));
        driver.findElement(By.id("cgv")).click();

        driver.findElement(By.cssSelector("body")).sendKeys(Keys.DOWN, Keys.DOWN, Keys.DOWN, Keys.DOWN, Keys.DOWN);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("button[name='processCarrier']")));
        driver.findElement(By.cssSelector("button[name='processCarrier']")).click();

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        assertEquals("$27.00", driver.findElement(By.id("total_product")).getText());
        assertEquals("$2.00", driver.findElement(By.id("total_shipping")).getText());
        assertEquals("$29.00", driver.findElement(By.id("total_price")).getText());
        driver.findElement(By.cssSelector("body")).sendKeys(Keys.DOWN, Keys.DOWN, Keys.DOWN, Keys.DOWN, Keys.DOWN);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("a[title='Pay by bank wire']")));
        driver.findElement(By.cssSelector("a[title='Pay by bank wire']")).click();

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        By confirm = By.id("module-bankwire-payment");
        wait.until(ExpectedConditions.presenceOfElementLocated(confirm));
        driver.findElement(By.cssSelector("button[type=submit]")).click();
    }

    @After
    public void close() {
        driver.close();
    }

}
