package functionalTests;

import baseForTests.TestBase;
import catalog.*;
import config.TestConfig;
import filters.Filters;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;
import sections.Man;

import java.util.List;

import static java.lang.Integer.parseInt;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Epic("Тесты каталога")
public class CatalogTest extends TestBase {

    @BeforeEach
    public void setUp() {
        mainSetUp();
        filters = new Filters(driver);
        bracelets = new Bracelets(driver);
        earrings = new Earrings(driver);
        necklaces = new Necklaces(driver);
        rings = new Rings(driver);
        brooches = new Brooches(driver);
        pirsing = new Pirsing(driver);
        man = new Man(driver);
    }

    /**
     * Вспомогательные методы для тестов:<p>
     * Получаем названия дизайнера с сайта
     */
    public void getDesignerNamesFromSite() {
        List<WebElement> elements = driver.findElements(designerName);
        for (WebElement text : elements) {
            String s = text.getText();
            siteList.add(s);
        }
    }

    /**
     * Получаем количество картинок с сайта
     */
    public void getPicturesFromSite() {
        List<WebElement> elements = driver.findElements(numberOfPictures);
        for (WebElement text : elements) {
            String s = text.getText();
            siteList.add(s);
            siteSize = siteList.size();
        }
        assertEquals(numberOfFoto, siteSize);
    }

    /**
     * Получаем цены изделий с сайта
     */
    public void getPricesFromSite() {
        List<WebElement> elements = driver.findElements(price);
        for (WebElement text : elements) {
            String s = text.getText();
            String replace = s.replace(" ", "");
            String result = replace.replaceAll("[^A-Za-z0-9]", "");
            Integer price = parseInt(result);
            priceList.add(price);
        }
    }


    /**
     * Сравниваем количество наименований в базе и каталоге(размеры и содержание списков):<p>
     * Браслеты(проверка по наименованию дизайнера)
     */
    @Test
    @Description("Браслеты(проверка по наименованию дизайнера)")
    public void braceletDesigners() {
        driver.get(TestConfig.SITE_URL + "catalog/braslety");
        String countHeader = filters.getCountHeader();
        Integer numberOnly = Integer.valueOf(countHeader.replaceAll("[^0-9]", ""));
        List<String> sqlList = bracelets.getDesigners();
        int sqlSize = sqlList.size();
        getDesignerNamesFromSite();
        Assertions.assertAll(
                () -> assertEquals(sqlSize, numberOnly),
                () -> assertEquals(sqlList.subList(0, 47), siteList.subList(0, 47)));
    }

    /**
     * Серьги(проверка по наименованию дизайнера)
     */
    @Test
    @Description("Серьги(проверка по наименованию дизайнера)")
    public void earringDesigners() {
        driver.get(TestConfig.SITE_URL + "catalog/sergi");
        String countHeader = filters.getCountHeader();
        Integer numberOnly = Integer.valueOf(countHeader.replaceAll("[^0-9]", ""));
        List<String> sqlList = earrings.getDesigners();
        int sqlSize = sqlList.size();
        getDesignerNamesFromSite();
        Assertions.assertAll(
                () -> assertEquals(sqlSize, numberOnly),
                () -> assertEquals(sqlList.subList(0, 47), siteList.subList(0, 47)));

    }

    /**
     * Колье(проверка по наименованию дизайнера)
     */
    @Test
    @Description("Колье(проверка по наименованию дизайнера)")
    public void necklacesDesigners() {
        driver.get(TestConfig.SITE_URL + "catalog/kole");
        String countHeader = filters.getCountHeader();
        int numberOnly = Integer.parseInt(countHeader.replaceAll("[^0-9]", ""));
        List<String> sqlList = necklaces.getDesigners();
        int sqlSize = sqlList.size();
        getDesignerNamesFromSite();
        Assertions.assertAll(
                () -> assertEquals(sqlSize, numberOnly),
                () -> assertEquals(sqlList.subList(0, 47), siteList.subList(0, 47)));
    }

    /**
     * Кольца(проверка по наименованию дизайнера)
     */
    @Test
    @Description("Кольца(проверка по наименованию дизайнера)")
    public void ringDesigners() {
        driver.get(TestConfig.SITE_URL + "catalog/koltsa");
        String countHeader = filters.getCountHeader();
        Integer numberOnly = Integer.valueOf(countHeader.replaceAll("[^0-9]", ""));
        List<String> sqlList = rings.getDesigners();
        int sqlSize = sqlList.size();
        getDesignerNamesFromSite();
        Assertions.assertAll(
                () -> assertEquals(sqlSize, numberOnly),
                () -> assertEquals(sqlList.subList(0, 47), siteList.subList(0, 47)));
    }

