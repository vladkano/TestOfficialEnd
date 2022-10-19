package functionalTests;

import baseForTests.TestBase;
import filters.Filters;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;
import sections.NewItems;

import java.util.List;

import static java.lang.Integer.parseInt;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Epic("Тесты раздела Новинок")
public class NewItemsTest extends TestBase {

    @BeforeEach
    public void setUp() {
        mainSetUp();
        driver.get(getUrl + "catalog/new/");
        newItems = new NewItems(driver);
        filters = new Filters(driver);
    }

    /**
     * Сравниваем количество наименований в базе и на первой странице раздела новинок, проверка по
     * наименованию изделия(содержание списка и порядок отображения изделий на странице)
     */
    @Test
    @Description("Сравниваем количество наименований в базе и на первой странице раздела новинок, " +
            "проверка по наименованию изделия(количество изделий в разделе, содержание списка и порядок отображения изделий на странице)")
    public void productNameCheck() {
        String countHeader = filters.getCountHeader();
        Integer numberOnly = Integer.valueOf(countHeader.replaceAll("[^0-9]", ""));
        List<String> sqlList = newItems.getNames();
        int sqlSize = sqlList.size();
        List<WebElement> elements = driver.findElements(numberOfItem);
        for (WebElement text : elements) {
            String s = text.getText();
            siteList.add(s);
        }
        Assertions.assertAll(
                () -> assertEquals(sqlSize, numberOnly),
                () -> assertEquals(sqlList.subList(0, 47), siteList.subList(0, 47)));
    }

    /**
     * Сравниваем количество наименований в базе и на первой странице раздела новинок, проверка по
     * наименованию дизайнера(содержание списка и порядок отображения изделий на странице)
     */
    @Test
    @Description("Сравниваем количество наименований в базе и на первой странице раздела новинок, " +
            "проверка по наименованию дизайнера(содержание списка и порядок отображения изделий на странице)")
    public void designersNameCheck() {
        List<String> sqlList = newItems.getDesigners();
        List<WebElement> elements = driver.findElements(designerName);
        for (WebElement text : elements) {
            String s = text.getText();
            siteList.add(s);
        }
        assertEquals(sqlList.subList(0, 47), siteList.subList(0, 47));
    }

    /**
     * Проверка на соответствие цены на первой странице раздела новинок цене в
     * базе данных(содержание списка и порядок отображения изделий на странице)
     */
    @Test
    @Description("Проверка на соответствие цены на первой странице раздела новинок цене в + " +
            "базе данных(содержание списка и порядок отображения изделий на странице)")
    public void checkingPricesForNewItems() {
        List<Integer> sqlList = newItems.getPrice();
        List<WebElement> elements = driver.findElements(price);
        for (WebElement text : elements) {
            String s = text.getText();
            String replace = s.replace(" ", "");
            String result = replace.replaceAll("[^A-Za-z0-9]", "");
            Integer price = parseInt(result);
            priceList.add(price);
        }
        assertEquals(sqlList.subList(0, 47), priceList.subList(0, 47));
    }

    /**
     * Проверяем отображение картинок и их количество на первой странице раздела новинок.
     */
    @Test
    @Description("Проверяем отображение картинок и их количество на первой странице раздела новинок")
    public void displayingPicturesInNewItems() {
        List<WebElement> elements = driver.findElements(numberOfPictures);
        for (WebElement text : elements) {
            String s = text.getText();
            siteList.add(s);
            siteSize = siteList.size();
        }
        assertEquals(numberOfFoto, siteSize);
    }

}
