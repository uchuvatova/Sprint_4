import PageObject.AboutRentScooter;
import PageObject.ForWhomScooter;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.junit.After;
import org.junit.Test;

public class TestScooter {
    private WebDriver driver;
    @Before
    public void begin() {
        ChromeOptions options = new ChromeOptions(); // создаю драйвер для браузера
        driver = new ChromeDriver(options);
        driver.get("https://qa-scooter.praktikum-services.ru/"); // перехожу на страницу тестового приложения
        HomePageScooter objHomePage = new HomePageScooter(driver); // создаю объект класса главной страницы приложения
        objHomePage.waitForLoadHomePage(); // загрузка главной страницы
        driver.manage().window().maximize(); //разворачиваю окно
        driver.findElement(By.id("rcc-confirm-button")).click(); //принимаю куки
    }

    @Test
    public void orderWithCorrectValuesSuccessed() {

        driver.findElement(By.className("Button_Button__ra12g")).click();

        // создаю объект класса страницы "Для кого самокат"
        ForWhomScooter objForWhomPage = new ForWhomScooter(driver);
        // дождись загрузки страницы "Для кого самокат"
        objForWhomPage.waitForLoadForWhomPage();

        objForWhomPage.clickMetroButton();
        driver.findElement(By.xpath("//div/ul/li[5]")).click();
        objForWhomPage.forWhom("Ирина",
                "Учуватова", "Романтиков 1", "89020000000");
        // создаю объект класса страницы "Про аренду"
        AboutRentScooter objAboutRentPage = new AboutRentScooter(driver);
        // загрузка страницы "Про аренду"
        objAboutRentPage.waitForLoadAboutRentScooter();
        objAboutRentPage.clickDateField();
        driver.findElement(By.xpath("//div[@aria-label='Choose среда, 10-е августа 2022 г.']")).click();
        objAboutRentPage.clickPeriodField();
        driver.findElement(By.xpath("//div[text()='сутки']")).click();
        objAboutRentPage.aboutRentBlack();
        driver.findElement(By.xpath(".//div[contains(text(),'Заказ оформлен')]"));

    }
    @Test
    public void orderWithIncorrectValuesFails() {
        driver.findElement(By.className("Button_Button__ra12g")).click();

        // создаю объект класса страницы "Для кого самокат"
        ForWhomScooter objForWhomPage = new ForWhomScooter(driver);
        // загрузка страницы "Для кого самокат"
        objForWhomPage.waitForLoadForWhomPage();

        objForWhomPage.clickMetroButton();
        driver.findElement(By.xpath("//div/ul/li[9]")).click();
        objForWhomPage.forWhom("Мария",
                "Ли", "Нижний Новгород", "00000000000");


        // создаю объект класса страницы "Про аренду"
        AboutRentScooter objAboutRentPage = new AboutRentScooter(driver);
        // загрузка страницы "Про аренду"
        objAboutRentPage.waitForLoadAboutRentScooter();
        objAboutRentPage.clickDateField();
        driver.findElement(By.xpath("//div[@aria-label='Choose суббота, 6-е августа 2022 г.']")).click();
        objAboutRentPage.clickPeriodField();
        driver.findElement(By.xpath("//div[text()='двое суток']")).click();
        objAboutRentPage.aboutRentGrey();
        driver.findElement(By.xpath(".//div[contains(text(),'Заказ оформлен')]"));

    }
    @After
    public void teardown() {
        // Закрой браузер
        driver.quit();
    }
}