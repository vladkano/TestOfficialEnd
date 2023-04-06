package baseForTests;

import base.Base;
import basket.Basket;
import catalog.*;
import collectionAndSet.Collection;
import collectionAndSet.Set;
import filters.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import mainPage.MainPage;
import mainPage.MainPageBanner;
import order.Order;
import org.junit.jupiter.api.AfterEach;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import personal.PersonalData;
import productCards.Picture;
import productCards.ProductCard;
import search.Search;
import sections.*;
import tags.Tags;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class TestBase {

    protected WebDriver driver;
    protected Basket basket;
    protected Filters filters;
    protected CatalogNavigation navigation;
    protected Earrings earrings;
    protected Necklaces necklaces;
    protected Bracelets bracelets;
    protected Rings rings;
    protected Brooches brooches;
    protected Pirsing pirsing;
    protected Man man;
    protected Certificate certificate;
    protected Order order;
    protected Collection collection;
    protected Material material;
    protected ColorsAndCoverage colorsAndCoverage;
    protected Size size;
    protected DesignersFilter designersFilter;
    protected Designers designers;
    protected MainPageBanner banner;
    protected NameNecklaces nameNecklaces;
    protected Sale sale;
    protected NewItems newItems;
    protected Jewelry jewelry;
    protected Trends trends;
    protected ShopTheLook shopTheLook;
    protected Shops shops;
    protected Footer footer;
    protected Wishlist wishlist;
    protected Tags tag;
    protected MainPage mainPage;
    protected PersonalData personalData;
    protected Search search;
    protected Picture picture;
    protected Set set;
    protected ProductCard productCard;
    protected Base base;
    protected Subscription subscription;


    protected By numberOfItem = By.xpath("//p[@class='catalog-card__name']");
    protected By numberOfPictures = By.xpath("//span[@class='picture catalog-card__image lazy']");
    protected By linkOfCollection = By.xpath("//ul[@class='product-modification__variants']//a");
    protected By countOfBanners = By.xpath("//div[@class='banner index-page__banner']/a");
    protected By namesOfTrends = By.xpath("//h4");
    protected By numberOfDesigners = By.xpath("//li[@class='index-designers__name']/a");
    protected By trendBanners = By.xpath("//span[@class='picture trend-item__image']");
    protected By nameLink = By.xpath("//p[@class='catalog-card__name']/a");
    protected By showMore = By.xpath("//span[text()='Показать ещё']");
    protected By designerName = By.xpath("//p[@class='catalog-card__designer']/a");

    protected By price = By.xpath("//div[@class='price-block__main']/span[1]");
    protected String phoneForAuthorization = "9501978905";
    protected String phoneForOrder = "9126459328";
    protected String phoneForRegistration = "9956766482";
    protected String testNameForOrder = "Александр Тест";
    protected String subscriptionName = "подписаться";
    protected String subscriptionHeader = "подписчики нашей рассылки выбирают самые классные украшения.";
    protected String email = "ran.owen@rambler.ru";
    protected List<String> siteList = new ArrayList<>();
    protected List<Integer> priceList = new ArrayList<>();
    protected int siteSize = 0;
    protected final int numberOfFoto = 48;


    protected void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void waitFor(ExpectedCondition<WebElement> conditions, Integer timeOutInSeconds) {
        timeOutInSeconds = timeOutInSeconds != 0 ? timeOutInSeconds : 30;
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
        wait.until(conditions);
    }

    protected void waitForVisibilityOf(By locator, Integer... timeOutInSeconds) {
        int attempts = 0;
        while (attempts < 2) {
            try {
                waitFor(ExpectedConditions.visibilityOfElementLocated(locator),
                        (timeOutInSeconds.length > 0 ? timeOutInSeconds[0] : null));
                break;
            } catch (StaleElementReferenceException ignored) {
            }
            attempts++;
        }
    }

    /**
     * Положить в корзину товар стоимостью более 5000 рублей.
     */
    protected void putItemInBasket() {
        basket = new Basket(driver);
        basket.clickToItemButton();
        basket.clickToItemInBasketButton();
        basket.clickToBasketButton();
    }

    @AfterEach
    public void tearDownEach() {
        driver.quit();
    }

    public void mainSetUp() {
        ChromeOptions options = new ChromeOptions();
//        EdgeOptions options = new EdgeOptions();
//        FirefoxOptions options = new FirefoxOptions();
        WebDriverManager.chromedriver().setup();
//        WebDriverManager.firefoxdriver().setup();
//        WebDriverManager.edgedriver().setup();
        options.setHeadless(true);
        options.setCapability(CapabilityType.BROWSER_NAME, "chrome");
//        options.setCapability(CapabilityType.PAGE_LOAD_STRATEGY, "eager");
//        options.setCapability(CapabilityType.BROWSER_NAME, "firefox");
//        options.setCapability(CapabilityType.BROWSER_NAME, "MicrosoftEdge");
        driver = new ChromeDriver(options);
//        driver = new FirefoxDriver(options);
//        driver = new EdgeDriver(options);
        driver.manage().window().setSize(new Dimension(1920, 1080));
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }
}
