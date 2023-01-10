package functionalTests;

import baseForTests.TestBase;
import catalog.*;
import collectionAndSet.Collection;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Epic("Тесты товаров входящих в коллекции")
public class CollectionTest extends TestBase {

    @BeforeEach
    public void setUp() {
        mainSetUp();
        collection = new Collection(driver);
        navigation = new CatalogNavigation(driver);
        bracelets = new Bracelets(driver);
        rings = new Rings(driver);
        brooches = new Brooches(driver);
        earrings = new Earrings(driver);
        necklaces = new Necklaces(driver);
    }

    /**
     * Вспомогательный метод для тестов:<p>
     * Проверка на дублирование ссылок
     */
    public void checkDouble() {
        List<WebElement> listOfLinks = driver.findElements(linkOfCollection);
        HashSet<WebElement> set = new HashSet<>(listOfLinks);
        assertEquals(set.size(), listOfLinks.size());
    }

    /**
     * Проверяем первый товар, который входит в коллекцию: БД и сайт
     */
    @Test
    @Description("Проверяем первый товар, который входит в коллекцию: БД и сайт")
    public void countOfCollectionItems() {
        driver.get(getUrl + "catalog/");
        List<String> namesItems = collection.getNamesItems();
        List<WebElement> site = driver.findElements(By.xpath("//li[@class='product-variant']/ancestor::div[@class='catalog-card__description']/p[@class='catalog-card__name']"));
        assertEquals(namesItems.get(0).substring(0,20), site.get(0).getAttribute("textContent").substring(0,20));
    }

    /**
     * Проверка правильности формирования ссылок и их работоспособность:<p>
     * Каталог(все изделия)
     */
    @Test
    @Description("Проверка правильности формирования ссылок и их работоспособность: Каталог(все изделия)")
    public void firstLinkOfItems() {
        driver.get(getUrl + "catalog/");
        String href = collection.getHref();
        collection.clickOnFirstHref();
        String url = driver.getCurrentUrl();
        assertEquals(url, href);
    }

    /**
     * Каталог(Браслеты)
     */
    @Test
    @Description("Проверка правильности формирования ссылок и их работоспособность: Каталог(Браслеты)")
    public void firstLinkOfBracelets() {
        driver.get(getUrl + "catalog/braslety");
        for (int i = 0; i < 3; i++){
            navigation.clickOnShowMoreButton();
        }
        String href = collection.getHref();
        collection.clickOnFirstHref();
        String url = driver.getCurrentUrl();
        assertEquals(url, href);
    }

    /**
     * Каталог(Броши)
     */
    @Test
    @Description("Проверка правильности формирования ссылок и их работоспособность: Каталог(Броши)")
    public void firstLinkOfBrooches() {
        driver.get(getUrl + "catalog/broshi");
        String href = collection.getHref();
        collection.clickOnFirstHref();
        String url = driver.getCurrentUrl();
        assertEquals(url, href);
    }

    /**
     * Каталог(Серьги)
     */
    @Test
    @Description("Проверка правильности формирования ссылок и их работоспособность: Каталог(Серьги)")
    public void firstLinkOfEarrings() {
        driver.get(getUrl + "catalog/sergi");
        String href = collection.getHref();
        collection.clickOnFirstHref();
        String url = driver.getCurrentUrl();
        assertEquals(url, href);
    }

    /**
     * Каталог(Колье)
     */
    @Test
    @Description("Проверка правильности формирования ссылок и их работоспособность: Каталог(Колье)")
    public void firstLinkOfNecklaces() {
        driver.get(getUrl + "catalog/kole");
        for (int i = 0; i < 3; i++){
            navigation.clickOnShowMoreButton();
        }
        String href = collection.getHref();
        collection.clickOnFirstHref();
        String url = driver.getCurrentUrl();
        assertEquals(url, href);
    }

    /**
     * Каталог(Кольца)
     */
    @Test
    @Description("Проверка правильности формирования ссылок и их работоспособность: Каталог(Кольца)")
    public void firstLinkOfRings() {
        driver.get(getUrl + "catalog/koltsa");
        for (int i = 0; i < 2; i++){
            navigation.clickOnShowMoreButton();
        }
        String href = collection.getHref();
        collection.clickOnFirstHref();
        String url = driver.getCurrentUrl();
        assertEquals(url, href);
    }

    /**
     * Проверка, что под товаром ссылки на другие товары коллекции не дублируются:<p>
     * Каталог(все изделия)
     */
    @Test
    @Description("Проверка, что под товаром ссылки на другие товары коллекции не дублируются: Каталог(все изделия)")
    public void checkDoubleMain() {
        driver.get(getUrl + "catalog/");
        checkDouble();
    }

    /**
     * Каталог(Браслеты)
     */
    @Test
    @Description("Проверка, что под товаром ссылки на другие товары коллекции не дублируются: Каталог(Браслеты)")
    public void checkDoubleBracelets() {
        driver.get(getUrl + "catalog/braslety");
        checkDouble();
    }

    /**
     * Каталог(Броши)
     */
    @Test
    @Description("Проверка, что под товаром ссылки на другие товары коллекции не дублируются: Каталог(Броши)")
    public void checkDoubleBrooches() {
        driver.get(getUrl + "catalog/broshi");
        checkDouble();
    }

    /**
     * Каталог(Серьги)
     */
    @Test
    @Description("Проверка, что под товаром ссылки на другие товары коллекции не дублируются: Каталог(Серьги)")
    public void checkDoubleEarrings() {
        driver.get(getUrl + "catalog/sergi");
        checkDouble();
    }

    /**
     * Каталог(Колье)
     */
    @Test
    @Description("Проверка, что под товаром ссылки на другие товары коллекции не дублируются: Каталог(Колье)")
    public void checkDoubleNecklaces() {
        driver.get(getUrl + "catalog/kole");
        checkDouble();
    }

    /**
     * Каталог(Кольца)
     */
    @Test
    @Description("Проверка, что под товаром ссылки на другие товары коллекции не дублируются: Каталог(Кольца)")
    public void checkDoubleRings() {
        driver.get(getUrl + "catalog/koltsa");
        checkDouble();
    }

}