    /**
     * Пирсинг(проверка по наименованию дизайнера)
     */
    @Test
    @Description("Пирсинг(проверка по наименованию дизайнера)")
    public void piercingDesigners() {
        driver.get(TestConfig.SITE_URL + "catalog/pirsing");
        String countHeader = filters.getCountHeader();
        Integer numberOnly = Integer.valueOf(countHeader.replaceAll("[^0-9]", ""));
        List<String> sqlList = pirsing.getDesigners();
        int sqlSize = sqlList.size();
        getDesignerNamesFromSite();
        Assertions.assertAll(
                () -> assertEquals(sqlSize, numberOnly),
                () -> assertEquals(sqlList.subList(0, sqlList.size()), siteList.subList(0, siteList.size())));
    }

    /**
     * Раздел для мужчин(проверка по наименованию дизайнера)
     */
    @Test
    @Description("Раздел для мужчин(проверка по наименованию дизайнера)")
    public void menItemDesigners() {
        driver.get(TestConfig.SITE_URL + "catalog/men");
        String countHeader = filters.getCountHeader();
        Integer numberOnly = Integer.valueOf(countHeader.replaceAll("[^0-9]", ""));
        List<String> sqlList = man.getDesigners();
        int sqlSize = sqlList.size();
        getDesignerNamesFromSite();
        Assertions.assertAll(
                () -> assertEquals(sqlSize, numberOnly),
                () -> assertEquals(sqlList.subList(0, 47), siteList.subList(0, 47)));
    }

    /**
     * Сравниваем количество наименований в базе и каталоге(размеры и содержание списков):<p>
     * Браслеты(проверка по наименованию изделия)
     */
    @Test
    @Description("Браслеты(проверка по наименованию изделия)")
    public void braceletNames() {
        driver.get(TestConfig.SITE_URL + "catalog/braslety");
        List<String> sqlList = bracelets.getNames();
        List<WebElement> elements = driver.findElements(numberOfItem);
        for (WebElement text : elements) {
            String s = text.getText();
            siteList.add(s.substring(0, 6));
        }
        assertEquals(sqlList.subList(0, 47), siteList.subList(0, 47));
    }

    /**
     * Серьги(проверка по наименованию изделия)
     */
    @Test
    @Description("Серьги(проверка по наименованию изделия)")
    public void earringNames() {
        driver.get(TestConfig.SITE_URL + "catalog/sergi");
        List<String> sqlList = earrings.getNames();
        List<WebElement> elements = driver.findElements(numberOfItem);
        for (WebElement text : elements) {
            String s = text.getText();
            siteList.add(s.substring(0, 6));
        }
        assertEquals(sqlList.subList(0, 47), siteList.subList(0, 47));
    }

    /**
     * Колье(проверка по наименованию изделия)
     */
    @Test
    @Description("Колье(проверка по наименованию изделия)")
    public void necklaceNames() {
        driver.get(TestConfig.SITE_URL + "catalog/kole");
        List<String> sqlList = necklaces.getNames();
        List<WebElement> elements = driver.findElements(numberOfItem);
        for (WebElement text : elements) {
            String s = text.getText();
            siteList.add(s.substring(0, 8));
        }
        assertEquals(sqlList.subList(0, 48), siteList.subList(0, 48));
    }

    /**
     * Кольца(проверка по наименованию изделия)
     */
    @Test
    @Description("Кольца(проверка по наименованию изделия)")
    public void ringNames() {
        driver.get(TestConfig.SITE_URL + "catalog/koltsa");
        List<String> sqlList = rings.getNames();
        List<WebElement> elements = driver.findElements(numberOfItem);
        for (WebElement text : elements) {
            String s = text.getText();
            siteList.add(s.substring(0, 9));
        }
        assertEquals(sqlList.subList(0, 47), siteList.subList(0, 47));
    }

    /**
     * Пирсинг(проверка по наименованию изделия)
     */
    @Test
    @Description("Пирсинг(проверка по наименованию изделия)")
    public void piercingNames() {
        driver.get(TestConfig.SITE_URL + "catalog/pirsing");
        List<String> sqlList = pirsing.getNames();
        List<WebElement> elements = driver.findElements(numberOfItem);
        for (WebElement text : elements) {
            String s = text.getText();
            siteList.add(s.substring(0, 17));
        }
        assertEquals(sqlList.subList(0, sqlList.size()), siteList.subList(0, siteList.size()));
    }

