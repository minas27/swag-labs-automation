package autotests;

import business.factory.DriverManager;
import business.pages.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import static business.data.CommonData.BASE_URL;
import static business.data.CommonData.BASE_URL;

public class BaseTest {
    private WebDriver driver;

    protected LoginPage loginPage;

    protected InventoryPage inventoryPage;

    protected ItemPage itemPage;

    protected CartPage cartPage;

    protected YourInformationPage yourInformationPage;

    protected LeftMenuComponent leftMenuComponent;

    protected CheckoutOverviewPage checkoutOverviewPage;

    protected AboutPage aboutPage;

    public WebDriver getDriver(){
        return driver;
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    @Parameters("browser")
    @BeforeMethod
    public void startDriver(@Optional String browser){
        browser = System.getProperty("browser", browser);
        setDriver(new DriverManager().initializeDriver(browser));
        getDriver().get(BASE_URL);
        loginPage = new LoginPage(getDriver());
        inventoryPage = new InventoryPage(getDriver());
        itemPage = new ItemPage(getDriver());
        cartPage = new CartPage(getDriver());
        yourInformationPage = new YourInformationPage(getDriver());
        leftMenuComponent = new LeftMenuComponent(getDriver());
        aboutPage = new AboutPage(getDriver());
        checkoutOverviewPage = new CheckoutOverviewPage(getDriver());
        System.out.println("CURRENT THREAD: " + Thread.currentThread().threadId() + ", " + "DRIVER = " + getDriver());
    }

    @AfterSuite(alwaysRun = true)
    public void close(){
        getDriver().quit();
        System.out.println("CURRENT THREAD: " + Thread.currentThread().threadId() + ", " + "DRIVER = " + getDriver());
    }
}
