package functionalTests;

import baseForTests.TestBase;
import basket.Basket;
import filters.Filters;
import filters.Size;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.*;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Epic("Тесты переноса товаров в корзину")
public class BasketTest extends TestBase {

    String testMethodName;

    @BeforeEach
    public void setUp(TestInfo testInfo) {
        mainSetUp();
        driver.navigate().to(getUrl + "catalog");
        basket = new Basket(driver);
        filters = new Filters(driver);
        size = new Size(driver);
        basket.clickToOkButton();
        this.testMethodName = testInfo.getTestMethod().get().getName();
    }

    protected void takeScreenshot(String fileName) {
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String path = System.getProperty("user.dir")
                + File.separator + "test-output"
                + File.separator + "screenshots"
                + File.separator + getTodayDate()
                + File.separator + testMethodName
                + File.separator + getSystemTime()
                + " " + fileName + ".png";
        try {
            FileUtils.copyFile(scrFile, new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String getTodayDate() {
        return (new SimpleDateFormat("yyyyMMdd").format(new Date()));
    }

    private static String getSystemTime() {
        return (new SimpleDateFormat("HHmmssSS").format(new Date()));
    }


//    @Attachment()
//    public byte[] attachScreenshotToAllure(TakesScreenshot takesScreenshot) {
//        return takesScreenshot.getScreenshotAs(OutputType.BYTES);
//    }

//    @Test
//    public void getScreenShot() throws Exception {
//        //Call take screenshot function
//        this.takeSnapShot(driver, "build\\reports\\tests\\" + Thread.currentThread().getStackTrace()[1].getMethodName() + ".jpg");
//    }


//    public void takeSnapShot(WebDriver webdriver, String fileWithPath) throws Exception {
//        //Convert web driver object to TakeScreenshot
//        TakesScreenshot scrShot = ((TakesScreenshot) webdriver);
//        //Call getScreenshotAs method to create image file
//        File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
//        //Move image file to new destination
//        File DestFile = new File(fileWithPath);
//        //Copy file at destination
//        FileUtils.copyFile(SrcFile, DestFile);
//
//    }


    /**
     * Проверяем работают ли кнопки корзины на разных типах товаров<p>
     * Обычный товар без размера
     */
    @Test
    @Description("Проверяем работают ли кнопки корзины на разных типах товаров. " +
            "Обычный товар без размера")
    @DisplayName("inBasketButton")
    public void inBasketButton() {
        basket.clickToItemButton();
        basket.clickToItemInBasketButton();
        basket.clickToGoToBasketButton();
        String number = basket.getBasketNumber();
        assertEquals("1", number);
    }

    @Test
    @Description("Проверка кнопки 'плюс', увеличение количества товаров при добавлении в корзину обычного товара без размера")
    public void plusButton() {
        basket.clickToItemButton();
        basket.clickToItemInBasketButton();
        basket.clickToGoToBasketButton();
        basket.clickToPlusBasketButton();
        String number = basket.getBasketNumber();
        assertEquals("2", number, "Ошибка увеличения количества товара при добавлении в корзину");
    }

    @Test
    @Description("Проверка кнопки 'минус', уменьшение количества товаров при добавлении в корзину обычного товара без размера")
    public void minusButton() {
        basket.clickToItemButton();
        basket.clickToItemInBasketButton();
        basket.clickToGoToBasketButton();
        basket.clickToPlusBasketButton();
        basket.clickToMinusBasketButton();
        String number = basket.getBasketNumber();
        assertEquals("1", number);
    }

    /**
     * Обычный товар с размером
     */
    @Test
    @Description("Добавление в корзину обычного товара с размером")
    public void inBasketButtonWithSize() {
        filters = new Filters(driver);
        size = new Size(driver);
        filters.clickToFilterButton();
        size.clickToUniversalSizeButton();
        filters.clickToShowProductsButton();
        basket.clickToRingButton();
        basket.clickToItemInBasketButton();
        basket.clickToGoToBasketButton();
        String number = basket.getBasketNumber();
        assertEquals("1", number);
    }

    @Test
    @Description("Проверка кнопки 'плюс', увеличение количества товаров при добавлении в корзину обычного товара с размером")
    public void plusButtonWithSize() {
        filters.clickToFilterButton();
        size.clickToUniversalSizeButton();
        filters.clickToShowProductsButton();
        basket.clickToRingButton();
        basket.clickToItemInBasketButton();
        basket.clickToGoToBasketButton();
        basket.clickToPlusBasketButton();
        String number = basket.getBasketNumber();
        assertEquals("2", number);
    }

    @Test
    @Description("Проверка кнопки 'минус', уменьшение количества товаров при добавлении в корзину обычного товара с размером")
    public void minusButtonWithSize() {
        filters.clickToFilterButton();
        size.clickToUniversalSizeButton();
        filters.clickToShowProductsButton();
        basket.clickToRingButton();
        basket.clickToItemInBasketButton();
        basket.clickToGoToBasketButton();
        basket.clickToPlusBasketButton();
        basket.clickToMinusBasketButton();
        String number = basket.getBasketNumber();
        assertEquals("1", number);
    }


    /**
     * Товар из коллекции без размера
     */
    @Test
    @Description("Добавление в корзину товара без размера из коллекции")
    public void inBasketButtonWithCollection() {
        driver.navigate().to(basket.getSecondLinkOfCollection());
        basket.clickToItemInBasketButton();
        basket.clickToGoToBasketButton();
        String number = basket.getBasketNumber();
        assertEquals("1", number);
    }

    @Test
    @Description("Проверка кнопки 'плюс', увеличение количества товаров при добавлении в корзину товара без размера из коллекции")
    public void plusButtonWithCollection() {
        driver.navigate().to(basket.getSecondLinkOfCollection());
        basket.clickToItemInBasketButton();
        basket.clickToGoToBasketButton();
        basket.clickToPlusBasketButton();
        String number = basket.getBasketNumber();
        assertEquals("2", number);
    }

    @Test
    @Description("Проверка кнопки 'минус', уменьшение количества товаров при добавлении в корзину товара без размера из коллекции")
    public void minusButtonWithCollection() {
        driver.navigate().to(basket.getSecondLinkOfCollection());
        basket.clickToItemInBasketButton();
        basket.clickToGoToBasketButton();
        basket.clickToPlusBasketButton();
        basket.clickToMinusBasketButton();
        String number = basket.getBasketNumber();
        assertEquals("1", number);
    }


    /**
     * Товар из коллекции с размером
     */
    @Test
    @Description("Добавление в корзину товара из коллекции с размером")
    public void inBasketButtonWithCollectionAndSize() {
        driver.navigate().to(basket.getFirstLinkOfCollection());
        basket.clickToItemInBasketButton();
        basket.clickToGoToBasketButton();
        String number = basket.getBasketNumber();
        assertEquals("1", number);
    }

    @Test
    @Description("Проверка кнопки 'плюс', увеличение количества товаров при добавлении в корзину товара из коллекции с размером")
    public void plusButtonWithCollectionAndSize() {
        driver.navigate().to(basket.getFirstLinkOfCollection());
        basket.clickToItemInBasketButton();
        basket.clickToGoToBasketButton();
        basket.clickToPlusBasketButton();
        String number = basket.getBasketNumber();
        assertEquals("2", number);
    }

    @Test
    @Description("Проверка кнопки 'минус', уменьшение количества товаров при добавлении в корзину товара из коллекции с размером")
    public void minusButtonWithCollectionAndSize() {
        driver.navigate().to(basket.getFirstLinkOfCollection());
        basket.clickToItemInBasketButton();
        basket.clickToGoToBasketButton();
        basket.clickToPlusBasketButton();
        basket.clickToMinusBasketButton();
        String number = basket.getBasketNumber();
        assertEquals("1", number);
    }


    /**
     * Проверка того, что нельзя положить в корзину больше товара, чем есть на остатках.
     */
    @Test
    @Description("Проверка того, что нельзя положить в корзину больше товара, чем есть на остатках")
    public void checkBalanceItem() {
//        takeScreenshot("Open catalog");
        Integer balance = basket.getBalance();
        basket.clickToItemButton();
        basket.clickToItemInBasketButton();
        basket.clickToGoToBasketButton();
        Integer dataMax = basket.getDataMax();
        assertEquals(balance, dataMax, "Неверный data-max в счётчике товаров");
        for (int i = 0; i < balance - 1; i++) {
            basket.clickToPlusBasketButton();
        }
        Integer number = Integer.valueOf(basket.getBasketNumber());
        takeScreenshot("Item In Basket");
        assertEquals(balance, number, "Неверное число в счётчике товаров");
    }

    /**
     * Кнопка "Перейти в корзину" ведет на getUrl + "cart"
     */
    @Test
    @Description("Проверка кнопки 'Перейти в корзину' со страницы заказа после добавления в корзину")
    public void checkHref() {
        basket.clickToAnotherItemButton();
        basket.clickToItemInBasketButton();
        basket.clickToBasketButton();
        String url = driver.getCurrentUrl();
        assertEquals(getUrl + "cart", url);
    }


    /**
     * Если товар уже находится в корзине, в карточке товара отображается кнопка "перейти в корзину"
     */
    @Test
    @Description("Проверка изменения кнопки 'В корзину' -> 'перейти в корзину', при добавлении товара в корзину")
    public void inBasket() {
        basket.clickToItemButton();
        basket.clickToItemInBasketButton();
        String header = basket.getGoToBasketButtonHeader();
        assertEquals("перейти в корзину", header);
    }

    /**
     * Значок корзины на всех страницах сайта отображает количество товаров в корзине<p>
     * Кладем в корзину 2 разных товара, затем переходим на страницу каталога и проверяем что в корзине отображается верное число товаров
     */
    @Test
    @Description("Кладем в корзину 2 разных товара, переходим на страницу каталога и проверяем число на иконке корзины")
    public void checkNumber() {
        basket.clickToItemButton();
        basket.clickToItemInBasketButton();
        basket.clickToBasketButton();
        basket.clickToCatalogButton();
        basket.clickToAnotherItemButton();
        basket.clickToItemInBasketButton();
        basket.clickToBasketButton();
        basket.clickToCatalogButton();
        String cartCount = basket.getCartCount();
        assertEquals("2", cartCount);
    }

    /**
     * Ссылка со значком корзины на всех страницах сайта ведет на getUrl + "cart"
     */
    @Test
    @Description("Проверка иконки корзины из 'Каталога'")
    public void checkCartHref() {
        basket.clickToCart();
        String url = driver.getCurrentUrl();
        assertEquals(getUrl + "cart/", url);
    }

    @Test
    @Description("Проверка иконки корзины из 'Новинок'")
    public void checkCartHrefFromNew() {
        basket.clickToCartFromNew();
        basket.clickToCart();
        String url = driver.getCurrentUrl();
        assertEquals(getUrl + "cart/", url);
    }

}