    /**
     * Раздел для мужчин(проверка по наименованию изделия)
     */
    @Test
    @Description("Раздел для мужчин(проверка по наименованию изделия)")
    public void menItemsNames() {
        driver.get(TestConfig.SITE_URL + "catalog/men/");
        List<String> sqlList = man.getNames();
        List<WebElement> elements = driver.findElements(numberOfItem);
        for (WebElement text : elements) {
            String s = text.getText();
            siteList.add(s.substring(0, 9));
        }
        assertEquals(sqlList.subList(0, siteList.size()), siteList.subList(0, siteList.size()));
    }

    /**
     * Проверяем отображение картинок и их количество.<p>
     * Браслеты(картинки)
     */
    @Test
    @Description("Браслеты(картинки)")
    public void picturesOfBracelets() {
        driver.get(TestConfig.SITE_URL + "catalog/braslety");
        getPicturesFromSite();
    }

    /**
     * Серьги(картинки)
     */
    @Test
    @Description("Серьги(картинки)")
    public void picturesOfEarrings() {
        driver.get(TestConfig.SITE_URL + "catalog/sergi");
        getPicturesFromSite();
    }

    /**
     * Колье(картинки)
     */
    @Test
    @Description("Колье(картинки)")
    public void picturesOfNecklaces() {
        driver.get(TestConfig.SITE_URL + "catalog/kole");
        getPicturesFromSite();
    }

    /**
     * Кольца(картинки)
     */
    @Test
    @Description("Кольца(картинки)")
    public void picturesOfRings() {
        driver.get(TestConfig.SITE_URL + "catalog/koltsa");
        getPicturesFromSite();
    }


    /**
     * Пирсинг(картинки)
     */
    @Test
    @Description("Пирсинг(картинки)")
    public void picturesOfPiercing() {
        driver.get(TestConfig.SITE_URL + "catalog/pirsing");
        List<WebElement> elements = driver.findElements(numberOfPictures);
        List<Integer> sqlList = pirsing.getPrice();
        for (WebElement text : elements) {
            String s = text.getText();
            siteList.add(s);
            siteSize = siteList.size();
        }
        assertEquals(sqlList.size(), siteSize);
    }

    /**
     * Раздел для мужчин(картинки)
     */
    @Test
    @Description("Раздел для мужчин(картинки)")
    public void picturesMenItems() {
        driver.get(TestConfig.SITE_URL + "catalog/men");
        getPicturesFromSite();
    }

    /**
     * Сравниваем цены в базе и каталоге(первые 48 изделий):<p>
     * Браслеты(проверка по цене)
     */
    @Test
    @Description("Браслеты(проверка по цене)")
    public void braceletsPrice() {
        driver.get(TestConfig.SITE_URL + "catalog/braslety");
        List<Integer> sqlList = bracelets.getPrice();
        getPricesFromSite();
        assertEquals(sqlList.subList(0, 47), priceList.subList(0, 47));
    }

    /**
     * Серьги(проверка по цене)
     */
    @Test
    @Description("Серьги(проверка по цене)")
    public void earringsPrice() {
        driver.get(TestConfig.SITE_URL + "catalog/sergi");
        List<Integer> sqlList = earrings.getPrice();
        getPricesFromSite();
        assertEquals(sqlList.subList(0, 47), priceList.subList(0, 47));
    }

    /**
     * Колье(проверка по цене)
     */
    @Test
    @Description("Колье(проверка по цене)")
    public void necklacesPrice() {
        driver.get(TestConfig.SITE_URL + "catalog/kole");
        List<Integer> sqlList = necklaces.getPrice();
        getPricesFromSite();
        assertEquals(sqlList.subList(0, 47), priceList.subList(0, 47));
    }

    /**
     * Кольца(проверка по цене)
     */
    @Test
    @Description("Кольца(проверка по цене)")
    public void ringsPrice() {
        driver.get(TestConfig.SITE_URL + "catalog/koltsa");
        List<Integer> sqlList = rings.getPrice();
        getPricesFromSite();
        assertEquals(sqlList.subList(0, 47), priceList.subList(0, 47));
    }

    /**
     * Раздел для мужчин(проверка по цене)
     */
    @Test
    @Description("Раздел для мужчин(проверка по цене)")
    public void menItemsPrice() {
        driver.get(TestConfig.SITE_URL + "catalog/men");
        List<Integer> sqlList = man.getPrice();
        getPricesFromSite();
        assertEquals(sqlList.subList(0, 47), priceList.subList(0, 47));
    }
}
