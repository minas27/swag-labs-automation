package autotests;

import business.pages.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import static business.Data.CommonData.BASE_URL;

public class BaseTest {
    private static ChromeDriver chromeDriver;

    protected LoginPage loginPage;

    protected InventoryPage inventoryPage;

    protected ItemPage itemPage;

    protected CartPage cartPage;

    protected YourInformationPage yourInformationPage;

    protected LeftMenuComponent leftMenuComponent;

    protected CheckoutOverviewPage checkoutOverviewPage;

    protected AboutPage aboutPage;

    public BaseTest(){
        WebDriverManager.chromedriver().setup();
        chromeDriver = new ChromeDriver();
        loginPage = new LoginPage(chromeDriver);
        inventoryPage = new InventoryPage(chromeDriver);
        itemPage = new ItemPage(chromeDriver);
        cartPage = new CartPage(chromeDriver);
        yourInformationPage = new YourInformationPage(chromeDriver);
        leftMenuComponent = new LeftMenuComponent(chromeDriver);
        aboutPage = new AboutPage(chromeDriver);
        checkoutOverviewPage = new CheckoutOverviewPage(chromeDriver);
    }

    public static ChromeDriver getDriver(){
        return chromeDriver;
    }

    @BeforeMethod
    public void open(){
        chromeDriver.manage().window().maximize();
        chromeDriver.get(BASE_URL);
        loginPage.isOnLoginPage();
    }

    @AfterSuite(alwaysRun = true)
    public void close(){
        chromeDriver.close();
    }
}
